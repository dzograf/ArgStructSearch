package arg.tech.argql.translator.sparql;

import java.util.ArrayList;
import java.util.HashMap;

import arg.tech.argql.patterns.ArgPattern;
import arg.tech.argql.patterns.PremisePattern;
import arg.tech.argql.results.Argument;
import arg.tech.argql.results.Proposition;
import arg.tech.argql.results.PropositionSet;
import arg.tech.argql.results.VariableValue;
import arg.tech.utils.Enums.Operation;

public class ArgumentValuesManager {
	private ArrayList<ArgPattern> argpatterns;

	public ArgumentValuesManager() {
		argpatterns = new ArrayList<ArgPattern>();
	}

	public void clear() {
		argpatterns.clear();
	}
	
	public boolean addAP(ArgPattern argpattern) {
		return argpatterns.add(argpattern);
	}

	public int searchByID(ArrayList<Argument> values, String id) {
		for (int i = 0; i < values.size(); i++) {
			Argument v = values.get(i);
			if (v.getId().compareTo(id) == 0) {
				return i;
			}
		}
		return -1;
	}

	private boolean contains(PropositionSet propset, Proposition proposition) {
		for (Proposition pr : propset.getProps()) {
			if (pr.value.compareTo(proposition.value) == 0) {
				return true;
			}
		}

		return false;
	}

	private boolean isSubset(PropositionSet prem1, PropositionSet prem2) {

		for (Proposition proposition : prem1.getProps()) {
			if (!contains(prem2, proposition))
				return false;
		}
		return true;
	}

	private boolean equalPropositionSets(PropositionSet prem1, PropositionSet prem2) {
		return isSubset(prem1, prem2) && isSubset(prem2, prem1);
	}

	public Argument searchByArgument(ArrayList<Argument> values, Argument arg) {
		for (Argument v : values) {
			PropositionSet premise1 = v.getPremise();
			PropositionSet premise2 = arg.getPremise();
			if (equalPropositionSets(premise1, premise2)
					&& v.getConclusion().getValue()
					.compareTo(arg.getConclusion().getValue()) == 0) {
				return v;
			}
		}
		return null;
	}

	/*
	 * private int findArgPattern(String varname) { for (int i=0;
	 * i<argpatterns.size(); i++) { if
	 * (argpatterns.get(i).getRaVariable().compareTo(varname) == 0) return i; }
	 * 
	 * return -1; }
	 */

	private int findArgValue(ArrayList<Argument> values, Argument argument) {
		for (int i = 0; i < values.size(); i++) {
			if (values.get(i).getId().compareTo(argument.getId()) == 0) {
				return i;
			}
		}
		return -1;
	}

	public void updateValue(int apIndex, int argValueIndex, Argument argument, Operation operation) {
		ArgPattern argpattern = argpatterns.get(apIndex);
		switch (operation) {
		case ADD:
			ArrayList<Argument> argvalues = argpattern.getValues();
			argvalues.add(argument);
			argpattern.setValues(argvalues);
			argpatterns.set(apIndex, argpattern);
			break;

		case UPDATE:
			argvalues = argpattern.getValues();
			argvalues.set(argValueIndex, argument);
			argpattern.setValues(argvalues);
			argpatterns.set(apIndex, argpattern);
			break;

		case NOTHING:
			break;
		}
	}

	public String valuesToString(ArgPattern ap) {
		ArrayList<Argument> values = ap.getValues();
		StringBuilder str = new StringBuilder();
		for (Argument arg : values) {
			str.append(arg.toString());
		}

		return str.toString();
	}

	public ArrayList<ArgPattern> getArgpatterns() {
		return argpatterns;
	}

	public void setArgpatterns(ArrayList<ArgPattern> argpatterns) {
		this.argpatterns = argpatterns;
	}

}
