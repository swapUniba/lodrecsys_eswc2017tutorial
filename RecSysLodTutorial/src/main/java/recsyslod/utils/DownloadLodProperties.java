/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recsyslod.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.RDFNode;

/**
 *
 * Download dbpedia properties
 * 
 * First argument: mapping file
 * Second argument: output file
 * 
 */
public class DownloadLodProperties {

    private static final Logger LOG = Logger.getLogger(DownloadLodProperties.class.getName());

    private static final String ENDPOINT = "https://dbpedia.org/sparql";

    private static List<Pair<String, String>> getProperties(String uri) {
        List<Pair<String, String>> results = new ArrayList<>();
        String sparqlQuery = "select ?r ?y where {<" + uri + "> ?r ?y}";
        //System.out.println(sparqlQuery);
        QueryExecution e = QueryExecutionFactory.sparqlService(ENDPOINT, sparqlQuery);
        ResultSet rs = e.execSelect();
        while (rs.hasNext()) {
            QuerySolution nextSolution = rs.nextSolution();
            RDFNode ynode = nextSolution.get("y");
            if (ynode.isResource()) {
                results.add(Pair.of(nextSolution.getResource("r").getURI(), nextSolution.getResource("y").getURI()));
            } else {
                results.add(Pair.of(nextSolution.getResource("r").getURI(), nextSolution.getLiteral("y").getString().replaceAll("\\n+", " ")));
            }
        }
        e.close();
        return results;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length == 2) {
            try {
                int c = 0;
                System.out.println();
                BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]));
                BufferedReader reader = new BufferedReader(new FileReader(args[0]));
                while (reader.ready()) {
                    String[] split = reader.readLine().split("\t");
                    String id = split[0];
                    String uri = split[2];
                    List<Pair<String, String>> properties = getProperties(uri);
                    for (Pair<String, String> p : properties) {
                        writer.append(id).append("\t").append(p.getLeft()).append("\t").append(p.getRight());
                        writer.newLine();
                    }
                    c++;
                    if (c % 10 == 0) {
                        System.out.print(".");
                        if (c % 1000 == 0) {
                            System.out.println("." + c);
                        }
                    }
                }
                reader.close();
                writer.close();
            } catch (Exception ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

}
