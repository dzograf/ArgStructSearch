package arg.tech.argql.patterns;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import arg.tech.argql.results.Path;
import arg.tech.argql.translator.sparql.SPARQLTranslator;

public class PathPattern {

	public ArrayList<RelationPattern> relSequence;
	public ArrayList<Path> values;

	public PathPattern() {
		relSequence = new ArrayList<RelationPattern>();
		values = new ArrayList<Path>();
	}

	public ArrayList<RelationPattern> getRelSequence() {
		return relSequence;
	}

	public void setRelSequence(ArrayList<RelationPattern> paths) {
		this.relSequence = paths;
	}

	public ArrayList<Path> getValues() {
		return values;
	}

	public void setValues(ArrayList<Path> values) {
		this.values = values;
	}

	public boolean addToPath(RelationPattern relation) {
		return relSequence.add(relation);
	}

	public static PathPattern multiplyPathWith(PathPattern path, int num) {

		PathPattern newPathPattern = new PathPattern();

		ArrayList<RelationPattern> newRelSequence = new ArrayList<RelationPattern>(path.getRelSequence());

		String raVar = SPARQLTranslator.generateRAVariable();
		ArgPattern newAP = SPARQLTranslator.argPatternVariable("_", raVar);
		// newPathPattern.setRelSequence(newRelSequence);

		RelationPattern lastRel = newRelSequence.get(newRelSequence.size() - 1);
		newAP.setIncomingRelPattern(lastRel);
		lastRel.setAp2(newAP);
		newRelSequence.set(newRelSequence.size() - 1, lastRel);

		for (int i = 1; i < num; i++) {

			for (int j = 0; j < path.getRelSequence().size(); j++) {

				RelationPattern relation = path.getRelSequence().get(j);
				RelationPattern relationCopy = RelationPattern.newRelation(relation);

				relationCopy.setAp1(newAP);

				if (j != path.getRelSequence().size() - 1 || i != num - 1) {
					raVar = SPARQLTranslator.generateRAVariable();
					newAP = SPARQLTranslator.argPatternVariable("_", raVar);
					newAP.setIncomingRelPattern(relationCopy);
					relationCopy.setAp2(newAP);
				}

				newRelSequence.add(relationCopy);
			}

			newPathPattern.setRelSequence(newRelSequence);
		}

		return newPathPattern;
	}

	public static PathPattern appendPathPattern(PathPattern path, PathPattern base) {
		PathPattern newPathPattern = new PathPattern();

		newPathPattern = deepCopy(path);
		String raVar = SPARQLTranslator.generateRAVariable();
		ArgPattern newAP = SPARQLTranslator.argPatternVariable("", raVar);

		RelationPattern lastRel = newPathPattern.relSequence.get(newPathPattern.relSequence.size() - 1);
		newAP.setIncomingRelPattern(lastRel);
		lastRel.setAp2(newAP);
		newPathPattern.relSequence.set(newPathPattern.relSequence.size() - 1, lastRel);
		for (int i = 0; i < base.relSequence.size(); i++) {
			RelationPattern relation = base.relSequence.get(i);
			RelationPattern relationCopy = RelationPattern.newRelation(relation);
			relationCopy.setAp1(newAP);

			if (i < base.relSequence.size() - 1) {
				raVar = SPARQLTranslator.generateRAVariable();
				newAP = SPARQLTranslator.argPatternVariable("", raVar);
				newAP.setIncomingRelPattern(relationCopy);
				relationCopy.setAp2(newAP);
			}

			newPathPattern.relSequence.add(relationCopy);
		}

		return newPathPattern;
	}

	public static PathPattern concat(PathPattern path1, PathPattern path2) {
		PathPattern newPathPattern = new PathPattern();

		newPathPattern = deepCopy(path1);
		newPathPattern.relSequence.addAll(deepCopy(path2).relSequence);
		return newPathPattern;
	}

	public static PathPattern deepCopy(PathPattern original) {
		PathPattern newPath = new PathPattern();
		for (RelationPattern rel : original.relSequence) {
			RelationPattern relationCopy = RelationPattern.deepCopy(rel);
			newPath.relSequence.add(relationCopy);
		}
		return newPath;
	}

	private boolean valueExists(Path path) {
		for (int i = 0; i < values.size(); i++) {
			Path p = values.get(i);
			if (p.ID().compareTo(path.ID()) == 0)
				return true;
		}

		return false;
	}

	public void addValue(Path value) {
		if (!valueExists(value)) {
			values.add(value);
		}
	}



	public String printPath() {
		StringBuilder pathStr = new StringBuilder();
		// int i=0;
		pathStr.append(toString() + ": \n");
		for (RelationPattern rel : relSequence) {

			if (rel.getAp1() != null) {
			//	pathStr.append(rel.getAp1().getIncomingRelPattern().getName() + " ");
				pathStr.append(rel.getAp1().getRaVariable());
			}

			if (rel.getName().compareTo("Rebut") == 0) {
				RebutPattern reb = (RebutPattern) rel;
				pathStr.append(" " + reb.getName() + "(" + reb.getCaVar() + ") ");
			} else if (rel.getName().compareTo("Undercut") == 0) {
				UndercutPattern und = (UndercutPattern) rel;
				pathStr.append(" " + und.getName() + "(" + und.getCaVar() + ") ");
			} else if (rel.getName().compareTo("Attack") == 0) {
				AttackPattern att = (AttackPattern) rel;
				pathStr.append(" " + att.getName() + "(" + att.getCaVar() + ") ");
			} else if (rel.getName().compareTo("Endorse") == 0) {
				EndorsePattern endorse = (EndorsePattern) rel;
				pathStr.append(" " + endorse.getName() + "(" + endorse.getEquivPattern().getMa() + ") ");
			} else if (rel.getName().compareTo("Back") == 0) {
				BackPattern back = (BackPattern) rel;
				pathStr.append(" " + back.getName() + "(" + back.getEquivPattern().getMa() + ") ");
			} else if (rel.getName().compareTo("Support") == 0) {
				SupportPattern support = (SupportPattern) rel;
				pathStr.append(" " + support.getName() + "(" + support.getEquivPattern().getMa() + ") ");
			}

			if (rel.getAp2() != null) {
			//	pathStr.append(rel.getAp2().getIncomingRelPattern().getName() + " ");
				pathStr.append(rel.getAp2().getRaVariable() + "\n");
			}
		}

		// pathStr += relSequence.get(relSequence.size() -1).getAp2().getRaVariable() +
		// "(" + relSequence.get(i-1).getAp2().getArgVar() + ")";
		return pathStr.toString();

	}

	public String toString() {
		String pathStr = "";
		for (int i = 0; i < relSequence.size(); i++) {
			pathStr += relSequence.get(i).getName() + "/";
		}
		return pathStr;
	}
}
