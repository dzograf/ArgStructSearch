package arg.tech.argql.patterns;


import arg.tech.argql.translator.sparql.SPARQLTranslator;
import arg.tech.utils.Enums.TranslationMode;

public class PropositionPattern extends Pattern {
	//private boolean isVariable = false;
	private String id="";
	private String text = "";
	private String uriVar = "";
	private boolean isVariable;

	private EquivalencePattern equivPattern;

	public PropositionPattern(String uriVar, String text, EquivalencePattern equivPattern, boolean isVariable) {
		this.uriVar = uriVar;
		this.text = text;
		this.equivPattern = equivPattern;
		this.isVariable = isVariable;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUriVar() {
		return uriVar;
	}

	public void setUriVar(String uriVar) {
		this.uriVar = uriVar;
	}

	public String getURIVar() {
		return uriVar;
	}

	public void setURIVar(String uriVar) {
		this.uriVar = uriVar;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public EquivalencePattern getEquivPattern() {
		return equivPattern;
	}

	public void setEquivPattern(EquivalencePattern equivPattern) {
		this.equivPattern = equivPattern;
	}

	public boolean isVariable() {
		return isVariable;
	}

	public void setVariable(boolean isVariable) {
		this.isVariable = isVariable;
	}


	public String getSparqlRepresentation() {
		StringBuilder str = new StringBuilder();
		
		if(SPARQLTranslator.translationMode == TranslationMode.Optimized && !isVariable) {
			str.append(uriVar + " rdf:type argtech:I-node.\n" +
				uriVar + " argtech:claimText \"" + text + "\".\n");
			
		} else {
			if(!this.isVariable) {

				String textVarString = SPARQLTranslator.generateTextVariable();
				
				str.append(uriVar + " rdf:type argtech:I-node.\n" +
						uriVar + " argtech:claimText "  + textVarString + ".\n");
				str.append("FILTER (CONTAINS("+ textVarString + ", "+ text +")).\n");
				
			} else {
				str.append(uriVar + " rdf:type argtech:I-node.\n" +
						uriVar + " argtech:claimText "  + text + ".\n");
			}
		}
		return str.toString();
	}

	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("\nProposition Variable: " + uriVar);
		str.append("\nProposition value: " + text);
		if (equivPattern != null) {
			str.append("\nEquivalent I-variable: " + equivPattern.getEqPropID());
			str.append("\nEquivalent text var: " + equivPattern.getItextVar2());
		}
		return str.toString();
	}

	@Override
	public String toArgQLString() {
		if(isVariable) 
			return this.text;// + "("+this.id + ")";
		else 
			return "\"" + this.text + "\"";// + "("+this.id + ")";
	}

}
