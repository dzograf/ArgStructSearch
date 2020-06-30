package arg.tech.argql.filters;

import java.util.ArrayList;
import java.util.Iterator;

import arg.tech.argql.patterns.ArgPattern;
import arg.tech.argql.patterns.EquivalencePattern;
import arg.tech.argql.patterns.PremisePattern;
import arg.tech.argql.patterns.PropositionPattern;
import arg.tech.argql.translator.sparql.SPARQLTranslator;
import arg.tech.utils.Enums.TranslationMode;

public class JoinFilter extends Filter {

	public JoinFilter(PremisePattern premPattern, ArgPattern ap2, String raVar) {
		super(premPattern, ap2, raVar);
		// TODO Auto-generated constructor stub
	}

	public JoinFilter(PremisePattern premPattern, ArrayList<PropositionPattern> propset, String raVar) {
		super(premPattern, propset, raVar);
		// TODO Auto-generated constructor stub
	}

	public String getSparqlRepresentation() {
		StringBuilder str = new StringBuilder();

		if (isVar()) {
			if (SPARQLTranslator.translationMode == TranslationMode.Normal) {
				str.append("{\n");
				str.append(ap2.getPremisePattern().getPropPattern().getURIVar() + " argtech:Premise " + raVar + ".\n");
				str.append("} UNION { \n");
				str.append(premPattern.getPropPattern().getURIVar() + " argtech:Premise "
						+ ap2.getRaVariable() + ".\n");
				str.append("} UNION {\n");
				EquivalencePattern equiv = SPARQLTranslator.newEquivalenceCondition(
						ap2.getPremisePattern().getPropPattern().getURIVar(),
						premPattern.getPropPattern().getURIVar());
				str.append(equiv.getSparqlRepresentation());
				str.append("}\n");
			} else {
				str.append("FILTER(" + premPattern.getPropPattern().getText() + " = "
						+ ap2.getPremisePattern().getPropPattern().getText() + ")\n");
			}
			str.append("FILTER(" + raVar + " != " + ap2.getRaVariable() + ")\n");
		} else {

				Iterator<PropositionPattern> iter = propset.iterator();
				while (iter.hasNext()) {
					PropositionPattern iNode = iter.next();

					if (SPARQLTranslator.translationMode == TranslationMode.Normal) {
						str.append("{\n" + iNode.getSparqlRepresentation() + "{\n" + iNode.getURIVar()
								+ " argtech:Premise " + raVar + ".\n" + "} UNION {\n"
								+ iNode.getEquivPattern().getSparqlRepresentation()
								+ iNode.getEquivPattern().getEqPropID() + " argtech:Premise " + raVar + ".\n" + "}\n"
								+ "}");
					} else {
						str.append("{\n" + iNode.getSparqlRepresentation() + iNode.getURIVar()
								+ " argtech:Premise " + raVar + ".\n " + "}");
					}
					if (iter.hasNext())
						str.append(" UNION\n");
					else
						str.append("\n");
				}
			} 
		return str.toString();
	}

	@Override
	public String toArgQLString() {
		// TODO Auto-generated method stub
		return null;
	}
}
