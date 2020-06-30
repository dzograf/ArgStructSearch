package arg.tech.argql.results;

import java.sql.ResultSet;
import java.sql.SQLException;

import arg.tech.argql.translator.sparql.SPARQLTranslator;
import arg.tech.utils.Enums.TranslationMode;

public class Proposition extends ResultValue {
	public String value;
	public String ID;
	public String propVar = "";
	public String argID = "";

	public Proposition(String ID, String value) {
		super(ResultType.Proposition);
		this.ID = ID;
		if (SPARQLTranslator.translationMode == TranslationMode.Normal) {
			this.value = value;
		} else {
			try { 
				String uri = "http://www.arg.dundee.ac.uk/AIFdb/nodes/" + ID;
				String query = "select * from equivalences where uri=\"" + uri + "\" and canonicalelement=\"" + value
						+ "\";";
			//	System.out.println("\n==>>>>" + query);
				ResultSet rs = SPARQLTranslator.dbMngr.executeQuery(query);
 
				rs.next();
				this.value = rs.getString("claimText");
			} catch (SQLException e ) {
				this.value = "VALUE NOT SPECIFIED";
				e.printStackTrace();
			}
		}
	}
	
	public Proposition(String ID, String value, String argID, String propVar) {
		super(ResultType.Proposition);
		this.ID = ID;
		this.argID = argID;
		this.propVar = propVar;
		if (SPARQLTranslator.translationMode == TranslationMode.Normal) {
			this.value = value;
		} else {
			try { 
				String uri = "http://www.arg.dundee.ac.uk/AIFdb/nodes/" + ID;
				String query = "select * from equivalences where uri=\"" + uri + "\" and canonicalelement=\"" + value
						+ "\";";
			//	System.out.println("\n==>>>>" + query);
				ResultSet rs = SPARQLTranslator.dbMngr.executeQuery(query);
 
				rs.next();
				this.value = rs.getString("claimText");
			} catch (SQLException e ) {
				this.value = "VALUE NOT SPECIFIED";
				e.printStackTrace();
			}
		}
	}
	
 
	public String getArgID() {
		return argID;
	}

	public void setArgID(String argID) {
		this.argID = argID;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getPropVar() {
		return propVar;
	}

	public void setPropVar(String propVar) {
		this.propVar = propVar;
	}

	public String toString() {
		if(argID!="") {
			return this.propVar + ": " + getValue();
		}
		return getValue();
	}
}
