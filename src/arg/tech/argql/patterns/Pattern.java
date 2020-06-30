package arg.tech.argql.patterns;

public abstract class Pattern {

	private String SparqlRepresentation = "";
	
	public Pattern() {
		
	}

	public String getSparqlRepresentation() {
		return SparqlRepresentation;
	}
	

	public void setSparqlRepresentation(String sparqlRepresentation) {
		this.SparqlRepresentation = sparqlRepresentation;
	}
	
	public abstract String toArgQLString();
}
