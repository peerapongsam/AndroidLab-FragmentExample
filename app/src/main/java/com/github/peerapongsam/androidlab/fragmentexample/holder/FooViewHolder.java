package com.github.peerapongsam.androidlab.fragmentexample.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.github.peerapongsam.androidlab.fragmentexample.R;

/**
 * Created by peerapong on 5/19/17.
 */

public class FooViewHolder extends RecyclerView.ViewHolder {

    private TextView tvFoo;

    public FooViewHolder(View itemView) {
        super(itemView);
        bindView(itemView);
    }

    private void bindView(View itemView) {
        tvFoo = (TextView) itemView.findViewById(R.id.tv_foo);
    }

    public void setFoo(String foo) {
        tvFoo.setText(foo);
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        itemView.setOnClickListener(onClickListener);
    }
}
