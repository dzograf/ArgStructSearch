package arg.tech.argql.patterns;

import java.util.ArrayList;

import org.antlr.v4.runtime.misc.Pair;

public class INodeVarSet {
	public ArrayList<Pair<String, String>> varSet = new ArrayList<Pair<String, String>>();
	
	public void addPairOfVars(String v1, String v2) {
		varSet.add(new Pair<String, String>(v1,v2));
	}
}
