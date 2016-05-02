package graduation.hnust.simplebook.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tencent.tauth.Tencent;

import graduation.hnust.simplebook.R;
import graduation.hnust.simplebook.activity.listener.LoginActivityListener;
import lombok.Getter;

/**
 * Login view
 *
 * @Author : panxin
 * @Date : 11:18 AM 3/26/16
 * @Email : panxin109@gmail.com
 */
public class LoginActivity extends Activity {

    // instance of activity
    public static LoginActivity activityInstance;

    //  views of login
    @Getter
    private LinearLayout layoutLogin;
    @Getter
    private EditText editUserName;
    @Getter
    private EditText editPassword;
    private TextView txtForgetPassword;
    private TextView txtRegister;
    @Getter
    private Button btnLogin;
    private ImageView imgBtnBack;
    private ImageView imgBtnLoginQq;
    private ImageView imgBtnLoginWechat;
    private ImageView imgBtnLoginWeibo;

    // views of register
    @Getter
    private LinearLayout layoutRegister;
    @Getter
    private EditText editMobile;
    @Getter
    private EditText editRegPassword;
    @Getter
    private EditText editVerifyCode;
    @Getter
    private Button btnSendSms;
    @Getter
    private Button btnRegister;
    private TextView txtBackLogin;

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
        // views of login
        layoutLogin = (LinearLayout) findViewById(R.id.layout_login);
        editUserName = (EditText) findViewById(R.id.edit_user_name);
        editPassword = (EditText) findViewById(R.id.edit_password);
        txtForgetPassword = (TextView) findViewById(R.id.txt_forget_password);
        txtRegister = (TextView) findViewById(R.id.txt_quick_register);
        btnLogin = (Button) findViewById(R.id.btn_login);
        imgBtnBack = (ImageView) findViewById(R.id.img_btn_back);
        imgBtnLoginQq = (ImageView) findViewById(R.id.img_btn_login_qq);
        imgBtnLoginWechat = (ImageView) findViewById(R.id.img_btn_login_wechat);
        imgBtnLoginWeibo = (ImageView) findViewById(R.id.img_btn_login_weibo);

        // views of register
        layoutRegister = (LinearLayout) findViewById(R.id.layout_register);
        editMobile = (EditText) findViewById(R.id.edit_reg_mobile);
        editRegPassword = (EditText) findViewById(R.id.edit_reg_password);
        editVerifyCode = (EditText) findViewById(R.id.edit_reg_code);
        btnSendSms = (Button) findViewById(R.id.btn_send_sms);
        btnRegister = (Button) findViewById(R.id.btn_register);
        txtBackLogin = (TextView) findViewById(R.id.txt_back_login);

        // init views of login, just set visible
        layoutLogin.setVisibility(View.VISIBLE);
        layoutRegister.setVisibility(View.INVISIBLE);
    }

    /**
     * 设置事件
     */
    private void setEvents() {
        // events if login
        editUserName.setOnClickListener(new LoginActivityListener(editUserName));
        editPassword.setOnClickListener(new LoginActivityListener(editPassword));
        btnLogin.setOnClickListener(new LoginActivityListener());
        txtForgetPassword.setOnClickListener(new LoginActivityListener());
        txtRegister.setOnClickListener(new LoginActivityListener());
        imgBtnBack.setOnClickListener(new LoginActivityListener());
        imgBtnLoginQq.setOnClickListener(new LoginActivityListener());
        imgBtnLoginWechat.setOnClickListener(new LoginActivityListener());
        imgBtnLoginWeibo.setOnClickListener(new LoginActivityListener());

        // events of register
        editMobile.setOnClickListener(new LoginActivityListener(editMobile));
        editRegPassword.setOnClickListener(new LoginActivityListener(editRegPassword));
        editVerifyCode.setOnClickListener(new LoginActivityListener(editVerifyCode));
        btnSendSms.setOnClickListener(new LoginActivityListener());
        btnRegister.setOnClickListener(new LoginActivityListener());
        txtBackLogin.setOnClickListener(new LoginActivityListener());
    }
}
