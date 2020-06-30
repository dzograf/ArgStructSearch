package arg.tech.argql.translator.sparql;
/*
import java.util.*;
import java.util.stream.Collectors;

import gr.forth.ics.argql.models.Argument;
import gr.forth.ics.argql.models.Proposition;
import gr.forth.ics.argql.models.PropositionSet;
import gr.forth.ics.argql.models.Variable;
import gr.forth.ics.argql.models.VariableValue;
import gr.forth.ics.argql.patterns.*;

public class SymbolTable {

	public HashMap<Variable, ArrayList<VariableValue>> variables;
	

	public SymbolTable(){
		variables = new HashMap<Variable, ArrayList<VariableValue>>();
	}
	
	/*public int positionOf(String varName) {

		for (int i = 0; i < variableList.size(); i++) {
			if (variableList.get(i).name.equals(varName))
				return i;
		}

		return -1;
	}*/
	
/*	public void clear(){
		variables.clear();
	}

	
	private boolean varExists(String name) {
		for(Map.Entry<Variable, ArrayList<VariableValue>> entry : variables.entrySet()){
			Variable key = entry.getKey();
			if(name.equals(key.name))
				return true;
		}
		
		return false;
	}
	
	
	
	public boolean addVar(String varName, Variable.Type type) {
		if(!varExists(varName)){
			Variable node = new Variable(varName, type);
			variables.put(node, new ArrayList<VariableValue>());

			return true;
		} else {
			return false;
		}
	}
	
	public void addValue(Variable var, VariableValue value) {
		for(Map.Entry<Variable, ArrayList<VariableValue>> entry : variables.entrySet()){
			Variable key = entry.getKey();
			if(var.name.equals(key.name)){
				ArrayList<VariableValue> values = entry.getValue();
				values.add(value);
				variables.put(key, values);
			}
		}
	}
	
	public void setValues(Variable var, ArrayList<VariableValue> values) {
		for(Map.Entry<Variable, ArrayList<VariableValue>> entry : variables.entrySet()){
			Variable key = entry.getKey();
			if(var.name.equals(key.name)){
				variables.put(key, values);
			}
		}
	}
	
	public ArrayList<VariableValue> getVariableValues(String var){
		for(Map.Entry<Variable, ArrayList<VariableValue>> entry: variables.entrySet()){
			Variable key = entry.getKey();
			if(var.equals(key.name)){
				return entry.getValue();
			}
		}
		return null;
	}

	public Variable.Type getType(String var){
		for(Map.Entry<Variable, ArrayList<VariableValue>> entry: variables.entrySet()){
			Variable v = entry.getKey();
			if(v.name.equals(var))
				return v.type;
		}
		return null;
	}
	
	
	
	public void updateValue(Variable variable, VariableValue value) {
		VariableValue val = valueExists(value.getId(), variable.name);
		if(val == null) {
			addValue(variable, value);
		} else {
			ArrayList<VariableValue> values = getVariableValues(variable.name);
			values.remove(val);
			values.add(value);
			setValues(variable, values);
		}
	}

	public VariableValue valueExists(String id, String var) {
		ArrayList<VariableValue> values = getVariableValues(var);
		for(VariableValue v : values) {
			if(v.getId().compareTo(id) == 0) {
				return v;
			}
		}
	 	return null;
	}
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		for(Map.Entry<Variable, ArrayList<VariableValue>> entry: variables.entrySet()){
			Variable v = entry.getKey();
			str.append("Variable: " + v.name + "\nValues: \n");
			
			ArrayList<VariableValue> values = getVariableValues(v.name);
			for(VariableValue value : values) {
				str.append(value.toString());
				str.append("\n");
			}
		}
		
		return str.toString();
	}

}*/
