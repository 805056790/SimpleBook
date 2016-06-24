package graduation.hnust.simplebook.web.services;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Looper;
import android.util.Log;

import com.google.common.collect.Maps;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import graduation.hnust.simplebook.common.GsonHelper;
import graduation.hnust.simplebook.common.HttpHelper;
import graduation.hnust.simplebook.enums.Users;
import graduation.hnust.simplebook.model.User;
import graduation.hnust.simplebook.service.UserReadService;
import graduation.hnust.simplebook.util.ToastUtil;
import graduation.hnust.simplebook.web.api.UserApi;
import graduation.hnust.simplebook.web.base.HttpUrl;
import graduation.hnust.simplebook.web.base.RequestMethod;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author : panxin109@gmail.com
 * @Date : 10:51 PM 4/20/16
 */
public class UserWebService {

    public static final String LOGIN_ACTION = "login";
    public static final String REGISTER_ACTION = "register";

    /**
     * 用户登录
     * (something old)
     *
     * @param userName 用户名
     * @param password 密码
     * @return 登录用户数据
     * @throws Exception
     */
    @Deprecated
    public User login(String userName, String password) throws Exception {
        String result = new HttpRequest().execute(HttpUrl.DOMAIN + UserApi.LOGIN,
                userName, password, UserWebService.LOGIN_ACTION).get();
        if (result == null) {
            return null;
        }
        Log.i("app", result);
        return GsonHelper.json2Bean(result, User.class);
    }

    /**
     * 用户注册
     * (something old)
     *
     * @param user 用户信息
     * @return 用户ID
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Deprecated
    public Long register(User user) throws ExecutionException, InterruptedException {
        String result = new HttpRequest().execute(HttpUrl.DOMAIN + UserApi.REGISTER,
                user.getMobile(), user.getPassword(), UserWebService.REGISTER_ACTION).get();
        if (result == null) {
            return null;
        }
        Log.i("app", result);
        return Long.parseLong(result);
    }

    /**
     * 发送HTTP请求
     */
    private class HttpRequest extends AsyncTask<String, Void, String> {

        private String userName;
        private String password;
        private String action;

        /**
         * URL 作为参数, 获取并处理网页返回的数据. 执行完毕后, 返回一个结果字符串.
         * @param urls 请求url
         * @return 结果
         */
        @Override
        protected String doInBackground(String... urls) {
            try {
                this.userName = urls[1];
                this.password = urls[2];
                this.action = urls[3];
                return doRequest(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        /**
         * 接收结果字符串并处理
         *
         * @param result 请求结果字符串
         */
        @Override
        protected void onPostExecute(String result) {
        }

        /**
         * 发送请求
         *
         * @param requestUrl 请求URL
         * @throws IOException
         */
        private String doRequest(String requestUrl) throws IOException {
            try {
                if (action.equals(UserWebService.LOGIN_ACTION)) {
                    return login(requestUrl, userName, password);
                }else {
                    return register(requestUrl, userName, password);
                }
            }catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

    }

    private String register(String requestUrl, String mobile, String password) throws Exception {
        // 创建连接
        URL url = new URL(requestUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000);
        conn.setConnectTimeout(15000);
        conn.setRequestMethod(RequestMethod.POST);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setUseCaches(false);

        Map<String, Object> context = Maps.newHashMap();
        context.put("mobile", mobile);
        context.put("password", password);
        context.put("userType", 1);
        context.put("loginType", Users.LoginType.MOBILE.value());

        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
        writer.write(HttpHelper.getPostDataString(context));

        writer.flush();
        writer.close();
        os.close();

        // 响应处理
        int response = conn.getResponseCode();
        String result = null;
        try {
            // 请求成功
            if (response == HttpURLConnection.HTTP_OK) {
                InputStream in = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                result = reader.readLine();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private String login(String requestUrl, String userName, String password) throws Exception {
        // 创建连接
        URL url = new URL(requestUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000);
        conn.setConnectTimeout(15000);
        conn.setRequestMethod(RequestMethod.POST);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setUseCaches(false);

        Map<String, Object> context = Maps.newHashMap();
        context.put("loginBy", userName);
        context.put("password", password);
        context.put("loginType", Users.LoginType.MOBILE.value());

        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
        writer.write(HttpHelper.getPostDataString(context));

        writer.flush();
        writer.close();
        os.close();

        // 响应处理
        int response = conn.getResponseCode();
        String result = null;
        try {
            // 请求成功
            if (response == HttpURLConnection.HTTP_OK) {
                InputStream in = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                result = reader.readLine();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
