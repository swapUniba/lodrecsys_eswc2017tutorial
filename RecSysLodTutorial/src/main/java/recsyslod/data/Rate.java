
package recsyslod.data;

/**
 *
 * Rate class
 * 
 */
public class Rate {

    int user;

    int item;

    int rate;

    long time;

    public Rate(int user, int item, int rate) {
        this.user = user;
        this.item = item;
        this.rate = rate;
    }

    public Rate(int user, int item, int rate, long time) {
        this.user = user;
        this.item = item;
        this.rate = rate;
        this.time = time;
    }

    public Rate(String line) {
        String[] split = line.split("::");
        this.user = Integer.parseInt(split[0]);
        this.item = Integer.parseInt(split[1]);
        this.rate = Integer.parseInt(split[2]);
        this.time = Long.parseLong(split[3]);
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
    
    public String outString()  {
        return this.user+"::"+this.item+"::"+this.rate+"::"+this.time;
    }

    @Override
    public String toString() {
        return "Rate{" + "user=" + user + ", item=" + item + ", rate=" + rate + ", time=" + time + '}';
    }

}
