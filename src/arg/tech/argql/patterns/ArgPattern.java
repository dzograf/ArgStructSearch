package arg.tech.argql.patterns;

import java.util.ArrayList;

import org.antlr.v4.codegen.model.chunk.ThisRulePropertyRef_ctx;

import arg.tech.argql.filters.Metadata;
import arg.tech.argql.results.*;
import arg.tech.utils.Enums.PremiseType;

public class ArgPattern extends Pattern{

	private String id = "";
	private String argVar = "";
	private String raVariable = "";
	private PremisePattern premisePattern;
	private ConclusionPattern conclusionPattern;	
	private ArrayList<Argument> values;
	private RelationPattern incomingRelPattern;
	private Metadata metadata;
	
	public ArgPattern() {
		values = new ArrayList<Argument>();
	}
	
	public ArgPattern(String argVar, String raVar, PremisePattern premisePattern, ConclusionPattern conclusionPattern) {
		this.argVar = argVar;
		this.raVariable = raVar;
		this.premisePattern = premisePattern;
		this.conclusionPattern = conclusionPattern;
		values = new ArrayList<Argument>();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getArgVar() {
		return argVar;
	}
	public void setArgVar(String argVar) {
		this.argVar = argVar;
	}

	public String getRaVariable() {
		return raVariable;
	}
	public void setRaVariable(String raVariable) {
		this.raVariable = raVariable;
	}
	public PremisePattern getPremisePattern() {
		return premisePattern;
	}
	public void setPremisePattern(PremisePattern premisePattern) {
		this.premisePattern = premisePattern;
	}
	public ConclusionPattern getConclusionPattern() {
		return conclusionPattern;
	}
	public void setConclusionPattern(ConclusionPattern conclusionPattern) {
		this.conclusionPattern = conclusionPattern;
	}
	
	public RelationPattern getIncomingRelPattern() {
		return incomingRelPattern;
	}

	public void setIncomingRelPattern(RelationPattern incomingRelPattern) {
		this.incomingRelPattern = incomingRelPattern;
	}

	public ArrayList<Argument> getValues() {
		return values;
	}
	
	public void setValues(ArrayList<Argument> values) {
		this.values = values;
	}
	
	public Metadata getMetadata() {
		return metadata;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	public String getSparqlRepresentation() {
		StringBuilder str = new StringBuilder();
		str.append(premisePattern.getSparqlRepresentation());
		str.append(conclusionPattern.getSparqlRepresentation());
		if(!(premisePattern.getType() == PremiseType.EMPTY))
			str.append(raVariable + " rdf:type argtech:RA-node.\r\n");
		if(this.metadata != null) {
			metadata.setElementURIVarString(raVariable);
			str.append(metadata.getSparqlRepresentation());
		}
		
		return str.toString();
	}
	
	public String toString() {
	
		StringBuilder s = new StringBuilder();
		
		s.append("ARG Pattern RA Var: " + raVariable + "\n");
		s.append(premisePattern.toString());

		s.append(conclusionPattern.toString());
		
		return s.toString();
	}
	
	public String toArgQLString() {
		StringBuilder stringBuilder = new StringBuilder();
		if(argVar != "") {
			stringBuilder.append(argVar + ": ");
		}
	//	stringBuilder.append("(" + this.id + ")");
		stringBuilder.append("<");
		stringBuilder.append(premisePattern.toArgQLString());
		stringBuilder.append(", ");
		stringBuilder.append(conclusionPattern.toArgQLString());
		stringBuilder.append(">");
		if(metadata!=null)
			stringBuilder.append(metadata.toArgQLString());
		
		return stringBuilder.toString();
	}
}
