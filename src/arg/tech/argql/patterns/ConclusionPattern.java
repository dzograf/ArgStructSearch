package arg.tech.argql.patterns;

import java.util.ArrayList;

import arg.tech.argql.results.Proposition;
import arg.tech.utils.Enums.PremiseType;

public class ConclusionPattern extends Pattern{

	private boolean isVariable;
	private ArgPattern argPattern;
	
	 
	private PropositionPattern propPattern;
	

	private ArrayList<Proposition> values;
	
	
	public ConclusionPattern() {super();}
	
	public ConclusionPattern(PropositionPattern propositionPattern, boolean isVariable) {
		super();
		this.propPattern = propositionPattern;
		this.isVariable = isVariable;
	}
	
	public PropositionPattern getPropPattern() {
		return propPattern;
	}

	public void setPropPattern(PropositionPattern propPattern) {
		this.propPattern = propPattern;
	} 
	
	public ArgPattern getArgPattern() {
		return argPattern;
	}

	public void setArgPattern(ArgPattern argPattern) {
		this.argPattern = argPattern;
	}

	public boolean isVariable() {
		return isVariable;
	}

	public void setVariable(boolean isVariable) {
		this.isVariable = isVariable;
	}	
	
	public ArrayList<Proposition> getValues() {
		return values;
	}

	public void setValues(ArrayList<Proposition> values) {
		this.values = values;
	}
	
	public int valueExists(String ID) {
		for (int i = 0; i < values.size(); i++) {
			Proposition prop = values.get(i);
			if (prop.argID.compareTo(ID) == 0) {
				return i;
			}
		}
		return -1;
	}
	
	public String getSparqlRepresentation_old() {
		if(isVariable) {
			return propPattern.getSparqlRepresentation() +
					argPattern.getRaVariable() + " argtech:Conclusion " + this.propPattern.getURIVar() + ".\n";
		} else {
			if(propPattern.getEquivPattern()!=null) {
			return 	propPattern.getSparqlRepresentation() + 
				"{\n" + argPattern.getRaVariable() + " argtech:Conclusion " + propPattern.getURIVar() + ".\n" + 
				"} UNION {\n" + 
				propPattern.getEquivPattern().getSparqlRepresentation() + 
				argPattern.getRaVariable() + " argtech:Conclusion " + propPattern.getEquivPattern().getEqPropID() + ".\n" + "}\n";
			} else {
			return propPattern.getSparqlRepresentation() + 
					argPattern.getRaVariable() + " argtech:Conclusion " + propPattern.getURIVar() + ".\n";
			}
		}
	}
	
	public String getSparqlRepresentation() {
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append(propPattern.getSparqlRepresentation());
		if(!(this.getArgPattern().getPremisePattern().getType() == PremiseType.EMPTY))
			stringBuilder.append(argPattern.getRaVariable() + " argtech:Conclusion " + this.propPattern.getURIVar() + ".\n");
		
		return stringBuilder.toString();
	}

	public String toString() {
		return "\nConclusion RA Var: " + argPattern.getRaVariable() +
			"\nIs Variable: " + isVariable() +
			propPattern.toString();
	}

	@Override
	public String toArgQLString() {
		StringBuilder stringBuilder = new StringBuilder();
		if(this.isVariable) {
			stringBuilder.append(this.propPattern.getText());
		} else {
			stringBuilder.append(this.getPropPattern().toArgQLString());
		}
		return stringBuilder.toString();
	}
	
	
}
