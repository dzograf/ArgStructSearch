package arg.tech.hevy.models;

public class EventDescription extends HevyNode{
	
	public String type = "EventDescription";
	private Event describesEvent;
	public String text;
	
	
	public EventDescription(String iD) {
		super(iD);
	}

	

	public Event getDescribesEvent() {
		return describesEvent;
	}



	public void setDescribesEvent(Event describesEvent) {
		this.describesEvent = describesEvent;
	}



	public String rdfEntry(String aifClassesBaseURI, String hevyClassesBaseURI, String aifNodesBaseURI, String hevyNodesBaseURI) {
		StringBuilder stringBuilder = new StringBuilder();
		String nodeURI = aifNodesBaseURI + ID;
		String eventClassURI = hevyClassesBaseURI + type;
		stringBuilder.append("<NamedIndividual rdf:about=\"" +  nodeURI + "\">\n");
		stringBuilder.append("\t\t<rdf:type rdf:resource=\""+ eventClassURI + "\"/>\n");
		//stringBuilder.append("\t\t<hevy:text>" + text + "</hevy:text>\n");
		stringBuilder.append("\t\t<hevy:describes rdf:resource=\""+ hevyNodesBaseURI + describesEvent.ID + "\"/>\n");
		//stringBuilder.append("\t\t<owl:sameAs rdf:resource=\"" + aifNodesBaseURI + ID + "\" />\n");
		stringBuilder.append("    </NamedIndividual>");
		
		return stringBuilder.toString();
	}
}
