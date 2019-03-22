package com.example.f433;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Fragment1 extends Fragment {

    /*** 第一个碎片中包含两个组件，图片轮播和滚动新闻栏 ***/

    private View mView;     // 注意，mView是两个组件共用的！
    private ViewPager mViewPaper;
    private List<ImageView> images;
    private List<View> dots;
    private int currentItem;
    //记录上一次点的位置
    private int oldPosition = 0;
    //存放图片的id
    private int[] imageIds = new int[]{
            R.mipmap.lunbo_1,
            R.mipmap.lunbo_2,
            R.mipmap.lunbo_3
    };
    private ViewPagerAdapter adapter;
    private ScheduledExecutorService scheduledExecutorService;
    //定义RecyclerView
    public RecyclerView mCollectRecyclerView;
    //定义以news实体类为对象的数据集合
    private ArrayList<Fragment1_NewsItem> f1NewsItemList = new ArrayList<Fragment1_NewsItem>();
    //自定义recyclerveiw的适配器
    private Fragment1_NewsAdapter mCollectRecyclerAdapter;

    /*** 滚动新闻开始 ***/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment1, container, false);
        setView();
        //对recycleview进行配置
        initRecyclerView1();
        //模拟数据
        initNewsItem();
        return mView;
    }

    /**
     * TODO 模拟数据
     */
    private void initNewsItem() {
        /*图片暂时从网上获取*/
        String[] url = {"http://image.thepaper.cn/www/image/8/15/751.jpg",
                        "http://www.ss28.com/newsfiles/file/world/italy/juv/2018-10-09/7acb04f387d969c6d24578bdb2d3a8fa.jpg",
                        "http://img.mp.itc.cn/upload/20160902/30880a2ef78e4a2983e2c96f665b3c6b_th.jpeg",
                        "http://img5.imgtn.bdimg.com/it/u=1979028530,4274692987&fm=11&gp=0.jpg",
                        "http://p1.qhimgs4.com/t0121dd731c29838075.jpg",
                        "http://image.thepaper.cn/www/image/8/15/751.jpg",
                        "http://www.ss28.com/newsfiles/file/world/italy/juv/2018-10-09/7acb04f387d969c6d24578bdb2d3a8fa.jpg",
                        "http://img.mp.itc.cn/upload/20160902/30880a2ef78e4a2983e2c96f665b3c6b_th.jpeg",
                        "http://img5.imgtn.bdimg.com/it/u=1979028530,4274692987&fm=11&gp=0.jpg",
                        "http://p1.qhimgs4.com/t0121dd731c29838075.jpg",};

        //  新闻的图片、标题、时间等在这里获取，将来设计好数据库后可以改成sql语句
        for (int i = 0; i < 10; i++) {
            Fragment1_NewsItem f1NewsItem = new Fragment1_NewsItem();
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

    private void initRecyclerView1() {
        //获取RecyclerView
        mCollectRecyclerView = (RecyclerView) mView.findViewById(R.id.recycler_view_news);
        //创建adapter
        mCollectRecyclerAdapter = new Fragment1_NewsAdapter(getActivity(), f1NewsItemList);
        //给RecyclerView设置adapter
        mCollectRecyclerView.setAdapter(mCollectRecyclerAdapter);
        //设置layoutManager,可以设置显示效果，是线性布局、grid布局，还是瀑布流布局
        //参数是：上下文、列表方向（横向还是纵向）、是否倒叙
        mCollectRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        //设置item的分割线
        mCollectRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        //RecyclerView中没有item的监听事件，需要自己在适配器中写一个监听事件的接口。参数根据自定义
        mCollectRecyclerAdapter.setOnItemClickListener(new Fragment1_NewsAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, Fragment1_NewsItem data) {
                //此处进行监听事件的业务处理
                Toast.makeText(getActivity(), "我是item", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*** 滚动新闻结束 ***/

    /*** 图片轮播开始 ***/

    private void setView() {
        mViewPaper = (ViewPager) mView.findViewById(R.id.vp_lunbo);

        //显示图片
        images = new ArrayList<>();
        for (int i = 0; i < imageIds.length; i++) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setBackgroundResource(imageIds[i]);
            images.add(imageView);
        }

        //显示小白点
        dots = new ArrayList<>();
        dots.add(mView.findViewById(R.id.dot_0));
        dots.add(mView.findViewById(R.id.dot_1));
        dots.add(mView.findViewById(R.id.dot_2));

        adapter = new ViewPagerAdapter();
        mViewPaper.setAdapter(adapter);

        mViewPaper.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                dots.get(position).setBackgroundResource(R.drawable.ic_point1);
                dots.get(position).setBackgroundResource(R.drawable.ic_point2);

                oldPosition = position;
                currentItem = position;

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    /*定义适配器*/
    public class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup view, int position, Object object) {
            view.removeView(images.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup view, int position) {
            view.addView(images.get(position));
            return images.get(position);
        }
    }

    //利用线性池执行动画轮播

    @Override
    public void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleWithFixedDelay(
                new ViewPageTask(),
                2,
                2,
                TimeUnit.SECONDS);
    }


    //图片轮播任务
    private class ViewPageTask implements Runnable {
        @Override
        public void run() {
            currentItem = (currentItem + 1) % imageIds.length;
            mHandler.sendEmptyMessage(0);

        }
    }

    //接受子线程传递过来的数据

    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            mViewPaper.setCurrentItem(currentItem);
        }

        ;

    };

    @Override
    public void onStop() {
        super.onStop();
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdown();
            scheduledExecutorService = null;
        }

    }
    /*** 图片轮播结束 ***/

}
