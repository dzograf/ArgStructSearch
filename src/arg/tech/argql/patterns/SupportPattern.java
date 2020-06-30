package arg.tech.argql.patterns;

import java.util.Iterator;

public class SupportPattern extends RelationPattern {

	EquivalencePattern equivPattern;


	public SupportPattern(String name, EquivalencePattern equivPattern) {
		super(name);
		this.equivPattern = equivPattern;
	}
	
	public EquivalencePattern getEquivPattern() {
		return equivPattern;
	}

	public void setEquivPattern(EquivalencePattern equivPattern) {
		this.equivPattern = equivPattern;
	}

	@Override
	public String getSparqlRepresentation() {
		
		StringBuilder str = new StringBuilder();
	
		BackPattern back = new BackPattern("Back", equivPattern);
		back.setAp1(this.getAp1());
		back.setAp2(this.getAp2());
		EndorsePattern endorse = new EndorsePattern("Endorse", equivPattern);
		endorse.setAp1(this.getAp1());
		endorse.setAp2(this.getAp2());
		
		str.append("{\n");
		str.append(back.getSparqlRepresentation());
		str.append("} UNION {\n");
		str.append(endorse.getSparqlRepresentation());
		str.append("}\n");
		
		
		
		
		
		/*
		str.append(ya1 + " aif:IllocutionaryContent " + this.getAp1().getConclusionPattern().getPropPattern().getPropURIVar()
				+ ".\n" + ya1 + " aif:Locution " + loc1 + ".\n" + loc1 + " rdf:type aif:L-node.\n");

		
		
		if (this.getAp2().getPremisePattern().isVariable()) {
			str.append(ya2 + " aif:IllocutionaryContent " + this.getAp2().getPremisePattern().getURIVar() + ".\n" );
		} else {
			Iterator<PropositionPattern> iter = this.getAp2().getPremisePattern().getProps().iterator();

			while (iter.hasNext()) {
				PropositionPattern prop = (PropositionPattern) iter.next();
				str.append("{\n");
					str.append("{" + ya2 + " aif:IllocutionaryContent " +  prop.getPropURIVar() + ".\n } \n");
					str.append("UNION {" + ya2 + " aif:IllocutionaryContent " + prop.getEqualINodeTxtVar()  + ".} \n");
				str.append("}\n");
				if (iter.hasNext())
					str.append("UNION\n");
			}
		}
			
		
		str.append(ya2	+ " aif:Locution " + loc2 + ".\n" + 
				loc2 + " rdf:type aif:L-node.\n" + 
				ta + " rdf:type aif:TA-node.\n" +
				loc1 + " aif:StartLocution " + ta + ".\n" + 
				ta + " aif:EndLocution " + loc2 + ".\n" + 
				ya4Ma + " aif:Anchor " + ta + ".\n" + 
				ya4Ma + " aif:IllocutionaryContent " + ma + ".\n" + 
				ma + " rdf:type aif:MA-node .\n" + 
			"} UNION {\n" + 
				ya1 + " aif:IllocutionaryContent " + this.getAp1().getConclusionPattern().getPropPattern().getPropURIVar() + ".\n" + ya1 + " aif:Locution " + loc1 + ".\n" + 
				loc1 + " rdf:type aif:L-node.\n" + ya2 + " aif:IllocutionaryContent "
				+ this.getAp2().getConclusionPattern().getPropPattern().getPropURIVar() + ".\n" + 
				ya2 + " aif:Locution " + loc2 + ".\n" + 
				loc2 + " rdf:type aif:L-node.\n" + 
				ta + " rdf:type aif:TA-node.\n" + 
				loc1 + " aif:StartLocution " + ta + ".\n" + 
				ta + " aif:EndLocution " + loc2 + ".\n" + 
				ya4Ma + " aif:Anchor " + ta + ".\n" + 
				ya4Ma + " aif:IllocutionaryContent " + ma + ".\n" + 
				ma + " rdf:type aif:MA-node .\n" + 
			"}\n");
*/
		return str.toString();
	}

	@Override
	public String keyId() {
		return "S";
	}

	@Override
	public String getSparqlRepresentationSimple() {
		
		StringBuilder str = new StringBuilder();
	
		BackPattern back = new BackPattern("Back", equivPattern);
		back.setAp1(this.getAp1());
		back.setAp2(this.getAp2());
		EndorsePattern endorse = new EndorsePattern("Endorse", equivPattern);
		endorse.setAp1(this.getAp1());
		endorse.setAp2(this.getAp2());
		
		str.append("{ \n");
		str.append(back.getSparqlRepresentation());
		str.append("} UNION {\n");
		str.append(endorse.getSparqlRepresentation());
		str.append("}\n");
		
		return str.toString();
	}

	@Override
	public String toArgQLString() {
		// TODO Auto-generated method stub
		return null;
	}

}
