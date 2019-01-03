package www.leigq.com.blackboxmusic.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import www.leigq.com.blackboxmusic.R;
import www.leigq.com.blackboxmusic.model.Song;

import java.io.File;
import java.util.List;

public class ListDetailsActivity extends AppCompatActivity implements BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.backIB)
    ImageButton backIB;
    @BindView(R.id.nameTV)
    TextView nameTV;
    @BindView(R.id.imgIV)
    ImageView imgIV;
    @BindView(R.id.descTV)
    TextView descTV;
    @BindView(R.id.listRV)
    RecyclerView listRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_details);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        initInfo();
        initList(null);
    }

    private void initList(List<Song> songs) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getApplicationContext()); //线性，垂直或水平布局
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        //设置recyclerView的布局管理器
        listRV.setLayoutManager(layoutManager);

        BaseQuickAdapter<Song, BaseViewHolder> localadapter = new BaseQuickAdapter<Song, BaseViewHolder>(R.layout.song_item, songs) {
            @Override
            protected void convert(BaseViewHolder holder, final Song s) {
                holder.setText(R.id.songIdTV, holder.getPosition() + 1 + "");
                holder.setText(R.id.nameTV, s.getName());
                holder.setText(R.id.singerTV, s.getSinger());
                holder.setImageResource(R.id.imageButton, R.drawable.ic_share_blue);

                holder.setOnClickListener(R.id.imageButton, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

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
        View rootView = LayoutInflater.from(this.getApplicationContext()).inflate(R.layout.no_song, null);
        localadapter.setEmptyView(rootView);

        //本地歌曲
        listRV.setAdapter(localadapter);

    }

    private void initInfo() {
        Intent intent = getIntent();
        String hash = intent.getStringExtra("hash");
        String name = intent.getStringExtra("name");
        String desc = intent.getStringExtra("desc");
        nameTV.setText(name);
        descTV.setText(desc);
    }

    @OnClick(R.id.backIB)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

    }
}
