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
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.AbstractList;
import java.util.ArrayList;

import br.com.infoterras.youtuberapp.BR;
import br.com.infoterras.youtuberapp.R;
import br.com.infoterras.youtuberapp.adapter.RecyclerBindingAdapter;
import br.com.infoterras.youtuberapp.component.RecyclerConfiguration;
import br.com.infoterras.youtuberapp.model.YouTubeComment;
import br.com.infoterras.youtuberapp.model.YouTubeItem;
import br.com.infoterras.youtuberapp.model.YouTubeResponse;
import br.com.infoterras.youtuberapp.network.ConsumerService;
import br.com.infoterras.youtuberapp.ui.DetailActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gustavoterras on 26/03/17.
 */

public class DetailViewModel implements ConsumerService.OnTaskCompleted<JsonObject>, RecyclerBindingAdapter.OnItemClickListener<YouTubeComment>{

    private static final String TAG = DetailViewModel.class.getSimpleName();
    private RecyclerBindingAdapter<YouTubeComment> adapter;
    public RecyclerConfiguration recyclerConfiguration;
    public YouTubeItem youTubeItem;
    private LinearLayout progress;
    private Context context;

    public DetailViewModel(final Context context, View view, YouTubeItem youTubeItem) {
        this.progress = (LinearLayout) view.findViewById(R.id.progress_layout);
        this.recyclerConfiguration = new RecyclerConfiguration();
        this.youTubeItem = youTubeItem;
        this.context = context;

        ConsumerService consumerService = new ConsumerService();
        consumerService.setOnTaskCompleted(this);
        consumerService.getComment(youTubeItem.getId().getVideoId(), -1);

        initRecycler();
    }

    private void initRecycler() {
        adapter = getAdapter();
        adapter.setOnItemClickListener(this);

        recyclerConfiguration.setLayoutManager(new LinearLayoutManager(context));
        recyclerConfiguration.setItemAnimator(new DefaultItemAnimator());
        recyclerConfiguration.setAdapter(adapter);
    }

    private RecyclerBindingAdapter<YouTubeComment> getAdapter() {
        return new RecyclerBindingAdapter<>(R.layout.item_comment_list, BR.viewModel, new ArrayList<YouTubeComment>());
    }

    @Override
    public void onSuccess(JsonObject response, int code, int requestCode) {
        if (code != 200)  return;

        ArrayList<YouTubeComment> comments = new ArrayList<>();

        JsonArray jsonArray = response.getAsJsonArray("items");

        for (JsonElement element : jsonArray) {
            YouTubeComment comment = new Gson().fromJson(element.getAsJsonObject().getAsJsonObject("snippet").getAsJsonObject("topLevelComment").getAsJsonObject("snippet").toString(), YouTubeComment.class);
            if(comment != null)
                comments.add(comment);
        }

        adapter.setList(comments);
        progress.setVisibility(comments.isEmpty() ? View.VISIBLE : View.GONE);
}

    @Override
    public void onFailure(Throwable error, int requestCode) {

    }

    @Override
    public void onItemClick(int position, View view, YouTubeComment item) {

    }
}
