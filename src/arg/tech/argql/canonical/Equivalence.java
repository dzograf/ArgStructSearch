package arg.tech.argql.canonical;

import java.util.ArrayList;

public class Equivalence {

	private ArrayList<INode> equivalence;
	private int canonicalElement = -1;

	public Equivalence() {
		equivalence = new ArrayList<INode>();
	}
	
	public ArrayList<INode> getEquivalence() {
		return equivalence;
	}

	public void setEquivalences(ArrayList<INode> equivalences) {
		this.equivalence = equivalences;
	}

	public boolean add(INode inode) {
		return equivalence.add(inode);
	}
	
	
	public int getCanonicalElement() {
		return canonicalElement;
	}

	public void setCanonicalElement(int canonicalElement) {
		this.canonicalElement = canonicalElement;
	}
	
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		for(INode i : equivalence) {
			str.append(i.toString() + "\n");
		}
		return str.toString();
	}
}
