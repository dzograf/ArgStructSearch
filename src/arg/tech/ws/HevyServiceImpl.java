package arg.tech.ws;

import java.io.File;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.jws.WebService;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.stringtemplate.v4.compiler.STParser.ifstat_return;

import arg.tech.data.DatabaseManager;
import arg.tech.hevy.HevyJSONtoRDF;
import arg.tech.hevy.HevyQueryManager;
import arg.tech.hevy.models.HevyNode;
import arg.tech.hevy.querypatterns.EventPattern;

@Path("/hevy/")
@WebService(endpointInterface = "arg.tech.ws.HevyService")
public class HevyServiceImpl implements HevyService{

	private JSONObject responseJsonObject;
	
	@POST
	@Path("/exec")
	@Produces("application/json")
	@Override
	public Response queryHevy(String jsonString) {
		String resultsString = "";

		String sparql = "";
		
		
		responseJsonObject = new JSONObject();
		
		try {
			Object obj = new JSONParser().parse(jsonString);

			JSONObject jsonObj = (JSONObject) obj;
			 

			ArrayList<EventPattern> eventPatterns = HevyQueryManager.parseJSON(jsonObj);
		
			sparql = HevyQueryManager.generateSPARQL(eventPatterns);
			
			System.out.println(sparql);
			
			 if(sparql.compareTo("")!=0) {
					ResultSet results = DatabaseManager.executeSparqlQuery(sparql);
					
					resultsString = arg.tech.argql.results.ResultManager.collectResults(results);
			 }
			
			responseJsonObject.put("result", resultsString);
		} catch (Exception e) {
			 	resultsString += e.getMessage();
				e.printStackTrace();
				responseJsonObject.put("results", resultsString);
				
		}
		

		return Response.status(200).entity(responseJsonObject.toString()).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "*").header("Access-Control-Allow-Headers", "*").build();

	}

	@POST
	@Path("/upload")
	@Produces("application/json")
	@Override
	public Response uploadToDB(String jsonString) {
		
		String filename = "./file.rdf";
		try {
			Object obj = new JSONParser().parse(jsonString);
			
			HevyJSONtoRDF.parseJSON((JSONObject) obj);
			JSONObject jsonObject = (JSONObject) obj;
			ArrayList<HevyNode> nodes = HevyJSONtoRDF.parseJSON(jsonObject);

			
			HevyJSONtoRDF.saveToRDF(nodes, filename);
			
			DatabaseManager.importFile(filename, "http://hevy");
			
			File f= new File(filename);         
			if(f.delete())          {  
				System.out.println(f.getName() + " deleted");   //getting and printing the file name  
			}  
			else  {  
				System.out.println("Deletion failed");  
			}  
			
		} catch (Exception e) {
			File f= new File(filename);         
			if(f.delete())          {  
				System.out.println(f.getName() + " deleted");   //getting and printing the file name  
			}  
			else  {  
				System.out.println("Deletion failed");  
			}  
			
		}
		return null;
	}
}
