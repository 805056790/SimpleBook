package graduation.hnust.simplebook.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import graduation.hnust.simplebook.R;
import graduation.hnust.simplebook.activity.listener.LoginActivityListener;
import graduation.hnust.simplebook.base.BaseApplication;
import graduation.hnust.simplebook.constants.AppConstants;
import graduation.hnust.simplebook.constants.KeyConstants;
import graduation.hnust.simplebook.dto.UserDto;
import graduation.hnust.simplebook.greendao.ItemDao;
import graduation.hnust.simplebook.listener.BaseUiListener;
import graduation.hnust.simplebook.model.Item;
import graduation.hnust.simplebook.model.QQInfoModel;
import graduation.hnust.simplebook.model.QQKeys;
import graduation.hnust.simplebook.model.QQTokenModel;
import graduation.hnust.simplebook.model.User;
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

    Tencent tencent;
    private UserDto userDto;

    public static Integer LOGIN_SUCCESS = 1;
    public static Integer LOGIN_FAILED = -1;
    public static Integer QQ_LOGIN_SUCCESS = 3;

    public static Integer REGISTER_SUCCESS = 2;
    public static Integer REGISTER_FAILED = -2;

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
        //imgBtnLoginQq.setOnClickListener(new LoginActivityListener());
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

    /**
     * QQ登录
     *
     * @param v view
     */
    public void loginByQQ(View v) {
        tencent = Tencent.createInstance(AppConstants.QQ_APP_ID, this);
        // BaseUiListener baseUiListener = new BaseUiListener(mTencent, this);
        tencent.login(LoginActivity.this, AppConstants.QQ_SCOPE, new BaseUiListener());
    }

    class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object o) {
            doComplete(o);
        }

        @Override
        public void onError(UiError uiError) {

        }

        @Override
        public void onCancel() {

        }

        private void doComplete(Object o) {
            JSONObject object = null;
            try{
                object = new JSONObject(o.toString());
                Iterator<String> it = object.keys();
                userDto = new UserDto();
                userDto.setUser(new User());
                userDto.setQqInfoModel(new QQInfoModel());
                userDto.setQqTokenModel(new QQTokenModel());
                while (it.hasNext()){
                    String key = it.next();
                    Log.i("qq_info", "JSON  key=" + key + ", value=" + object.get(key));
                }
                userDto.getQqTokenModel().setRet((object.get(QQKeys.Token.ret)  + ""));
                userDto.getQqTokenModel().setPayToken((String) object.get(QQKeys.Token.pay_token));
                userDto.getQqTokenModel().setPf((String) object.get(QQKeys.Token.pf));
                userDto.getQqTokenModel().setQueryAuthorityCost(object.get(QQKeys.Token.query_authority_cost) + "");
                userDto.getQqTokenModel().setExpiresIn(object.get(QQKeys.Token.expires_in) + "");
                userDto.getQqTokenModel().setOpenId((String) object.get(QQKeys.Token.openid));
                userDto.getQqTokenModel().setPfKey((String) object.get(QQKeys.Token.pfkey));
                userDto.getQqTokenModel().setMsg(object.get(QQKeys.Token.msg) + "");
                userDto.getQqTokenModel().setLoginCost(object.get(QQKeys.Token.login_cost) + "");
                userDto.getQqTokenModel().setAccessToken((String) object.get(QQKeys.Token.access_token));

                userDto.getUser().setQqToken((String) object.get(QQKeys.Token.openid));

                getUserInfo(o);
            }catch (Exception e){
                Log.e("failed to login by qq.", e.toString());
            }
        }
    }

    /**
     * 获取用户详细信息
     *
     * @param o
     * @throws JSONException
     */
    private void getUserInfo(Object o) throws JSONException {
        JSONObject obj = new JSONObject(o.toString());
        tencent.setAccessToken(obj.getString(Constants.PARAM_ACCESS_TOKEN), obj.getString(Constants.PARAM_EXPIRES_IN));
        tencent.setOpenId(obj.getString(Constants.PARAM_OPEN_ID));

        UserInfo info = new UserInfo(LoginActivity.this, tencent.getQQToken());
        info.getUserInfo(new IUiListener() {
            @Override
            public void onComplete(Object o) {
                JSONObject object = null;
                try {
                    object = new JSONObject(o.toString());
                    Iterator<String> it = object.keys();
                    while (it.hasNext()){
                        String key = it.next();
                        Log.i("qq_info", "JSON  key="+key+", value="+object.get(key));
                    }
                    userDto.getQqInfoModel().setRet(object.get(QQKeys.Info.ret) + "");
                    userDto.getQqInfoModel().setIsYellowYearVip(object.get(QQKeys.Info.is_yellow_year_vip) + "");
                    userDto.getQqInfoModel().setFigureUrlQq1((String) object.get(QQKeys.Info.figureurl_qq_1));
                    userDto.getQqInfoModel().setNickname((String) object.get(QQKeys.Info.nickname));
                    userDto.getQqInfoModel().setFigureUrlQq2((String) object.get(QQKeys.Info.figureurl_qq_2));
                    userDto.getQqInfoModel().setYellowVipLevel(object.get(QQKeys.Info.yellow_vip_level) + "");
                    userDto.getQqInfoModel().setIsLost(object.get(QQKeys.Info.is_lost) + "");
                    userDto.getQqInfoModel().setMsg(object.get(QQKeys.Info.msg)  + "");
                    userDto.getQqInfoModel().setCity(object.get(QQKeys.Info.city)  + "");
                    userDto.getQqInfoModel().setFigureUrl1((String) object.get(QQKeys.Info.figureurl_1));
                    userDto.getQqInfoModel().setVip(object.get(QQKeys.Info.vip)  + "");
                    userDto.getQqInfoModel().setFigureUrl2((String) object.get(QQKeys.Info.figureurl_2));
                    userDto.getQqInfoModel().setLevel(object.get(QQKeys.Info.level) + "");
                    userDto.getQqInfoModel().setProvince((String) object.get(QQKeys.Info.province));
                    userDto.getQqInfoModel().setGender((String) object.get(QQKeys.Info.gender));
                    userDto.getQqInfoModel().setIsYellowVip(object.get(QQKeys.Info.is_yellow_vip) + "");
                    userDto.getQqInfoModel().setFigureUrl((String) object.get(QQKeys.Info.figureurl));
                    // 返回首页
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(KeyConstants.LOGIN_USER_BY_QQ, userDto);
                    intent.putExtras(bundle);
                    LoginActivity.this.setResult(LoginActivity.QQ_LOGIN_SUCCESS, intent);
                    finish();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(UiError uiError) {

            }

            @Override
            public void onCancel() {

            }
        });
    }
}
