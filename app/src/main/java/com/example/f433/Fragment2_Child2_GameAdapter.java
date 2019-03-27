package com.example.f433;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Fragment2_Child2_GameAdapter extends RecyclerView.Adapter<Fragment2_Child2_GameAdapter.myViewHolder> {
    private Context context;
    private ArrayList<Fragment2_Child2_GameItem> GameItemList;

    //创建构造函数
    public Fragment2_Child2_GameAdapter(Context context, ArrayList<Fragment2_Child2_GameItem> GameItemList) {
        //将传递过来的数据，赋值给本地变量
        this.context = context;//上下文
        this.GameItemList = GameItemList;//实体类数据ArrayList
    }

    /**
     * 创建viewhodler，相当于listview中getview中的创建view和viewhodler
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //创建自定义布局
        View itemView = View.inflate(context, R.layout.fragment2_child2_gameitem, null);
        return new myViewHolder(itemView);
    }

    /**
     * 绑定数据，数据与view绑定
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {
        //根据点击位置绑定数据
        Fragment2_Child2_GameItem data = GameItemList.get(position);

        holder.date.setText(data.date);    //获取实体类中的title字段并设置
        holder.team1.setText(data.team1);
        holder.team2.setText(data.team2);
        holder.VS.setText(data.centertext);
        holder.logo1.setBackgroundResource(R.mipmap.ic_launcher);
        holder.logo2.setBackgroundResource(R.mipmap.ic_launcher);

    }

    /**
     * 得到总条数
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return GameItemList.size();
    }

    //自定义viewHolder
    class myViewHolder extends RecyclerView.ViewHolder {

        private TextView date;
        private TextView team1;
        private TextView team2;
        private ImageView logo1;
        private ImageView logo2;
        private TextView VS;

        public myViewHolder(View itemView) {
            super(itemView);
            date = (TextView) itemView.findViewById(R.id.game_date);
            team1 = (TextView) itemView.findViewById(R.id.game_team1);
            team2 = (TextView) itemView.findViewById(R.id.game_team2);
            logo1 = (ImageView) itemView.findViewById(R.id.game_pic1);
            logo2 = (ImageView) itemView.findViewById(R.id.game_pic2);
            VS = (TextView) itemView.findViewById(R.id.game_VS);

            //点击事件放在adapter中使用，也可以写个接口在activity中调用
            //方法一：在adapter中设置点击事件
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //可以选择直接在本位置直接写业务处理
                    Toast.makeText(context,"点击了xxx",Toast.LENGTH_SHORT).show();
                    //此处回传点击监听事件
                    if (onItemClickListener != null) {
                        onItemClickListener.OnItemClick(v, GameItemList.get(getLayoutPosition()));
                    }
                }
            });

        }
    }

    /**
     * 设置item的监听事件的接口
     */
    public interface OnItemClickListener {
        /**
         * 接口中的点击每一项的实现方法，参数自己定义
         *
         * @param view 点击的item的视图
         * @param data 点击的item的数据
         */
        public void OnItemClick(View view, Fragment2_Child2_GameItem data);
    }

    //需要外部访问，所以需要设置set方法，方便调用
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
