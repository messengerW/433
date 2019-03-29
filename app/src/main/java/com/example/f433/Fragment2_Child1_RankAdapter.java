package com.example.f433;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Fragment2_Child1_RankAdapter extends RecyclerView.Adapter<Fragment2_Child1_RankAdapter.myViewHodler> {
    private Context context;
    private ArrayList<Fragment2_Child1_RankItem> RankItemList;

    //创建构造函数
    public Fragment2_Child1_RankAdapter(Context context, ArrayList<Fragment2_Child1_RankItem> RankItemList) {
        //将传递过来的数据，赋值给本地变量
        this.context = context;//上下文
        this.RankItemList = RankItemList;//实体类数据ArrayList
    }

    /**
     * 创建viewhodler，相当于listview中getview中的创建view和viewhodler
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public myViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        //创建自定义布局
        View itemView = View.inflate(context, R.layout.fragment2_child1_rankitem, null);
        return new myViewHodler(itemView);
    }

    /**
     * 绑定数据，数据与view绑定
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(myViewHodler holder, int position) {
        //根据点击位置绑定数据
        Fragment2_Child1_RankItem data = RankItemList.get(position);

        holder.rank.setText(data.rank);    //获取实体类中的title字段并设置
        holder.logo.setBackgroundResource(R.mipmap.ic_launcher);
        holder.name.setText(data.name);
        holder.turn.setText(data.turn);
        holder.num1.setText(data.num1);
        holder.num2.setText(data.num2);
        holder.num3.setText(data.num3);
        holder.rate.setText(data.rate);
        holder.points.setText(data.points);

        /*//  上面三条是获取并设置新闻框中的三个文本TextView,比较简单，下面是图片的设置
        RequestOptions options = new RequestOptions()
                //        .override(10,10)                 //设置图片尺寸
                .placeholder(R.drawable.ic_news_loading)      //处理图片加载时显示的画面
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                *//*
         * 上面这句话解释是这样的：Glide具有缓存机制，加载过的图片它会自动缓存下来，因此下一次加载时
         * 会从缓存中直接读取，加载速度特别快，看不到加载的过程，所以上面的占位图也就“失效”了，加上这
         * 句话相当于禁止读取缓存吧大概。
         * *//*
                .error(R.drawable.ic_news_failure);


        Glide.with(holder.itemView.getContext())
                .load(data.logo)     //网络图片链接
                .apply(options)          //图片加载失败后，显示的图片
                .into(holder.logo);*/

    }

    /**
     * 得到总条数
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return RankItemList.size();
    }

    //自定义viewhodler
    class myViewHodler extends RecyclerView.ViewHolder {

        private TextView rank;
        private ImageView logo;
        private TextView name;
        private TextView turn;
        private TextView num1;
        private TextView num2;
        private TextView num3;
        private TextView rate;
        private TextView points;

        public myViewHodler(View itemView) {
            super(itemView);
            rank = (TextView) itemView.findViewById(R.id.rank_rank);
            logo = (ImageView) itemView.findViewById(R.id.rank_logo);
            name = (TextView) itemView.findViewById(R.id.rank_name);
            turn = (TextView) itemView.findViewById(R.id.rank_turn);
            num1 = (TextView) itemView.findViewById(R.id.rank_num1);
            num2 = (TextView) itemView.findViewById(R.id.rank_num2);
            num3 = (TextView) itemView.findViewById(R.id.rank_num3);
            rate = (TextView) itemView.findViewById(R.id.rank_rate);
            points = (TextView) itemView.findViewById(R.id.rank_points);
            //点击事件放在adapter中使用，也可以写个接口在activity中调用
            //方法一：在adapter中设置点击事件
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //可以选择直接在本位置直接写业务处理
                    //Toast.makeText(context,"点击了xxx",Toast.LENGTH_SHORT).show();
                    //此处回传点击监听事件
                    if (onItemClickListener != null) {
                        onItemClickListener.OnItemClick(v, RankItemList.get(getLayoutPosition()));
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
        public void OnItemClick(View view, Fragment2_Child1_RankItem data);
    }

    //需要外部访问，所以需要设置set方法，方便调用
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
