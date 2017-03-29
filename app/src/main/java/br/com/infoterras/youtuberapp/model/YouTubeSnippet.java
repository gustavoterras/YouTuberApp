package br.com.infoterras.youtuberapp.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by gustavoterras on 26/03/17.
 */

public class YouTubeSnippet implements Serializable {

    private Date publishedAt;
    private String title;
    private String description;
    private YouTubeThumb thumbnails;

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public YouTubeThumb getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(YouTubeThumb thumbnails) {
        this.thumbnails = thumbnails;
    }
}
