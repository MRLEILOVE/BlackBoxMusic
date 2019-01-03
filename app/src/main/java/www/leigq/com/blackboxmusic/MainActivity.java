package www.leigq.com.blackboxmusic;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import io.reactivex.functions.Consumer;
import www.leigq.com.blackboxmusic.activity.SearchActivity;
import www.leigq.com.blackboxmusic.fragment.AboutFragment;
import www.leigq.com.blackboxmusic.fragment.LocalFragment;
import www.leigq.com.blackboxmusic.fragment.MusicFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.searchBtn)
    ImageButton searchBtn;
    @BindView(R.id.shareBtn)
    ImageButton shareBtn;
    @BindView(R.id.relativeLayout)
    RelativeLayout relativeLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigation;

    /*
     * 主要有3个Fragment
     * */
    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //动态申请内存存储权限
        RxPermissions rxPermission = new RxPermissions(this);

        rxPermission.requestEach(
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) {
                        if (permission.granted) {
                            // 用户已经同意该权限
                            initView();
                        } else if (permission.shouldShowRequestPermissionRationale) {
                            // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
                            Toast.makeText(MainActivity.this, "用户拒绝了该权限，没有选中『不再询问』", Toast.LENGTH_SHORT).show();
                        } else {
                            // 用户拒绝了该权限，并且选中『不再询问』
                            Toast.makeText(MainActivity.this, "用户拒绝了该权限，并且选中『不再询问』", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    /**
     * 初始化
     */
    private void initView() {
        initBottomNavigationBar();
        initViewPager();
    }

    /**
     * 初始化ViewPager
     */
    private void initViewPager() {
        fragments = new ArrayList<>();
        fragments.add(new MusicFragment());
        fragments.add(new LocalFragment());
        fragments.add(new AboutFragment());

        viewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });

        viewpager.addOnPageChangeListener(this);

    }

    /**
     * 初始化BottomNavigationBar
     */
    private void initBottomNavigationBar() {
        bottomNavigation.setOnNavigationItemSelectedListener(this);
    }

    /**
     * 点击事件
     *
     * @param view
     */
    @OnClick({R.id.searchBtn, R.id.shareBtn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.searchBtn:
                //跳转至搜索页面
                startActivity(new Intent(this, SearchActivity.class));
                break;
            case R.id.shareBtn:
                //分享APP
                //https://blog.csdn.net/fancheng614/article/details/77923256
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "www.xiaoluo666.cn");
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, "请选择需要分享的地方："));
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        bottomNavigation.getMenu().getItem(position).setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        switch (itemId) {
            case R.id.item_music:
                viewpager.setCurrentItem(0);
                break;
            case R.id.item_local:
                viewpager.setCurrentItem(1);
                break;
            case R.id.item_about:
                viewpager.setCurrentItem(2);
                break;
        }
        return false;
    }

}
