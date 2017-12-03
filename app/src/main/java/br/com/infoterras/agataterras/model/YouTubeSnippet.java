package br.com.infoterras.agataterras.model;

import android.text.Html;
import android.text.Spanned;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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

    public Spanned getPublishedFormated(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("'<big>'dd'</big><br><small>'MMM'</small'", Locale.getDefault());
        return Html.fromHtml(simpleDateFormat.format(publishedAt).toUpperCase());
    }
}
