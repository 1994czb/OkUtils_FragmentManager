package com.okutils.fragment.manager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.okutils.fragment.manager.adapter.Mybase;
import com.okutils.fragment.manager.bean.NewsBean;
import com.okutils.fragment.manager.utils.NetDataCallBack;
import com.okutils.fragment.manager.utils.OKutils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NetDataCallBack<NewsBean> {

    private RecyclerView recycler_main;
    private List<NewsBean.StoriesBean> stories = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
        setmaindata();
    }

    private void setmaindata() {
        String url = "http://news-at.zhihu.com/api/4/news/latest";
        //实例化OKUtils
        OKutils oKutils = new OKutils();
        /**
         * get请求 this会报错 然后实现NetDataCallBack方法 先不要实现方法，在方法后面添加bean
         * NetDataCallBack<NewsBean>
         * 然后实现方法
         */
        oKutils.getdata(url, this, NewsBean.class);


        /**
         * post请求 this会报错 然后实现NetDataCallBack 先不要实现方法，在方法后面添加bean   NetDataCallBack<NewsBean>
         * 然后实现方法
         */
//        FormBody body=new FormBody.Builder()
//          .add("添加参数","添加值")
//          .build();
//          oKutils.getLoadDataPost(url,this,NewsBean.class,body);

    }

    private void initview() {
        recycler_main = (RecyclerView) findViewById(R.id.recycler_main);
    }

    /**
     * 请求成功返回数据
     * @param newsBean
     */
    @Override
    public void success(NewsBean newsBean) {
        stories.addAll(newsBean.getStories());
        Mybase mybase = new Mybase(stories);
        recycler_main.setAdapter(mybase);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recycler_main.setLayoutManager(linearLayoutManager);
        mybase.setmOnclick(new Mybase.Onclick() {
            @Override
            public void mclick(View view, int position) {
                Toast.makeText(MainActivity.this, stories.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 请求失败返回的方法
     * @param position
     * @param str
     */
    @Override
    public void field(int position, String str) {

    }
}
