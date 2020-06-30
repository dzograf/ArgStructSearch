package arg.tech.argql.results;

import java.util.ArrayList;

public class PropositionSet extends ResultValue{
	public ArrayList<Proposition> props;
	private String premVar;
	private String setID;

	public PropositionSet(String setID, String premVar) {
		super(ResultType.PropositionSet);
		this.setID = setID;
		this.premVar = premVar;
		props = new ArrayList<Proposition>();
	}

	public ArrayList<Proposition> getProps() {
		return props;
	}

	public void setProps(ArrayList<Proposition> props) {
		this.props = props;
	}

	public String getSetID() {
		return setID;
	}

	public void setSetID(String setID) {
		this.setID = setID;
	}

	
	
	public String getPremVar() {
		return premVar;
	}

	public void setPremVar(String premVar) {
		this.premVar = premVar;
	}

	private boolean exists(Proposition proposition) {
		for (Proposition prop : props) {
			if (prop.value.compareTo(proposition.value) == 0) {
				return true;
			}
		}
		return false;
	}
	 
	public boolean addProposition(Proposition proposition) {

		if(!exists(proposition))
		{
			return props.add(proposition);
		}
		return false;
	}

	public String toString() {
		StringBuilder str = new StringBuilder();
		if(premVar != "") {
			str.append(premVar + ": ");
		}
		str.append("{");
		int i = 0;
		for (i = 0; i < props.size() - 1; i++) {
			str.append("\"" + props.get(i).value + " \", ");
		}
		str.append("\"" + props.get(i).value + "\"}");
		return str.toString();
	}
}
