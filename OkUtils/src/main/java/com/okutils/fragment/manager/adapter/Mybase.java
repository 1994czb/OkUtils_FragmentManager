package com.okutils.fragment.manager.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.okutils.fragment.manager.R;
import com.okutils.fragment.manager.bean.NewsBean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/21.
 */

public class Mybase extends RecyclerView.Adapter<Mybase.MyViewHolder> {
    private List<NewsBean.StoriesBean> stories;

    public Mybase(List<NewsBean.StoriesBean> stories) {
        this.stories = stories;
    }

    private Onclick mOnclick;

    public void setmOnclick(Onclick mOnclick) {
        this.mOnclick = mOnclick;
    }

    public interface Onclick {
        void mclick(View view, int position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, null);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.item_txt.setText(stories.get(position).getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnclick != null) {
                    mOnclick.mclick(v, position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return stories.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView item_txt;

        public MyViewHolder(View itemView) {
            super(itemView);
            item_txt = (TextView) itemView.findViewById(R.id.item_txt);
        }
    }
}

