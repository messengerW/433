package com.example.f433.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.f433.R;

import java.util.ArrayList;

public class FavAvtivity extends AppCompatActivity {
    private ArrayList<CardBean> ItemList = new ArrayList<CardBean>();
    private FavAdapter adapter;
    public RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        initCardItem();
        initRecyclerView();
    }

    private void initCardItem(){
//        CardDao dao = new CardDao(getActivity());
//        ItemList = dao.getItemList();

        for (int i = 0; i < 10; i++) {
            CardBean cardBean = new CardBean();
            cardBean.setUserName("CardItem" + i);
            ItemList.add(cardBean);
        }
    }

    private void initRecyclerView(){
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview111);
        recyclerView.setPadding(8,8,8,8);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

        adapter = new FavAdapter(this,ItemList);
        recyclerView.setAdapter(adapter);
        //  设置recycler的样式
        //  recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        //  设置分割线
        //recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

    }
}