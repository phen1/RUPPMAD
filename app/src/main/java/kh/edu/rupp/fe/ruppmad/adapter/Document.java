package kh.edu.rupp.fe.ruppmad.adapter;

import com.google.gson.annotations.SerializedName;

/**
 * RUPPMAD
 * Created by leapkh on 4/26/17.
 */

public class Document {

    @SerializedName("_id")
    private int id;

    @SerializedName("_title")
    private String title;

    @SerializedName("_thumbnail_url")
    private String thumbnailUrl;

    @SerializedName("_size")
    private int size;

    @SerializedName("_hits")
    private int hits;

    public Document(int id, String title, String thumbnailUrl, int size, int hits) {
        this.id = id;
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
        this.size = size;
        this.hits = hits;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getFormatSize(){
        return size + " M";
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }
}
