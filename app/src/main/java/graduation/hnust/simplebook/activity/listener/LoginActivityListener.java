package graduation.hnust.simplebook.activity.listener;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.tencent.tauth.Tencent;

import graduation.hnust.simplebook.R;
import graduation.hnust.simplebook.activity.LoginActivity;
import graduation.hnust.simplebook.constants.AppConstants;
import graduation.hnust.simplebook.listener.BaseUiListener;
import graduation.hnust.simplebook.util.ToastUtil;

/**
 * Event Listener for the LoginActivity
 * @see graduation.hnust.simplebook.activity.LoginActivity
 *
 * @Author : panxin
 * @Date : 2:55 PM 3/26/16
 * @Email : panxin109@gmail.com
 */
public class LoginActivityListener implements View.OnClickListener{

    private Context context;

    private Tencent mTencent;
    private BaseUiListener baseUiListener;

    @Override
    public void onClick(View view) {
        context = view.getContext();
        int id = view.getId();
        switch (id) {
            case R.id.img_btn_back:         // 返回上个界面, 这样做肯定太暴力了?!!
                LoginActivity.activityInstance.finish();
                break;
            case R.id.btn_login:            // 平台账户登录
                login();
                break;
            case R.id.txt_forget_password:  // 忘记密码
                forgetPassword();
                break;
            case R.id.txt_quick_register:   // 注册
                register();
                break;
            case R.id.img_btn_login_wechat: // 微信登录
                loginWechat();
                break;
            case R.id.img_btn_login_qq:     // QQ登录
                loginQq();
                break;
            case R.id.img_btn_login_weibo:  // 新浪微博登录
                loginWeibo();
                break;
            default:
                break;
        }
    }

    /**
     * 平台账号登录
     */
    private void login() {
        // TODO
        ToastUtil.show(context, "还没有该功能login");
    }

    /**
     * 忘记密码 ? 找回密码
     */
    private void forgetPassword() {
        // TODO
        ToastUtil.show(context, "还没有该功能getPassword");
    }

    /**
     * 平台注册
     */
    private void register() {
        // TODO
        ToastUtil.show(context, "还没有该功能register");
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
        Log.i("login", "start login by qq");
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

}
