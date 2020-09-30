package arg.tech.hevy.models;

public class Locution extends EventDescription {
	private LocutionEvent describesEvent;
	String typeString = "Locution";
	
	
	public Locution(String ID) {
		super(ID);
		// TODO Auto-generated constructor stub
	}

	public LocutionEvent getDescribesEvent() {
		return describesEvent;
	}


	public void setDescribesEvent(LocutionEvent describesEvent) {
		this.describesEvent = describesEvent;
	}

	public String rdfEntry(String aifClassesBaseURI, String hevyClassesBaseURI, String aifNodesBaseURI, String hevyNodesBaseURI) {
		StringBuilder stringBuilder = new StringBuilder();
		String nodeURI = aifNodesBaseURI + ID;
		String eventClassURI = aifClassesBaseURI + "L-node";
		stringBuilder.append("<NamedIndividual rdf:about=\"" +  nodeURI + "\">\n");
		stringBuilder.append("\t\t<rdf:type rdf:resource=\""+ eventClassURI + "\"/>\n");
		stringBuilder.append("\t\t<hevy:describes rdf:resource=\"" + hevyNodesBaseURI + describesEvent.ID + "\" />\n");
		stringBuilder.append("    </NamedIndividual>");
		
		return stringBuilder.toString();
	}
}
