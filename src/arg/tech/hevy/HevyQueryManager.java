package arg.tech.hevy;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.stringtemplate.v4.compiler.STParser.ifstat_return;

import arg.tech.hevy.querypatterns.EventPattern;
import arg.tech.hevy.querypatterns.VariableGenerator;

public class HevyQueryManager {

	public static String generateSPARQL(ArrayList<EventPattern> eventPatterns) {
		try {
			VariableGenerator.initializeVariables();
			
			StringBuilder stringBuilder = new StringBuilder();

			stringBuilder.append("prefix  rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" + 
					"prefix  aifnodes: <http://www.aifdb.org/nodes/#>\n" + 
					"prefix  aif: <http://www.arg.tech/aif#> \n" + 
					"prefix  hevy: <http://www.arg.tech/hevy#> \n" + 
					"prefix  hevynodes: <http://www.arg.tech/hevy/nodes#> \n\n");


			String graphVar = VariableGenerator.graphVar();
			stringBuilder.append("SELECT distinct ?g \n"); 
			stringBuilder.append("WHERE { \n");

			for (EventPattern eventPattern : eventPatterns) {
				stringBuilder.append(eventPattern.toSPARQL());

				String eventDescrVar = VariableGenerator.iNode();

				stringBuilder.append(eventDescrVar + " hevy:describes " + eventPattern.getVar() + ".\n" );
				stringBuilder.append("GRAPH ?g { \n");
				stringBuilder.append("   " + eventDescrVar + " aif:claimText " + VariableGenerator.textVariable() + ".\n"); ;
				stringBuilder.append("}");
			}	


			stringBuilder.append("}");
			return stringBuilder.toString();
			
		}catch (Exception e) {
			return "";
		}
		
	}

	private static EventPattern parseEvent(JSONObject event) {
		
		String text = "";
		if(event.get("text") != null) {
			text = event.get("text").toString();
		} 
		
		String actor = "";
		if(event.get("actor") != null) {
			actor = event.get("actor").toString();
		} 
	
		String time = "";
		if(event.get("time") != null) {
			time = event.get("time").toString();
		} 
		
		String location = "";
		if(event.get("location") != null) {
			location = event.get("location").toString();
		} 
		
		String involved = "";
		if(event.get("involved") != null) {
			involved = event.get("involved").toString();
		} 
		
		String illustrate = "";
		if(event.get("illustrate") != null) {
			illustrate = event.get("illustrate").toString();
		} 

		EventPattern eventPattern = new EventPattern(text, actor, time, location, involved, illustrate);
		
		return eventPattern;
	}
	
	
	public static ArrayList<EventPattern> parseJSON(JSONObject jsonObject) {
		
		ArrayList<EventPattern> eventPatterns = new ArrayList<EventPattern>();
		
		
		JSONObject event = new JSONObject();
		if( jsonObject.get("event") != null) {
			event = (JSONObject)jsonObject.get("event") ;
		}

		
		EventPattern eventPattern1 = parseEvent(event);
		
		eventPatterns.add(eventPattern1);
		
		JSONObject about;
		if(event.get("about") != null) {
			about = (JSONObject) event.get("about");
			
			EventPattern eventPattern2 = parseEvent(about);
			
			eventPatterns.add(eventPattern2);
		} 
		return eventPatterns;
	}
}
