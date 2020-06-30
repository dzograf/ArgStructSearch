package arg.tech.argql.canonical;

public class INode {
	private String uri = "";
	private String claimText = "";
	
	public INode(String uri, String claimText) {
		this.uri = uri;
		this.claimText = claimText;
	}

	public String getClaimText() {
		return claimText;
	}

	public void setClaimText(String claimText) {
		this.claimText = claimText;
	}

	
	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("\nURI: " + this.getUri());
		//str.append("\nClaim text: " + this.claimText);
		
		return str.toString();
	}
	
}
