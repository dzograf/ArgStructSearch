package arg.tech.argql.patterns;

import java.util.Iterator;

import arg.tech.argql.translator.sparql.SPARQLTranslator;
import arg.tech.utils.Enums.PremiseType;
import arg.tech.utils.Enums.TranslationMode;

public class UndercutPattern extends RelationPattern {

	private String caVar = "";

	public UndercutPattern(String name, String caVar) {
		super(name);
		this.caVar = caVar;
	}

	public String getCaVar() {
		return caVar;
	}

	public void setCaVar(String caVar) {
		this.caVar = caVar;
	}

	
	@Override
	public String getSparqlRepresentation() {

		StringBuilder str = new StringBuilder();


		str.append(caVar + " rdf:type argtech:CA-node.\n");
		if (SPARQLTranslator.translationMode == TranslationMode.Normal) {
			String u1 = getAp1().getConclusionPattern().getPropPattern().getURIVar();
			//String u2 = SPARQLTranslator.generateIVariable();

			str.append(u1 + " argtech:Premise " + caVar + ".\n");
			
			if(getAp2().getPremisePattern().getType() == PremiseType.VARIABLE) {
				String ra2 = getAp2().getRaVariable();

				str.append(caVar + " argtech:Conclusion " + ra2 + ".\n");
			}

			
		}
		return str.toString();
	}
	
	
	public String getSparqlRepresentation_old() {

		StringBuilder str = new StringBuilder();

		str.append(caVar + " rdf:type argtech:CA-node.\n");

		str.append(caVar + " rdf:type argtech:CA-node.\n");
		if (SPARQLTranslator.translationMode == TranslationMode.Normal) {
			String u1 = getAp1().getConclusionPattern().getPropPattern().getURIVar();
			String u2 = SPARQLTranslator.generateIVariable();
			String eq1 = SPARQLTranslator.generateIVariable();
			String eq2 = SPARQLTranslator.generateIVariable();
			EquivalencePattern eqp1 = SPARQLTranslator.newEquivalenceCondition(u1, eq1);
			EquivalencePattern eqp2 = SPARQLTranslator.newEquivalenceCondition(u2, eq2);

			//str.append(getAp1().getRaVariable() + " aif:Conclusion " + u1 + ".\n");
			str.append(u2 + " argtech:Premise " + getAp2().getRaVariable() + ".\n");

			str.append("{\n");
			str.append(u1 + " argtech:Premise " + caVar + ".\n");
			str.append(caVar + " argtech:Conclusion " + u2 + ".\n");

			str.append("} UNION {\n");

			str.append(eqp1.getSparqlRepresentation());
			str.append(eqp1.getEqPropID() + " argtech:Premise " + caVar + ".\n");
			str.append(caVar + " argtech:Conclusion " + u2 + ".\n");

			str.append("} UNION {\n");

			str.append(u1 + " argtech:Premise " + caVar + ".\n");
			str.append(eqp2.getSparqlRepresentation());
			str.append(caVar + " argtech:Conclusion " + eqp2.getEqPropID() + ".\n");

			str.append("} UNION {\n");

			str.append(eqp1.getSparqlRepresentation());
			str.append(eqp2.getSparqlRepresentation());
			str.append(eqp1.getEqPropID() + " argtech:Premise " + caVar + ".\n");
			str.append(caVar + " argtech:Conclusion " + eqp2.getEqPropID() + ".\n");
			str.append("}");
			/*
			 * str.append(this.getAp1().getConclusionPattern().getPropPattern().getURIVar()
			 * + " aif:Premise " + caVar + ".\n");
			 * 
			 * if (this.getAp2().getPremisePattern().isVariable()) { str.append(caVar +
			 * " aif:Conclusion " +
			 * this.getAp2().getPremisePattern().getPropVar().getURIVar() + ". \n"); } else
			 * {
			 * 
			 * Iterator<PropositionPattern> iter =
			 * this.getAp2().getPremisePattern().getProps().iterator();
			 * 
			 * while (iter.hasNext()) { PropositionPattern prop = (PropositionPattern)
			 * iter.next(); str.append("{" + caVar + " aif:Conclusion " + prop.getURIVar() +
			 * ".\n } \n"); str.append( "UNION \n{" + caVar + " aif:Conclusion " +
			 * prop.getEquivPattern().getEqPropID() + ". }\n"); if (iter.hasNext())
			 * str.append("UNION\n"); } }
			 */
		} else if (SPARQLTranslator.translationMode == TranslationMode.Optimized) {
			String concl1, prem2;
			if (this.getAp1().getConclusionPattern().isVariable()) {
				concl1 = this.getAp1().getConclusionPattern().getPropPattern().getText();
			} else {
				concl1 = "\"" + this.getAp1().getConclusionPattern().getPropPattern().getText() + "\"";
			}
			String iNode1 = SPARQLTranslator.generateIVariable();
			str.append(iNode1 + " argtech:claimText " + concl1 + ".\n");

			String iNode2 = SPARQLTranslator.generateIVariable();

			if (this.getAp2().getPremisePattern().getType() == PremiseType.VARIABLE) {
				prem2 = this.getAp2().getPremisePattern().getPropPattern().getText();

				str.append(iNode2 + " argtech:claimText " + prem2 + ".\n");
				str.append(iNode1 + " argtech:Premise " + caVar + ".\n");
				str.append(caVar + " argtech:Conclusion " + iNode2 + ".\n");
			} else if(this.getAp2().getPremisePattern().getType() == PremiseType.VARIABLE) {

				Iterator<PropositionPattern> iter = this.getAp2().getPremisePattern().getProps().iterator();

				while (iter.hasNext()) {
					PropositionPattern prop = (PropositionPattern) iter.next();
					prem2 = "\"" + prop.getText() + "\"";
					if (iter.hasNext())
						str.append("{\n");

					str.append(iNode2 + " argtech:claimText " + prem2 + ".\n");
					str.append(iNode1 + " argtech:Premise " + caVar + ".\n");
					str.append(caVar + " argtech:Conclusion " + iNode2 + ".\n");
					if (iter.hasNext())
						str.append(" } UNION \n");
				}
			}
		}
		return str.toString();
	}

	@Override
	public String keyId() {
		return "U";
	}

	@Override
	public String getSparqlRepresentationSimple() {

		StringBuilder str = new StringBuilder();


		str.append(caVar + " rdf:type argtech:CA-node.\n");
		if (SPARQLTranslator.translationMode == TranslationMode.Normal) {
			String u1 = getAp1().getConclusionPattern().getPropPattern().getURIVar();
			String u2 = SPARQLTranslator.generateIVariable();
			String eq1 = SPARQLTranslator.generateIVariable();
		//	String eq2 = SPARQLTranslator.generateIVariable();
			EquivalencePattern eqp1 = SPARQLTranslator.newEquivalenceCondition(u1, eq1);
		//	EquivalencePattern eqp2 = SPARQLTranslator.newEquivalenceCondition(u2, eq2);*/

	//		str.append(getAp1().getRaVariable() + " aif:Conclusion " + getAp1().getConclusionPattern().getPropPattern().getURIVar() + ".\n");
			str.append(u2 + " argtech:Premise " + getAp2().getRaVariable() + ".\n");

			str.append("{\n");
			str.append(u1 + " argtech:Premise " + caVar + ".\n");
			str.append(caVar + " argtech:Conclusion " + u2 + ".\n");

			str.append("} UNION {\n");

			str.append(eqp1.getSparqlRepresentation());
			str.append(eqp1.getEqPropID() + " argtech:Premise " + caVar + ".\n");
			str.append(caVar + " argtech:Conclusion " + u2 + ".\n");
/*
			str.append("} UNION {\n");

			str.append(u1 + " aif:Premise " + caVar + ".\n");
			str.append(eqp2.getSparqlRepresentation());
			str.append(caVar + " aif:Conclusion " + eqp2.getEqPropID() + ".\n");

			str.append("} UNION {\n");

			str.append(eqp1.getSparqlRepresentation());
			str.append(eqp2.getSparqlRepresentation());
			str.append(eqp1.getEqPropID() + " aif:Premise " + caVar + ".\n");
			str.append(caVar + " aif:Conclusion " + eqp2.getEqPropID() + ".\n");*/
			str.append("}");

		} else if (SPARQLTranslator.translationMode == TranslationMode.Optimized) {
			String concl1, prem2;
			if (this.getAp1().getConclusionPattern().isVariable()) {
				concl1 = this.getAp1().getConclusionPattern().getPropPattern().getText();
			} else {
				concl1 = "\"" + this.getAp1().getConclusionPattern().getPropPattern().getText() + "\"";
			}
			String iNode1 = SPARQLTranslator.generateIVariable();
			str.append(iNode1 + " argtech:claimText " + concl1 + ".\n");

			String iNode2 = SPARQLTranslator.generateIVariable();

			if (this.getAp2().getPremisePattern().getType() == PremiseType.VARIABLE) {
				prem2 = this.getAp2().getPremisePattern().getPropPattern().getText();

				str.append(iNode2 + " argtech:claimText " + prem2 + ".\n");
				str.append(iNode1 + " argtech:Premise " + caVar + ".\n");
				str.append(caVar + " argtech:Conclusion " + iNode2 + ".\n");
			} else if (this.getAp2().getPremisePattern().getType() == PremiseType.PROPSET) {

				Iterator<PropositionPattern> iter = this.getAp2().getPremisePattern().getProps().iterator();

				while (iter.hasNext()) {
					PropositionPattern prop = (PropositionPattern) iter.next();
					prem2 = "\"" + prop.getText() + "\"";
					if (iter.hasNext())
						str.append("{\n");

					str.append(iNode2 + " argtech:claimText " + prem2 + ".\n");
					str.append(iNode1 + " argtech:Premise " + caVar + ".\n");
					str.append(caVar + " argtech:Conclusion " + iNode2 + ".\n");
					if (iter.hasNext())
						str.append(" } UNION \n");
				}
			}
		}
		return str.toString();
	}

	@Override
	public String toArgQLString() {
		// TODO Auto-generated method stub
		return null;
	}

}
