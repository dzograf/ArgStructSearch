package arg.tech.ws;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.jws.WebService;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import arg.tech.data.DatabaseManager;

@Path("/virtuoso/")
@WebService(endpointInterface = "arg.tech.ws.VirtuosoDBService")
public class VirtuosoDBServiceImpl implements VirtuosoDBService {

	

	@POST
	@Path("/load")
	@Produces("application/json")
	@Override
	public Response loadNodeSet(String nodesetID) {
		// TODO Auto-generated method stub
		
		String downloadUrlStr = new String("http://www.aifdb.org/rdf/");

		try {
			
			// DOWNLOAD 
			String destination = nodesetID + ".rdf";
			File downloadFile = new File(destination);
			if(downloadFile.exists()) {
				downloadFile.delete();
			}
			
			String downloadUrl = downloadUrlStr + nodesetID;
			URL url = new URL(downloadUrl);

			FileUtils.copyURLToFile(url, downloadFile);

			
			//UPLOAD
			
			DatabaseManager.importFile(destination, "http://"+nodesetID);
			
			downloadFile.delete();
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(500).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "*").header("Access-Control-Allow-Headers", "*").build();
		}

		
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "*").header("Access-Control-Allow-Headers", "*").build();
	}

}
