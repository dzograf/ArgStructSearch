package arg.tech.argql.results;

import java.util.ArrayList;
import java.util.Iterator;

public class Path extends ResultValue{

	private ArrayList<Relation> relationList;
	
	public Path() {
		super(ResultType.Path);
		relationList = new ArrayList<Relation>();
	}
	
	public ArrayList<Relation> getRelationList() {
		return relationList;
	}

	public void setRelationList(ArrayList<Relation> relationList) {
		this.relationList = relationList;
	}

	public String ID() {
		StringBuilder str = new StringBuilder();
		for (Iterator iterator = relationList.iterator(); iterator.hasNext();) {
			Relation relation = (Relation) iterator.next();
			if(relation.getSrcArg()!=null) {
				str.append(relation.getSrcArg().getId() + "-");
			} else {
				str.append("*");
			}
			str.append(relation.getType());
			if(iterator.hasNext()) {
				if(relation.getDstArg() != null) {
					str.append(relation.getDstArg().getId());
				} else {
					str.append("*");
				}
			}
		}
		return str.toString();
	}
	
	public boolean addToPath(Relation relation) {
		return relationList.add(relation);
	}
	
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		int i=0;
		for (Iterator iterator = relationList.iterator(); iterator.hasNext();) {
			Relation relation = (Relation) iterator.next();
			if(relation.getSrcArg()!=null) {
				if(i!=0) {
					str.append("    ");
				}
				str.append(relation.getSrcArg().toString());
			} 
			
			if(relation.getDstArg() !=null ) {
				str.append("\n     - " + relation.getType().toUpperCase() + " - \n");
			}
			if(!iterator.hasNext() && relation.getDstArg() != null) {
				str.append("      " + relation.getDstArg().toString());	
			}
			i++;
		}
		return str.toString();
	}

}
