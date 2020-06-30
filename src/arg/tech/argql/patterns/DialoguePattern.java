package arg.tech.argql.patterns;

public class DialoguePattern {

	public boolean isArgPattern = false;
	public boolean isPathPattern = false;
	
	public ArgPattern argPattern;
	public PathPatternCollection pathPatternCollection;
	
	public DialoguePattern(ArgPattern ap) {
		this.isArgPattern = true;
		this.argPattern = ap;
		this.pathPatternCollection = null;
	}
	
	public DialoguePattern(PathPatternCollection ppCol) {
		this.isPathPattern = true;
		this.pathPatternCollection = ppCol;
		this.argPattern = null;
	}

	public ArgPattern getAp() {
		return argPattern;
	}

	public void setAp(ArgPattern ap) {
		this.argPattern = ap;
	}

	public PathPatternCollection getPpCol() {
		return pathPatternCollection;
	}

	public void setPpCol(PathPatternCollection ppCol) {
		this.pathPatternCollection = ppCol;
	}
	
	
}
