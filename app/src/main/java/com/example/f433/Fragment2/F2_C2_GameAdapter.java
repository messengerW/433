package com.example.f433.Fragment2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.f433.Activities.AnalysisAcyivity;
import com.example.f433.Activities.GameActivity;
import com.example.f433.R;

import java.util.ArrayList;

public class F2_C2_GameAdapter extends RecyclerView.Adapter<F2_C2_GameAdapter.myViewHolder> {
    private Context context;
    private ArrayList<F2_C2_GameBean> GameItemList;

    //创建构造函数
    public F2_C2_GameAdapter(Context context, ArrayList<F2_C2_GameBean> GameItemList) {
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
        F2_C2_GameBean bean = GameItemList.get(position);

        holder.date.setText(bean.date);    //获取实体类中的title字段并设置
        holder.time.setText(bean.time);
        holder.team1.setText(bean.team1);
        holder.team2.setText(bean.team2);
        holder.VS.setText(bean.centertext);
        holder.logo1.setImageDrawable(bean.logo1);
        holder.logo2.setImageDrawable(bean.logo2);

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
        private TextView time;
        private TextView team1;
        private TextView team2;
        private ImageView logo1;
        private ImageView logo2;
        private TextView VS;
        private Button button;

        public myViewHolder(View itemView) {
            super(itemView);
            date = (TextView) itemView.findViewById(R.id.game2_date);
            time = (TextView) itemView.findViewById(R.id.game2_time);
            team1 = (TextView) itemView.findViewById(R.id.game2_team1);
            team2 = (TextView) itemView.findViewById(R.id.game2_team2);
            logo1 = (ImageView) itemView.findViewById(R.id.game2_pic1);
            logo2 = (ImageView) itemView.findViewById(R.id.game2_pic2);
            VS = (TextView) itemView.findViewById(R.id.game2_VS);
            button = (Button) itemView.findViewById(R.id.game2_btn1);

            //点击事件放在adapter中使用，也可以写个接口在activity中调用
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*这里是直接添加整个item的点击跳转事件，如果想为item中具体的小的控件添加点击事件的话，
                     * 稍微麻烦一点。*/
                    //Toast.makeText(context,"点击了xxx",Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(context, GameActivity.class);
                    context.startActivity(intent);

                    //此处回传点击监听事件
                    if (onItemClickListener != null) {
                        onItemClickListener.OnItemClick(v, GameItemList.get(getLayoutPosition()));
                    }
                }
            });

            // 单独为button添加点击事件，跳转到分析页面 (2020.04.01 Rainy)
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, AnalysisAcyivity.class);
                    context.startActivity(intent);
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
        public void OnItemClick(View view, F2_C2_GameBean data);
    }

    //需要外部访问，所以需要设置set方法，方便调用
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
