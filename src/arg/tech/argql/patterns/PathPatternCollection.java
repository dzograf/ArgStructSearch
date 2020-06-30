package arg.tech.argql.patterns;

import java.util.ArrayList;
import java.util.Iterator;

public class PathPatternCollection {
	private ArrayList<PathPattern> pathSet;

	public PathPatternCollection() {
		pathSet = new ArrayList<PathPattern>();
	}

	public ArrayList<PathPattern> getPathSet() {
		return pathSet;
	}

	public void setPathSet(ArrayList<PathPattern> pathSet) {
		this.pathSet.clear();
		this.pathSet.addAll(pathSet);
	}

	private boolean equalPaths(PathPattern path1, PathPattern path2) {
		if (path1.toString().compareTo(path2.toString()) == 0)
			return true;

		return false;
	}
	
	public void updateLast(ArgPattern ap) {
		for(int i=0; i< pathSet.size(); i++){
			PathPattern pp = pathSet.get(i);
			RelationPattern lastRelation = pp.relSequence.get(pp.relSequence.size()-1);
			lastRelation.setAp2(ap);
			lastRelation.getAp2().setIncomingRelPattern(lastRelation);
			pp.relSequence.set(pp.relSequence.size()-1, lastRelation);
			pathSet.set(i, pp);
		}	
	}
	
	public void updateFirst(ArgPattern ap) {
		for(int i=0; i< pathSet.size(); i++){
			PathPattern pp = pathSet.get(i);
			RelationPattern firstRelation = pp.relSequence.get(0);
			firstRelation.setAp1(ap);
			pp.relSequence.set(0, firstRelation);
			pathSet.set(i, pp);
		}	
	}	

	private boolean pathExists(PathPattern path2) {
		for (PathPattern currpath : pathSet) {
			if (equalPaths(currpath, path2)) {
				return true;
			}
		}

		return false;
	}

	public void addToCollection(PathPattern pathpattern) {
		if (!pathExists(pathpattern))
			pathSet.add(pathpattern);
	}

	public String toString() {
		StringBuilder str = new StringBuilder();
		for (PathPattern path : pathSet) {
			str.append(path.printPath() + "\n");
		}
		return str.toString();
	}

	
	public String printRelSequence() {
		StringBuilder str = new StringBuilder();
		for (PathPattern path : pathSet) {
			str.append(path.toString() + "\n");
		}
		return str.toString();
	}
	
	public static PathPatternCollection merge(PathPatternCollection first, PathPatternCollection second) {

		PathPatternCollection newCollection = null;
		if (!second.getPathSet().isEmpty() && first.getPathSet().isEmpty() )
			return second;
		else if (!first.getPathSet().isEmpty() && second.getPathSet().isEmpty())
			return first;
		else {

		
			newCollection = new PathPatternCollection();
			for (PathPattern path1 : first.getPathSet()) {
			//	ArrayList<RelationPattern> path1Rel = path1.getRelSequence();
				for (PathPattern path2 : second.getPathSet()) {
					
					PathPattern pathPattern = PathPattern.concat(path1, path2);
					
				//	pathPattern.getRelSequence().addAll(path1Rel);
				//	pathPattern.getRelSequence().addAll(path2.getRelSequence());
					newCollection.addToCollection(pathPattern);
					pathPattern = null;
				}
			}
		}
		
	//	System.out.println("\n\n**** Result of merging:");
	//	System.out.println(newCollection.toString());
		return newCollection;
	}
}
