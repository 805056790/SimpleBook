package graduation.hnust.simplebook.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import graduation.hnust.simplebook.R;
import graduation.hnust.simplebook.constants.KeyConstants;
import graduation.hnust.simplebook.dto.UserDto;
import graduation.hnust.simplebook.greendao.UserDao;
import graduation.hnust.simplebook.model.User;

/**
 * 用户信息界面
 *
 * @Author : panxin109@gmail.com
 * @Date : 6:23 PM 5/2/16
 */
public class UserInfoActivity extends Activity implements View.OnClickListener {

    private ImageView imgBack;
    private ImageView headImage;
    private TextView txtNickName;
    private TextView txtLoginChannel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        initViews();
        initData(getIntent());
        imgBack.setOnClickListener(this);
    }

    private void initData(Intent intent) {
        UserDto user = (UserDto) intent.getSerializableExtra(KeyConstants.LOGIN_USER);
        txtNickName.setText(user.getUser().getMobile());
        if (user.getQqInfoModel() != null) {
            txtLoginChannel.setText("当前为QQ登录");
        }else {
            txtLoginChannel.setText("当前为手机登录");
        }
    }

    private void initViews() {
        imgBack = (ImageView) findViewById(R.id.img_btn_back_of_user);
        headImage = (ImageView) findViewById(R.id.img_user_head_image);
        txtNickName = (TextView) findViewById(R.id.txt_user_nickname);
        txtLoginChannel = (TextView) findViewById(R.id.txt_user_login_channel);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.img_btn_back_of_user) {
            this.finish();
        }
    }
}
