package br.com.infoterras.youtuberapp.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Gustavo on 30/03/2017.
 */

public class YouTubeComment implements Serializable{

    private String authorDisplayName;
    private String authorProfileImageUrl;
    private String authorChannelUrl;
    private String textDisplay;
    private String textOriginal;
    private Date publishedAt;
    private Date updatedAt;

    public String getAuthorDisplayName() {
        return authorDisplayName;
    }

    public void setAuthorDisplayName(String authorDisplayName) {
        this.authorDisplayName = authorDisplayName;
    }

    public String getAuthorProfileImageUrl() {
        return authorProfileImageUrl;
    }

    public void setAuthorProfileImageUrl(String authorProfileImageUrl) {
        this.authorProfileImageUrl = authorProfileImageUrl;
    }

    public String getAuthorChannelUrl() {
        return authorChannelUrl;
    }

    public void setAuthorChannelUrl(String authorChannelUrl) {
        this.authorChannelUrl = authorChannelUrl;
    }

    public String getTextDisplay() {
        return textDisplay;
    }

    public void setTextDisplay(String textDisplay) {
        this.textDisplay = textDisplay;
    }

    public String getTextOriginal() {
        return textOriginal;
    }

    public void setTextOriginal(String textOriginal) {
        this.textOriginal = textOriginal;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getPublishedFormated(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yy", Locale.getDefault());
        return simpleDateFormat.format(publishedAt);
    }
}
