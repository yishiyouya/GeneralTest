package myjava.mybyte;

import java.io.Serializable;

public class News implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = -2760610314024836659L;
    String title;
    String content;
    public News(String title, String content) {
        super();
        this.title = title;
        this.content = content;
    }
    @Override
    public String toString() {
        return "News [title=" + title + ", content=" + content + "]";
    }
}