package br.com.infoterras.youtuberapp.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import java.io.Serializable;

import br.com.infoterras.youtuberapp.BR;

/**
 * Created by gustavoterras on 30/03/17.
 */

public class YouTubeStatistics extends BaseObservable implements Serializable {

    private int viewCount;
    private int likeCount;
    private int dislikeCount;
    private int favoriteCount;
    private int commentCount;

    @Bindable
    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
        notifyPropertyChanged(BR.viewCount);
    }

    @Bindable
    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
        notifyPropertyChanged(BR.likeCount);
    }

    @Bindable
    public int getDislikeCount() {
        return dislikeCount;
    }

    public void setDislikeCount(int dislikeCount) {
        this.dislikeCount = dislikeCount;
        notifyPropertyChanged(BR.dislikeCount);
    }

    @Bindable
    public int getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(int favoriteCount) {
        this.favoriteCount = favoriteCount;
        notifyPropertyChanged(BR.favoriteCount);
    }

    @Bindable
    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
        notifyPropertyChanged(BR.commentCount);
    }
}
