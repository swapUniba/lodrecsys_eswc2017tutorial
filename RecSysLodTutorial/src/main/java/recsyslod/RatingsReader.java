
package recsyslod;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import recsyslod.data.Rate;

/**
 *
 * Iterator for ratings in M1M file
 * 
 */
public class RatingsReader implements Iterator<Rate> {
    
    public BufferedReader reader;
    
    public RatingsReader(File file) throws IOException {
        reader=new BufferedReader(new FileReader(file));
    }

    @Override
    public boolean hasNext() {
        try {
            boolean hasNext = (reader != null) && reader.ready();
            if (hasNext == false) {
                reader.close();
                reader = null;
            }
            return hasNext;
        } catch (IOException ex) {
            Logger.getLogger(RatingsReader.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public Rate next() {
        try {
            String readLine = reader.readLine();
            return new Rate(readLine);
        } catch (IOException ex) {
            Logger.getLogger(RatingsReader.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
