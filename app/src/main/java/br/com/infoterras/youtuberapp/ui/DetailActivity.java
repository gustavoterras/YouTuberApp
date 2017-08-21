package br.com.infoterras.youtuberapp.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.Slide;
import android.transition.TransitionInflater;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import br.com.infoterras.youtuberapp.BuildConfig;
import br.com.infoterras.youtuberapp.R;
import br.com.infoterras.youtuberapp.databinding.ActivityDetailBinding;
import br.com.infoterras.youtuberapp.model.YouTubeItem;
import br.com.infoterras.youtuberapp.ui.widget.ElasticDragDismissFrameLayout;
import br.com.infoterras.youtuberapp.viewModel.DetailViewModel;

public class DetailActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener{

    private static final int RECOVERY_REQUEST = 1;
    private ElasticDragDismissFrameLayout draggableFrame;
    private ActivityDetailBinding binding;
    private YouTubePlayer youTubePlayer;
    private YouTubeItem youTubeItem;
    private boolean onFullScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        youTubeItem = (YouTubeItem) getIntent().getSerializableExtra("extra");

        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);

        Slide slide = new Slide(Gravity.BOTTOM);
        slide.addTarget(R.id.layout_comments);
        slide.setInterpolator(AnimationUtils.loadInterpolator(this, android.R.interpolator.linear_out_slow_in));
        slide.setDuration(getResources().getInteger(android.R.integer.config_longAnimTime));
        getWindow().setEnterTransition(slide);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        binding.setViewModel(new DetailViewModel(this, findViewById(android.R.id.content), youTubeItem));

        draggableFrame = binding.draggableFrame;
        draggableFrame.addListener(
                new ElasticDragDismissFrameLayout.SystemChromeFader(this) {
                    @Override
                    public void onDragDismissed() {
                        if (draggableFrame.getTranslationY() > 0) {
                            getWindow().setReturnTransition(
                                    TransitionInflater.from(DetailActivity.this)
                                            .inflateTransition(R.transition.about_return_downward));
                        }
                        finishAfterTransition();
                    }
                });

        binding.btnActionPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.btnActionPlay.setVisibility(View.GONE);
                binding.imgVideo.setVisibility(View.GONE);
                binding.player.setVisibility(View.VISIBLE);
                binding.player.initialize(BuildConfig.KEY, DetailActivity.this);
            }
        });
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
        if (!wasRestored) {

            youTubePlayer = player;

            youTubePlayer.setOnFullscreenListener(new YouTubePlayer.OnFullscreenListener() {
                @Override
                public void onFullscreen(boolean onFullscreen) {
                    onFullScreen = onFullscreen;
                }
            });

            youTubePlayer.setFullscreen(true);

            youTubePlayer.loadVideo(youTubeItem.getId().getVideoId());
            //player.play();
            //player.setFullscreen(true);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_REQUEST).show();
        } else {
            Toast.makeText(this, "error ", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_REQUEST) {
            binding.player.initialize(BuildConfig.KEY, this);
        }
    }

    @Override
    public void onBackPressed() {
        if (onFullScreen){
            youTubePlayer.setFullscreen(false);
            youTubePlayer.pause();
        } else{
            super.onBackPressed();
        }
    }
}