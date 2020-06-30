package arg.tech.argql.patterns;

import java.util.Iterator;

import arg.tech.argql.translator.sparql.SPARQLTranslator;

public class AttackPattern extends RelationPattern {

	private String caVar = "";

	public AttackPattern(String name, String caVar) {
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
		
		/*String u1 = SPARQLTranslator.generateIVariable();
		String u2 = SPARQLTranslator.generateIVariable();
		String u3 = SPARQLTranslator.generateIVariable();
		String eq1 = SPARQLTranslator.generateIVariable();
		String eq2 = SPARQLTranslator.generateIVariable();
		String eq3 = SPARQLTranslator.generateIVariable();
		EquivalencePattern eqp1 = SPARQLTranslator.newEquivalenceCondition(u1, eq1);
		EquivalencePattern eqp2 = SPARQLTranslator.newEquivalenceCondition(u2, eq2);
		EquivalencePattern eqp3 = SPARQLTranslator.newEquivalenceCondition(u3, eq3);

		str.append(getAp1().getRaVariable() + " aif:Conclusion " + u1 + ".\n");
		str.append(u2 + " aif:Premise " + getAp2().getRaVariable() + ".\n");
		str.append(getAp2().getRaVariable() + " aif:Conclusion " + u3 +  ".\n");

		
		str.append("{\n");
		str.append(u1 + " aif:Premise " + caVar + ".\n");
		str.append(caVar + " aif:Conclusion " + u2 + ".\n");

		str.append("} UNION {\n");

		str.append(eqp1.getSparqlRepresentation());
		str.append(eqp1.getEqPropID() + " aif:Premise " + caVar + ".\n");
		str.append(caVar + " aif:Conclusion " + u2
				+ ".\n");

		str.append("} UNION {\n");

		str.append(u1 + " aif:Premise " + caVar + ".\n");
		str.append(eqp2.getSparqlRepresentation());
		str.append(caVar + " aif:Conclusion " + eqp2.getEqPropID() + ".\n");

		str.append("} UNION {\n");

		str.append(eqp1.getSparqlRepresentation());
		str.append(eqp2.getSparqlRepresentation());
		str.append(eqp1.getEqPropID() + " aif:Premise " + caVar + ".\n");
		str.append(caVar + " aif:Conclusion " + eqp2.getEqPropID() + ".\n");
		str.append("}");
		
		
		/////////////////////////////////////////////////////////////
		
		str.append("{\n");
		str.append(u1 + " aif:Premise " + caVar + ".\n");
		str.append(caVar + " aif:Conclusion " + u3 + ".\n");

		str.append("} UNION {\n");

		str.append(eqp1.getSparqlRepresentation());
		str.append(eqp1.getEqPropID() + " aif:Premise " + caVar + ".\n");
		str.append(caVar + " aif:Conclusion " + u3 + ".\n");

		str.append("} UNION {\n");

		str.append(u1 + " aif:Premise " + caVar + ".\n");
		str.append(eqp3.getSparqlRepresentation());
		str.append(caVar + " aif:Conclusion " + eqp3.getEqPropID() + ".\n");

		str.append("} UNION {\n");

		str.append(eqp1.getSparqlRepresentation());
		str.append(eqp3.getSparqlRepresentation());
		str.append(eqp1.getEqPropID() + " aif:Premise " + caVar + ".\n");
		str.append(caVar + " aif:Conclusion " + eqp3.getEqPropID() + ".\n");
		str.append("}");
		*/
		
		RebutPattern rebut = new RebutPattern("Rebut", caVar);
		rebut.setAp1(this.getAp1());
		rebut.setAp2(this.getAp2());
		UndercutPattern undercut = new UndercutPattern("Undercut", caVar);
		undercut.setAp1(this.getAp1());
		undercut.setAp2(this.getAp2());
		
		str.append("{ \n");
		str.append(rebut.getSparqlRepresentation());
		str.append("} UNION {\n");
		str.append(undercut.getSparqlRepresentation());
		str.append("}\n");
		

		return str.toString();
	}

	@Override
	public String keyId() {
		return "A";
	}

	@Override
	public String getSparqlRepresentationSimple() {
StringBuilder str = new StringBuilder();
		
		/*String u1 = SPARQLTranslator.generateIVariable();
		String u2 = SPARQLTranslator.generateIVariable();
		String u3 = SPARQLTranslator.generateIVariable();
		String eq1 = SPARQLTranslator.generateIVariable();
		String eq2 = SPARQLTranslator.generateIVariable();
		String eq3 = SPARQLTranslator.generateIVariable();
		EquivalencePattern eqp1 = SPARQLTranslator.newEquivalenceCondition(u1, eq1);
		EquivalencePattern eqp2 = SPARQLTranslator.newEquivalenceCondition(u2, eq2);
		EquivalencePattern eqp3 = SPARQLTranslator.newEquivalenceCondition(u3, eq3);

		str.append(getAp1().getRaVariable() + " aif:Conclusion " + u1 + ".\n");
		str.append(u2 + " aif:Premise " + getAp2().getRaVariable() + ".\n");
		str.append(getAp2().getRaVariable() + " aif:Conclusion " + u3 +  ".\n");

		
		str.append("{\n");
		str.append(u1 + " aif:Premise " + caVar + ".\n");
		str.append(caVar + " aif:Conclusion " + u2 + ".\n");

		str.append("} UNION {\n");

		str.append(eqp1.getSparqlRepresentation());
		str.append(eqp1.getEqPropID() + " aif:Premise " + caVar + ".\n");
		str.append(caVar + " aif:Conclusion " + u2
				+ ".\n");

		str.append("} UNION {\n");

		str.append(u1 + " aif:Premise " + caVar + ".\n");
		str.append(eqp2.getSparqlRepresentation());
		str.append(caVar + " aif:Conclusion " + eqp2.getEqPropID() + ".\n");

		str.append("} UNION {\n");

		str.append(eqp1.getSparqlRepresentation());
		str.append(eqp2.getSparqlRepresentation());
		str.append(eqp1.getEqPropID() + " aif:Premise " + caVar + ".\n");
		str.append(caVar + " aif:Conclusion " + eqp2.getEqPropID() + ".\n");
		str.append("}");
		
		
		/////////////////////////////////////////////////////////////
		
		str.append("{\n");
		str.append(u1 + " aif:Premise " + caVar + ".\n");
		str.append(caVar + " aif:Conclusion " + u3 + ".\n");

		str.append("} UNION {\n");

		str.append(eqp1.getSparqlRepresentation());
		str.append(eqp1.getEqPropID() + " aif:Premise " + caVar + ".\n");
		str.append(caVar + " aif:Conclusion " + u3 + ".\n");

		str.append("} UNION {\n");

		str.append(u1 + " aif:Premise " + caVar + ".\n");
		str.append(eqp3.getSparqlRepresentation());
		str.append(caVar + " aif:Conclusion " + eqp3.getEqPropID() + ".\n");

		str.append("} UNION {\n");

		str.append(eqp1.getSparqlRepresentation());
		str.append(eqp3.getSparqlRepresentation());
		str.append(eqp1.getEqPropID() + " aif:Premise " + caVar + ".\n");
		str.append(caVar + " aif:Conclusion " + eqp3.getEqPropID() + ".\n");
		str.append("}");
		*/
		
		RebutPattern rebut = new RebutPattern("Rebut", caVar);
		rebut.setAp1(this.getAp1());
		rebut.setAp2(this.getAp2());
		UndercutPattern undercut = new UndercutPattern("Undercut", caVar);
		undercut.setAp1(this.getAp1());
		undercut.setAp2(this.getAp2());
		
		str.append("{ \n");
		str.append(rebut.getSparqlRepresentationSimple());
		str.append("} UNION {\n");
		str.append(undercut.getSparqlRepresentationSimple());
		str.append("}\n");
		

		return str.toString();
	}

	@Override
	public String toArgQLString() {
		// TODO Auto-generated method stub
		return null;
	}

}
