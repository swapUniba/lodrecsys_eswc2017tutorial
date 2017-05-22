/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recsyslod.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import recsyslod.RatingsReader;
import recsyslod.data.Rate;

/**
 *
 * @author pierpaolo
 */
public class Convert2mymedialite {

    /**
     * 
     * Convert from M1M format to mymedialite format
     * 
     * First argument: M1M file
     * Second argument: Output file
     * 
     */
    public static void main(String[] args) {
        if (args.length == 2) {
            try {
                RatingsReader rr = new RatingsReader(new File(args[0]));
                BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]));
                while (rr.hasNext()) {
                    Rate r = rr.next();
                    writer.append(String.valueOf(r.getUser())).append("\t").append(String.valueOf(r.getItem())).append("\t").append(String.valueOf(r.getRate()));
                    writer.newLine();
                }
                writer.close();
            } catch (IOException ioex) {
                Logger.getLogger(Convert2mymedialite.class.getName()).log(Level.SEVERE, null, ioex);
            }
        }
    }

}
