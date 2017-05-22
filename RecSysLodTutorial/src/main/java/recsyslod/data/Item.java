
package recsyslod.data;

/**
 *
 * Item class
 * 
 */
public class Item {

    private int itemId;

    private String title;

    private String[] genres;

    public Item(int itemId, String title) {
        this.itemId = itemId;
        this.title = title;
    }

    public Item(int itemId, String title, String[] genres) {
        this.itemId = itemId;
        this.title = title;
        this.genres = genres;
    }

    public Item(String line) {
        String[] split = line.split("::");
        this.itemId = Integer.parseInt(split[0]);
        this.title = split[1];
        this.genres = split[2].split("|");
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getGenres() {
        return genres;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

}
