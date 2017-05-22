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
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import recsyslod.RatingsReader;
import recsyslod.data.Rate;

/**
 *
 * 
 */
public class RandomSampling {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length == 3) {
            try {
                RatingsReader ratings = new RatingsReader(new File(args[0]));
                Set<Integer> userSet = new HashSet<>();
                while (ratings.hasNext()) {
                    userSet.add(ratings.next().getUser());
                }
                List<Integer> list = new ArrayList<>(userSet);
                Collections.shuffle(list);
                int newSize = Math.round((float) list.size() * Float.parseFloat(args[2]));
                userSet = new HashSet<>(list.subList(0, newSize));
                BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]));
                ratings = new RatingsReader(new File(args[0]));
                while (ratings.hasNext()) {
                    Rate rate = ratings.next();
                    if (userSet.contains(rate.getUser())) {
                        writer.write(rate.outString());
                        writer.newLine();
                    }
                }
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(RandomSampling.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
