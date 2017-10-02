package br.com.infoterras.youtuberapp.utils;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import br.com.infoterras.youtuberapp.ui.widget.CropCircleTransformation;
import br.com.infoterras.youtuberapp.model.Recycler;

/**
 * Created by gustavoterras on 21/08/17.
 */

public class BindAdapterUtils {

    @BindingAdapter({"configuration"})
    public static void configureRecyclerView(RecyclerView recyclerView, Recycler recycler) {
        recyclerView.setLayoutManager(recycler.getLayoutManager());
        recyclerView.setItemAnimator(recycler.getItemAnimator());
        recyclerView.setAdapter(recycler.getAdapter());
    }

    @BindingAdapter({"src"})
    public static void loadImage(ImageView view, String url) {
        Glide.with(view.getContext())
                .load(url)
                .centerCrop()
                .crossFade(1000)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(view);
    }

    @BindingAdapter({"circle"})
    public static void circle(ImageView view, String url) {
        Glide.with(view.getContext())
                .load(url)
                .centerCrop()
                .crossFade(1000)
                .bitmapTransform(new CropCircleTransformation(view.getContext()))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(view);
    }

    @BindingAdapter({"animation"})
    public static void animation(final View view, int animationResource){
        Animation anim = AnimationUtils.loadAnimation(view.getContext(), animationResource);
        view.setAnimation(anim);
        anim.setStartOffset(500);
        anim.start();
    }
}
