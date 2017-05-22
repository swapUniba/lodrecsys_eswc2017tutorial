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
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Convert dbpedia properties from tsv format to mymedialite attribute file
 * 
 * First argument: dbpedia properties file
 * Second argument: Output file
 * 
 */
public class CreateItemAttribute {

    private static final String[] FILTER_PROPERTIES = new String[]{"http://www.w3.org/2000/01/rdf-schema#label", "http://www.w3.org/2000/01/rdf-schema#comment", "http://dbpedia.org/ontology/abstract"};

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length == 2) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(args[0]));
                BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]));
                Map<String, Integer> dict = new HashMap<>();
                Set<String> filterSet = new HashSet<>(Arrays.asList(FILTER_PROPERTIES));
                int id = 0;
                while (reader.ready()) {
                    String line = reader.readLine();
                    String[] split = line.split("\t");
                    if (split.length == 3) {
                        if (!filterSet.contains(split[1])) {
                            String v = split[1] + ":" + split[2];
                            Integer fid = dict.get(v);
                            if (fid == null) {
                                fid = id;
                                dict.put(v, fid);
                                id++;
                            }
                            writer.append(split[0]).append("\t").append(fid.toString());
                            writer.newLine();
                        }
                    } else {
                        Logger.getLogger(CreateItemAttribute.class.getName()).log(Level.WARNING, "WRONG LINE: " + line);
                    }
                }
                reader.close();
                writer.close();
            } catch (IOException ioex) {
                Logger.getLogger(CreateItemAttribute.class.getName()).log(Level.SEVERE, null, ioex);
            }
        }
    }

}
