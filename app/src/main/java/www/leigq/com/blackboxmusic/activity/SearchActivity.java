package www.leigq.com.blackboxmusic.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wyt.searchbox.SearchFragment;
import com.wyt.searchbox.custom.IOnSearchClickListener;
import www.leigq.com.blackboxmusic.R;
import www.leigq.com.blackboxmusic.model.Song;

import java.util.List;

public class SearchActivity extends AppCompatActivity implements BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.relativeLayout)
    RelativeLayout relativeLayout;
    @BindView(R.id.backIB)
    ImageButton backIB;
    @BindView(R.id.searchIB)
    ImageButton searchIB;
    @BindView(R.id.searchRV)
    RecyclerView searchRV;

    private SearchFragment searchFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        initSearchRV(null);
        initSearchFrag();
    }

    private void initSearchFrag() {
        searchFragment = SearchFragment.newInstance();
        searchFragment.setOnSearchClickListener(new IOnSearchClickListener() {
            @Override
            public void OnSearchClick(String keyword) {
                //这里处理逻辑
                Toast.makeText(getApplicationContext(), keyword, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initSearchRV(List<Song> songs) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext()); //线性，垂直或水平布局
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //设置recyclerView的布局管理器
        searchRV.setLayoutManager(layoutManager);
        BaseQuickAdapter<Song, BaseViewHolder> localadapter = new BaseQuickAdapter<Song, BaseViewHolder>(R.layout.song_item, songs) {
            @Override
            protected void convert(BaseViewHolder holder, final Song s) {

            }
        };
        //Item 点击事件
        localadapter.setOnItemClickListener(this);
        // 没有数据的时候默认显示该布局
        View rootView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.no_song, null);
        localadapter.setEmptyView(rootView);
        searchRV.setAdapter(localadapter);
    }

    @OnClick({R.id.backIB, R.id.searchIB})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.backIB:
                finish();
                break;
            case R.id.searchIB:
                searchFragment.show(getSupportFragmentManager(), SearchFragment.TAG);
                break;
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

    }
}
