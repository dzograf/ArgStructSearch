package arg.tech.argql.filters;

import java.util.ArrayList;

import arg.tech.argql.patterns.ArgPattern;
import arg.tech.argql.patterns.PremisePattern;
import arg.tech.argql.patterns.PropositionPattern;

public abstract class Filter {

	protected PremisePattern premPattern;
	protected ArgPattern ap2;
	protected ArrayList<PropositionPattern> propset;
	protected String raVar;
	protected boolean isvar;
	
	public Filter(PremisePattern premPattern, ArgPattern ap2, String raVar) {
		this.ap2 = ap2;
		this.raVar = raVar;
		this.premPattern = premPattern;
		this.propset = null;
		this.setIsvar(true);
	}

	public Filter(PremisePattern premPattern, ArrayList<PropositionPattern> propset, String raVar) {
		this.setPropset(propset);
		this.raVar = raVar;
		this.premPattern = premPattern;
		this.setIsvar(false);
	}
	
	public ArgPattern getAp2() {
		return ap2;
	}

	public void setAp2(ArgPattern ap2) {
		this.ap2 = ap2;
	}

	public String getRaVar() {
		return raVar;
	}

	public void setRaVar(String raVar) {
		this.raVar = raVar;
	}

	public ArrayList<PropositionPattern> getPropset() {
		return propset;
	}

	public void setPropset(ArrayList<PropositionPattern> propset) {
		this.propset = propset;
	}
	

	public PremisePattern getPremPattern() {
		return premPattern;
	}

	public void setPremPattern(PremisePattern premPattern) {
		this.premPattern = premPattern;
	}

	public boolean isVar() {
		return isvar;
	}

	public void setIsvar(boolean isvar) {
		this.isvar = isvar;
	}

	public abstract String getSparqlRepresentation();
	
	public abstract String toArgQLString();
	
}
