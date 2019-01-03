package www.leigq.com.blackboxmusic.fragment;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import www.leigq.com.blackboxmusic.R;
import www.leigq.com.blackboxmusic.activity.ListDetailsActivity;
import www.leigq.com.blackboxmusic.model.Leaderboard;
import www.leigq.com.blackboxmusic.model.Song;

import java.util.ArrayList;
import java.util.List;

/**
 * 音乐
 * <p>
 * 创建人：asus <br>
 * 创建时间：2018-12-24 10:40 <br>
 * <p>
 * 修改人： <br>
 * 修改时间： <br>
 * 修改备注： <br>
 * </p>
 */
public class MusicFragment extends Fragment {
    @BindView(R.id.musicRV)
    RecyclerView musicRV;
    Unbinder unbinder;
    @BindView(R.id.recommendRV)
    RecyclerView recommendRV;

    private List<Song> initSong() {
        List<Song> songs = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            Song s = new Song();
            s.setId(i + 1);
            s.setName("歌曲名" + (i + 1));
            s.setAlbum("专辑名" + (i + 1));
            s.setSinger("歌手名" + (i + 1));
            songs.add(s);
        }

        return songs;
    }

    private List<Leaderboard> initData() {

        List<Leaderboard> leaderboards = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            Leaderboard l = new Leaderboard();
            l.setId(i + 1);
            l.setName("榜单名称");
            l.setImg("http://www.xiaoluo666.cn/musicPlayer/static/image/11.jpg");
            l.setDesc("榜单描述");
            leaderboards.add(l);
        }

        return leaderboards;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_music, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext()); //线性，垂直或水平布局
        //设置为水平排列，用setOrientation方法设置
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        //设置recyclerView的布局管理器
        musicRV.setLayoutManager(layoutManager);

        //榜单适配器
        musicRV.setAdapter(new BaseQuickAdapter<Leaderboard, BaseViewHolder>(R.layout.leaderboard_item, initData()) {
            @Override
            protected void convert(final BaseViewHolder holder, Leaderboard l) {
                holder.setText(R.id.nameTV, l.getName());
                holder.setText(R.id.descTV, l.getDesc());

                holder.setOnClickListener(R.id.LinearLayout, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(mContext, holder.getPosition() + "", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getContext(), ListDetailsActivity.class);
                        i.putExtra("hash", "X01121212");
                        i.putExtra("name", "第" + holder.getAdapterPosition() + "个榜单");
                        i.putExtra("desc", "第" + holder.getAdapterPosition() + "个榜单的简介");
                        startActivity(i);
                    }
                });
            }

        });

        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this.getContext()); //线性，垂直或水平布局
        layoutManager1.setOrientation(LinearLayoutManager.VERTICAL);

        //设置recyclerView的布局管理器
        recommendRV.setLayoutManager(layoutManager1);

        //推荐歌曲适配器
        recommendRV.setAdapter(new BaseQuickAdapter<Song, BaseViewHolder>(R.layout.song_item, initSong()) {
            @Override
            protected void convert(final BaseViewHolder holder, Song s) {
                holder.setText(R.id.songIdTV, holder.getPosition() + 1 + "");
                holder.setText(R.id.nameTV, s.getName() + "-" + s.getSinger());
                holder.setImageResource(R.id.imageButton, R.drawable.ic_download);

                holder.setOnClickListener(R.id.imageButton, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(mContext, "下载" + holder.getPosition(), Toast.LENGTH_SHORT).show();

                        //下载路径，如果路径无效了，可换成你的下载路径
                        String url = "http://www.xiaoluo666.cn/musicPlayer/webadvice/upload.html?u=55479EE9AAD33F4D0C4EF9B7436BE277";
                        //创建下载任务,downloadUrl就是下载链接
                        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
                        //指定下载路径和下载文件名
                        request.setDestinationInExternalPublicDir("黑盒音乐", "测试下载音乐" + ".mp3");
                        //获取下载管理器
                        DownloadManager downloadManager= (DownloadManager) getContext().getSystemService(Context.DOWNLOAD_SERVICE);
                        //将下载任务加入下载队列，否则不会进行下载
                        downloadManager.enqueue(request);
                    }
                });
            }
        });


        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
