package com.github.peerapongsam.androidlab.fragmentexample.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.peerapongsam.androidlab.fragmentexample.R;
import com.github.peerapongsam.androidlab.fragmentexample.holder.FooViewHolder;

import java.util.List;

/**
 * Created by peerapong on 5/19/17.
 */

public class FooAdapter extends RecyclerView.Adapter<FooViewHolder> {
    private List<String> foos;
    private OnFooItemClickListener listener;

    public void setListener(OnFooItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public FooViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        return new FooViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_foo, parent, false));
    }

    @Override
    public void onBindViewHolder(final FooViewHolder holder, int position) {
        holder.setFoo(foos.get(position));
        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onFooItemClick(foos.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        if (foos == null) return 0;
        return foos.size();
    }

    public List<String> getFoos() {
        return foos;
    }

    public void setFoos(List<String> foos) {
        this.foos = foos;
        notifyDataSetChanged();
    }

    public interface OnFooItemClickListener {
        void onFooItemClick(String foo);
    }
}
