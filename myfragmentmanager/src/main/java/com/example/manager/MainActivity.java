package com.example.manager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;

import com.example.manager.fragment.FindFragment;
import com.example.manager.fragment.MainFragment;
import com.example.manager.fragment.MyFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RadioButton rb_home_page;
    private RadioButton rb_find;
    private RadioButton rb_my;
    private MainFragment mainframent;
    private MyFragment myFragment;
    private FindFragment findFragment;
    private FragmentTransaction ft;
    private FragmentManager supportFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();

    }

    private void initview() {
        rb_home_page = (RadioButton) findViewById(R.id.rb_home_page);
        rb_find = (RadioButton) findViewById(R.id.rb_find);
        rb_my = (RadioButton) findViewById(R.id.rb_my);

        mainframent = new MainFragment();
        myFragment = new MyFragment();
        findFragment = new FindFragment();
        /**
         * 获得fragment的管理者
         * 开启事务
         */
        supportFragmentManager = getSupportFragmentManager();
        ft = supportFragmentManager.beginTransaction();

        Intent intent = getIntent();
        /**
         *  0 为占位/默认
         */
        int key = intent.getIntExtra("key", 0);
        if (key == 1) {
            ft.add(R.id.framelayout_main, mainframent, "首页");
            rb_home_page.setChecked(true);
            ft.commit();
        } else if (key == 2) {
            ft.add(R.id.framelayout_main, findFragment, "发现");
            rb_find.setChecked(true);
            ft.commit();
        } else if (key == 3) {
            ft.add(R.id.framelayout_main, myFragment, "我的");
            rb_my.setChecked(true);
            ft.commit();
        } else {
            ft.add(R.id.framelayout_main, mainframent, "首页");
            /**
             *
             * 提交
             */
            ft.commit();
        }

        /**
         * 设置rediobutton的点击事件
         */
        rb_home_page.setOnClickListener(this);
        rb_find.setOnClickListener(this);
        rb_my.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        ft = supportFragmentManager.beginTransaction();
        switch (v.getId()) {
            case R.id.rb_home_page:
                /**
                 * 判断是否添加进去布局
                 * 如果没有那就添加布局
                 * 否则就显示布局提交事物
                 */
                if (!mainframent.isAdded()) {
                    ft.add(R.id.framelayout_main, mainframent, "首页");
                }
                ft.show(mainframent).hide(myFragment).hide(findFragment);
                ft.commit();
                break;
            case R.id.rb_find:
                if (!findFragment.isAdded()) {
                    ft.add(R.id.framelayout_main, findFragment, "发现");

                }
                ft.show(findFragment).hide(myFragment).hide(mainframent);
                ft.commit();
                break;
            case R.id.rb_my:
                if (!myFragment.isAdded()) {
                    ft.add(R.id.framelayout_main, myFragment, "我的");

                }
                ft.show(myFragment).hide(mainframent).hide(findFragment);
                ft.commit();
                break;
            default:
                break;
        }
    }
}

