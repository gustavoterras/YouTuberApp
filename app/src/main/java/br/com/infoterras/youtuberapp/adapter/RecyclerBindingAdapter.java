package br.com.infoterras.youtuberapp.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.AbstractList;
import java.util.ArrayList;

/**
 * Created by Gustavo on 26/03/2017.
 */

public class RecyclerBindingAdapter<T> extends RecyclerView.Adapter<RecyclerBindingAdapter.BindingHolder> {

    private int lastPosition = 1;
    private int holderLayout, variableId;
    private AbstractList<T> items = new ArrayList<>();
    private OnItemClickListener<T> onItemClickListener;

    public interface OnItemClickListener<T> {
        void onItemClick(int position, View view, T item);
    }

    public void setOnItemClickListener(OnItemClickListener<T> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setList(AbstractList<T> items){
        this.items = items;
        notifyDataSetChanged();
    }

    public RecyclerBindingAdapter(int holderLayout, int variableId, AbstractList<T> items) {
        this.holderLayout = holderLayout;
        this.variableId = variableId;
        this.items = items;
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(holderLayout, parent, false);
        return new BindingHolder(v);
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, final int position) {
        final T item = items.get(position);
        holder.getBinding().getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null)
                    onItemClickListener.onItemClick(position, view, item);
            }
        });

        holder.getBinding().setVariable(variableId, item);
        holder.getBinding().executePendingBindings();

        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), android.R.anim.fade_in);
            holder.itemView.startAnimation(animation);
        }

        lastPosition = position;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class BindingHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding binding;

        BindingHolder(View v) {
            super(v);
            binding = DataBindingUtil.bind(v);
        }

        public ViewDataBinding getBinding() {
            return binding;
        }
    }
}