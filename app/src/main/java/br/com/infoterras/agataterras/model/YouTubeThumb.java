package br.com.infoterras.agataterras.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by gustavoterras on 26/03/17.
 */

public class YouTubeThumb implements Serializable {

    @SerializedName("default")
    private Default thumbDefault;
    @SerializedName("medium")
    private Medium thumbMedium;
    @SerializedName("high")
    private High  thumbHigh;

    public Default getThumbDefault() {
        return thumbDefault;
    }

    public void setThumbDefault(Default thumbDefault) {
        this.thumbDefault = thumbDefault;
    }

    public Medium getThumbMedium() {
        return thumbMedium;
    }

    public void setThumbMedium(Medium thumbMedium) {
        this.thumbMedium = thumbMedium;
    }

    public High getThumbHigh() {
        return thumbHigh;
    }

    public void setThumbHigh(High thumbHigh) {
        this.thumbHigh = thumbHigh;
    }

    public class Default implements Serializable{

        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public class Medium implements Serializable{

        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public class High implements Serializable{

        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
