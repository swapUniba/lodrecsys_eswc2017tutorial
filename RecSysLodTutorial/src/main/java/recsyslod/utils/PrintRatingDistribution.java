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
 * Print the number of ratings for each user
 * 
 * First argument: ratings file
 * Second argument: output file 
 * 
 */
public class PrintRatingDistribution {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length == 2) {
            try {
                RatingsReader ratings = new RatingsReader(new File(args[0]));
                BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]));
                int prevUser = 0, count = 0;
                if (ratings.hasNext()) {
                    Rate rate = ratings.next();
                    prevUser = rate.getUser();
                    count = 1;
                }
                while (ratings.hasNext()) {
                    Rate rate = ratings.next();
                    if (prevUser == rate.getUser()) {
                        count++;
                    } else {
                        writer.append(String.valueOf(prevUser)).append("\t").append(String.valueOf(count));
                        writer.newLine();
                        count = 1;
                        prevUser = rate.getUser();
                    }

                }
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(PrintRatingDistribution.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
