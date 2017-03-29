package br.com.infoterras.youtuberapp.model;

import java.io.Serializable;

/**
 * Created by gustavoterras on 26/03/17.
 */

public class YouTubeId implements Serializable {

    private String kind;
    private String videoId;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }
}
