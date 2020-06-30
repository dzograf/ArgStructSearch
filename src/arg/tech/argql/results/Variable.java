package arg.tech.argql.results;

import arg.tech.utils.Enums.VariableType;

public class Variable {

	public VariableType type;
	public String name;


	public Variable(String name, VariableType type) {
		this.name = name;
		this.type = type;		
	}
}
