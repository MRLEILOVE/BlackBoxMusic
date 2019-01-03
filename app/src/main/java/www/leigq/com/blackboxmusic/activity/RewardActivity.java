package www.leigq.com.blackboxmusic.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import www.leigq.com.blackboxmusic.R;
import www.leigq.com.blackboxmusic.utils.AlipayUtil;
import www.leigq.com.blackboxmusic.utils.ImgUtils;

import java.io.InputStream;

public class RewardActivity extends AppCompatActivity {

    @BindView(R.id.backIV)
    ImageButton backIV;
    @BindView(R.id.alipayIV)
    ImageView alipayIV;
    @BindView(R.id.wechartIV)
    ImageView wechartIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward);
        ButterKnife.bind(this);

        //将支付宝和微信二维码保存至手机相册
        @SuppressLint("ResourceType") Bitmap bitmap1 = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.alipay));
        @SuppressLint("ResourceType") Bitmap bitmap2 = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.wechartpay));

        ImgUtils.saveImageToGallery(this, bitmap1, "支付宝");
        ImgUtils.saveImageToGallery(this, bitmap2, "微信");

    }

    @OnClick({R.id.backIV, R.id.alipayIV, R.id.wechartIV})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.backIV:
                finish();
                break;
            case R.id.alipayIV:
                if(AlipayUtil.hasInstalledAlipayClient(this)){
                    //第二个参数代表要给被支付的二维码code  可以在用草料二维码在线生成
                    AlipayUtil.startAlipayClient(this, "FKX05594FTJAIMZAPAYO25");
                }else{
                    Toast.makeText(this, "没有检测到支付宝客户端", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.wechartIV:
                try {
                    //利用Intent打开微信
                    Uri uri = Uri.parse("weixin://");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                } catch (Exception e) {
                    //若无法正常跳转，在此进行错误处理
                    Toast.makeText(this, "没有检测到微信客户端", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
