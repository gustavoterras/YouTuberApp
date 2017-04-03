package br.com.infoterras.youtuberapp.model;

import android.content.Intent;
import android.provider.SyncStateContract;
import android.view.View;
import android.widget.Toast;

import java.io.Serializable;

import br.com.infoterras.youtuberapp.R;
import br.com.infoterras.youtuberapp.util.Constants;

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

    public void doLike(View view){

        View actionButton = view.findViewById(R.id.action_button);

        if(actionButton.isEnabled())
            actionButton.setBackgroundResource(R.drawable.shape_button_enable);
        else
            actionButton.setBackgroundResource(R.drawable.shape_button_disable);

        actionButton.setEnabled(!actionButton.isEnabled());

        Toast.makeText(view.getContext(), "like", Toast.LENGTH_SHORT).show();
    }

    public void doShare(View view){
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, Constants.URL_VIDEO + id.getVideoId());
        view.getContext().startActivity(Intent.createChooser(shareIntent, "Compartilhe meu vídeo"));    }

    public void doComment(View view){
        Toast.makeText(view.getContext(), "comment", Toast.LENGTH_SHORT).show();
    }
}
