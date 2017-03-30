package br.com.infoterras.youtuberapp.viewModel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.View;

import java.util.AbstractList;
import java.util.ArrayList;

import br.com.infoterras.youtuberapp.BR;
import br.com.infoterras.youtuberapp.R;
import br.com.infoterras.youtuberapp.adapter.RecyclerBindingAdapter;
import br.com.infoterras.youtuberapp.component.RecyclerConfiguration;
import br.com.infoterras.youtuberapp.model.YouTubeItem;
import br.com.infoterras.youtuberapp.model.YouTubeResponse;
import br.com.infoterras.youtuberapp.network.ConsumerService;
import br.com.infoterras.youtuberapp.ui.DetailActivity;

/**
 * Created by gustavoterras on 26/03/17.
 */

public class DetailViewModel {

    private static final String TAG = DetailViewModel.class.getSimpleName();
    public YouTubeItem youTubeItem;
    private Context context;

    public DetailViewModel(final Context context, YouTubeItem youTubeItem) {
        this.youTubeItem = youTubeItem;
        this.context = context;

        ConsumerService consumerService = new ConsumerService();
        //consumerService.setOnTaskCompleted(this);
        //consumerService.getUContent(context.getString(R.string.channel_id), -1);
    }


}
