package br.com.infoterras.youtuberapp.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by gustavoterras on 26/03/17.
 */

public class YouTubeResponse implements Serializable {

    private String etag;
    private String nextPageToken;
    private List<YouTubeItem> items;

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

    public List<YouTubeItem> getItems() {
        return items;
    }

    public void setItems(List<YouTubeItem> items) {
        this.items = items;
    }
}
