package arg.tech.argql.translator.json;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Edge {

	private String edgeID;
	private Node fromNode;
	private Node toNode;
	
	public Edge() {}
	
	public Edge(String edgeID, Node fromNode, Node toNode) {
		this.edgeID = edgeID;
		this.fromNode = fromNode;
		this.toNode = toNode;
	}

	public String getEdgeID() {
		return edgeID;
	}

	public void setEdgeID(String edgeID) {
		this.edgeID = edgeID;
	}

	public Node getFromNode() {
		return fromNode;
	}

	public void setFromNode(Node fromNode) {
		this.fromNode = fromNode;
	}

	public Node getToNode() {
		return toNode;
	}

	public void setToNode(Node toNode) {
		this.toNode = toNode;
	}
	
	public String toString() {
		return "EdgeID: " + this.edgeID +
				"\nFrom Node: " +this.fromNode.getNodeID()+
				"\nTo Node: " + this.toNode.getNodeID();
	}
}
