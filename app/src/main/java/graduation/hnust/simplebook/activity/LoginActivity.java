package graduation.hnust.simplebook.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.tencent.tauth.Tencent;

import graduation.hnust.simplebook.R;
import graduation.hnust.simplebook.activity.listener.LoginActivityListener;

/**
 * Login view
 *
 * @Author : panxin
 * @Date : 11:18 AM 3/26/16
 * @Email : panxin109@gmail.com
 */
public class LoginActivity extends Activity {

    public static LoginActivity activityInstance;

    private EditText editUserName;
    private EditText editPassword;
    private TextView txtForgetPassword;
    private TextView txtRegister;
    private Button btnLogin;
    private ImageView imgBtnBack;
    private ImageView imgBtnLoginQq;
    private ImageView imgBtnLoginWechat;
    private ImageView imgBtnLoginWeibo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        activityInstance = this;

        // init something necessary
        initViews();
        setEvents();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // login by qq callback
        Tencent.onActivityResultData(requestCode, resultCode, data, null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 获得控件信息
     */
    private void initViews() {
        editUserName = (EditText) findViewById(R.id.edit_user_name);
        editPassword = (EditText) findViewById(R.id.edit_password);
        txtForgetPassword = (TextView) findViewById(R.id.txt_forget_password);
        txtRegister = (TextView) findViewById(R.id.txt_quick_register);
        btnLogin = (Button) findViewById(R.id.btn_login);
        imgBtnBack = (ImageView) findViewById(R.id.img_btn_back);
        imgBtnLoginQq = (ImageView) findViewById(R.id.img_btn_login_qq);
        imgBtnLoginWechat = (ImageView) findViewById(R.id.img_btn_login_wechat);
        imgBtnLoginWeibo = (ImageView) findViewById(R.id.img_btn_login_weibo);
    }

    /**
     * 设置事件
     */
    private void setEvents() {
        txtForgetPassword.setOnClickListener(new LoginActivityListener());
        txtRegister.setOnClickListener(new LoginActivityListener());
        btnLogin.setOnClickListener(new LoginActivityListener());
        imgBtnBack.setOnClickListener(new LoginActivityListener());
        imgBtnLoginQq.setOnClickListener(new LoginActivityListener());
        imgBtnLoginWechat.setOnClickListener(new LoginActivityListener());
        imgBtnLoginWeibo.setOnClickListener(new LoginActivityListener());
    }
}
