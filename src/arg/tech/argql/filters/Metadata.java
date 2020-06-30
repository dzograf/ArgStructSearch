package arg.tech.argql.filters;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;

public class Metadata {
	ArrayList<SimpleEntry<String, String>> dataArrayList;
	String elementURIVarString = "";

	public Metadata() {
		dataArrayList = new ArrayList<AbstractMap.SimpleEntry<String,String>>();
	}

	public Metadata(ArrayList<SimpleEntry<String, String>> data) {
		this.dataArrayList = data;
	}

	public ArrayList<SimpleEntry<String, String>> getDataArrayList() {
		return dataArrayList;
	}

	public void setDataArrayList(ArrayList<SimpleEntry<String, String>> dataArrayList) {
		this.dataArrayList = dataArrayList;
	}
	

	public String getElementURIVarString() {
		return elementURIVarString;
	}

	public void setElementURIVarString(String elementURIVarString) {
		this.elementURIVarString = elementURIVarString;
	}
	
	public void addSimpleEntry(AbstractMap.SimpleEntry<String, String> sEntry) {
		dataArrayList.add(sEntry);
	}

	public String getSparqlRepresentation() {
		StringBuilder stringBuilder = new StringBuilder();
		
		for (SimpleEntry<String, String> simpleEntry : dataArrayList) {
			
			if(simpleEntry.getKey().compareTo("tm") == 0) {
				stringBuilder.append(this.elementURIVarString + " argtech:creationDate " + simpleEntry.getValue() + ".\n");
			}
			
			if(simpleEntry.getKey().compareTo("sch") == 0) {
				//sparql for argschemes
			}
			
			if(simpleEntry.getKey().compareTo("auth") == 0) {
				//sparql for author
			}
		}
		
		return stringBuilder.toString();
	}
	
	public String toArgQLString() {

		StringBuilder stringBuilder = new StringBuilder();

		if (!this.dataArrayList.isEmpty()) {
			stringBuilder.append("[");

			Iterator<SimpleEntry<String, String>> iterator = dataArrayList.iterator();
			while (iterator.hasNext()) {
				SimpleEntry<String, String> simpleEntry = iterator.next();
				stringBuilder.append(simpleEntry.getKey() + ":" + "\"" + simpleEntry.getValue() + "\"");
				if (iterator.hasNext())
					stringBuilder.append(", ");
			}
			stringBuilder.append("]");
		}
		return stringBuilder.toString();
	}

}
