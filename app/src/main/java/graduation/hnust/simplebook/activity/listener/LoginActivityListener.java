package graduation.hnust.simplebook.activity.listener;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.common.collect.Maps;
import com.tencent.tauth.Tencent;

import java.util.Map;

import graduation.hnust.simplebook.R;
import graduation.hnust.simplebook.activity.LoginActivity;
import graduation.hnust.simplebook.base.GsonRequest;
import graduation.hnust.simplebook.common.GsonHelper;
import graduation.hnust.simplebook.common.NetworkHelper;
import graduation.hnust.simplebook.common.SmsService;
import graduation.hnust.simplebook.constants.AppConstants;
import graduation.hnust.simplebook.constants.KeyConstants;
import graduation.hnust.simplebook.dto.UserDto;
import graduation.hnust.simplebook.listener.BaseUiListener;
import graduation.hnust.simplebook.model.User;
import graduation.hnust.simplebook.util.ToastUtil;
import graduation.hnust.simplebook.web.api.UserApi;
import graduation.hnust.simplebook.web.base.HttpUrl;
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
        if (TextUtils.isEmpty(mobile) || TextUtils.isEmpty(password)) {
            ToastUtil.show(context, "请填写手机号和密码!");
            return;
        }
        // 登录
        UserWebService service = new UserWebService();
        try {
            User user = service.login(mobile, password);
            UserDto userDto = new UserDto();
            if (user == null) {
                ToastUtil.show(context, "登录失败, 用户名后密码错误!");
                return;
            }
            // ToastUtil.show(context, "登录成功, 用户名: "+user.getMobile());
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            userDto.setUser(user);
            bundle.putSerializable(KeyConstants.LOGIN_USER, userDto);
            intent.putExtras(bundle);
            LoginActivity.activityInstance.setResult(LoginActivity.LOGIN_SUCCESS, intent);
            LoginActivity.activityInstance.finish();
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
        ToastUtil.show(context, "调用QQ登录.");
        Tencent mTencent = Tencent.createInstance(AppConstants.QQ_APP_ID, context);
        BaseUiListener baseUiListener = new BaseUiListener(mTencent, context);
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
        final String mobile = String.valueOf(LoginActivity.activityInstance.getEditMobile().getText());
        if (TextUtils.isEmpty(mobile)) {
            ToastUtil.show(context, "请填写手机号!");
            return;
        }
        String url = HttpUrl.DOMAIN+ UserApi.USER_EXISTS + "?loginType=1&loginBy="+mobile;
        RequestQueue queue = Volley.newRequestQueue(context);
        GsonRequest<String> request = new GsonRequest<String>(
                Request.Method.GET,
                url,
                String.class,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if ("true".equals(response)) {
                            ToastUtil.show(context, "注册失败, 请检查您的网络状态!");
                        }else {
                            SmsService service = new SmsService();
                            Boolean result = service.sendSms(context, mobile);
                            if (!result) {
                                ToastUtil.show(context, "短信发送失败, 请检查您的网络状态!");
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        ToastUtil.show(context, "注册失败, 请检查您的网络状态!");
                    }
                },
                GsonHelper.getBeanGson()){
        };
        queue.add(request);
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
        final String mobile = String.valueOf(LoginActivity.activityInstance.getEditMobile().getText());
        final String password = String.valueOf(LoginActivity.activityInstance.getEditRegPassword().getText());
        final String verifyCode = String.valueOf(LoginActivity.activityInstance.getEditVerifyCode().getText());
        if (TextUtils.isEmpty(mobile)) {
            ToastUtil.show(context, "请填写手机号!");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            ToastUtil.show(context, "请填写密码!");
            return;
        }
        if (TextUtils.isEmpty(verifyCode)) {
            ToastUtil.show(context, "请填写验证码!");
            return;
        }
        // ...
        String url = HttpUrl.DOMAIN+ UserApi.USER_EXISTS + "?loginType=1&loginBy="+mobile;
        RequestQueue queue = Volley.newRequestQueue(context);
        GsonRequest<String> request = new GsonRequest<String>(
                Request.Method.GET,
                url,
                String.class,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if ("true".equals(response)) {
                            ToastUtil.show(context, "注册失败, 请检查您的网络状态!");
                        }else {
                            doRegister(mobile, password, verifyCode);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        ToastUtil.show(context, "注册失败, 请检查您的网络状态!");
                    }
                },
                GsonHelper.getBeanGson()){
        };
        queue.add(request);
    }

    /**
     * 注册
     *
     * @param mobile 手机号
     * @param password 密码
     * @param verifyCode 验证码
     */
    private void doRegister(String mobile, String password, String verifyCode) {
        // 验证码校验
        SmsService service = new SmsService();
        Boolean result = service.verifySmsCode(context, mobile, verifyCode);
        if (!result) {
            ToastUtil.show(context, "验证码不正确, 请重新输入!");
            return;
        }
        // 注册
        try {
            UserDto userDto = new UserDto();
            UserWebService userWebService = new UserWebService();
            User user = new User();
            user.setMobile(mobile);
            user.setPassword(password);
            Long userId = userWebService.register(user);
            user.setId(userId);
            // 返回用户信息
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            userDto.setUser(user);
            bundle.putSerializable(KeyConstants.LOGIN_USER, userDto);
            intent.putExtras(bundle);
            LoginActivity.activityInstance.setResult(LoginActivity.REGISTER_SUCCESS, intent);
            LoginActivity.activityInstance.finish();
        }catch (Exception e) {
            ToastUtil.show(context, "用户注册失败!");
            e.printStackTrace();
        }
    }
}
