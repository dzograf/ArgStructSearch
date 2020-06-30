package arg.tech.argql.patterns;

import arg.tech.argql.translator.sparql.SPARQLTranslator;
import arg.tech.utils.Enums.TranslationMode;

public class EndorsePattern extends RelationPattern {
	
	public EquivalencePattern equivPattern;
	public EndorsePattern(String name, EquivalencePattern equivPattern) {
		super(name);
		this.equivPattern = equivPattern;
	}
	
	public EquivalencePattern getEquivPattern() {
		return equivPattern;
	}

	public void setEquivPattern(EquivalencePattern equivPattern) {
		this.equivPattern = equivPattern;
	}

	private String getNormalSparqlRepresentation() {
		StringBuilder str = new StringBuilder();
		/*

		if(this.getAp1().getConclusionPattern().isVariable()) {
			if(this.getAp2().getConclusionPattern().isVariable()) {
				str.append("{\n");
				//the two arguments have the same conclusion
				str.append(this.getAp2().getRaVariable() + " aif:Conclusion " + this.getAp1().getConclusionPattern().getPropPattern().getURIVar()+"\n");
				str.append("} UNION {\n");
				str.append(this.getAp1().getRaVariable() + " aif:Conclusion " + this.getAp2().getConclusionPattern().getPropPattern().getURIVar()+"\n");
				str.append("} UNION {\n");
				equivPattern.setPropID(this.getAp1().getConclusionPattern().getPropPattern().getURIVar());
				equivPattern.setEqPropID(this.getAp2().getConclusionPattern().getPropPattern().getURIVar());
				str.append(equivPattern.getSparqlRepresentation());
				
			} else {
				str.append("{\n");
				//the two arguments have the same conclusion
				str.append(this.getAp2().getRaVariable() + " aif:Conclusion " + this.getAp1().getConclusionPattern().getPropPattern().getURIVar()+"\n");
				str.append("} UNION {\n");
				str.append(this.getAp1().getRaVariable() + " aif:Conclusion " + this.getAp2().getConclusionPattern().getPropPattern().getURIVar()+"\n");
			//	str.append("} UNION {\n");
				//or an equivalent proposition of the AP2's conclusion, is conclusion of AP1
				//str.append(this.getAp1().getRaVariable() + " aif:Conclusion " + this.getAp2().getConclusionPattern().getPropPattern().getEquivPattern().getEqPropID() +"\n");
				str.append("} UNION {\n");
				
				equivPattern.setPropID(this.getAp1().getConclusionPattern().getPropPattern().getURIVar());
				equivPattern.setEqPropID(this.getAp2().getConclusionPattern().getPropPattern().getURIVar());
				str.append(equivPattern.getSparqlRepresentation());
			}
		} else {
			if(this.getAp2().getConclusionPattern().isVariable()) {
				str.append("{\n");
				//the two arguments have the same conclusion
				str.append(this.getAp2().getRaVariable() + " aif:Conclusion " + this.getAp1().getConclusionPattern().getPropPattern().getURIVar()+"\n");
				str.append("} UNION {\n");
				//or an equivalent of the conclusion of the first ap is also conclusion of the second
				str.append(this.getAp2().getRaVariable() + " aif:Conclusion " + this.getAp1().getConclusionPattern().getPropPattern().getEquivPattern().getEqPropID() +"\n");
				str.append("} UNION {\n");
				str.append(this.getAp1().getRaVariable() + " aif:Conclusion " + this.getAp2().getConclusionPattern().getPropPattern().getURIVar()+"\n");
				str.append("} UNION {\n");
				equivPattern.setPropID(this.getAp1().getConclusionPattern().getPropPattern().getURIVar());
				equivPattern.setEqPropID(this.getAp2().getConclusionPattern().getPropPattern().getURIVar());
				str.append(equivPattern.getSparqlRepresentation());
			} else {
				str.append("{\n");
				//the two arguments have the same conclusion
				str.append(this.getAp2().getRaVariable() + " aif:Conclusion " + this.getAp1().getConclusionPattern().getPropPattern().getURIVar()+"\n");
				str.append("} UNION {\n");
				//or an equivalent of the conclusion of the first ap is also conclusion of the second
				str.append(this.getAp2().getRaVariable() + " aif:Conclusion " + this.getAp1().getConclusionPattern().getPropPattern().getEquivPattern().getEqPropID() +"\n");
				str.append("} UNION {\n");
				str.append(this.getAp1().getRaVariable() + " aif:Conclusion " + this.getAp2().getConclusionPattern().getPropPattern().getURIVar()+"\n");
		//		str.append("} UNION {\n"); 
			//	str.append(this.getAp1().getRaVariable() + " aif:Conclusion " + this.getAp2().getConclusionPattern().getPropPattern().getEquivPattern().getEqPropID() +"\n");
				str.append("} UNION {\n");
				equivPattern.setPropID(this.getAp1().getConclusionPattern().getPropPattern().getURIVar());
				equivPattern.setEqPropID(this.getAp2().getConclusionPattern().getPropPattern().getURIVar());
				str.append(equivPattern.getSparqlRepresentation());
			}
		}
		str.append("}\n");*/
		
		
		String u1 = this.getAp1().getConclusionPattern().getPropPattern().getURIVar();
		String u2 = this.getAp2().getConclusionPattern().getPropPattern().getURIVar();

		//str.append(getAp1().getRaVariable() + " aif:Conclusion " + u1 + ".\n");
		//str.append(getAp2().getRaVariable() + " aif:Conclusion " + u2 + ".\n");

		
		str.append("{\n");
		//the two arguments have the same conclusion
		str.append(this.getAp2().getRaVariable() + " argtech:Conclusion " + u1 +"\n");
		str.append("} UNION {\n");
		str.append(this.getAp1().getRaVariable() + " argtech:Conclusion " + u2 +"\n");
		str.append("} UNION {\n");
		equivPattern.setPropID(this.getAp1().getConclusionPattern().getPropPattern().getURIVar());
		equivPattern.setEqPropID(this.getAp2().getConclusionPattern().getPropPattern().getURIVar());
		str.append(equivPattern.getSparqlRepresentation());
		str.append("}\n");
		
		str.append("FILTER(" + this.getAp1().getRaVariable() + " != " + this.getAp2().getRaVariable() + ")\n");
		
		return str.toString();
	}
	
	
	private String getOptimizedSparqlRepresentation() {
		StringBuilder str = new StringBuilder();
		
		String concl1;
		if(this.getAp1().getConclusionPattern().isVariable()) {
			concl1 = this.getAp1().getConclusionPattern().getPropPattern().getText();
		} else {
			concl1 = "\"" + this.getAp1().getConclusionPattern().getPropPattern().getText() + "\"";
		}
		
		String concl2;
		
		if(this.getAp2().getConclusionPattern().isVariable()) {
			concl2 = this.getAp2().getConclusionPattern().getPropPattern().getText();
		} else {
			concl2 = "\"" + this.getAp2().getConclusionPattern().getPropPattern().getText() + "\"";
		}
		
	//	str.append("{\n");
	//	str.append(this.getAp1().getRaVariable() + " aif:Conclusion " + this.getAp2().getConclusionPattern().getPropPattern().getURIVar() + ".\n");
	//	str.append("} UNION {\n");
		str.append("FILTER(" + concl1 + " = " + concl2 + ")\n");
	//	str.append("}\n");
		str.append("FILTER(" + this.getAp1().getRaVariable() + " != " + this.getAp2().getRaVariable() + ")\n");
		return str.toString();
	}
	
	
	@Override
	public String getSparqlRepresentation() {
		if(SPARQLTranslator.translationMode == TranslationMode.Normal) {
			return getNormalSparqlRepresentation();
		} else {
			return getOptimizedSparqlRepresentation();
		}
	}

	@Override
	public String keyId() {
		return "E";
	}

	@Override
	public String getSparqlRepresentationSimple() {
		if(SPARQLTranslator.translationMode == TranslationMode.Normal) {
			return getNormalSparqlRepresentation();
		} else {
			return getOptimizedSparqlRepresentation();
		}
	}

	@Override
	public String toArgQLString() {
		// TODO Auto-generated method stub
		return null;
	}


}

