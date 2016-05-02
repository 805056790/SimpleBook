package graduation.hnust.simplebook.activity.listener;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.tencent.tauth.Tencent;

import graduation.hnust.simplebook.R;
import graduation.hnust.simplebook.activity.LoginActivity;
import graduation.hnust.simplebook.common.NetworkHelper;
import graduation.hnust.simplebook.common.SmsService;
import graduation.hnust.simplebook.constants.AppConstants;
import graduation.hnust.simplebook.listener.BaseUiListener;
import graduation.hnust.simplebook.util.ToastUtil;
import graduation.hnust.simplebook.web.services.UserWebService;
import lombok.NoArgsConstructor;

/**
 * Event Listener for the LoginActivity
 * @see graduation.hnust.simplebook.activity.LoginActivity
 *
 * @Author : panxin
 * @Date : 2:55 PM 3/26/16
 * @Email : panxin109@gmail.com
 */
@NoArgsConstructor
public class LoginActivityListener implements View.OnClickListener{

    private Context context;

    private Tencent mTencent;
    private BaseUiListener baseUiListener;

    private Object element;

    public LoginActivityListener(Object element) {
        this.element = element;
    }

    @Override
    public void onClick(View view) {
        context = view.getContext();
        int id = view.getId();
        switch (id) {
            case R.id.img_btn_back:
                // 返回上个界面, 这样做肯定太暴力了?!!
                LoginActivity.activityInstance.finish();
                break;
            case R.id.btn_login:
                // 平台账户登录
                login();
                break;
            case R.id.txt_forget_password:
                // 忘记密码
                forgetPassword();
                break;
            case R.id.txt_quick_register:
                // 快速注册, 进入注册界面
                showRegisterView();
                break;
            case R.id.img_btn_login_wechat:
                // 微信登录
                loginWechat();
                break;
            case R.id.img_btn_login_qq:
                // QQ登录
                loginQq();
                break;
            case R.id.img_btn_login_weibo:
                // 新浪微博登录
                loginWeibo();
                break;
            case R.id.btn_send_sms:
                // 发送短信
                sendSms();
                break;
            case R.id.btn_register:
                // 注册
                register();
                break;
            case R.id.txt_back_login:
                // 返回登录页
                LoginActivity.activityInstance.getLayoutRegister().setVisibility(View.GONE);
                LoginActivity.activityInstance.getLayoutLogin().setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }

    /**
     * 平台账号登录
     */
    private void login() {
        // 获取手机号, 密码
        String mobile = LoginActivity.activityInstance.getEditUserName().getText().toString();
        String password = LoginActivity.activityInstance.getEditPassword().getText().toString();
        UserWebService service = new UserWebService();
        try {
            service.login(mobile, password);
        } catch (Exception e) {
            ToastUtil.show(context, "出错啦!");
            e.printStackTrace();
        }
    }

    /**
     * 忘记密码 ? 找回密码
     */
    private void forgetPassword() {
        // TODO
        ToastUtil.show(context, "还没有该功能getPassword");
    }

    /**
     * 微信登录
     */
    private void loginWechat() {
        // TODO
        ToastUtil.show(context, "还没有该功能wechat");
    }

    /**
     * QQ 登录
     */
    private void loginQq() {
        // tencent
        mTencent = Tencent.createInstance(AppConstants.QQ_APP_ID, context);
        baseUiListener = new BaseUiListener(mTencent, context);
        mTencent.login(LoginActivity.activityInstance, AppConstants.QQ_SCOPE, baseUiListener);
    }

    /**
     * 微博登录
     */
    private void loginWeibo() {
        // TODO
        ToastUtil.show(context, "还没有该功能weibo");
    }

    /**
     * 发送短信
     */
    private void sendSms() {
        String mobile = String.valueOf(LoginActivity.activityInstance.getEditMobile().getText());
        if (TextUtils.isEmpty(mobile)) {
            return;
        }
        SmsService service = new SmsService();
        service.sendSms(context, mobile);
    }

    /**
     * 进入注册页面
     */
    public void showRegisterView() {
        if (NetworkHelper.isNetworkAvailable(context)) {
            LoginActivity.activityInstance.getLayoutLogin().setVisibility(View.GONE);
            LoginActivity.activityInstance.getLayoutRegister().setVisibility(View.VISIBLE);
        }else {
            ToastUtil.show(context, "请先连接网络!");
        }
    }

    /**
     * 手机注册
     */
    private void register() {
        String mobile = String.valueOf(LoginActivity.activityInstance.getEditMobile().getText());
        String password = String.valueOf(LoginActivity.activityInstance.getEditRegPassword().getText());
        String verifyCode = String.valueOf(LoginActivity.activityInstance.getEditVerifyCode().getText());
        if (TextUtils.isEmpty(verifyCode)) {
            ToastUtil.show(context, "请填写验证码!");
            return;
        }
        // 验证码校验
        SmsService service = new SmsService();
        service.sendSms(context, mobile);
        // 注册
    }

}
