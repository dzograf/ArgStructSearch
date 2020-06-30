package arg.tech.argql.translator.sparql;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import arg.tech.argql.canonical.*;
import arg.tech.argql.parser.*;
import arg.tech.argql.patterns.*;
import arg.tech.argql.results.*;
import arg.tech.data.DatabaseManager;
import arg.tech.utils.Enums.*;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class SPARQLTranslator {
	// public static ArrayList<ArgPattern> argpatterns = new
	// ArrayList<ArgPattern>();
	public static ArrayList<PropositionPattern> propPatterns = new ArrayList<PropositionPattern>();
	public static ValueManager argManager = new ValueManager();
	public static TranslationMode translationMode;

	public static arg.tech.argql.canonical.RelationalDatabaseManager dbMngr;
	public static ArrayList<ReturnElement> returnValues;
	private static ArrayList<String> logicErrors;
	// private static SymbolTable memory;
	private static ArrayList<Variable> variables;
	public static ArrayList<DialoguePattern> dpList;

	private static int raVarCntr;
	private static int iVarCnt;
	private static int maVarCnt;
	private static int caVarCnt;
	private static int paVarCnt;
	private static int yaVarCnt;
	private static int locVarCnt;
	private static int taVarCnt;
	private static int txtVarCnt;
	private static int tempVarCnt;
	private static int topicVarCnt;

	public SPARQLTranslator() {

	}

	public static void initialize(boolean optimized) {
		// OntologyManager.initializeJDBC();
		// OntologyManager.initializeSesame();
		returnValues = new ArrayList<ReturnElement>();
		//dbMngr = new RelationalDatabaseManager();
		// memory = new SymbolTable();
		dpList = new ArrayList<DialoguePattern>();
		variables = new ArrayList<Variable>();
		logicErrors = new ArrayList<String>();
		if (optimized)
			translationMode = translationMode.Optimized;
		else
			translationMode = translationMode.Normal;
		resetVariables();
	}

	public static String setNamespaces(String query) {
		query += "prefix rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" + 
				"prefix aif:<http://www.aifdb.org/nodes/#>\n" + 
				"prefix argtech:<http://www.arg.tech/aif#>";

		return query;
	}

	public static void terminate() {
		DatabaseManager.terminateJDBC();
		// OntologyManager.terminateSesame();
		//dbMngr.terminate();
		returnValues.clear();
		argManager.clear();
		logicErrors.clear();
		dpList.clear();
		// memory.clear();
	}

	public static void resetVariables() {
		raVarCntr = 0;
		iVarCnt = 0;
		topicVarCnt = 0;
		maVarCnt = 0;
		caVarCnt = 0;
		yaVarCnt = 0;
		paVarCnt = 0;
		locVarCnt = 0;
		taVarCnt = 0;
		tempVarCnt = 0;
		txtVarCnt = 0;
	}

	public static String generateMAVariable() {
		maVarCnt++;
		String var = "?_ma" + maVarCnt;
		// addVarToSymbolTable(var, Variable.Type.URI);
		return var;
	}

	public static String generateCAVariable() {
		caVarCnt++;
		String var = "?_ca" + caVarCnt;
		// addVarToSymbolTable(var, Variable.Type.URI);
		return var;
	}

	public static String generateRAVariable() {
		raVarCntr++;
		String var = "?_ra" + raVarCntr;
		// addVarToSymbolTable(var, Variable.Type.URI);
		return var;
	}
	
	public static String generatePAVariable() {
		paVarCnt++;
		String var = "?_pa" + paVarCnt;
		// addVarToSymbolTable(var, Variable.Type.URI);
		return var;
	}

	public static String generateIVariable() {
		iVarCnt++;
		String var = "?_i" + iVarCnt;
		// addVarToSymbolTable(var, Variable.Type.URI);
		return var;
	}

	public static String generateYAVariable() {
		yaVarCnt++;
		String var = "?_ya" + yaVarCnt;

		// addVarToSymbolTable(var, Variable.Type.URI);
		return var;
	}

	public static String generateLocVariable() {
		locVarCnt++;
		String var = "?_loc" + locVarCnt;
		// addVarToSymbolTable(var, Variable.Type.URI);
		return var;
	}

	public static String generateTAVariable() {
		taVarCnt++;
		String var = "?_ta" + taVarCnt;
		// addVarToSymbolTable(var, Variable.Type.URI);
		return var;
	}

	public static String generateTempVariable() {
		tempVarCnt++;
		String var = "?_x" + tempVarCnt;
		// addVarToSymbolTable(var, Variable.Type.URI);
		return var;
	}

	public static String generateTextVariable() {
		txtVarCnt++;
		String var = "?_txt" + txtVarCnt;
		// addVarToSymbolTable(var, Variable.Type.LITERAL);
		return var;
	}

	public static String generateTopicVariable() {
		topicVarCnt++;
		String var = "?_t" + topicVarCnt;
		// addVarToSymbolTable(var, Variable.Type.URI);
		return var;
	}

	// ********** SYMBOLTABLE MANAGEMENT ********************************//

	public static boolean addVar(String name, VariableType type) {

		for (Variable v : variables) {
			if (v.name.compareTo(name) == 0) {
			//	logicErrors.add("Variable " + name + " is being used.\n");
			}
		}

		Variable var = new Variable(name, type);
		return variables.add(var);
		// return memory.addVar(name, type); 

	}

	// ********** SELECT_TABLE MANAGEMENT ********************************//

	public static void addVarReturnvalue(String varName) {
		Variable var = findVariable(varName);
		if(var!=null){
			ReturnElement retValue = new ReturnElement(var, ReturnValueType.Variable);
			returnValues.add(retValue);
		} else{
			logicErrors.add("There is no variable " + varName + ".\n");
		}
	}
	
	public static void addPathReturnValue(String varName1, String varName2) {
		Variable var1 = findVariable(varName1);
		Variable var2 = findVariable(varName2);
		if(var1 == null){
			logicErrors.add("There is no variable " + varName1 + ".\n");
		}
		if(var1 == null){
			logicErrors.add("There is no variable " + varName2 + ".\n");
		}
		if(var1 != null && var2 != null) {
			ReturnElement retValue = new ReturnElement(var1, var2, ReturnValueType.Path);
			returnValues.add(retValue);
		}
	}

	public static void addDialoguePattern(DialoguePattern dp) {
		dpList.add(dp);
	}

	public static EquivalencePattern newEquivalenceCondition(String id1, String id2) {
		String ya1 = generateYAVariable();
		String loc1 = generateLocVariable();

		String itextVar2 = generateTextVariable();
		String ya2 = generateYAVariable();
		String loc2 = generateLocVariable();

		String ta = generateTAVariable();
		String ya4Ma = generateYAVariable();
		String ma = generateMAVariable();

		EquivalencePattern equiv = new EquivalencePattern(id1, id2, itextVar2, ya1, loc1, ya2, loc2, ta, ya4Ma, ma);

		return equiv;
	}
	
	

	// *************TRANSLATE ARGPATTERN SINGLE VARIABLE *****************//

	public static ArgPattern argPatternVariable(String varName, String raVar) {

		// ***********PREMISE**********
		String uriVar = generateIVariable();
		String textVar = generateTextVariable();

		PropositionPattern premise = new PropositionPattern(uriVar, textVar, null, true);
		propPatterns.add(premise);
		PremisePattern premPattern = new PremisePattern(premise, raVar, PremiseType.VARIABLE);

		// ***********CONCLUSION**********

		String conclURIVar = generateIVariable();
		String conclTextVar = generateTextVariable();

		PropositionPattern conclProp = new PropositionPattern(conclURIVar, conclTextVar, null, true);
		propPatterns.add(conclProp);

		ConclusionPattern conclPattern = new ConclusionPattern(conclProp, true);

		ArgPattern argpat = new ArgPattern(varName, raVar, premPattern, conclPattern);
		conclPattern.setArgPattern(argpat);

		return argpat;
	}

	// *************TRANSLATE PROPOSITION PATTERN **********************

	private static String getCanonicalIRI(String proposition) {
		String canonical = "";
		try {
			proposition = new String(proposition.getBytes("Windows-1252"), "UTF-8");
			String query = "select * from equivalences where claimText = " + proposition + ";";
			System.out.println("\n" + query);
			ResultSet rs = dbMngr.executeQuery(query);

			if (rs.next())
				canonical = rs.getString("canonicalelement");

		} catch (Exception e) {
			logicErrors.add("Proposition with value " + proposition + " does not exist");
		}
		return canonical;
	}

	public static PropositionPattern newPropositionPattern(String proposition) {
		PropositionPattern propPattern = null;

		String iVar1 = generateIVariable();
		if (SPARQLTranslator.translationMode == TranslationMode.Normal) {
			String iVar2 = generateIVariable();

			EquivalencePattern equivPattern = newEquivalenceCondition(iVar1, iVar2);

			propPattern = new PropositionPattern(iVar1, proposition, equivPattern, false);

		} else if (SPARQLTranslator.translationMode == TranslationMode.Optimized) {

			String canonicalvalue = getCanonicalIRI(proposition);
			propPattern = new PropositionPattern(iVar1, canonicalvalue, null, false);
		}

		propPatterns.add(propPattern);
		return propPattern;
	}

	// *************TRANSLATE PREMISE PATTERN (PROPOSITION SET)
	// **********************
	public static PremisePattern premisePropSet(ArrayList<PropositionPattern> props, String raVar) {

		PremisePattern premisePattern = new PremisePattern(props, raVar, PremiseType.PROPSET);
		return premisePattern;
	}

	// *************TRANSLATE PREMISE PATTERN (VARIABLE) **********************
	public static PremisePattern premiseVar(String premVar, String raVar) {
		String uriVar = generateIVariable();

		PropositionPattern premise = new PropositionPattern(uriVar, premVar, null, true);

		PremisePattern premisePattern = new PremisePattern(premise, raVar, PremiseType.VARIABLE);

		return premisePattern;
	}
	
	public static PremisePattern premiseEmpty() {

		PremisePattern premisePattern = new PremisePattern(PremiseType.EMPTY);
		return premisePattern;
	}

	// *************TRANSLATE FILTERS PROPOSITION SET **********************
	/*
	 * public static String joinFilterPropSet(ArrayList<PropositionPattern> props,
	 * String raVar) { StringBuilder sparqlBuilder = new StringBuilder();
	 * Iterator<PropositionPattern> iter = props.iterator(); while (iter.hasNext())
	 * { PropositionPattern iNode = iter.next();
	 * 
	 * if(SPARQLTranslator.translationMode == TranslationMode.Normal) {
	 * sparqlBuilder.append("{\n" + iNode.getSparqlRepresentation() + "{\n" +
	 * iNode.getURIVar() + " aif:Premise " + raVar + ".\n" + "} UNION {\n" +
	 * iNode.getEquivPattern().getSparqlRepresentation() +
	 * iNode.getEquivPattern().getEqPropID() + " aif:Premise " + raVar + ".\n" +
	 * "}\n" + "}"); } else { sparqlBuilder.append("{\n" +
	 * iNode.getSparqlRepresentation() + iNode.getURIVar() + " aif:Premise "+ raVar
	 * + ".\n " + "}"); } if (iter.hasNext()) sparqlBuilder.append(" UNION\n"); else
	 * sparqlBuilder.append("\n"); } return sparqlBuilder.toString(); }
	 * 
	 */

	// *************TRANSLATE FILTERS WITH VARIABLE **********************
	/*
	 * public static String inclusionFilterWithVariable(String raVar, ArgPattern
	 * arg2) { String tempVar = generateTempVariable();
	 * 
	 * String s = "FILTER NOT EXISTS\n" + "{\n" + tempVar + " aif:Premise " +
	 * arg2.getRaVariable() + ".\n" + "FILTER NOT EXISTS\n" + "{\n" + tempVar +
	 * " aif:Premise " + raVar + ".\n" + "}\n" + "}\n" + "FILTER (" +
	 * arg2.getRaVariable() + " != " + raVar + ")\n";
	 * 
	 * return s; }
	 * 
	 * public static String joinWithVariable(PremisePattern premPattern, String
	 * raVar, ArgPattern arg2) { StringBuilder str = new StringBuilder();
	 * if(translationMode == TranslationMode.Normal) { str.append("{\n");
	 * str.append(arg2.getPremisePattern().getPropVar().getURIVar() +
	 * " aif:Premise " + raVar + ".\n"); str.append("} UNION { \n");
	 * str.append(premPattern.getPropVar().getURIVar() + " aif:Premise " +
	 * arg2.getRaVariable() + ".\n"); str.append("} UNION {\n"); EquivalencePattern
	 * equiv =
	 * newEquivalenceCondition(arg2.getPremisePattern().getPropVar().getURIVar(),
	 * premPattern.getPropVar().getURIVar());
	 * str.append(equiv.getSparqlRepresentation()); str.append("}\n"); } else {
	 * str.append("FILTER(" + premPattern.getPropVar().getText() + " = " +
	 * arg2.getPremisePattern().getPropVar().getText() + ")\n"); }
	 * str.append("FILTER(" + raVar + " != " + arg2.getRaVariable() + ")\n"); return
	 * str.toString(); }
	 */

	// *************CONCLUSION PATTERN ***************

	public static ConclusionPattern conclVarPattern(String conclTextVar) {
		String conclURIVar = generateIVariable();

		PropositionPattern propPattern = new PropositionPattern(conclURIVar, conclTextVar, null, true);

		propPatterns.add(propPattern);

		ConclusionPattern conclPattern = new ConclusionPattern(propPattern, true);

		return conclPattern;
	}

	public static ConclusionPattern conclPropositionPattern(String conclValue) {

		PropositionPattern prop = newPropositionPattern(conclValue);

		ConclusionPattern conclPattern = new ConclusionPattern(prop, false);

		return conclPattern;
	}

	// **************PATHPATTERNS*********************//
	public static RelationPattern undercut() {
		String caVar = generateCAVariable();
		UndercutPattern undercut = new UndercutPattern("Undercut", caVar);
		return undercut;
	}
	
	public static RelationPattern undermine() {
		String caVar = generateCAVariable();
		UnderminePattern undermine = new UnderminePattern("Undermine", caVar);
		return undermine;
	}

	public static RelationPattern rebut() {
		String caVar = generateCAVariable();
		RebutPattern rebut = new RebutPattern("Rebut", caVar);
		return rebut;
	}

	public static RelationPattern attack() {
		String caVar = generateCAVariable();
		AttackPattern attack = new AttackPattern("Attack", caVar);

		return attack;
	}

	public static RelationPattern endorse() {

		EquivalencePattern equivPattern = newEquivalenceCondition("", "");
		EndorsePattern endorse = new EndorsePattern("Endorse", equivPattern);

		return endorse;
	}

	public static RelationPattern backing() {
		EquivalencePattern equiv = newEquivalenceCondition("", "");
		BackPattern backing = new BackPattern("Back", equiv);

		return backing;
	}

	public static RelationPattern support() {
		EquivalencePattern equiv = newEquivalenceCondition("", "");
		SupportPattern support = new SupportPattern("Support", equiv);

		return support;
	}

	public static ArgPattern premVarExists(String premVar) {

		for (ArgPattern arg : argManager.getArgpatterns()) {
			if (arg.getPremisePattern().getType() == PremiseType.VARIABLE
					&& arg.getPremisePattern().getPropPattern().getText().compareTo(premVar) == 0) {
				return arg;
			}
		}

		for (DialoguePattern dp : dpList) {
			if (dp.isArgPattern) {
				if (dp.getAp().getPremisePattern().getType() == PremiseType.VARIABLE
						&& dp.getAp().getPremisePattern().getPropPattern().getText().compareTo(premVar) == 0)
					return dp.getAp();
			} else if (dp.isPathPattern) {
				PathPattern pp = dp.getPpCol().getPathSet().get(dp.getPpCol().getPathSet().size() - 1);
				for (RelationPattern rel : pp.relSequence) {
					ArgPattern ap1 = rel.getAp1();
					ArgPattern ap2 = rel.getAp2();

					if (ap1.getPremisePattern().getType()== PremiseType.VARIABLE
							&& ap1.getPremisePattern().getPropPattern().getText().compareTo(premVar) == 0)
						return ap1;
					if (ap2.getPremisePattern().getType() == PremiseType.VARIABLE
							&& ap2.getPremisePattern().getPropPattern().getText().compareTo(premVar) == 0)
						return ap2;
				}
			}
		}
		logicErrors.add("Premise variable " + premVar + " is not referring to some premise variable.\n");
		return null;
	}

	public static boolean addArgPattern(ArgPattern ap) {
		return (argManager.addAP(ap));
	}

	public static boolean logicErrorsExist() {
		if (logicErrors.size() > 0) {
			return true;
		}
		return false;
	}

	public static ArrayList<String> getLogicErrors() {
		return logicErrors;
	}

	public static void setLogicErrors(ArrayList<String> logicErrors) {
		SPARQLTranslator.logicErrors = logicErrors;
	}

	public static String generateSPARQLQuery(boolean optimized) {

		StringBuilder str = new StringBuilder();
		String rdfPrefix = "rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>";
		String aifPrefix = "aif:<http://www.aifdb.org/nodes/#>";
		String argtechPrefix = "argtech:<http://www.arg.tech/aif#>";
			 
		str.append("prefix " + rdfPrefix + "\n" + "prefix " + aifPrefix + "\n"+ "prefix " + argtechPrefix + "\n" + 
		"SELECT DISTINCT ?g \n");
		


		/*if (graph != "") {
			str.append("FROM " + "<http://" + graph + ">\n");
		}*/
		str.append("WHERE {\n GRAPH ?g {\n");

		for (DialoguePattern dp : dpList) {
			if (dp.isArgPattern) {
				ArgPattern ap = dp.getAp();
				str.append(ap.getSparqlRepresentation());
			} else if (dp.isPathPattern) {
				PathPatternCollection pathSet = dp.getPpCol();
				

				for (int j = 0; j < pathSet.getPathSet().size(); j++) {
					if (pathSet.getPathSet().size()>1 && j <= pathSet.getPathSet().size()-1) 
						str.append("{\n");
					
					if(j==0) {
						ArgPattern firstAP = pathSet.getPathSet().get(0).getRelSequence().get(0).getAp1();
						str.append(firstAP.getSparqlRepresentation());
					}
					
					PathPattern path = (PathPattern) pathSet.getPathSet().get(j);

			
					for (int i = 0; i < path.getRelSequence().size(); i++) {
						RelationPattern relPattern = path.getRelSequence().get(i);
						str.append(relPattern.getAp2().getSparqlRepresentation());
						str.append(relPattern.getSparqlRepresentation());
						//str.append(relPattern.getSparqlRepresentationSimple());
					}
  

					if (j < pathSet.getPathSet().size()-1) { 
						str.append("} UNION");
					}
					if(pathSet.getPathSet().size()>1 && j == pathSet.getPathSet().size()-1) {
						str.append("}\n"); 
					}
				}
			}
		}
		str.append("} \n}");
		return str.toString();
	}

	
	// ********* TRANSFORM RETURN VALUES *******************************//

	/*public static String collectResults(ResultSet results) throws Exception {

		ResultManager resMngr = new ResultManager();
		String resultStr = "";
		try {
			while (results.next()) {

				try {
					String rowKey = resMngr.createRowKey(dpList, results);
					
					int pos = resMngr.exists(rowKey);
					ResultRow resRow;

					if (pos == -1) {
						resRow = new ResultRow(rowKey);
					} else {
						resRow = resMngr.getRow(pos);
					}

				//	for (int indx = 0; indx < dpList.size(); indx++) {
					for(int indx=0; indx < returnValues.size(); indx++) {	
						if(returnValues.get(indx).getType() == ReturnValueType.Variable) {
						//	String var = returnValues.get(indx).getVar();
						}
						DialoguePattern dp = (DialoguePattern) dpList.get(indx);
						if (dp.isArgPattern) {
							ArgPattern argpattern = dp.getAp();
							Argument argValue = resMngr.createArgValue(argpattern, results);

							if (pos == -1) {
								ResultValue newDialogue = new ResultValue(argValue);
								resRow.add(newDialogue);
							} else {
								resRow.getDialogue(indx).setArgValue(argValue);
							}
						} else if (dp.isPathPattern) {
							PathPatternCollection ppColection = dp.getPpCol();
							PathPattern longestPP = ppColection.getPathSet().get(ppColection.getPathSet().size() - 1);

							Path newPath = new Path();

							for (int i = 0; i < longestPP.relSequence.size(); i++) {
								RelationPattern rel = longestPP.relSequence.get(i);
								Relation relation = new Relation();
								ArgPattern ap1 = rel.getAp1();
								ArgPattern ap2 = rel.getAp2();

								String arg1URI = results.getString(ap1.getRaVariable().trim().replace("?", ""));
								String arg2URI = results.getString(ap2.getRaVariable().trim().replace("?", ""));
								Argument arg1Value = null;
								Argument arg2Value = null;
								if (arg1URI != null) {
									arg1Value = resMngr.createArgValue(ap1, results);
								}
								if (arg2URI != null) {
									arg2Value = resMngr.createArgValue(ap2, results);
								}
								relation.setSrcArg(arg1Value);
								relation.setDstArg(arg2Value);
								relation.setType(rel.getName());
								newPath.addToPath(relation);
							}
							longestPP.addValue(newPath);

							if (pos == -1) {
								ResultValue newDialogue = new ResultValue(newPath);
								resRow.add(newDialogue);
							} else {
								resRow.getDialogue(indx).setPath(newPath);
							}
						}
					}

					if (pos == -1)
						resMngr.addRow(resRow);

				} catch (Exception e) {
					System.out.println(">>Error: " + e.getMessage());
					e.printStackTrace();
				}

			}
			resultStr = resMngr.printResults();
			System.out.println(resultStr);

			// }
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			return (e.getMessage());
		}
		return resultStr;
	}
	
	*/

	public static Variable findVariable(String name) {
		for(Variable var : variables) {
			if(var.name.compareTo(name) == 0) {
				return var;
			}
		}
		return null;
	}

}
