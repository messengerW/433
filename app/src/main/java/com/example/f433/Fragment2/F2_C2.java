package com.example.f433.Fragment2;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.f433.R;

import java.util.ArrayList;

public class F2_C2 extends Fragment {

    private View view;
    public RecyclerView mCollectRecyclerView;
    private ArrayList<F2_C2_GameBean> ItemArrayList = new ArrayList<F2_C2_GameBean>();
    private F2_C2_GameAdapter mCollectRecyclerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment2_child2, container, false);
        initGameItem();
        initRecyclerView();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * TODO 模拟数据
     */
    private void initGameItem() {

        F2_C2_GameDao Dao = new F2_C2_GameDao(getActivity());
        ItemArrayList = Dao.getGameList();
    }

    /*
    下面这个东西目前还不知道有什么用，是上次上网查闪退原因是查到的，但当时这个东西并没有解决app闪退的问题，
    上次做积分榜的时候闪退的原因是文本内容不能设置为int型，只能设置成字符串。这个东西貌似和返回数据有关，
    Fragment的数据最终都要返回到Activity中，用这个好像可以避免空指针.
    */

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
        mCollectRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_game2);
        //创建adapter
        mCollectRecyclerAdapter = new F2_C2_GameAdapter(getActivity(), ItemArrayList);
        //给RecyclerView设置adapter
        mCollectRecyclerView.setAdapter(mCollectRecyclerAdapter);
        //设置layoutManager,可以设置显示效果，是线性布局、grid布局，还是瀑布流布局
        //参数是：上下文、列表方向（横向还是纵向）、是否倒叙
        mCollectRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        //设置item的分割线
        //mCollectRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        //RecyclerView中没有item的监听事件，需要自己在适配器中写一个监听事件的接口。参数根据自定义
        // 尝试一下不在这里定义点击事件，这一次写在适配器里面试一试
    }
}