package arg.tech.hevy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.sound.midi.VoiceStatus;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.stringtemplate.v4.compiler.STParser.andConditional_return;
import org.stringtemplate.v4.compiler.STParser.ifstat_return;

import arg.tech.argql.translator.json.Node;
import arg.tech.hevy.models.Event;
import arg.tech.hevy.models.EventDescription;
import arg.tech.hevy.models.HevyNode;
import arg.tech.hevy.models.Locution;
import arg.tech.hevy.models.LocutionEvent;

public class HevyJSONtoRDF {

	private static String aifClassesBaseURI = "&http;www.arg.tech/aif#";
	private static String aifNodesBaseURI = "&http;www.aifdb.org/nodes/";
	private static String hevyClassesBaseURI = "&http;www.arg.tech/hevy#";
	private static String hevyNodesBaseURI = "&http;www.arg.tech/hevy/nodes/";

	public static ArrayList<HevyNode> parseJSON(JSONObject jsonObject) {

		JSONArray nodesJSON = (JSONArray) jsonObject.get("nodes");
		JSONArray edgesJSON = (JSONArray) jsonObject.get("edges");

		ArrayList<HevyNode> nodes = new ArrayList<HevyNode>();

		Iterator<JSONObject> iterator = nodesJSON.iterator();
		while (iterator.hasNext()) {
			JSONObject obj = iterator.next();
			String nodeIDString = "";
			if (obj.get("id") != null) {
				nodeIDString = obj.get("id").toString();
			}
			String textString = "";
			if (obj.get("text") != null)
				textString = obj.get("text").toString();

			String typeString = "";
			if (obj.get("type") != null) {
				typeString = obj.get("type").toString();
			}

			if (typeString.compareTo("Event") == 0 || typeString.compareTo("LocutionEvent") == 0) {

				String name = "";
				if(obj.get("name") != null) {
					name = obj.get("name").toString();
				}
				
				String atTime = "";
				if (obj.get("atTime") != null) {
					atTime = obj.get("atTime").toString();
					DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
					LocalDateTime dateTime = LocalDateTime.from(f.parse(atTime));
				}

				String atPlace = "";
				if (obj.get("atPlace") != null) {
					atPlace = obj.get("atPlace").toString();
				}

				String inSpace = "";
				if (obj.get("inSpace") != null) {
					inSpace = obj.get("inSpace").toString();
				}

				String circa = "";
				if (obj.get("circa") != null) {
					circa = obj.get("circa").toString();
				}

				String illustrate = "";
				if (obj.get("illustrate") != null) {
					illustrate = obj.get("illustrate").toString();
				}

				String involved = "";
				if (obj.get("involved") != null) {
					involved = obj.get("involved").toString();
				}

				String involvedAgent = "";
				if (obj.get("involvedAgent") != null) {
					involvedAgent = obj.get("involvedAgent").toString();
				}

				if (typeString.compareTo("Event") == 0) {
					Event event = new Event(nodeIDString, name, atTime, atPlace, inSpace, circa, illustrate,
							involved, involvedAgent);
					nodes.add(event);
				} else {
					LocutionEvent locutionEvent = new LocutionEvent(nodeIDString, name, atTime, atPlace, inSpace,
							circa, illustrate, involved, involvedAgent);
					nodes.add(locutionEvent);
				}
			} else if (typeString.compareTo("Event Description") == 0) {
				EventDescription eventDescription = new EventDescription(nodeIDString);
				eventDescription.text = textString;
				nodes.add(eventDescription);
			}
		}

		Iterator<JSONObject> iter = edgesJSON.iterator();
		while (iter.hasNext()) {
			JSONObject obj = iter.next();
			String edgeIDString = "";
			if (obj.get("id") != null)
				edgeIDString = obj.get("id").toString();

			String fromID = "";
			if (obj.get("from") != null)
				fromID = obj.get("from").toString();

			String toID = "";
			if (obj.get("to") != null)
				toID = obj.get("to").toString();

			String edgeType = "";
			if (obj.get("text") != null && obj.get("text").toString().compareTo("describes") == 0) {

				int fromNodePos = findNode(nodes, fromID);
				int toNodePos = findNode(nodes, toID);

				if (fromNodePos != -1 && toNodePos != -1) {
					EventDescription eventDescription = (EventDescription) nodes.get(fromNodePos);
					Event event = (Event) nodes.get(toNodePos);
					if (eventDescription.type.compareTo("EventDescription") == 0) {
						eventDescription.setDescribesEvent(event);
						nodes.set(fromNodePos, eventDescription);
					}
				}

				if (fromNodePos == -1 && toNodePos != -1) {

					Locution locution = new Locution(fromID);
					LocutionEvent locutionEvent = (LocutionEvent) nodes.get(toNodePos);
					locution.setDescribesEvent(locutionEvent);
					nodes.add(locution);

				} else {
				}
			}

		}
		return nodes;

	}

	private static int findNode(ArrayList<HevyNode> nodes, String id) {
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i).ID.compareTo(id) == 0) {
				return i;
			}
		}
		return -1;
	}

	public static void saveToRDF(ArrayList<HevyNode> nodes, String filename) {
		FileWriter fw;
		try {
			fw = new FileWriter(filename);

			fw.write("<?xml version=\"1.0\"?>\n" + "\n" + "<!DOCTYPE rdf:RDF [\n" + "    <!ENTITY http \"http://\" >\n"
					+ "    <!ENTITY www \"http://www.ArgOWL.org#\" >\n"
					+ "    <!ENTITY owl \"http://www.w3.org/2002/07/owl#\" >\n"
					+ "    <!ENTITY aif \"http://www.arg.tech/aif#\" >\n"
					+ "    <!ENTITY hevy \"http://www.arg.tech/hevy#\" >\n"
					+ "    <!ENTITY xsd \"http://www.w3.org/2001/XMLSchema#\" >\n"
					+ "    <!ENTITY rdfs \"http://www.w3.org/2000/01/rdf-schema#\" >\n"
					+ "    <!ENTITY rdf \"http://www.w3.org/1999/02/22-rdf-syntax-ns#\" >\n" + "]>\n\n");

			fw.write("<rdf:RDF xmlns=\"&http;www.w3.org/2002/07/owl#\"\n" + "     xml:base=\"" + hevyClassesBaseURI
					+ "\"\n" + "     xmlns:rdfs=\"&http;www.w3.org/2000/01/rdf-schema#\"\n"
					+ "     xmlns:http=\"http://\"\n" + "     xmlns:www=\"&http;www.ArgOWL.org#\"\n"
					+ "     xmlns:owl=\"&http;www.w3.org/2002/07/owl#\"\n" 
					+ "     xmlns:xsd=\"&http;www.w3.org/2001/XMLSchema#\"\n" 
					+ "     xmlns:rdf=\"&http;www.w3.org/1999/02/22-rdf-syntax-ns#\"\n" 
					+ "     xmlns:aif=\"" + aifClassesBaseURI + "\"\n" 
					+ "     xmlns:hevy=\"" + hevyClassesBaseURI + "\">\n\n" 
					
					+ "   <Ontology rdf:about=\"" + hevyClassesBaseURI + "\">\n"
					+ "        <www:createdBy rdf:datatype=\"&http;www.w3.org/2001/XMLSchema#string\">Dimitra Zografistou</www:createdBy>\n"
					+ "        <rdfs:comment rdf:datatype=\"&http;www.w3.org/2001/XMLSchema#string\"> Hevy Ontology </rdfs:comment>\n"
					+ "        <versionInfo>version 1.0</versionInfo>\n" + "    </Ontology>\n\n");

			for (HevyNode hevyNode : nodes) {
				fw.write("    ");
				fw.write(hevyNode.rdfEntry(aifClassesBaseURI, hevyClassesBaseURI, aifNodesBaseURI, hevyNodesBaseURI));
				fw.write("\n\n");
			}
			fw.write("</rdf:RDF>");

			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String args[]) {
		JSONParser parser = new JSONParser();

		try {
			Object obj = parser.parse(new FileReader("./files/hevy/examples/kaka_final_file.json"));
			JSONObject jsonObject = (JSONObject) obj;
			ArrayList<HevyNode> nodes = parseJSON(jsonObject);

			saveToRDF(nodes, "./files/hevy/examples/test_hevy.rdf");
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
}
