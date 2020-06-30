package arg.tech.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FilenameUtils;
import org.openrdf.rio.RDFFormat;

import gr.forth.ics.virtuoso.JDBCVirtuosoRep;
import gr.forth.ics.virtuoso.SesameVirtRep;


public class DatabaseManager {

	public static SesameVirtRep sesame;
	public static JDBCVirtuosoRep jdbc;
	
	private static String virtuosoProperties = "virtuoso.properties";
	private static String virtuosoConfigString = "Repository_IP=virtuoso\n" + 
      		"Repository_Username=dba\n" + 
      		"Repository_Password=dba\n" + 
      		"Repository_Port=1111";
	
 /*  private static String virtuosoConfigString = "Repository_IP= 134.36.36.88\n" + 
		"Repository_Username=dba\n" + 
		"Repository_Password=eDu9xeSe\n" + 
		"Repository_Port=1111";*/

	public static SesameVirtRep getSesame() {
		return sesame;
	}

	public static void setSesame(SesameVirtRep sesame) {
		DatabaseManager.sesame = sesame;
	}

	public static void initializeSesame() {
		try {

			Properties prop = new Properties();
			 FileWriter myWriter = new FileWriter(virtuosoProperties);

	      
			  myWriter.write(virtuosoConfigString);
		      myWriter.close();
			

			prop.load(new FileInputStream("virtuoso.properties"));
			sesame = new SesameVirtRep(prop);
		} catch (Exception ex) {
			Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static void terminateSesame() {
		try {
			if (sesame != null)
				sesame.terminate();
		} catch (Exception ex) {
			Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static void initializeJDBC() {
		try {
			Properties prop = new Properties();
			
			 FileWriter myWriter = new FileWriter(virtuosoProperties);
		      
		      myWriter.write(virtuosoConfigString);
		      myWriter.close();
		
		      prop.load(new FileInputStream("virtuoso.properties"));
		      jdbc = new JDBCVirtuosoRep(prop);
		} catch (Exception ex) {
			Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static void terminateJDBC() {
		try {
			if (jdbc != null)
				jdbc.terminate();
		} catch (Exception ex) {
			Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static void importFile(String filename, String graph) throws Exception {
		initializeSesame();

		sesame.importFile(filename, RDFFormat.RDFXML, graph);
		sesame.terminate();
	}

	public static void importRepository(String repository) throws Exception {
		//String graph = "http://aif.db";

		File folder = new File(repository);
		File[] listOfFiles = folder.listFiles();

		initializeSesame();

		for (int j = 0; j < listOfFiles.length; j++) {
			String filenameString = listOfFiles[j].getName();
			System.out.println("Importing file: " + listOfFiles[j].getAbsolutePath());
			
			String graph = "http://" + FilenameUtils.removeExtension(filenameString);
			System.out.println("GRAPH: "+ graph);
			sesame.importFile(listOfFiles[j].getAbsolutePath(), RDFFormat.RDFXML, graph);
			System.out.println("Imported");
		}
		 
		
		terminateSesame();
	}

	public static ResultSet executeSparqlQuery(String query) throws Exception {
		ResultSet results = null;
		initializeJDBC();
		results = jdbc.executeSparqlQuery(query, true);
		// terminateJDBC();

		return results;
	}

}
