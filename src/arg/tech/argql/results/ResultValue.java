package arg.tech.argql.results;


enum ResultType{Argument, PropositionSet, Proposition, Path}

public class ResultValue {

	private ResultType type;
	
	public ResultValue(ResultType type) {
		this.type = type;
	}

	public ResultType getType() {
		return type;
	}

	public void setType(ResultType type) {
		this.type = type;
	}
	
}
