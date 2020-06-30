package arg.tech.argql.results;

import java.util.ArrayList;

public class ResultRow {
	
	private ArrayList<ResultValue> row;
	private String key;
	
	public ResultRow(String key) {
		this.key = key;
		row = new ArrayList<ResultValue>();
	}

	public ArrayList<ResultValue> getRow() {
		return row;
	}

	public ResultValue getResultValue(int index) {
		return row.get(index);
	}
	
	public void setResultValue(int index, ResultValue resValue) {
		row.set(index, resValue);
	}
	
	public void setRow(ArrayList<ResultValue> row) {
		this.row = row;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	public void add(ResultValue resultValue) {
		row.add(resultValue); 
	}
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("[");
		for (int i = 0; i < row.size(); i++) {
			ResultValue resValue = row.get(i);
			if(resValue.getType() == ResultType.Argument) {
				Argument value = (Argument) resValue;
				str.append(value.toString());
			}
			
			if(resValue.getType() == ResultType.Path) {
				Path path = (Path) resValue;
				str.append(path.toString());
			}
			
			if(resValue.getType() == ResultType.PropositionSet) {
				PropositionSet path = (PropositionSet) resValue;
				str.append(path.toString());
			}
			
			if(resValue.getType() == ResultType.Proposition) {
				Proposition path = (Proposition) resValue;
				str.append(path.toString());
			}
			if(i < row.size()-1)
				str.append(",\n        ");
		}
		str.append("]\n");
		
		return str.toString();
	}

}
