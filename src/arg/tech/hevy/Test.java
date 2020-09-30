package arg.tech.hevy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import arg.tech.data.DatabaseManager;

public class Test {
	
	private static void consumeAPI(String jsonString, URL url) {

		try {

		//	URL url = new URL("http://tomcat.arg.tech/ArgStructSearch/search/query/exec/");
			
		//	URL url = new URL("http://localhost:8080/ArgStructSearch/search/hevy/exec/");
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setDoOutput(true);
			
			OutputStream os = conn.getOutputStream();
		    os.write(jsonString.getBytes());
		    os.flush();
		    os.close();
			
			
			
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP Error code : " + conn.getResponseCode());
			}
			InputStreamReader in = new InputStreamReader(conn.getInputStream());
			BufferedReader br = new BufferedReader(in);
			String output;
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}
			conn.disconnect();

		} catch (Exception e) {
			System.out.println("-->Exception in NetClientGet:- " + e);
		}

	}
	
	
	private static void queryHevy() {
		try {
			FileReader reader = new FileReader("./files/hevy/examples/query.json");
			StringBuilder str = new  StringBuilder();
			int i; 
		    while ((i=reader.read()) != -1) 
		      str.append((char) i);
		    
		    String jsonString = str.toString();
		    URL url = new URL("http://localhost:8080/ArgStructSearch/search/hevy/exec/");
		  //  URL url = new URL("http://tomcat.arg.tech/ArgStructSearch/search/hevy/exec/"); 
		    consumeAPI(jsonString, url);
		    reader.close();
		  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	} 
	
	
	private static void loadFile() {
		JSONParser parser = new JSONParser();
		try {
			URL url = new URL("http://localhost:8080/ArgStructSearch/search/hevy/upload/");
		//	URL url = new URL("http://tomcat.arg.tech/ArgStructSearch/search/hevy/upload/");
			Object obj = parser.parse(new FileReader("./files/hevy/examples/Kakadu_event_data.json"));
			JSONObject jsonObject = (JSONObject) obj;
			
			consumeAPI(jsonObject.toJSONString(), url);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

	public static void main(String[] args) {
		
	/*	try {
			DatabaseManager.importFile("./files/hevy/examples/18913.rdf", "http://18913");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	//	loadFile();
		queryHevy();
		
	}

}
