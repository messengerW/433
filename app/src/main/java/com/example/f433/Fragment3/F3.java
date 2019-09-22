package com.example.f433.Fragment3;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.example.f433.R;

import java.util.ArrayList;

public class F3 extends Fragment {

    private View view;
    public RecyclerView mCollectRecyclerView;
    private ArrayList<F3_GuessBean> ItemArrayList = new ArrayList<F3_GuessBean>();
    private F3_GuessAdapter mCollectRecyclerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment3, container, false);
        initRecyclerView();
        initGuessItem();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    //我还是有部分没有理解，不过也照着前面的样子弄下来了，按效果来看应该是对的
    //但是不排除有不对的地方，有的话还请改正


    private void initGuessItem() {

        //  竞猜的队伍logo、队名、比赛时间等在这里获取，将来设计好数据库后可以改成sql语句
        for (int i = 0; i < 10; i++) {
            F3_GuessBean GuessItem = new F3_GuessBean();
            GuessItem.setTime("北京时间  4月2日 1"+ i +":00");
            GuessItem.setTeam1("巴塞罗那");
            GuessItem.setTeam2("皇家马德里");
            ItemArrayList.add(GuessItem);
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
        mCollectRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_game_guess);
        //创建adapter
        mCollectRecyclerAdapter = new F3_GuessAdapter(mActivity, ItemArrayList);
        //给RecyclerView设置adapter
        mCollectRecyclerView.setAdapter(mCollectRecyclerAdapter);
        //设置layoutManager,可以设置显示效果，是线性布局、grid布局，还是瀑布流布局
        //参数是：上下文、列表方向（横向还是纵向）、是否倒叙
        mCollectRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
        //设置item的分割线
        mCollectRecyclerView.addItemDecoration(new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL));
        //RecyclerView中没有item的监听事件，需要自己在适配器中写一个监听事件的接口。参数根据自定义
        mCollectRecyclerAdapter.setOnItemClickListener(new F3_GuessAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, F3_GuessBean data) {
                //此处进行监听事件的业务处理
                Toast.makeText(getActivity(), "我是item", Toast.LENGTH_SHORT).show();
            }
        });
    }
}