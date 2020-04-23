package com.example.f433.Fragment1;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.f433.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

public class F1 extends Fragment {

    /*** 第一个碎片中包含两个组件，图片轮播和滚动新闻栏 ***/

    private View view;

    /* 图片轮播用到的变量 */
    // 轮播组件
    private Banner mBanner;
    //  直接使用本地图片，res资源的id号；如果使用网络图片这里数组需改为字符串数组
    private ArrayList<Integer> images;
    //  轮播图片对应的 title
    private ArrayList<String> titles;

    /* 滑动新闻用到的变量 */
    //定义RecyclerView
    public RecyclerView recyclerView;
    //定义以news实体类为对象的数据集合
    private ArrayList<F1_NewsBean> f1NewsItemList = new ArrayList<F1_NewsBean>();
    //自定义recyclerveiw的适配器
    private F1_NewsAdapter adapter;

    /*** 滚动新闻开始 ***/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment1, container, false);

        // 注意，这里一开始踩了个坑，用getActivity()一直闪退，好好思考一下
        mBanner = (Banner) view.findViewById(R.id.banner);
        // 初始化轮播数据
        initData();
        // 初始化布局
        initView();


        // 模拟数据
        initNewsItem();
        // 对recycleview进行配置
        initRecyclerView();
        return view;
    }

    /*** 下拉新闻开始 ***/
    /**
     * TODO 模拟数据
     */
    private void initNewsItem() {
        /*图片暂时从网上获取*/
        String[] url = {
                "http://p1.qhimgs4.com/t0121dd731c29838075.jpg",
                "http://p1.qhimgs4.com/t0121dd731c29838075.jpg",
                "http://p1.qhimgs4.com/t0121dd731c29838075.jpg",
                "http://p1.qhimgs4.com/t0121dd731c29838075.jpg",
                "http://p1.qhimgs4.com/t0121dd731c29838075.jpg",
                "http://p1.qhimgs4.com/t0121dd731c29838075.jpg",
                "http://www.ss28.com/newsfiles/file/world/italy/juv/2018-10-09/7acb04f387d969c6d24578bdb2d3a8fa.jpg",
                "http://img.mp.itc.cn/upload/20160902/30880a2ef78e4a2983e2c96f665b3c6b_th.jpeg",
                "http://img5.imgtn.bdimg.com/it/u=1979028530,4274692987&fm=11&gp=0.jpg",
                "http://p1.qhimgs4.com/t0121dd731c29838075.jpg",};

        //  新闻的图片、标题、时间等在这里获取，将来设计好数据库后可以改成sql语句
        for (int i = 0; i < 10; i++) {
            F1_NewsBean f1NewsItem = new F1_NewsBean();
            f1NewsItem.setImgPath(url[i]);
            f1NewsItem.setTitle("新闻标题 曼联是冠军 曼联是冠军 曼联是冠军" + i);
            f1NewsItem.setTime("7:3" + i);
            f1NewsItem.setSource("澎湃新闻");
            f1NewsItemList.add(f1NewsItem);
        }
    }

    /**
     * TODO 对recycleview进行配置
     */

    private void initRecyclerView() {
        //获取RecyclerView
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_news);
        //创建adapter
        adapter = new F1_NewsAdapter(getActivity(), f1NewsItemList);
        //给RecyclerView设置adapter
        recyclerView.setAdapter(adapter);
        //设置layoutManager,可以设置显示效果，是线性布局、grid布局，还是瀑布流布局
        //参数是：上下文、列表方向（横向还是纵向）、是否倒叙
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        //设置item的分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        //RecyclerView中没有item的监听事件，需要自己在适配器中写一个监听事件的接口。参数根据自定义
        adapter.setOnItemClickListener(new F1_NewsAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, F1_NewsBean data) {
                //此处进行监听事件的业务处理
                Toast.makeText(getActivity(), "我是item", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*** 下拉新闻结束 ***/

    /*** Begin---图片轮播 ***/
    //  有改进！433当时用的是viewpager，这个banner更加简单好用
    private void initView() {

        //设置banner样式,默认为:Banner.NOT_INDICATOR(不显示指示器和标题)
        //可选样式如下:
        //1. Banner.CIRCLE_INDICATOR    显示圆形指示器
        //2. Banner.NUM_INDICATOR   显示数字指示器
        //3. Banner.NUM_INDICATOR_TITLE 显示数字指示器和标题
        //4. Banner.CIRCLE_INDICATOR_TITLE  显示圆形指示器和标题
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);

        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());

        //设置轮播样式（没有标题默认为右边,有标题时默认左边）
        //可选样式:
        //Banner.LEFT   指示器居左
        //Banner.CENTER 指示器居中
        //Banner.RIGHT  指示器居右
        mBanner.setIndicatorGravity(BannerConfig.CENTER);

        //设置是否允许手动滑动轮播图
        mBanner.setViewPagerIsScroll(true);

        //设置是否自动轮播（不设置则默认自动）
        mBanner.isAutoPlay(true);

        //设置轮播图片间隔时间（不设置默认为2000）
        mBanner.setDelayTime(1500);

        //设置图片资源:可选图片网址/资源文件，默认用Glide加载,也可自定义图片的加载框架
        //所有设置参数方法都放在此方法之前执行
        mBanner.setImages(images);

        //设置标题资源（当banner样式有显示title时）
        //mBanner.setBannerTitles(imageTitle);

        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.DepthPage);

        //添加点击事件
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(getContext(), "你点了第" + (position + 1) + "张轮播图", Toast.LENGTH_SHORT).show();
            }
        });
        mBanner.start();

    }

    private void initData() {
        //设置图片资源:url或本地资源
        images = new ArrayList<>();
        images.add(R.mipmap.lunbo_1);
        images.add(R.mipmap.lunbo_2);
        images.add(R.mipmap.lunbo_3);

        //设置图片标题:自动对应
        titles = new ArrayList<>();
        titles.add("Title1");
        titles.add("Title2");
        titles.add("Title3");

    }

    /**
     * 网络加载图片
     * 使用了Glide图片加载框架
     */
    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context)
                    .load(path)
                    .into(imageView);
        }

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
    /*** End---图片轮播 ***/

}
