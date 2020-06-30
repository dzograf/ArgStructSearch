package arg.tech.argql.patterns;

import java.util.ArrayList;

import arg.tech.argql.filters.Filter;
import arg.tech.argql.results.PropositionSet;
import arg.tech.argql.translator.sparql.SPARQLTranslator;
import arg.tech.utils.Enums.PremiseType;
import arg.tech.utils.Enums.TranslationMode;

public class PremisePattern extends Pattern {

	private PremiseType type;

	// if isVariable=true -> a single PropositionPattern..
	private PropositionPattern propPattern;
	private Filter filter;
	private ArrayList<PropositionSet> values;
    
	// if isVariable=false -> list of propositionPatterns..
	private ArrayList<PropositionPattern> props;

	String raVar = "";

	public PremisePattern(PropositionPattern propVar, String raVar, PremiseType type) {
		super();
		this.raVar = raVar;
		this.type = type;
		this.propPattern = propVar;
		this.props = null;
 		this.filter = null;
	}

	public PremisePattern(ArrayList<PropositionPattern> props, String raVar, PremiseType type) {
		super();
		this.raVar = raVar;
		this.type = type;
		this.props = props;
		this.propPattern = null;
		this.filter = null;
	}

	public PremisePattern(PremiseType type) {
		super();
		this.raVar = null;
		this.type = type;
		this.props = null;
		this.propPattern = null;
		this.filter = null;
	}
	
	public ArrayList<PropositionPattern> getProps() {
		return props;
	}

	public void setProps(ArrayList<PropositionPattern> props) {
		this.props = props;
	}

	public String getRaVar() {
		return raVar;
	}

	public void setRaVar(String raVar) {
		this.raVar = raVar;
	}
	
	public PremiseType getType() {
		return type;
	}

	public void setType(PremiseType type) {
		this.type = type;
	}

	public PropositionPattern getPropPattern() {
		return propPattern;
	}

	public void setPropPattern(PropositionPattern propVar) {
		this.propPattern = propVar;
	}

	public Filter getFilter() {
		return filter;
	}

	public void setFilter(Filter filter) {
		this.filter = filter; 
	}
    
    public ArrayList<PropositionSet> getValues() {
		return values;
	}

	public void setValues(ArrayList<PropositionSet> values) {
		this.values = values;
	}

	public int valueExists(String setID) {
		for (int i = 0; i < values.size(); i++) {
			PropositionSet set = values.get(i);
			if (set.getSetID().compareTo(setID) == 0) {
				return i;
			}
		}
		return -1;
	}
	

	public String getSparqlRepresentation_old() {
		StringBuilder str = new StringBuilder();
		
		if (this.type == PremiseType.VARIABLE) {
			
			str.append(propPattern.getSparqlRepresentation() + propPattern.getURIVar() + " argtech:Premise "
					+ raVar + ".\r\n");
			
			if(filter != null) {
				str.append(filter.getSparqlRepresentation());
			}
			
		} else if (this.type == PremiseType.PROPSET){
		
			if (SPARQLTranslator.translationMode == TranslationMode.Optimized) {
				for (PropositionPattern prop : props) {
					str.append(
							prop.getSparqlRepresentation() + 
							prop.getURIVar() + " argtech:Premise " + raVar + ".\n");
				}
			} else {
				for (PropositionPattern prop : props) {
					str.append(prop.getSparqlRepresentation() + 
							"{ \n" + prop.getURIVar() + " argtech:Premise " + raVar + ".\n" + 
							"} UNION { \n" + 
							prop.getEquivPattern().getSparqlRepresentation() + prop.getEquivPattern().getEqPropID() + " aif:Premise " + raVar + ".\n" + "}\n");
				}
			}
		} else if(this.type == PremiseType.EMPTY) {
			return "";
		}

		return str.toString();
	}
	
	public String getSparqlRepresentation() {
		StringBuilder str = new StringBuilder();
		
		if (this.type == PremiseType.VARIABLE) {
			
			str.append(propPattern.getSparqlRepresentation() + propPattern.getURIVar() + " argtech:Premise "
					+ raVar + ".\r\n");
			
			if(filter != null) {
				str.append(filter.getSparqlRepresentation());
			}
			
		} else if (this.type == PremiseType.PROPSET){
		
			if (SPARQLTranslator.translationMode == TranslationMode.Optimized) {
				for (PropositionPattern prop : props) {
					str.append(
							prop.getSparqlRepresentation() + 
							prop.getURIVar() + " argtech:Premise " + raVar + ".\n");
				}
			} else {
				for (PropositionPattern prop : props) {
					str.append(prop.getSparqlRepresentation() +
					prop.getURIVar() + " argtech:Premise " + raVar + ".\n");
				}
			}
		} else if(this.type == PremiseType.EMPTY) {
			return "";
		}

		return str.toString();
	}

	public String toString() {
		StringBuilder s = new StringBuilder();

		s.append("\nPremise Type: " + this.type);
		if (this.type == PremiseType.VARIABLE) {
			s.append("\nPremise ArgQL variable: " + propPattern.getText());
			s.append("\nPremise SPARQL variable: " + propPattern.getURIVar());
		} else if (this.type == PremiseType.PROPSET){
			for (PropositionPattern prop : props) {
				s.append(prop.toString() + "\n");
			}
		}

		s.append("\nPremise pattern RA var: " + raVar + "\n");

		return s.toString();
	}

	@Override
	public String toArgQLString() {
		StringBuilder stringBuilder = new StringBuilder();
		// TODO Auto-generated method stub
		if(this.type == PremiseType.VARIABLE) {
			stringBuilder.append(propPattern.toArgQLString());
			if(filter!=null) {
				stringBuilder.append(filter.toArgQLString());
			}
		} else if(this.type == PremiseType.PROPSET)
		{
			for (PropositionPattern prop : props) {
				stringBuilder.append(prop.toArgQLString()+ ", ");
			}
		} else if(this.type == PremiseType.EMPTY) {
			stringBuilder.append("_");
		}
		return stringBuilder.toString();
	}

}
