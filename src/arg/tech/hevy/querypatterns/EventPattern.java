package arg.tech.hevy.querypatterns;

import java.util.Date;

import com.ibm.icu.impl.duration.DateFormatter;

public class EventPattern {

	public String text;
	public String actor;
	public String time;
	public String location;
	public String involved;
	public String illustrate;
	private String eventvar;

	
	public EventPattern() {}
	
	public EventPattern(String text, String actor, String time, String location, String involved, String illustrate) {
		super();
		this.text = text;
		this.actor = actor;
		this.time = time;
		this.location = location;
		this.involved = involved;
		this.illustrate = illustrate;
		eventvar = VariableGenerator.eventVariable();
	}

	public String getVar() {
		return eventvar;
	}

	public void setVar(String eventvar) {
		this.eventvar = eventvar;
	}

	public String toSPARQL() {
		StringBuilder str = new StringBuilder();

		String type = VariableGenerator.typeVar();
		
		str.append("{\n" + eventvar + " rdf:type hevy:Event.\n");
		str.append("} UNION {\n");
		str.append(eventvar + " rdf:type hevy:LocutionEvent.\n");
		str.append("}\n");

		if (text.compareTo("") != 0) {
			if (text.compareTo("locution") == 0) {

			} else {
				String textVar = VariableGenerator.textVariable();
				str.append(eventvar + " hevy:name " + textVar + ".\n");
				str.append("FILTER (CONTAINS(lcase(str(" + textVar + ")), lcase(\"" + text + "\"))).\n");
			}
		}

		if (actor.compareTo("") != 0) {
			String actorVarString = VariableGenerator.actorVar();
			str.append(eventvar + " hevy:involvedAgent " + actorVarString + ".\n");
			str.append("FILTER (CONTAINS(lcase(str(" + actorVarString + ")), lcase(\"" + actor + "\"))).\n");
		}

		if (time.compareTo("") != 0) {
			
			if (time.startsWith("=") || time.startsWith("<") || time.startsWith(">") || time.startsWith("<>")) {
				
				if (time.startsWith("=")) {
					time = time.replace("=", "").trim();
					str.append(eventvar + " hevy:atTime " + time + ".\n");
				} else {
					String dateVar = VariableGenerator.dateVar();
					str.append(eventvar + " hevy:atTime " + dateVar + ".\n");
					
					if (time.startsWith("<")) {
						time = time.replace("<", "").trim();
						str.append(" FILTER ( " + dateVar + " < \"" + time+ "\" ) \n");
					}

					if (time.startsWith(">")) {
						time = time.replace(">", "").trim();
						str.append(" FILTER ( " + dateVar + " > \"" + time+ "\" ) \n");
					}
				
					if (time.startsWith("<>")) {
						time = time.replace("<>", "").trim();
						String time1 = time.split(",")[0];
						String time2 = time.split(",")[1];

						str.append(" FILTER ( ( " + dateVar + " > \"" + time1+ "\" ) && \n");
						str.append("          ( " + dateVar + " < \"" + time2+ "\" ) )\n");
					}
				}
				
			} else {

				String circaVar = VariableGenerator.timeVar();
				str.append(eventvar + " hevy:circa " + circaVar + ".\n");
				str.append("FILTER (CONTAINS(lcase(str(" + circaVar + ")), lcase(\"" + time + "\"))).\n");
			}
		}
		
		if(location.compareTo("") != 0) {
			str.append("{\n");
			String locationVar = VariableGenerator.locationVar();
			str.append("  " + eventvar + " hevy:atPlace " + locationVar + ".\n");
			str.append("FILTER (CONTAINS(lcase(str(" + locationVar + ")), lcase(\"" + location + "\"))).\n");
			str.append("} UNION { \n");
			str.append("  " + eventvar + " hevy:inSpace " + locationVar + ".\n");
			str.append("FILTER (CONTAINS(lcase(str(" + locationVar + ")), lcase(\"" + location + "\"))).\n");
			str.append("}\n");
		}

		
		if(involved.compareTo("") != 0) {
			String involvedVar = VariableGenerator.involvedVar();
			str.append("  " + eventvar + " hevy:involved " + involvedVar + ".\n");
			str.append("FILTER (CONTAINS(lcase(str(" + involvedVar + ")), lcase(\"" + involved + "\"))).\n");
		}
		
		if(illustrate.compareTo("") != 0) {
			String illustratesVar = VariableGenerator.illustratesVar();
			str.append("  " + eventvar + " illustrate " + illustratesVar + ".\n");
			str.append("FILTER (CONTAINS(lcase(str(" + illustratesVar + ")), lcase(\"" + illustrate + "\"))).\n");
		  //str.append("FILTER (CONTAINS(lcase(str(" + actorVarString + "), lcase(\"" + actor + "\"))).\n");
		}
		return str.toString();
	}

}
