package arg.tech.argql.translator.json;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import arg.tech.argql.patterns.ArgPattern;
import arg.tech.argql.patterns.ConclusionPattern;
import arg.tech.argql.patterns.DialoguePattern;
import arg.tech.argql.patterns.PathPattern;
import arg.tech.argql.patterns.PathPatternCollection;
import arg.tech.argql.patterns.PremisePattern;
import arg.tech.argql.patterns.PropositionPattern;
import arg.tech.argql.patterns.RelationPattern;
import arg.tech.argql.results.*;
import arg.tech.argql.translator.sparql.SPARQLTranslator;
import arg.tech.utils.Enums.Operation;
import arg.tech.utils.Enums.PremiseType;
import arg.tech.utils.Enums.ReturnValueType;
import arg.tech.utils.Enums.TranslationMode;
import arg.tech.utils.Enums.VariableType;

public class ResultManager {

	private static ArrayList<ResultRow> results;
	private static ArrayList<String> rowKeys;

	public ResultManager() {
		
	}

	public static void initialize() {
		results = new ArrayList<ResultRow>();
		rowKeys = new ArrayList<String>();
	}
	public static boolean addRow(ResultRow row) {
		return results.add(row);
	}

	public static int exists(String rowkey) {

		for (int indx = 0; indx < results.size(); indx++) {
			ResultRow row = results.get(indx);
			if (rowkey.compareTo(row.getKey()) == 0) {
				return indx;
			}
		}
		return -1;
	}

	public static ResultRow getRow(int index) {
		return results.get(index);
	}

	/*
	 * public boolean add(String key) { return rowKeys.add(key); }
	 */

	private static int searchByID(ArrayList<Argument> values, String id) {
		for (int i = 0; i < values.size(); i++) {
			Argument v = values.get(i);
			if (v.getId().compareTo(id) == 0) {
				return i;
			}
		}
		return -1;
	}

	private static ArgPattern updateArgValue(ArgPattern argpattern, int argValueIndex, Argument argument,
			Operation operation) {

		switch (operation) {
		case ADD:
			ArrayList<Argument> argvalues = argpattern.getValues();
			argvalues.add(argument);
			argpattern.setValues(argvalues);
			// argpatterns.set(apIndex, argpattern);
			break;

		case UPDATE:
			argvalues = argpattern.getValues();
			argvalues.set(argValueIndex, argument);
			argpattern.setValues(argvalues);
			// argpatterns.set(apIndex, argpattern);
			break;

		case NOTHING:
			break;
		}
		return argpattern;
	}

	private static PremisePattern updatePrempValue(PremisePattern premp, int premValueIndx, PropositionSet propset,
			Operation operation) {

		switch (operation) {
		case ADD:
			ArrayList<PropositionSet> premValues = premp.getValues();
			premValues.add(propset);
			premp.setValues(premValues);
			// argpatterns.set(apIndex, argpattern);
			break;

		case UPDATE:
			premValues = premp.getValues();
			premValues.set(premValueIndx, propset);
			premp.setValues(premValues);
			// argpatterns.set(apIndex, argpattern);
			break;

		case NOTHING:
			break;
		}
		return premp;
	}
	
	private static Path createPathValue(DialoguePattern dp, ResultSet results) {
		PathPatternCollection ppColection = dp.getPpCol();
		PathPattern longestPP = ppColection.getPathSet().get(ppColection.getPathSet().size() - 1);
		Path newPath = new Path();

		try {

			for (int i = 0; i < longestPP.relSequence.size(); i++) {
				RelationPattern rel = longestPP.relSequence.get(i);
				arg.tech.argql.results.Relation relation = new arg.tech.argql.results.Relation();
				ArgPattern ap1 = rel.getAp1();
				ArgPattern ap2 = rel.getAp2();

				String arg1URI = results.getString(ap1.getRaVariable().trim().replace("?", ""));
				String arg2URI = results.getString(ap2.getRaVariable().trim().replace("?", ""));
				Argument arg1Value = null;
				Argument arg2Value = null;
				if (arg1URI != null) {
					arg1Value = createArgValue(ap1, results);
				}
				if (arg2URI != null) {
					arg2Value = createArgValue(ap2, results);
				}
				relation.setSrcArg(arg1Value);
				relation.setDstArg(arg2Value);
				relation.setType(rel.getName());
				newPath.addToPath(relation);
			}
			longestPP.addValue(newPath);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return newPath;
	}

	private static PropositionSet createPremiseValue(PremisePattern premp, ResultSet results) {

		PropositionSet premValue=null;
		try {
			String argURI;
			argURI = results.getString(premp.getRaVar().trim().replace("?", ""));

			Operation operation = Operation.NOTHING;

			if (argURI != null) {
				argURI = argURI.replace("http://www.arg.dundee.ac.uk/AIFdb/nodes/", ""); //
				int premValueIndex = premp.valueExists(argURI);

					// if the value of the argument does not exist
				if (premValueIndex == -1) {
					premValue = new PropositionSet(argURI, premp.getPropPattern().getText());
					operation = Operation.ADD;
						// System.out.println("Arg with id "+ argURI + " does not exist");
				} else {
					premValue = premp.getValues().get(premValueIndex);
					operation = Operation.UPDATE;
				}
				
				String premiseID = results.getString(premp.getPropPattern().getURIVar().trim().replace("?", ""));
				String premiseValue = results.getString(premp.getPropPattern().getText().trim().replace("?", ""));

				premiseID = premiseID.replace("http://www.arg.dundee.ac.uk/AIFdb/nodes/", "");
				Proposition prop = new Proposition(premiseID, premiseValue);

				premValue.addProposition(prop);

				updatePrempValue(premp, premValueIndex, premValue, operation);
			}
			
			return premValue;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	private static Proposition createConclusionValue(ConclusionPattern conclp, ResultSet results) {
		
		Proposition conclValue=null;
		try {
			String argURI;
			argURI = results.getString(conclp.getArgPattern().getRaVariable().trim().replace("?", ""));

			if (argURI != null) {
				argURI = argURI.replace("http://www.arg.dundee.ac.uk/AIFdb/nodes/", ""); //
				int conclValueIndex = conclp.valueExists(argURI);

					// if the value of the argument does not exist
				if (conclValueIndex == -1) {
					String conclID = results.getString(conclp.getPropPattern().getURIVar().trim().replace("?", ""));
					String conclValueStr = results.getString(conclp.getPropPattern().getText().trim().replace("?", ""));
					conclID = conclID.replace("http://www.arg.dundee.ac.uk/AIFdb/nodes/", "");
				    conclValue = new Proposition(conclID, conclValueStr, argURI, conclp.getPropPattern().getText());
				} 
			}
			
			return conclValue;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	private static Argument createArgValue(ArgPattern argpattern, ResultSet results) {
		Operation operation = Operation.NOTHING;

		Argument argValue = null; 
		String argURI;
		try {
			argURI = results.getString(argpattern.getRaVariable().trim().replace("?", ""));

			if (argURI != null) {
				argURI = argURI.replace("http://www.arg.dundee.ac.uk/AIFdb/nodes/", ""); //
				int argValueIndex = searchByID(argpattern.getValues(), argURI);

				// if the value of the argument does not exist
				if (argValueIndex == -1) {
					argValue = new Argument(argURI, argpattern.getArgVar());
					operation = Operation.ADD;
					// System.out.println("Arg with id "+ argURI + " does not exist");
				} else {
					argValue = argpattern.getValues().get(argValueIndex);
				}

				if (argpattern.getPremisePattern().getType() == PremiseType.VARIABLE) {
					String premiseID = results.getString(
							argpattern.getPremisePattern().getPropPattern().getURIVar().trim().replace("?", ""));
					String premiseValue = results.getString(
							argpattern.getPremisePattern().getPropPattern().getText().trim().replace("?", ""));

					premiseID = premiseID.replace("http://www.arg.dundee.ac.uk/AIFdb/nodes/", "");
					Proposition prop = new Proposition(premiseID, premiseValue);

					argValue.premise.addProposition(prop);

					if (argValueIndex != -1)
						operation = Operation.UPDATE;
				} else {
					if (argValueIndex == -1) {
						ArrayList<PropositionPattern> premisePattern = argpattern.getPremisePattern().getProps();
						for (PropositionPattern pp : premisePattern) {
							String propId = results.getString(pp.getURIVar().trim().replace("?", ""));
							// String propValue = results.getString(pp.getText().trim().replace("?", ""));
							if (propId != null) { // if the actual value in the proposition pattern is found
								propId = propId.replace("http://www.arg.dundee.ac.uk/AIFdb/nodes/", "");
								Proposition proposition = new Proposition(propId, pp.getText());
								argValue.premise.props.add(proposition);
							} else {// if an equivalent proposition has been found..
								String propEqualID = results
										.getString(pp.getEquivPattern().getEqPropID().trim().replace("?", ""));
								String propEqualValue = results
										.getString(pp.getEquivPattern().getItextVar2().trim().replace("?", ""));

								propEqualID = propEqualID.replace("http://www.arg.dundee.ac.uk/AIFdb/nodes/", "");
								Proposition prop = new Proposition(propEqualID, propEqualValue);
								argValue.premise.addProposition(prop);
							}
						}
					} else
						operation = Operation.NOTHING;
				}

				if (argValueIndex == -1) {
					Proposition conclusion;

					if (!argpattern.getConclusionPattern().isVariable()) {
						String eqID = null;
						String eqValue = null;
						String conclusionID = results.getString(
								argpattern.getConclusionPattern().getPropPattern().getURIVar().trim().replace("?", ""));

						String conclusionValue = argpattern.getConclusionPattern().getPropPattern().getText();

						conclusionID = conclusionID.replace("http://www.arg.dundee.ac.uk/AIFdb/nodes/", "");

						if (SPARQLTranslator.translationMode == TranslationMode.Normal
								&& argpattern.getConclusionPattern().getPropPattern().getEquivPattern() != null) {
							eqID = results.getString(argpattern.getConclusionPattern().getPropPattern()
									.getEquivPattern().getEqPropID().trim().replace("?", ""));
							eqValue = results.getString(argpattern.getConclusionPattern().getPropPattern()
									.getEquivPattern().getItextVar2().trim().replace("?", ""));

						}
						if (eqID != null) {
							conclusion = new Proposition(eqID, eqValue);
						} else {
							conclusion = new Proposition(conclusionID, conclusionValue);
						}

					} else {
						String conclusionID = results.getString(
								argpattern.getConclusionPattern().getPropPattern().getURIVar().trim().replace("?", ""));
						String conclusionValue = results.getString(
								argpattern.getConclusionPattern().getPropPattern().getText().trim().replace("?", ""));
						conclusionID = conclusionID.replace("http://www.arg.dundee.ac.uk/AIFdb/nodes/", "");

						conclusion = new Proposition(conclusionID, conclusionValue);

					}

					argValue.setConclusion(conclusion);
				}

				updateArgValue(argpattern, argValueIndex, argValue, operation);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return argValue;
	}

	private static String createRowKey(ArrayList<DialoguePattern> dpList, ResultSet results) {
		StringBuilder rowKey = new StringBuilder();

		// in this for we create the rowKey to check if it already exists in the results
		for (int indx = 0; indx < dpList.size(); indx++) {
			DialoguePattern dp = (DialoguePattern) dpList.get(indx);
			try {
				if (dp.isArgPattern) {
					String argURI;

					argURI = results.getString(dp.getAp().getRaVariable().trim().replace("?", ""));

					if (argURI != null) {
						argURI = argURI.replace("http://www.arg.dundee.ac.uk/AIFdb/nodes/", "");
						rowKey.append(argURI);
					}
				} else if (dp.isPathPattern) {
					PathPatternCollection ppCol = dp.getPpCol();
					PathPattern longestPathPattern = ppCol.getPathSet().get(ppCol.getPathSet().size() - 1);

					int i;
					for (i = 0; i < longestPathPattern.getRelSequence().size(); i++) {
						RelationPattern rel = longestPathPattern.getRelSequence().get(i);
						String arg1URI = results.getString(rel.getAp1().getRaVariable().trim().replace("?", ""));
						if (arg1URI != null) {
							arg1URI = arg1URI.replace("http://www.arg.dundee.ac.uk/AIFdb/nodes/", "");
							rowKey.append(arg1URI);
							rowKey.append(rel.keyId());
						}
					}
					RelationPattern rel = longestPathPattern.getRelSequence().get(i - 1);
					String arg2URI = results.getString(rel.getAp2().getRaVariable().trim().replace("?", ""));
					arg2URI = arg2URI.replace("http://www.arg.dundee.ac.uk/AIFdb/nodes/", "");
					rowKey.append(arg2URI);
				}
				if (indx < dpList.size() - 1)
					rowKey.append(",");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return rowKey.toString();
	}

	private static ArgPattern findArgPattern(String varName) {
		for (DialoguePattern dp : SPARQLTranslator.dpList) {
			if (dp.isArgPattern && dp.getAp().getArgVar().compareTo(varName) == 0) {
				return dp.getAp();
			} else if (dp.isPathPattern) {
				PathPattern pp = dp.getPpCol().getPathSet().get(dp.getPpCol().getPathSet().size() - 1);
				ArgPattern firstAP = pp.getRelSequence().get(0).getAp1();
				ArgPattern lastAP = pp.getRelSequence().get(pp.getRelSequence().size() - 1).getAp2();
				if (firstAP.getArgVar().compareTo(varName) == 0) {
					return firstAP;
				}
				if (lastAP.getArgVar().compareTo(varName) == 0) {
					return lastAP;
				}
			}
		}
		return null;
	}

	private static PremisePattern findPremisePattern(String varName) {
		for (DialoguePattern dp : SPARQLTranslator.dpList) {
			if (dp.isArgPattern) {
				if (dp.getAp().getPremisePattern().getType() == PremiseType.VARIABLE
						&& dp.getAp().getPremisePattern().getPropPattern().getText().compareTo(varName) == 0) {
					return dp.getAp().getPremisePattern();
				}
			} else if (dp.isPathPattern) {
				PathPattern pp = dp.getPpCol().getPathSet().get(dp.getPpCol().getPathSet().size() - 1);
				ArgPattern firstAP = pp.getRelSequence().get(0).getAp1();
				ArgPattern lastAP = pp.getRelSequence().get(pp.getRelSequence().size() - 1).getAp2();
				if (firstAP.getPremisePattern().getType() == PremiseType.VARIABLE
						&& firstAP.getPremisePattern().getPropPattern().getText().compareTo(varName) == 0) {
					return firstAP.getPremisePattern();
				}
				if (lastAP.getPremisePattern().getType() == PremiseType.VARIABLE
						&& lastAP.getPremisePattern().getPropPattern().getText().compareTo(varName) == 0) {
					return lastAP.getPremisePattern();
				}
			}
		}
		return null;
	}

	private static ConclusionPattern findConclusionPAttern(String varName) {
		for (DialoguePattern dp : SPARQLTranslator.dpList) {
			if (dp.isArgPattern) {
				if (dp.getAp().getConclusionPattern().isVariable()
						&& dp.getAp().getConclusionPattern().getPropPattern().getText().compareTo(varName) == 0) {
					return dp.getAp().getConclusionPattern();
				}
			} else if (dp.isPathPattern) {
				PathPattern pp = dp.getPpCol().getPathSet().get(dp.getPpCol().getPathSet().size() - 1);
				ArgPattern firstAP = pp.getRelSequence().get(0).getAp1();
				ArgPattern lastAP = pp.getRelSequence().get(pp.getRelSequence().size() - 1).getAp2();
				if (firstAP.getConclusionPattern().isVariable()
						&& firstAP.getConclusionPattern().getPropPattern().getText().compareTo(varName) == 0) {
					return firstAP.getConclusionPattern();
				}
				if (lastAP.getConclusionPattern().isVariable()
						&& lastAP.getConclusionPattern().getPropPattern().getText().compareTo(varName) == 0) {
					return lastAP.getConclusionPattern();
				}
			}
		}
		return null;
	}

	private static DialoguePattern findDP(String var1, String var2) {
		for (DialoguePattern dp : SPARQLTranslator.dpList) {
			if (dp.isPathPattern) {
				PathPattern pp = dp.getPpCol().getPathSet().get(dp.getPpCol().getPathSet().size() - 1);
				ArgPattern firstAP = pp.getRelSequence().get(0).getAp1();
				ArgPattern lastAP = pp.getRelSequence().get(pp.getRelSequence().size() - 1).getAp2();
				if (firstAP.getArgVar().compareTo(var1) == 0 && lastAP.getArgVar().compareTo(var2) == 0) {
					return dp;
				}
			}
		}
		return null;
	}

	private static ArrayList<Object> listOfPatternsFromReturnVars() {
		ArrayList<Object> returnPatterns = new ArrayList<Object>();

		for (ReturnElement retElement : SPARQLTranslator.returnValues) {
			if (retElement.getType() == ReturnValueType.Variable) {
				Variable var = retElement.getVar();

				if (var.type == VariableType.ARGUMENT) {
					ArgPattern ap = findArgPattern(var.name);
					returnPatterns.add(ap);
				}
				if (var.type == VariableType.PREMISE) {
					PremisePattern premp = findPremisePattern(var.name);
					returnPatterns.add(premp);
				}
				if (var.type == VariableType.CONCLUSION) {
					ConclusionPattern conclp = findConclusionPAttern(var.name);
					returnPatterns.add(conclp);
				}
			} else if (retElement.getType() == ReturnValueType.Path) {
				DialoguePattern dp = findDP(retElement.getVar().name, retElement.getVar2().name);
				returnPatterns.add(dp);
			}
		}

		return returnPatterns;
	}

	public static String collectResults(ResultSet results, ArrayList<DialoguePattern> dpList) throws Exception {

		String resultStr = "";
		try {
			initialize();

			ArrayList<Object> returnElements = listOfPatternsFromReturnVars();

			while (results.next()) {

				try {
					String rowKey = createRowKey(dpList, results);

					int pos = exists(rowKey);
					ResultRow resRow;

					if (pos == -1) {
						resRow = new ResultRow(rowKey);
					} else {
						resRow = getRow(pos);
					}

					for (int indx = 0; indx < returnElements.size(); indx++) {

						if (returnElements.get(indx) instanceof ArgPattern) {

							ArgPattern argpattern = (ArgPattern) returnElements.get(indx);
							Argument argValue = createArgValue(argpattern, results);

							if (pos == -1) {
								// ResultValue newDialogue = new ResultValue(argValue);
								resRow.add(argValue);
							} else {
								resRow.setResultValue(indx, argValue);
							}

						} else if (returnElements.get(indx) instanceof DialoguePattern) {
							DialoguePattern dp = (DialoguePattern) returnElements.get(indx);
							Path newPath = createPathValue(dp, results);
							if (pos == -1) {
								resRow.add(newPath);
							} else {
								resRow.setResultValue(indx, newPath);
							}
						}

						else if (returnElements.get(indx) instanceof PremisePattern) {
							PremisePattern premp = (PremisePattern) returnElements.get(indx);
							PropositionSet premise = createPremiseValue(premp, results);
							if (pos == -1) {
								resRow.add(premise);
							} else {
								resRow.setResultValue(indx, premise);
							}
						}
						else if (returnElements.get(indx) instanceof ConclusionPattern) {
							ConclusionPattern conclp = (ConclusionPattern) returnElements.get(indx);
							Proposition premise = createConclusionValue(conclp, results);
							if (pos == -1) {
								resRow.add(premise);
							} else {
								resRow.setResultValue(indx, premise);
							}
						}
					}

					if (pos == -1)
						addRow(resRow);

				} catch (Exception e) {
					System.out.println(">>Error: " + e.getMessage());
					e.printStackTrace();
				}

			}
			resultStr = printResults();
			System.out.println(resultStr);

			// }
		} catch (

		Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			return (e.getMessage());
		}
		return resultStr;
	}



	private static String printResults() {
		StringBuilder str = new StringBuilder();

		for (int i = 0; i <= results.size() - 1; i++) {
			str.append("(" + (i + 1) + ") ");
			str.append(results.get(i).toString());
			str.append("\n");
		}

		return str.toString();
	}
}
