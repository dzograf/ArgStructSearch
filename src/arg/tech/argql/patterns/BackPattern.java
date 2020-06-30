package arg.tech.argql.patterns;

import java.util.ArrayList;
import java.util.Iterator;

import arg.tech.argql.translator.sparql.SPARQLTranslator;
import arg.tech.utils.Enums.PremiseType;
import arg.tech.utils.Enums.TranslationMode;

public class BackPattern extends RelationPattern {
	
	public EquivalencePattern equivPattern;
	public BackPattern(String name,  EquivalencePattern equivPattern) {
		super(name);
		this.equivPattern = equivPattern;
	}

	public EquivalencePattern getEquivPattern() {
		return equivPattern;
	}

	public void setEquivPattern(EquivalencePattern equivPattern) {
		this.equivPattern = equivPattern;
	}

	public String getNormalSparqlRepresentation() {
		StringBuilder str = new StringBuilder();
		String concl1 = getAp1().getConclusionPattern().getPropPattern().getURIVar();
		String eq = SPARQLTranslator.generateIVariable();
		
		
		str.append("{\n");
		str.append(concl1 +	" argtech:Premise " + this.getAp2().getRaVariable() + "\n");
		str.append("} UNION {\n");
		equivPattern.setPropID(concl1);
		equivPattern.setEqPropID(eq);
		str.append(equivPattern.getSparqlRepresentation());
		str.append(eq +	" argtech:Premise " + this.getAp2().getRaVariable() + "\n");
		/*equivPattern.setPropID(this.getAp1().getConclusionPattern().getPropPattern().getURIVar());
		if(this.getAp2().getPremisePattern().isVariable()) {
			str.append("{\n");
			str.append(this.getAp1().getRaVariable() + " aif:Conclusion " + this.getAp2().getPremisePattern().getPropVar().getURIVar()+"\n");
			str.append("} UNION {\n");
			equivPattern.setEqPropID(this.getAp2().getPremisePattern().getPropVar().getURIVar());
			str.append(equivPattern.getSparqlRepresentation());
			str.append("}\n");
			
		} else {
			
			Iterator<PropositionPattern> iter = this.getAp2().getPremisePattern().getProps().iterator();

			while (iter.hasNext()) {
				PropositionPattern prop = (PropositionPattern) iter.next();
				str.append("{\n");
				str.append("{\n");
				str.append(this.getAp1().getRaVariable() + " aif:Conclusion " + prop.getURIVar()+"\n");
				str.append("} UNION {\n");
				
				equivPattern.setEqPropID(prop.getURIVar());
				str.append(equivPattern.getSparqlRepresentation());
				str.append("}\n");
				str.append("}\n");
				if (iter.hasNext())
					str.append("UNION ");
			}
		}*/
		str.append("}\n");
	
		return str.toString();
	}
	
	
	public String getOptimizedSparqlRepresentation() {
		StringBuilder str = new StringBuilder();
		String concl1;
		if(this.getAp1().getConclusionPattern().isVariable()) {
			concl1 = this.getAp1().getConclusionPattern().getPropPattern().getText();
		} else {
			concl1 = "\"" + this.getAp1().getConclusionPattern().getPropPattern().getText() + "\"";
		}
		
		if(this.getAp2().getPremisePattern().getType() == PremiseType.VARIABLE) {
		//	str.append("{\n");
		//	str.append(this.getAp1().getRaVariable() + " aif:Conclusion " + this.getAp2().getPremisePattern().getPropVar().getURIVar()+"\n");
		//	str.append("} UNION {\n");	
			//String iNode1 = SPARQLTranslator.generateIVariable();
			//str.append(iNode1 + " aif:claimText " + concl1 + ".\n");
			//str.append("");
		//	str.append("}\n");
			
			str.append("FILTER(" + concl1 + " = " +this.getAp2().getPremisePattern().getPropPattern().getText() + ")\n");
		} else if(this.getAp2().getPremisePattern().getType() == PremiseType.PROPSET) {
			String iNode1 = SPARQLTranslator.generateIVariable();
			String txtVal = SPARQLTranslator.generateTextVariable();
			
			str.append(iNode1 + " argtech:Premise " + this.getAp2().getRaVariable()+ ".\n");
			str.append(iNode1 + " argtech:claimText " + txtVal + ".\n");
			str.append("FILTER(" + concl1 + " = " + txtVal + ")\n");
			/*ArrayList<PropositionPattern> props = this.getAp2().getPremisePattern().getProps();
			for(int i=0; i<props.size(); i++) {
				PropositionPattern prop = props.get(i);
				
				if(i+1<props.size())
					str.append("{\n");
				
				str.append("FILTER(" + concl1 + " = " +prop.getText() + ")\n");
				
				if(i+1<props.size())
					str.append("} UNION");
			}*/
		}
		
		return str.toString();
	}  
	
	public String getSparqlRepresentation_old() {
		if(SPARQLTranslator.translationMode == TranslationMode.Normal) {
			return getNormalSparqlRepresentation();
		} else {
			return getOptimizedSparqlRepresentation();
		}
	}
	
	@Override
	public String getSparqlRepresentation() {
		StringBuilder str = new StringBuilder();
		String concl1 = getAp1().getConclusionPattern().getPropPattern().getURIVar();
		
		str.append(concl1 +	" argtech:Premise " + this.getAp2().getRaVariable() + ".\n");
		
	
		return str.toString();
	}

	@Override
	public String keyId() {
		return "B";
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


