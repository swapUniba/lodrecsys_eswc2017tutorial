
package recsyslod.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import recsyslod.RatingsReader;
import recsyslod.data.Rate;

/**
 *
 * @author pierpaolo
 */
public class CutUserByRatings {

    /**
     * 
     * Filter users that have a number of ratings less than a specified threshold
     * 
     * First argument: ratings file
     * Second argument: output file
     * Third argument: threshold
     * 
     */
    public static void main(String[] args) {
        if (args.length == 3) {
            try {
                RatingsReader ratings = new RatingsReader(new File(args[0]));
                BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]));
                int cut = Integer.parseInt(args[2]);
                int prevUser = 0;
                List<Rate> list = new ArrayList<>();
                if (ratings.hasNext()) {
                    Rate rate = ratings.next();
                    prevUser = rate.getUser();
                    list.add(rate);
                }
                while (ratings.hasNext()) {
                    Rate rate = ratings.next();
                    if (prevUser == rate.getUser()) {
                        list.add(rate);
                    } else {
                        if (list.size() < cut) {
                            for (Rate r : list) {
                                writer.write(r.outString());
                                writer.newLine();
                            }
                        }
                        prevUser = rate.getUser();
                        list.clear();
                        list.add(rate);
                    }
                }
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(CutUserByRatings.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
