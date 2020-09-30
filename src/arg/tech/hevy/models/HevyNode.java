package arg.tech.hevy.models;

import com.ibm.icu.impl.ICUCurrencyDisplayInfoProvider;

public abstract class HevyNode {
	public String ID;
	
	public HevyNode(String ID) {
		this.ID = ID;
	}

	public abstract String rdfEntry(String aifClassesBaseURI, String hevyClassesBaseURI, String aifNodesBaseURI, String hevyNodesBaseURI);
}
