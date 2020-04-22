package com.example.f433.Activities;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.example.f433.Fragment1.F1;
import com.example.f433.Fragment2.F2;
import com.example.f433.Fragment3.F3;
import com.example.f433.R;
import com.example.f433.Util.CustomDrawableUtil;
import com.example.f433.Util.StatusBarUtil;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private F1 f1;     // Fragment1 对象
    private F2 f2;     // Fragment2 对象
    private F3 f3;     // Fragment3 对象
    private Fragment[] fragments;
    private int momentFragment = 0;   // 表示最后一个显示的 Fragment
    private ImageButton imageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage);

        /*沉浸式状态栏*/
        StatusBarUtil.setStatusBar(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.hide();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

//        要使用自定义toolbar需要先把这一行注释掉
        toggle.syncState();

        drawer.addDrawerListener(toggle);

        /*
         * 自定义toolbar左侧头像按钮样式，将其设置为比较流行的圆形头像按钮，
         * 用到了 CustomDrawableUtil 类
         * 但是目前还有一个小bug，就是位置问题，所以可以选择使用默认的菜单键，只需要把上面的syncState取消注释
         */
//        Resources resources = MainActivity.this.getResources();
//        Drawable drawable = resources.getDrawable(R.drawable.ic_nav_head);
//        int size = 42;
//        CustomDrawableUtil customDrawableUtil = new CustomDrawableUtil(drawable, MainActivity.this, size);
//        toolbar.setNavigationIcon(customDrawableUtil);

        //  获取侧拉抽屉
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_left);
        //  屏蔽图标自带颜色
        navigationView.setItemIconTintList(null);
        //  为抽屉中的 item 添加“选定事件”，我想，和onClick还是有所不同的吧，不然为什么这样命名
        navigationView.setNavigationItemSelectedListener(this);

        //  为抽屉顶部头像按钮添加点击事件，点击可以跳转到个人信息页面，之前我放在抽屉的点击事件里，
        //  结果放在一起有bug，每次打开抽屉第一次点击头像时无法跳转，必须先点一个下面的任意按钮，然后
        //  再打开抽屉点击头像才会跳转

        //  解决了，要想把侧拉抽屉头部图片按钮的点击事件写在外面要先获取到抽屉的头部view
        // 获取 NavigationView 头部 view
        View view = navigationView.getHeaderView(0);
        // 再从头部 view 中获取到 ImageButton
        imageButton = (ImageButton) view.findViewById(R.id.imagebutton_info);
        // 为 ImageButton 设置点击事件
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UserInfoActivity.class);
                startActivity(intent);
            }
        });


        //  获取底部导航栏
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation_bottom);
        //  屏蔽图标自带颜色
        bottomNavigationView.setItemIconTintList(null);
        //  初始化三个 fragment，并默认显示 fragment1
        initFragments();
        bottomNavigationView.getMenu().findItem(R.id.item1).setIcon(R.drawable.ic_bottom_home2);
        //  为底部导航栏按钮添加 初次点击事件，点击选择更换 Fragment
        bottomNavigationView.setOnNavigationItemSelectedListener(ClickToChange);
        //  为底部导航栏按钮添加 重复点击事件，我理解的就是：
        //  当前已经在展示这个Fragment了，这时如果再次点击Fragment对应的按钮(item)时，触发此事件
        //  bottomNavigationView.setOnNavigationItemReselectedListener(...);
    }

    /*** 处理右上溢出菜单中菜单项的点击事件 ***/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.toolbar_settings) {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


    /*** 侧拉抽屉，可为每个item按钮增加点击事件 ***/
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        //  这样就不难理解了，顶部头像按钮的点击事件写在这里的话就一定会出现那个问题了——
        //  必须要先点击一下下面的任意一个 item，然后再打开抽屉点击头像按钮才会实现跳转。
//        imageButton = (ImageButton) findViewById(R.id.imagebutton_info);
//        imageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, UserInfoActivity.class);
//                startActivity(intent);
//            }
//        });

        // Handle main_bottom_navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_1) {

        } else if (id == R.id.nav_2) {

        } else if (id == R.id.nav_3) {

        } else if (id == R.id.nav_4) {

        } else if (id == R.id.nav_5) {

        } else if (id == R.id.nav_6) {
            goSettings();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);


        return true;
    }

    private void goSettings(){
        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(intent);
    }

    /*** 底部导航栏的点击事件，点击可以切换 Fragment ***/
    private BottomNavigationView.OnNavigationItemSelectedListener ClickToChange
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            //这里是因为需要重置底部icon，所以声明一个控件找到底部导航栏
            BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation_bottom);
            switch (item.getItemId()) {
                case R.id.item1:
                    //  每次更换Fragment时都要先重置底部 3个 icon 为默认图标
                    initItemIcon(bottomNavigationView);
                    if (momentFragment != 0) {
                        item.setIcon(R.drawable.ic_bottom_home2);
                        changeFragment(momentFragment, 0);
                        momentFragment = 0;
                    }
                    return true;
                case R.id.item2:
                    //  每次更换Fragment时都要先重置底部 3个 icon 为默认图标
                    initItemIcon(bottomNavigationView);
                    if (momentFragment != 1) {
                        item.setIcon(R.drawable.ic_bottom_rank2);
                        changeFragment(momentFragment, 1);
                        momentFragment = 1;
                    }
                    return true;
                case R.id.item3:
                    //  每次更换Fragment时都要先重置底部 3个 icon 为默认图标
                    initItemIcon(bottomNavigationView);
                    if (momentFragment != 2) {
                        item.setIcon(R.drawable.ic_bottom_data2);
                        changeFragment(momentFragment, 2);
                        momentFragment = 2;
                    }
                    return true;
            }
            return false;
        }

    };

    //  初始化底部 3 个 icon
    public void initItemIcon(BottomNavigationView bottomNavigationView) {
        MenuItem item1 = bottomNavigationView.getMenu().findItem(R.id.item1);
        item1.setIcon(R.drawable.ic_bottom_home1);
        MenuItem item2 = bottomNavigationView.getMenu().findItem(R.id.item2);
        item2.setIcon(R.drawable.ic_bottom_rank1);
        MenuItem item3 = bottomNavigationView.getMenu().findItem(R.id.item3);
        item3.setIcon(R.drawable.ic_bottom_data1);
    }

    public void changeFragment(int lastFragment, int nextFragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(fragments[lastFragment]);
        if (!fragments[nextFragment].isAdded()) {
            transaction.add(R.id.fm_container, fragments[nextFragment]);
        }
        transaction.show(fragments[nextFragment]).commitAllowingStateLoss();
    }

    //  Fragment初始化函数，默认首先显示 fragment1
    private void initFragments() {
        f1 = new F1();
        f2 = new F2();
        f3 = new F3();
        fragments = new Fragment[]{f1, f2, f3};
        momentFragment = 0;
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fm_container, f1)
                .show(f1)
                .commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_more, menu);
        return true;
    }

    /**
     * 解决Toolbar中Menu无法同时显示图标和文字的问题
     */
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (menu != null) {
            if (menu.getClass().getSimpleName().equalsIgnoreCase("MenuBuilder")) {
                try {
                    Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    method.setAccessible(true);
                    method.invoke(menu, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }


}
