package www.leigq.com.blackboxmusic.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import www.leigq.com.blackboxmusic.R;
import www.leigq.com.blackboxmusic.model.Song;
import www.leigq.com.blackboxmusic.utils.SongUtils;

import java.io.File;
import java.util.List;

/**
 * 本地
 * <p>
 * 创建人：asus <br>
 * 创建时间：2018-12-24 10:40 <br>
 * <p>
 * 修改人： <br>
 * 修改时间： <br>
 * 修改备注： <br>
 * </p>
 */
public class LocalFragment extends Fragment implements BaseQuickAdapter.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {
    //    @BindView(R.id.SearchView)
//    android.support.v7.widget.SearchView SearchView;
    Unbinder unbinder;
    @BindView(R.id.localRV)
    RecyclerView localRV;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;

    private List<Song> songs;

    BaseQuickAdapter localadapter;

    private void initView() {
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary,
                R.color.colorPrimary,
                R.color.colorPrimary,
                R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(this);
//        initSearchView();

        songs = SongUtils.getAllMusic(getContext());
        //查询所有歌曲
        initLocalList();
    }

    /**
     * 初始化本地音乐列表
     */
    private void initLocalList() {
        /*
         * 本地音乐
         * */
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext()); //线性，垂直或水平布局
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        //设置recyclerView的布局管理器
        localRV.setLayoutManager(layoutManager);

        localadapter = new BaseQuickAdapter<Song, BaseViewHolder>(R.layout.song_item, songs) {
            @Override
            protected void convert(BaseViewHolder holder, final Song s) {
                holder.setText(R.id.songIdTV, holder.getPosition() + 1 + "");
                holder.setText(R.id.nameTV, s.getName());
                holder.setText(R.id.singerTV, s.getSinger());
                holder.setImageResource(R.id.imageButton, R.drawable.ic_share_blue);

                holder.setOnClickListener(R.id.imageButton, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //分享当前歌曲
                        Uri uri = Uri.fromFile(new File(s.getPath()));
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("audio/*");
                        intent.putExtra(Intent.EXTRA_STREAM, uri);
                        startActivity(Intent.createChooser(intent, "请选择需要分享的地方："));
                    }
                });
            }
        };

        //更换动画
        //ALPHAIN, SCALEIN, SLIDEIN_BOTTOM, SLIDEIN_LEFT, SLIDEIN_RIGHT, //SLIDEIN_LEFT_RIGHT, SLIDEIN_BOTTOM_TOP, CUSTOMIN
        localadapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);

        //Item 点击事件
        localadapter.setOnItemClickListener(this);

        // 没有数据的时候默认显示该布局
        View rootView = LayoutInflater.from(this.getContext()).inflate(R.layout.no_song, null);
        localadapter.setEmptyView(rootView);

        //本地歌曲
        localRV.setAdapter(localadapter);

        swipeRefresh.setRefreshing(false);
    }

    /**
     * 初始化搜索框
     */
//    private void initSearchView() {

    // 设置搜索文本监听
//        SearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            // 当点击搜索按钮时触发该方法
//            public boolean onQueryTextSubmit(String query) {
//
//                System.out.println("query======" + query);
//
//                if (query != null || !query.equals("")) {
//                    //根据歌名搜索歌曲
//                    List<Song> songs = SongUtils.getMusicByName(getContext(), query);
//
//                    System.out.println("onQueryTextChange === " + songs);
//
//                    localadapter.addData(songs);
//
//                    //更新适配器
//                    localadapter.notifyDataSetChanged();
//                } else {
//                    //查询所有歌曲
//                    initLocalList(SongUtils.getAllMusic(getContext()));
//                }
//                return false;
//            }
//
//            @Override
//            // 当搜索内容改变时触发该方法
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        });
//
//    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_local, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        // https://blog.csdn.net/guiping_ding/article/details/78502290
        // 解决exposed beyond app through ClipData.Item.getUri() 错误
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();

        initView();
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * 歌曲列表item点击
     *
     * @param baseQuickAdapter
     * @param view
     * @param i
     */
    @Override
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

        Intent mIntent = new Intent();
        mIntent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.parse(songs.get(i).getPath());
        mIntent.setDataAndType(uri, "audio/MP3");
        startActivity(mIntent);

    }


    @Override
    public void onRefresh() {
        songs = SongUtils.getAllMusic(getContext());
        initLocalList();
    }
}
