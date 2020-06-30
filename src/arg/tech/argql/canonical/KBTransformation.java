/*package arg.tech.argql.canonical;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//import java.sql.*;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFReader;
import org.apache.jena.rdf.model.RDFWriter;
import org.apache.jena.rdf.model.ResIterator;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.openrdf.rio.RDFFormat;

import arg.tech.utils.OntologyManager;

public class KBTransformation {

	static OntModel newmodel;
	static Model model;

	
	static int currentCanonicalElement = 0;
	
	public KBTransformation() {
		
	}
	
	private static ArrayList<Equivalence> transformEquivPairs(ArrayList<EquivPair> initial){
		ArrayList<Equivalence> newEquivs = new ArrayList<Equivalence>();
		for (int i=0; i<initial.size(); i++) {
			EquivPair eqp = initial.get(i);
			Equivalence eq = new Equivalence();
			eq.add(eqp.getI1());
			eq.add(eqp.getI2());
			
			newEquivs.add(eq);
		}
		
		return newEquivs;
	}
	
	private static boolean haveCommonElements(Equivalence eq1, Equivalence eq2) {
		for(INode i1 : eq1.getEquivalence()) {
			for(INode i2 : eq2.getEquivalence()) {
				if(i1.getUri().compareTo(i2.getUri()) == 0 ) {
					return true;
				}
			}
		}
		return false;
	}
	
	private static Equivalence merge(Equivalence eq1, Equivalence eq2) {
		Equivalence newEq = new Equivalence();
		
		newEq.getEquivalence().addAll(eq1.getEquivalence());
		for(INode inode : eq2.getEquivalence()) {
			if(!newEq.getEquivalence().contains(inode)) {
				newEq.add(inode);
			}
		}
		
		return newEq;
	}
	
	private static boolean existsEqs(ArrayList<Equivalence> equivs) {
		for(int i=0; i < equivs.size(); i++) {
			Equivalence eq1 = equivs.get(i);
			for(int j = i+1; j < equivs.size(); j++) {
				Equivalence eq2 = equivs.get(j);
				if(haveCommonElements(eq1, eq2)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	
	
	private static ArrayList<Equivalence> equivalenceClosure(ArrayList<Equivalence> equivalences){
		
		while(existsEqs(equivalences)) {
			for(int i=0; i < equivalences.size(); i++) {
				Equivalence eq1 = equivalences.get(i);
				for(int j = i+1; j < equivalences.size(); j++) {
					Equivalence eq2 = equivalences.get(j);
					if(haveCommonElements(eq1, eq2)) {
						Equivalence newEq = merge(eq1, eq2);
						equivalences.set(i, newEq);
						equivalences.remove(j);
					}
				}
			}
		}
		return equivalences;
	}
	
	private static int existsInEquivalence(ArrayList<Equivalence> equivs, String uri) {
		for(int i=0; i<equivs.size(); i++) {
			Equivalence eq = equivs.get(i);
			for(INode inode : eq.getEquivalence()) {
				if(inode.getUri().compareTo(uri) == 0) {
					return i;
				}
			}
		}
		return -1;
	}
	private static ArrayList<EquivPair> getEquivalences(){
		
		ArrayList<EquivPair> equivalences = new ArrayList<EquivPair>();
		
		String queryForEquivalences = "prefix rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n" + 
				"prefix aif:<http://www.arg.dundee.ac.uk/aif#>\r\n" + 
				"SELECT distinct ?i1, ?text1, ?i2, ?text2\r\n" + 
				"FROM <http://aif.db>\r\n" + 
				"WHERE {\r\n" + 
				"?i1 rdf:type aif:I-node.\r\n" + 
				"?i1 aif:claimText ?text1.\r\n" + 
				"?_ya1 aif:IllocutionaryContent ?i1.\r\n" + 
				"?_ya1 aif:Locution ?_loc1.\r\n" + 
				"?_loc1 rdf:type aif:L-node.\r\n" + 
				"?i2 rdf:type aif:I-node.\r\n" + 
				"?i2 aif:claimText ?text2.\r\n" + 
				"?_ya2 aif:IllocutionaryContent ?i2.\r\n" + 
				"?_ya2 aif:Locution ?_loc2.\r\n" + 
				"?_loc2 rdf:type aif:L-node.\r\n" + 
				"?_ta1 rdf:type aif:TA-node.\r\n" + 
				"?_loc1 aif:StartLocution ?_ta1.\r\n" + 
				"?_ta1 aif:EndLocution ?_loc2.\r\n" + 
				"?_ya3 aif:Anchor ?_ta1.\r\n" + 
				"?_ya3 aif:IllocutionaryContent ?_ma1.\r\n" + 
				"?_ma1 rdf:type aif:MA-node .\r\n" + 
				"}";
		try {
			ResultSet results = OntologyManager.executeSQLQuery(queryForEquivalences);
			
			
			while (results.next()) {
			
				String uri1 = results.getString("i1");
				String uri2 = results.getString("i2");
				String text1 = results.getString("text1");
				String text2 = results.getString("text2");
				
				INode i1 = new INode(uri1, text1);
				INode i2 = new INode(uri2, text2);
				
				EquivPair eq = new EquivPair(i1, i2);
				equivalences.add(eq);
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
			
			return equivalences;
	}
	
	
	private static int computeCanonicalElement( RelationalDatabaseManager dbmngr, ArrayList<Equivalence> equivalences,
			String uri, String claimText, int currentCanonicalElement, String foldename) {
		int iCanonicalElem=-1;
		String str;
		
		ResultSet rs = dbmngr.executeQuery("select * from equivalences where uri=\"" + uri+ "\";");
		try {
			if(rs.next()) {
				str = rs.getString("canonicalelement");
				iCanonicalElem = Integer.valueOf(str);
			} else {
				int position = existsInEquivalence(equivalences, uri);
				
				if(position != -1) {
					int canonical = equivalences.get(position).getCanonicalElement();
					if(canonical == -1) {
						iCanonicalElem = currentCanonicalElement;
						equivalences.get(position).setCanonicalElement(iCanonicalElem);
					} else {
						iCanonicalElem = equivalences.get(position).getCanonicalElement();
					}						
				} else {
					iCanonicalElem = currentCanonicalElement;
				}

				dbmngr.executeUpdateQuery("insert into equivalences values('"+ uri + "' , '" + claimText + "'," + iCanonicalElem + ", '" + foldename + "')");
			}
		} catch (Exception e) {
			System.out.println("ERROR IN METHOD computeCanonicalElement");
			e.printStackTrace();
		}
		
		return iCanonicalElem;	
	}
	
	
	public static void loadAIFdb() throws Exception {
		String dbPath = "E://Argument Base//AIFdb";
		
		String graph = "http://aif.db";
		
		File folder = new File(dbPath);
		File[] listOfFolders = folder.listFiles();
		
		//ArrayList<String> collection = new ArrayList<String>();
		
		 //OntologyManager.initializeSesame();
		
		for(int i=0; i<listOfFolders.length; i++) {
			File[] listOfFiles = listOfFolders[i].listFiles(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					return name.toLowerCase().endsWith(".rdf");
				}
				
			});
			
			for(int j=0; j<listOfFiles.length; j++) {
				//DatabaseManager.importFile(listOfFiles[j].getName(), graph);
			//	 OntologyManager.sesame.importFile( listOfFiles[j].getAbsolutePath(), RDFFormat.RDFXML, graph);
			}
		}
       // OntologyManager.terminateSesame(); 
	}
	
	private static void transformFile(File inputFile, String dstFolder, ArrayList<Equivalence> equivalences, RelationalDatabaseManager dbmngr, String graph) {
		//String inputFileName = "files/9678_MAnode.rdf";
	//	String graphCanonical = "http://aifdb.canonical";
		model = ModelFactory.createDefaultModel();
		RDFReader arp = model.getReader("RDF/XML");

		// read the RDF/XML file
		InputStream in;
		try {
			in = new FileInputStream(inputFile.getAbsolutePath());
			arp.read(model, in, "http://www.arg.dundee.ac.uk/aif#");

			newmodel = ModelFactory.createOntologyModel();
			RDFWriter writer = newmodel.getWriter("RDF/XML");
			writer.setProperty("showXmlDeclaration", true);
			writer.setProperty("showDoctypeDeclaration", true);
			
			model.removeNsPrefix("http");
			newmodel.setNsPrefixes(model.getNsPrefixMap());
			
			
			ResIterator iter = model.listSubjects();
			while (iter.hasNext()) {
				Resource subject = iter.next();
				boolean isINode = false;
				Property nameProperty = model.getProperty("http://www.w3.org/1999/02/22-rdf-syntax-ns#", "type");
				
				StmtIterator stmtIt = subject.listProperties(nameProperty);
				while(stmtIt.hasNext()) {
					Statement typeTriple = stmtIt.next();
					if(typeTriple.getObject().toString().compareTo("http://www.arg.dundee.ac.uk/aif#I-node") == 0) {
						isINode = true;
					}
				}
								
				if(isINode) {
					int iCanonicalElem;
					Property claimTextProp = model.getProperty("http://www.arg.dundee.ac.uk/aif#", "claimText");
					Statement cTextTriple = subject.getProperty(claimTextProp);
					String claimText = cTextTriple.getObject().toString();
					claimText = claimText.replace("\'", "\\'");
					
					iCanonicalElem = computeCanonicalElement(dbmngr, equivalences, subject.getURI(), claimText, currentCanonicalElement, dstFolder+inputFile.getName());

					StmtIterator propIter = subject.listProperties();
					while(propIter.hasNext()) {
						Statement stmt = propIter.next();
						Individual ind = newmodel.createIndividual(subject.toString(),null);
						if(stmt.getPredicate().getLocalName().compareTo("claimText") == 0) {
							ind.addProperty(stmt.getPredicate(), Integer.toString(iCanonicalElem));
						} else {
							ind.addProperty(stmt.getPredicate(), stmt.getObject());
						}
					}
					currentCanonicalElement++;
				} else {			
					StmtIterator propIter = subject.listProperties();
					while(propIter.hasNext()) {
						Statement stmt = propIter.next();
					
						Individual ind = newmodel.createIndividual(subject.toString(),null);
						ind.addProperty(stmt.getPredicate(), stmt.getObject());
					}
				}
					
			}

			FileOutputStream outputStream;

			String outputFilename = dstFolder.concat(inputFile.getName());
			File folder = new File(dstFolder);
			if(!folder.exists()) {
				folder.mkdir();
			}
			
			File fout = new File(outputFilename);
			if (fout.exists()) {
				fout.delete();
				fout = new File(outputFilename);
			}
			outputStream = new FileOutputStream(fout);
			writer.write(newmodel, outputStream, "");
			outputStream.close();
		
			System.out.println("\n\n File " + outputFilename + " created");
			Thread.sleep(50);
			OntologyManager.sesame.importFile(outputFilename, RDFFormat.RDFXML, graph);
			System.out.println("File " + outputFilename + " uploaded");
		} catch (FileNotFoundException e) {
			System.out.println("!!! ERROR in writing file.....");
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}
	
	public static void main(String[] args) {
		
		RelationalDatabaseManager dbmngr = new RelationalDatabaseManager();
		
		ArrayList<EquivPair> equivPairs = getEquivalences();
		ArrayList<Equivalence> equivalences = transformEquivPairs(equivPairs);
		equivalences = equivalenceClosure(equivalences);
		

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("files/equivalences.txt"));
			      
			for(Equivalence eq : equivalences) {
				writer.write(eq.toString());
				writer.write("\n=====================\n");
			}
			 writer.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		OntologyManager.initializeSesame();
		
		
	//	transformFile(new File("files/8.rdf"), "files/new/", equivalences, dbmngr, "http://9678.gr");
		///////////// READ AND PROCESS INPUT FILE ///////////

		
		String sourceDirectory = "E://Argument Base//AIFdb";
		String dstDirectory = "E://Argument Base//AIFdbCanonical//";
		
	
		
		
		File folder = new File(sourceDirectory);
		File[] listOfFolders = folder.listFiles();
		
		
		
		for(int i=0; i<listOfFolders.length; i++) {
			File[] listOfFiles = listOfFolders[i].listFiles(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					return name.toLowerCase().endsWith(".rdf");
				}
				
			});
			
			for(int j=0; j<listOfFiles.length; j++) {
				String dstFolder = dstDirectory.concat(listOfFolders[i].getName()+"//");
				transformFile(listOfFiles[j], dstFolder, equivalences, dbmngr, "http://aifdb.canonical");
				//DatabaseManager.importFile(listOfFiles[j].getName(), graph);
			//	 OntologyManager.sesame.importFile( listOfFiles[j].getAbsolutePath(), RDFFormat.RDFXML, graph);
			}
		}
		
		dbmngr.terminate();
		OntologyManager.terminateSesame(); 
			
		System.out.println("AIF db uploaded successfully..");
		
	}

}*/
