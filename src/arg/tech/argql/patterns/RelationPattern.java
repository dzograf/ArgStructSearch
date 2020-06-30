package arg.tech.argql.patterns;

import arg.tech.argql.translator.sparql.SPARQLTranslator;

public abstract class  RelationPattern extends Pattern {
	private ArgPattern ap1;
	private ArgPattern ap2;	
	private String name;
	
	public RelationPattern(String name) {
		this.name = name;
	}
	
	public ArgPattern getAp1() {
		return ap1;
	}
	public void setAp1(ArgPattern ap1) {
		this.ap1 = ap1;
	}
	public ArgPattern getAp2() {
		return ap2;
	}
	public void setAp2(ArgPattern ap2) {
		this.ap2 = ap2;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static RelationPattern newRelation(RelationPattern relation) {
		RelationPattern relationCopy;
		switch (relation.getName()) {
		case "Rebut":
			relationCopy = SPARQLTranslator.rebut();
			break;

		case "Undercut":
			relationCopy = SPARQLTranslator.undercut();
			break;

		case "Attack":
			relationCopy = SPARQLTranslator.attack();
			break;

		case "Endorse":
			relationCopy = SPARQLTranslator.endorse();
			break;

		case "Back":
			relationCopy = SPARQLTranslator.backing();
			break;

		case "Support":
			relationCopy = SPARQLTranslator.support();
			break;

		default:
			relationCopy = null;
		}

	/*	if(relation.ap1 != null) {
			relationCopy.setAp1(relation.ap1);
		}
		if(relation.ap2 != null) {
			relationCopy.setAp2(relation.ap2);
		}*/
		relationCopy.name = relation.name;
		
		return relationCopy;
	}
	
	public static RelationPattern deepCopy(RelationPattern relation) {
		RelationPattern relPattern = null	;
		
		switch(relation.getName()){
		case "Undercut":
			relPattern = new UndercutPattern("Undercut", ((UndercutPattern)relation).getCaVar());
			break;
		case "Undermine":
			relPattern = new UnderminePattern("Undermine", ((UnderminePattern)relation).getCaVar());
			break;
		case "Rebut":
			relPattern = new RebutPattern("Rebut", ((RebutPattern)relation).getCaVar());
			break;
		case "Attack":
			relPattern = new AttackPattern("Attack", ((AttackPattern)relation).getCaVar());
			break;
		case "Endorse":
			relPattern = new EndorsePattern("Endorse", ((EndorsePattern)relation).getEquivPattern());
			break;
		case "Back":
			relPattern = new BackPattern("Back", ((BackPattern)relation).getEquivPattern());
			break;
		case "Support":
			relPattern = new SupportPattern("Support", ((SupportPattern)relation).getEquivPattern());
			break;
		}

		if(relation.ap1 != null) {
			relPattern.setAp1(relation.ap1);
		}
		if(relation.ap2 != null) {
			relPattern.setAp2(relation.ap2);
		}
		relPattern.name = relation.name;
		
		return relPattern;
	}
	
	
	public abstract String keyId();
	public abstract String getSparqlRepresentation();
	public abstract String getSparqlRepresentationSimple();
}
