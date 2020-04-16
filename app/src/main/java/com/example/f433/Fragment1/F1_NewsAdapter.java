package com.example.f433.Fragment1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.f433.R;

import java.util.ArrayList;

public class F1_NewsAdapter extends RecyclerView.Adapter<F1_NewsAdapter.myViewHodler> {
    private Context context;
    private ArrayList<F1_NewsBean> f1NewsItemList;

    //创建构造函数
    public F1_NewsAdapter(Context context, ArrayList<F1_NewsBean> f1NewsItemList) {
        //将传递过来的数据，赋值给本地变量
        this.context = context;//上下文
        this.f1NewsItemList = f1NewsItemList;//实体类数据ArrayList
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
        View itemView = View.inflate(context, R.layout.fragment1_newsitem, null);
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
        F1_NewsBean data = f1NewsItemList.get(position);

        holder.newsTitle.setText(data.title);//获取实体类中的title字段并设置
        holder.newsTime.setText(data.time);
        holder.newsSource.setText(data.source);

        //  上面三条是获取并设置新闻框中的三个文本TextView,比较简单，下面是图片的设置
        RequestOptions options = new RequestOptions()
                //        .override(10,10)                 //设置图片尺寸
                .placeholder(R.drawable.ic_news_loading)      //处理图片加载时显示的画面
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                /*
                 * 上面这句话解释是这样的：Glide具有缓存机制，加载过的图片它会自动缓存下来，因此下一次加载时
                 * 会从缓存中直接读取，加载速度特别快，看不到加载的过程，所以上面的占位图也就“失效”了，加上这
                 * 句话相当于禁止读取缓存吧大概。
                 * */
                .error(R.drawable.ic_news_failure);


        Glide.with(holder.itemView.getContext())
                .load(data.imgPath)     //网络图片链接
                .apply(options)          //图片加载失败后，显示的图片
                .into(holder.newsPic);

    }

    /**
     * 得到总条数
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return f1NewsItemList.size();
    }

    //自定义viewhodler
    class myViewHodler extends RecyclerView.ViewHolder {
        private ImageView newsPic;
        private TextView newsTitle;
        private TextView newsTime;
        private TextView newsSource;

        public myViewHodler(View itemView) {
            super(itemView);
            newsPic = (ImageView) itemView.findViewById(R.id.news_pic);
            newsTitle = (TextView) itemView.findViewById(R.id.news_title);
            newsTime = (TextView) itemView.findViewById(R.id.news_time);
            newsSource = (TextView) itemView.findViewById(R.id.news_source);
            //点击事件放在adapter中使用，也可以写个接口在activity中调用
            //方法一：在adapter中设置点击事件
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //可以选择直接在本位置直接写业务处理
                    //Toast.makeText(context,"点击了xxx",Toast.LENGTH_SHORT).show();
                    //此处回传点击监听事件
                    if (onItemClickListener != null) {
                        onItemClickListener.OnItemClick(v, f1NewsItemList.get(getLayoutPosition()));
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
        public void OnItemClick(View view, F1_NewsBean data);
    }

    //需要外部访问，所以需要设置set方法，方便调用
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
