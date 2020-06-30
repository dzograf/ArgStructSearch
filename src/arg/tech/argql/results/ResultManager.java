package arg.tech.argql.results;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import org.stringtemplate.v4.compiler.STParser.ifstat_return;

import arg.tech.argql.patterns.ArgPattern;
import arg.tech.argql.patterns.ConclusionPattern;
import arg.tech.argql.patterns.DialoguePattern;
import arg.tech.argql.patterns.PathPattern;
import arg.tech.argql.patterns.PathPatternCollection;
import arg.tech.argql.patterns.PremisePattern;
import arg.tech.argql.patterns.PropositionPattern;
import arg.tech.argql.patterns.RelationPattern;
import arg.tech.argql.translator.sparql.SPARQLTranslator;
import arg.tech.utils.*;
import arg.tech.utils.Enums.Operation;
import arg.tech.utils.Enums.PremiseType;
import arg.tech.utils.Enums.ReturnValueType;
import arg.tech.utils.Enums.TranslationMode;
import arg.tech.utils.Enums.VariableType;

public class ResultManager {


	public ResultManager() {
		
	}


	public static String collectResults(ResultSet results) throws Exception {

		StringBuilder stringBuilder = new StringBuilder();
		
		try {

			while (results.next()) {
				try {	
					String graphID = results.getString("g").replace("http://", "");
					
					stringBuilder.append(graphID + ",");
				} catch (Exception e) {
					System.out.println(">>Error: " + e.getMessage());
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			return (e.getMessage());
		}
		String resultString = stringBuilder.toString();
		if(resultString.length()>1) {
			resultString = resultString.substring(0, resultString.length()-1);
		}
		return resultString;
	}



	
}
