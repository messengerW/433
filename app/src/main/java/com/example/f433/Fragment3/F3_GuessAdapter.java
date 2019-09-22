package com.example.f433.Fragment3;

import android.support.v7.widget.RecyclerView;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.f433.R;

import java.util.ArrayList;

public class F3_GuessAdapter extends RecyclerView.Adapter<F3_GuessAdapter.myViewHolder> {
    private Context context;
    private ArrayList<F3_GuessBean> GuessItemList;

    //创建构造函数
    public F3_GuessAdapter(Context context, ArrayList<F3_GuessBean> GuessItemList) {
        //将传递过来的数据，赋值给本地变量
        this.context = context;//上下文
        this.GuessItemList = GuessItemList;//实体类数据ArrayList
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
        View itemView = View.inflate(context, R.layout.fragment3_guessitem, null);
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
        F3_GuessBean data = GuessItemList.get(position);

        holder.time.setText(data.time);
        holder.team1.setText(data.team1);
        holder.team2.setText(data.team2);
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
        return GuessItemList.size();
    }

    //自定义viewHolder
    class myViewHolder extends RecyclerView.ViewHolder {

        private TextView time;
        private TextView team1;
        private TextView team2;
        private ImageView logo1;
        private ImageView logo2;

        public myViewHolder(View itemView) {
            super(itemView);
            time = (TextView) itemView.findViewById(R.id.game_guess_time);
            team1 = (TextView) itemView.findViewById(R.id.game_guess_t1);
            team2 = (TextView) itemView.findViewById(R.id.game_guess_t2);
            logo1 = (ImageView) itemView.findViewById(R.id.game_guess_pic1);
            logo2 = (ImageView) itemView.findViewById(R.id.game_guess_pic2);

            //点击事件放在adapter中使用，也可以写个接口在activity中调用
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //此处回传点击监听事件
                    if (onItemClickListener != null) {
                        onItemClickListener.OnItemClick(v, GuessItemList.get(getLayoutPosition()));
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
        public void OnItemClick(View view, F3_GuessBean data);
    }

    //需要外部访问，所以需要设置set方法，方便调用
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}