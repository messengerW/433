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

public class Fragment2_Child2 extends Fragment {

    private View view;
    public RecyclerView mCollectRecyclerView;
    private ArrayList<Fragment2_Child2_GameItem> ItemArrayList = new ArrayList<Fragment2_Child2_GameItem>();
    private Fragment2_Child2_GameAdapter mCollectRecyclerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment2_child2, container, false);
        initRecyclerView();
        initGameItem();
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

        //  新闻的图片、标题、时间等在这里获取，将来设计好数据库后可以改成sql语句
        for (int i = 0; i < 18; i++) {
            Fragment2_Child2_GameItem GameItem = new Fragment2_Child2_GameItem();
            GameItem.setDate("3月31日");
            GameItem.setTime("23:15");
            GameItem.setTeam1("巴塞罗那");
            GameItem.setTeam2("皇家马德里");
            GameItem.setCentertext("VS");
            /*
            上面三条是设置文本内容，图片内容已经在适配器中设置过setBackgroundResource()，因此这里注释掉。
            GameItem.setlogo1("url");
            GameItem.setlogo2("url");
            */
            ItemArrayList.add(GameItem);
        }
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
        mCollectRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_game);
        //创建adapter
        mCollectRecyclerAdapter = new Fragment2_Child2_GameAdapter(mActivity, ItemArrayList);
        //给RecyclerView设置adapter
        mCollectRecyclerView.setAdapter(mCollectRecyclerAdapter);
        //设置layoutManager,可以设置显示效果，是线性布局、grid布局，还是瀑布流布局
        //参数是：上下文、列表方向（横向还是纵向）、是否倒叙
        mCollectRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
        //设置item的分割线
        //mCollectRecyclerView.addItemDecoration(new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL));

        //RecyclerView中没有item的监听事件，需要自己在适配器中写一个监听事件的接口。参数根据自定义
        // 尝试一下不在这里定义点击事件，这一次写在适配器里面试一试
    }
}