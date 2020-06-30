package arg.tech.argql.translator.json;

import arg.tech.argql.patterns.ArgPattern;

public class Relation {
	private ArgPattern fromAP;
	private ArgPattern toAP;
	private String type;
	
	public Relation(ArgPattern fromAP, ArgPattern toAP, String type) {
		this.fromAP = fromAP;
		this.toAP = toAP;
		this.type = type;
	}

	public ArgPattern getFromAP() {
		return fromAP;
	}

	public void setFromAP(ArgPattern fromAP) {
		this.fromAP = fromAP;
	}

	public ArgPattern getToAP() {
		return toAP;
	}

	public void setToAP(ArgPattern toAP) {
		this.toAP = toAP;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String toString() {
		return this.fromAP.getId() + " " +this.type + " " + this.toAP.getId() + ". "; 
	}
}
