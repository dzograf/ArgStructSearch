package arg.tech.argql.results;

import arg.tech.utils.Enums.*;

public class ReturnElement {
	private ReturnValueType type;
	private Variable var;
	private Variable var2;

	public ReturnElement(Variable var, ReturnValueType type) {
		// TODO Auto-generated constructor stub
		this.type = type;
		this.var = var;
	}
	
	public ReturnElement(Variable var1, Variable var2, ReturnValueType type) {
		this.type = type;
		this.var = var1;
		this.var2 = var2;
	}

	public ReturnValueType getType() {
		return type;
	}

	public void setType(ReturnValueType type) {
		this.type = type;
	}

	public Variable getVar() {
		return var;
	}

	public void setVar(Variable var) {
		this.var = var;
	}

	public Variable getVar2() {
		return var2;
	}

	public void setVar2(Variable var2) {
		this.var2 = var2;
	}
	
	

}
