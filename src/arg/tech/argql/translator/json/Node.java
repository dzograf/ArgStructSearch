package arg.tech.argql.translator.json;

import java.util.ArrayList;


public class Node {
	private String nodeID = "";
	private String text = "";
	private String type = "";
	private String timestamp = "";
	private String scheme = "";
	private String uriVar = "";
	
	private ArrayList<Edge> incomingEdges;
	private ArrayList<Edge> outgoingEdges;
	
	public Node() {}
	
	public Node(String nodeID, String text, String type, String scheme, String timestamp) {
		this.nodeID = nodeID;
		this.text = text;
		this.type = type;
		this.scheme = scheme;
		this.timestamp = timestamp;
		incomingEdges = new ArrayList<Edge>();
		outgoingEdges = new ArrayList<Edge>();
	}

	public String getNodeID() {
		return nodeID;
	}

	public void setNodeID(String nodeID) {
		this.nodeID = nodeID;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getScheme() {
		return scheme;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	
	public ArrayList<Edge> getIncomingEdges() {
		return incomingEdges;
	}

	public void setIncomingEdges(ArrayList<Edge> incomingEdges) {
		this.incomingEdges = incomingEdges;
	}

	public ArrayList<Edge> getOutgoingEdges() {
		return outgoingEdges;
	}

	public void setOutgoingEdges(ArrayList<Edge> outgoingEdges) {
		this.outgoingEdges = outgoingEdges;
	}
	
	public String getUriVar() {
		return uriVar;
	}

	public void setUriVar(String uriVar) {
		this.uriVar = uriVar;
	}

	public boolean addIncomingEdge(Edge edge) {
		return incomingEdges.add(edge);
	}
	
	public boolean addOutgoingEdge(Edge edge) {
		return outgoingEdges.add(edge);
	}

	public String toString() {
		
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append("ID: " + this.nodeID +
				"\nText: " + this.text + 
				"\nType: " + this.type + 
				"\nTimestamp: " +this.timestamp);
		stringBuilder.append("\nINCOMING Nodes\n");
		for (Edge edge : incomingEdges) {
			stringBuilder.append(edge.getFromNode().nodeID+"\n");
		}
		stringBuilder.append("\nOUTGOING Nodes\n");
		for (Edge edge : outgoingEdges) {
			stringBuilder.append(edge.getToNode().nodeID + "\n");
		}
		return stringBuilder.toString();
	}
}
