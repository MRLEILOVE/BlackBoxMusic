package www.leigq.com.blackboxmusic.fragment;

import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import www.leigq.com.blackboxmusic.R;
import www.leigq.com.blackboxmusic.activity.RewardActivity;

/**
 * 关于
 * <p>
 * 创建人：asus <br>
 * 创建时间：2018-12-24 10:40 <br>
 * <p>
 * 修改人： <br>
 * 修改时间： <br>
 * 修改备注： <br>
 * </p>
 */
public class AboutFragment extends Fragment {
    @BindView(R.id.authorRL)
    RelativeLayout authorRL;
    @BindView(R.id.updateRL)
    RelativeLayout updateRL;
    @BindView(R.id.webRL)
    RelativeLayout webRL;
    @BindView(R.id.qqRL)
    RelativeLayout qqRL;
    @BindView(R.id.shareRL)
    RelativeLayout shareRL;
    @BindView(R.id.RewardRL)
    RelativeLayout RewardRL;
    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_about, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.authorRL, R.id.updateRL, R.id.webRL, R.id.qqRL, R.id.shareRL, R.id.RewardRL})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.authorRL:
                ClipboardManager cm = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                // 将文本内容放到系统剪贴板里。
                cm.setText("111111");
                Toast.makeText(getContext(), "微信号复制成功，可以添加好友了。", Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                ComponentName cmp=new ComponentName("com.tencent.mm","com.tencent.mm.ui.LauncherUI");
                intent.setAction(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setComponent(cmp);
                startActivity(intent);
                break;
            case R.id.updateRL:
                break;
            case R.id.webRL:
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("http://www.xiaoluo666.cn"));
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                break;
            case R.id.qqRL:
                /****************
                 *
                 * 发起添加群流程。群号：逼格BiGe(572031397) 的 key 为： _h9t7-HeIk6iVteamzuqU7P9c269Zsf1
                 * 调用 joinQQGroup(_h9t7-HeIk6iVteamzuqU7P9c269Zsf1) 即可发起手Q客户端申请加群 逼格BiGe(572031397)
                 *
                 * @param key 由官网生成的key
                 * @return 返回true表示呼起手Q成功，返回fals表示呼起失败
                 ******************/
                Intent intent1 = new Intent();
                intent1.setData(Uri.parse("mqqopensdkapi://bizAgent/qm/qr?url=http%3A%2F%2Fqm.qq.com%2Fcgi-bin%2Fqm%2Fqr%3Ffrom%3Dapp%26p%3Dandroid%26k%3D" + "_h9t7-HeIk6iVteamzuqU7P9c269Zsf1"));
                // 此Flag可根据具体产品需要自定义，如设置，则在加群界面按返回，返回手Q主界面，不设置，按返回会返回到呼起产品界面    //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                try {
                    startActivity(intent1);
                } catch (Exception e) {
                    // 未安装手Q或安装的版本不支持
                    Toast.makeText(getContext(), "未安装手Q或安装的版本不支持", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.shareRL:
                //分享APP
                //https://blog.csdn.net/fancheng614/article/details/77923256
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "www.xiaoluo666.cn");
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, "请选择需要分享的地方："));
                break;
            case R.id.RewardRL:
                startActivity(new Intent(this.getContext(), RewardActivity.class));
                break;
        }
    }
}
