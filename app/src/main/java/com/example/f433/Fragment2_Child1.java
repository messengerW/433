package com.example.f433;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class Fragment2_Child1 extends Fragment {

    private View view;
    public RecyclerView mCollectRecyclerView;
    private ArrayList<Fragment2_Child1_RankItem> ItemArrayList = new ArrayList<Fragment2_Child1_RankItem>();
    private Fragment2_Child1_RankAdapter mCollectRecyclerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment2_child1, container, false);
        initRecyclerView();
        initRankItem();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * TODO 模拟数据
     */
    private void initRankItem() {

        //  新闻的图片、标题、时间等在这里获取，将来设计好数据库后可以改成sql语句
        for (int i = 1; i <= 20; i++) {
            Fragment2_Child1_RankItem RankItem = new Fragment2_Child1_RankItem();
            RankItem.setRank(String.valueOf(i));
            //RankItem.setLogo("url");
            RankItem.setName("Barcelona");
            RankItem.setTurn("28");
            RankItem.setNum1("20");
            RankItem.setNum2("6");
            RankItem.setNum3("2");
            RankItem.setRate("73/27");
            RankItem.setPoints("66");
            ItemArrayList.add(RankItem);
        }
    }

    protected Activity mActivity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = activity;
    }

    /**
     * TODO 对recycleview进行配置
     */

    private void initRecyclerView() {
        //获取RecyclerView
        mCollectRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_rank);
        //创建adapter
        mCollectRecyclerAdapter = new Fragment2_Child1_RankAdapter(mActivity, ItemArrayList);
        //给RecyclerView设置adapter
        mCollectRecyclerView.setAdapter(mCollectRecyclerAdapter);
        //设置layoutManager,可以设置显示效果，是线性布局、grid布局，还是瀑布流布局
        //参数是：上下文、列表方向（横向还是纵向）、是否倒叙
        mCollectRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
        //设置item的分割线
        mCollectRecyclerView.addItemDecoration(new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL));
        //RecyclerView中没有item的监听事件，需要自己在适配器中写一个监听事件的接口。参数根据自定义
        mCollectRecyclerAdapter.setOnItemClickListener(new Fragment2_Child1_RankAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, Fragment2_Child1_RankItem data) {
                //此处进行监听事件的业务处理
                Toast.makeText(getActivity(), "我是item", Toast.LENGTH_SHORT).show();
            }
        });
    }
}