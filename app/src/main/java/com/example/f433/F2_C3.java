package com.example.f433;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class F2_C3 extends Fragment {

    private View view;
    public RecyclerView mCollectRecyclerView;
    private ArrayList<F2_C3_RankBean> ItemArrayList = new ArrayList<F2_C3_RankBean>();
    private F2_C3_RankAdapter mCollectRecyclerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment2_child3, container, false);

        //  坑——这两个初始化函数的调用顺序必须是这样，否则就没法把数据读出来，但是很奇怪前两个Child就没关系，哪个在前都行
        initRankItem();
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
    private void initRankItem() {

        //  实例化一个Dao
        F2_C3_RankDao Dao = new F2_C3_RankDao(getActivity());
        //  调用Dao的getlist方法，并把从数据库中读取出来的数据传递给ItemArrayList
        ItemArrayList = Dao.getRankList();

        /*//  新闻的图片、标题、时间等在这里获取，将来设计好数据库后可以改成sql语句
        for (int i = 1; i <= 18; i++) {
            F2_C3_RankBean RankItem = new F2_C3_RankBean();

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
        }*/
    }


    /*protected Activity mActivity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = activity;
    }*/

    /**
     * TODO 对recyclerview进行配置
     */

    private void initRecyclerView() {
        //获取RecyclerView
        mCollectRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_rank);
        //创建adapter
        mCollectRecyclerAdapter = new F2_C3_RankAdapter(getActivity(), ItemArrayList);
        //给RecyclerView设置adapter
        mCollectRecyclerView.setAdapter(mCollectRecyclerAdapter);
        //设置layoutManager,可以设置显示效果，是线性布局、grid布局，还是瀑布流布局
        //参数是：上下文、列表方向（横向还是纵向）、是否倒叙
        mCollectRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        //设置item的分割线
        mCollectRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        //RecyclerView中没有item的监听事件，需要自己在适配器中写一个监听事件的接口。参数根据自定义
        mCollectRecyclerAdapter.setOnItemClickListener(new F2_C3_RankAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, F2_C3_RankBean data) {
                //此处进行监听事件的业务处理
                //Toast.makeText(getActivity(), "我是item", Toast.LENGTH_SHORT).show();
            }
        });
    }
}