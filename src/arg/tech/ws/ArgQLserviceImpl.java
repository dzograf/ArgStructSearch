package arg.tech.ws;


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;

import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import arg.tech.argql.parser.ArgQLGrammarLexer;
import arg.tech.argql.parser.ArgQLGrammarParser;
import arg.tech.argql.parser.ThrowingErrorListener;
import arg.tech.argql.results.ResultManager;
import arg.tech.argql.translator.json.JSONTranslator;
import arg.tech.argql.translator.sparql.SPARQLTranslator;
import arg.tech.data.DatabaseManager;

@Path("/query/")
@WebService(endpointInterface = "arg.tech.ws.ArgQLservice")
public class ArgQLserviceImpl implements ArgQLservice{
	

	JSONObject jsonObject;
	
	private String translateArgQL(String query, boolean optimized) {
		InputStream in = new ByteArrayInputStream(query.getBytes(StandardCharsets.UTF_8));
		String sparql = "";
		//jsonObject = new JSONObject();
		try {
			ANTLRInputStream input = new ANTLRInputStream(in);
			ArgQLGrammarLexer lexer = new ArgQLGrammarLexer(input);
			lexer.removeErrorListeners();
			lexer.addErrorListener(ThrowingErrorListener.INSTANCE);

			CommonTokenStream tokens = new CommonTokenStream(lexer);
			ArgQLGrammarParser parser = new ArgQLGrammarParser(tokens);
			parser.removeErrorListeners();
			parser.addErrorListener(ThrowingErrorListener.INSTANCE);

			sparql = parser.query(optimized).sparqlQuery;
			
			
			
		} catch (ParseCancellationException ex) {
			System.out.println("Syntax Error: " + ex.getMessage());
			jsonObject.put("results", "Syntax Error\n\n" + ex.getMessage());// jsonArray.toString());
			SPARQLTranslator.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sparql;
	}

	
	
	@POST
	@Path("/exec")
	@Produces("application/json")
	@Override
	public Response executeArgQL(String jsonString) {
		
		String resultsString = "";
		String sparql = "";
		jsonObject = new JSONObject();
		
		try {
			Object obj = new JSONParser().parse(jsonString);

			org.json.simple.JSONObject jsonObj = (org.json.simple.JSONObject) obj;
			JSONTranslator.initialize();
			boolean isArgQL = JSONTranslator.parseJSON(jsonObj);
			
			if(isArgQL) {
			
				String argqString = JSONTranslator.createArgQLQuery(jsonString);
				System.out.println(argqString);
			
				SPARQLTranslator.initialize(false);
				sparql = translateArgQL(argqString, false);				
				SPARQLTranslator.terminate();
			} else {
				sparql = JSONTranslator.createSPARQLquery();
				System.out.println(sparql);
			}
			
		/*	if (SPARQLTranslator.logicErrorsExist()) {
				for (String error : SPARQLTranslator.getLogicErrors()) {
					resultsString += error;
				}
				jsonObject.put("results", "Logic errors\n\n" + resultsString);// jsonArray.toString());
				
			} else*/
			 if(sparql.compareTo("")!=0) {

				ResultSet results = DatabaseManager.executeSparqlQuery(sparql);
				
			//	long collectStartTime = System.currentTimeMillis();
				//if(!results.isLast()) {
					resultsString = ResultManager.collectResults(results);
					jsonObject.put("results", resultsString);
			//	}
				
				jsonObject.put("results", resultsString);// jsonArray.toString());

			}


		} 

		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			jsonObject.put("results", "");
			//SPARQLTranslator.terminate();
		}

		return Response.status(200).entity(jsonObject.toString()).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "*").header("Access-Control-Allow-Headers", "*").build();
	}
	
	
}
