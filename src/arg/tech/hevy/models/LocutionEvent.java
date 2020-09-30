package arg.tech.hevy.models;

public class LocutionEvent extends Event {

	String typeString = "LocutionEvent";

	public LocutionEvent(String ID, String text, String atTime, String atPlace, String inSpace,
			String circa, String illustrate, String involved, String involvedAgent) {
		super(ID, text, atTime, atPlace, inSpace, circa, illustrate, involved, involvedAgent);
		// TODO Auto-generated constructor stub
	}
	
	public String rdfEntry(String aifClassesBaseURI, String hevyClassesBaseURI, String aifNodesBaseURI, String hevyNodesBaseURI) {
		StringBuilder stringBuilder = new StringBuilder();
		String nodeURI = hevyNodesBaseURI + ID;
		String eventClassURI = hevyClassesBaseURI + typeString;
		stringBuilder.append("<NamedIndividual rdf:about=\"" +  nodeURI + "\">\n");
		stringBuilder.append("\t\t<rdf:type rdf:resource=\""+ eventClassURI + "\"/>\n");
		if(text.compareTo("") != 0)
			stringBuilder.append("\t\t<hevy:name>"+ text + "</hevy:name>\n");
		if(atTime.compareTo("") != 0)
			stringBuilder.append("\t\t<hevy:atTime>"+ atTime + "</hevy:atTime>\n");
		if(atPlace.compareTo("") != 0)
			stringBuilder.append("\t\t<hevy:atPlace>"+ atPlace + "</hevy:atPlace>\n");
		if(inSpace.compareTo("") != 0)
			stringBuilder.append("\t\t<hevy:inSpace>"+ inSpace + "</hevy:inSpace>\n");
		if(circa.compareTo("") != 0)
			stringBuilder.append("\t\t<hevy:circa>"+ circa + "</hevy:circa>\n");
		if(illustrate.compareTo("") != 0)
			stringBuilder.append("\t\t<hevy:illustrate>"+ illustrate + "</hevy:illustrate>\n");
		if(involved.compareTo("") != 0)
			stringBuilder.append("\t\t<hevy:involved>"+ involved + "</hevy:involved>\n");
		if(involvedAgent.compareTo("") != 0)
			stringBuilder.append("\t\t<hevy:involvedAgent>"+ involvedAgent + "</hevy:involvedAgent>\n");
		
		stringBuilder.append("    </NamedIndividual>");
		
		return stringBuilder.toString();
	}
}
