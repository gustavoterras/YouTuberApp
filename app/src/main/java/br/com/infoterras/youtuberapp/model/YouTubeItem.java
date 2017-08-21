package br.com.infoterras.youtuberapp.model;

import android.animation.Animator;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Toast;

import java.io.Serializable;

import br.com.infoterras.youtuberapp.R;
import br.com.infoterras.youtuberapp.utils.Constants;

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

    public void doLike(final View view){

        view.setVisibility(View.GONE);

        View actionButton = view.getRootView().findViewById(R.id.content_layout_action).findViewById(R.id.action_button);

        int finalRadius = (int) Math.hypot(actionButton.getWidth() / 2, actionButton.getHeight() / 2);

        Animator anim = ViewAnimationUtils.createCircularReveal(actionButton, actionButton.getWidth() / 2, actionButton.getHeight() / 2, 0, finalRadius);
        anim.setDuration(view.getContext().getResources().getInteger(android.R.integer.config_mediumAnimTime));
        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                view.getRootView().findViewById(R.id.content_layout_action).setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animator) {

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        anim.start();


//        final Animation animationIn = AnimationUtils.loadAnimation(view.getContext(), android.R.anim.fade_in);
//        Animation animationOut = AnimationUtils.loadAnimation(view.getContext(), android.R.anim.fade_out);
//
//        animationOut.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                view.setVisibility(View.GONE);
//                view.getRootView().findViewById(R.id.content_layout_action).setVisibility(View.VISIBLE);
//                view.getRootView().findViewById(R.id.content_layout_action).startAnimation(animationIn);
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
//
//        view.startAnimation(animationOut);

    }

    public void doUnLike(final View view){

        int finalRadius = (int) Math.hypot(view.getWidth() / 2, view.getHeight() / 2);

        Animator anim = ViewAnimationUtils.createCircularReveal(view, view.getWidth() / 2, view.getHeight() / 2, finalRadius, 0);
        anim.setDuration(view.getContext().getResources().getInteger(android.R.integer.config_mediumAnimTime));
        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                view.getRootView().findViewById(R.id.content_layout_action).setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animator) {

                view.setVisibility(View.GONE);
                view.getRootView().findViewById(R.id.action_button_like).setVisibility(View.VISIBLE);

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        anim.start();

//        final Animation animationIn = AnimationUtils.loadAnimation(view.getContext(), android.R.anim.fade_in);
//        Animation animationOut = AnimationUtils.loadAnimation(view.getContext(), android.R.anim.fade_out);
//
//        animationOut.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                view.setVisibility(View.GONE);
//                view.getRootView().findViewById(R.id.action_button_like).setVisibility(View.VISIBLE);
//                view.getRootView().findViewById(R.id.action_button_like).startAnimation(animationIn);
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
//
//        view.startAnimation(animationOut);


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
