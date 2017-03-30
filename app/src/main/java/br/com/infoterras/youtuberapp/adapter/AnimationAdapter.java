package br.com.infoterras.youtuberapp.adapter;

import android.databinding.BindingAdapter;
import android.view.View;
import android.view.animation.Animation;

/**
 * Created by gustavoterras on 30/03/17.
 */

public class AnimationAdapter {

    @BindingAdapter("app:anim")
    public static void animationView(View view, Animation animation){
        view.startAnimation(animation);
    }

}
