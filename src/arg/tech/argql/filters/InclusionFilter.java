package arg.tech.argql.filters;

import java.util.ArrayList;

import arg.tech.argql.patterns.ArgPattern;
import arg.tech.argql.patterns.PremisePattern;
import arg.tech.argql.patterns.PropositionPattern;
import arg.tech.argql.translator.sparql.SPARQLTranslator;
import arg.tech.utils.Enums.TranslationMode;

public class InclusionFilter extends Filter {
 



	public InclusionFilter(PremisePattern premPattern, ArgPattern ap2, String raVar) {
		super(premPattern, ap2, raVar);
		// TODO Auto-generated constructor stub
	}

	public InclusionFilter(PremisePattern premPattern, ArrayList<PropositionPattern> propset, String raVar) {
		super(premPattern, propset, raVar);
		// TODO Auto-generated constructor stub
	}

	public String getSparqlRepresentation_old() {
		StringBuilder str = new StringBuilder();
		String tempVar1 = SPARQLTranslator.generateTempVariable();
		String tempVar2 = SPARQLTranslator.generateTempVariable();
		if(isVar()) {
			str.append(tempVar1 + " argtech:Premise " + raVar + ".\n");
			str.append("FILTER NOT EXISTS\n" + "{\n" + tempVar2 + " argtech:Premise " + ap2.getRaVariable()  + ".\n"
					+ "FILTER(" + tempVar2 + " NOT IN ("  + tempVar1 + ")) .\n" + "}\n" +
					"FILTER (" + ap2.getRaVariable() + " != " + raVar + ")\n" );
		} else {
			if (SPARQLTranslator.translationMode == TranslationMode.Optimized) {
				for (PropositionPattern prop : propset) {
					str.append(
							prop.getSparqlRepresentation() + 
							prop.getURIVar() + " argtech:Premise " + raVar + ".\n");
				}
			} else {
				for (PropositionPattern prop : propset) {
					str.append(prop.getSparqlRepresentation() + 
							"{ \n" + prop.getURIVar() + " argtech:Premise " + raVar + ".\n" + 
							"} UNION { \n" + 
							prop.getEquivPattern().getSparqlRepresentation() + prop.getEquivPattern().getEqPropID() + " aif:Premise " + raVar + ".\n" + "}\n");
				}
			}
		}
		return str.toString();
	}

	public String getSparqlRepresentation() {
		StringBuilder str = new StringBuilder();
		String tempVar1 = SPARQLTranslator.generateTempVariable();
		String tempVar2 = SPARQLTranslator.generateTempVariable();
		if(isVar()) {
			str.append(tempVar1 + " argtech:Premise " + raVar + ".\n");
			str.append("FILTER NOT EXISTS\n" + "{\n" + tempVar2 + " aif:Premise " + ap2.getRaVariable()  + ".\n"
					+ "FILTER(" + tempVar2 + " NOT IN ("  + tempVar1 + ")) .\n" + "}\n" +
					"FILTER (" + ap2.getRaVariable() + " != " + raVar + ")\n" );
		} else {
			if (SPARQLTranslator.translationMode == TranslationMode.Optimized) {
				for (PropositionPattern prop : propset) {
					str.append(
							prop.getSparqlRepresentation() + 
							prop.getURIVar() + " argtech:Premise " + raVar + ".\n");
				}
			} else {
				for (PropositionPattern prop : propset) {
					str.append(prop.getSparqlRepresentation() + 
							 prop.getURIVar() + " argtech:Premise " + raVar + ".\n"); 
							
				}
			}
		}
		return str.toString();
	}

	
	@Override
	public String toArgQLString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("[/");
		if(propset!=null) {
			stringBuilder.append("{");
			for (int i=0; i< propset.size()-1; i++ ){
				stringBuilder.append(propset.get(i).toArgQLString() + ", ");
			}
			stringBuilder.append(propset.get(propset.size()-1).toArgQLString());
			stringBuilder.append("}");
		} 
			stringBuilder.append("]");
		return stringBuilder.toString();
	}
	
	

}
