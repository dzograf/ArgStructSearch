package arg.tech.argql.results;


public class Relation {

	
	private Argument srcArg;
	private Argument dstArg;
	
	private String type;
	
	public Relation(){}
	
	public Relation(String type, Argument srcArg, Argument dstArg) {
		this.type = type;
		this.srcArg = srcArg;
		this.dstArg = dstArg;
	}

	public Argument getSrcArg() {
		return srcArg;
	}

	public void setSrcArg(Argument srcArg) {
		this.srcArg = srcArg;
	}

	public Argument getDstArg() {
		return dstArg;
	}

	public void setDstArg(Argument dstArg) {
		this.dstArg = dstArg;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
