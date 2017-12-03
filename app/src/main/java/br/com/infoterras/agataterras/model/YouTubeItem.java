package br.com.infoterras.agataterras.model;

import java.io.Serializable;

/**
 * Created by gustavoterras on 26/03/17.
 */

public class YouTubeItem implements Serializable {

    private YouTubeId id;

    private YouTubeSnippet snippet;

    public YouTubeSnippet getSnippet() {
        return snippet;
    }

    public void setSnippet(YouTubeSnippet snippet) {
        this.snippet = snippet;
    }

    public YouTubeId getId() {
        return id;
    }

    public void setId(YouTubeId id) {
        this.id = id;
    }
}
