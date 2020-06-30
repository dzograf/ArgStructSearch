package arg.tech.argql.patterns;

public class SequentialPattern extends RelationPattern {
	
	PathPattern pp1;
	PathPattern pp2;
	
	public SequentialPattern() {
		super("");
	}

	public PathPattern getPp1() {
		return pp1;
	}

	public void setPp1(PathPattern pp1) {
		this.pp1 = pp1;
	}
	
	public PathPattern getPp2() {
		return pp2;
	}
	
	public void setPp2(PathPattern pp2) {
		this.pp2 = pp2;
	}

	@Override
	public String getSparqlRepresentation() {
		
		//String sparql = pp1.getSparqlRepresentation() + pp2.getSparqlRepresentation();
		return "";
	}

	@Override
	public String keyId() {
		// TODO Auto-generated method stub
		return "Seq";
	}

	@Override
	public String getSparqlRepresentationSimple() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toArgQLString() {
		// TODO Auto-generated method stub
		return null;
	}

}