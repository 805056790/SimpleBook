package graduation.hnust.simplebook.web.services;

import android.os.AsyncTask;
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
import java.net.URL;
import java.util.Map;

import graduation.hnust.simplebook.common.GsonHelper;
import graduation.hnust.simplebook.common.HttpHelper;
import graduation.hnust.simplebook.enums.Users;
import graduation.hnust.simplebook.model.User;
import graduation.hnust.simplebook.web.api.UserApi;
import graduation.hnust.simplebook.web.base.HttpUrl;
import graduation.hnust.simplebook.web.base.RequestMethod;

/**
 * @Author : panxin109@gmail.com
 * @Date : 10:51 PM 4/20/16
 */
public class UserWebService {

    public Long login(String userName, String password) throws Exception {
        new HttpRequest().execute();
        return null;
    }

    /**
     * 发送HTTP请求
     */
    private class HttpRequest extends AsyncTask<String, Void, String> {

        /**
         * URL 作为参数, 获取并处理网页返回的数据. 执行完毕后, 返回一个结果字符串.
         * @param urls 请求url
         * @return 结果
         */
        @Override
        protected String doInBackground(String... urls) {
            try {
                doRequest(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * 接收结果字符串并处理
         *
         * @param result 请求结果字符串
         */
        @Override
        protected void onPostExecute(String result) {
            Log.i("simplebook", " -- -- >" + result);
        }

        /**
         * 发送请求
         *
         * @param requestUrl 请求URL
         * @throws IOException
         */
        private void doRequest(String requestUrl) throws IOException {
            // 创建连接
            URL url = new URL(HttpUrl.DOMAIN + UserApi.LOGIN);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod(RequestMethod.POST);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);

            Map<String, Object> context = Maps.newHashMap();
            context.put("loginBy", "18673231309");
            context.put("password", "123456");
            context.put("loginType", Users.LoginType.MOBILE.value());

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(HttpHelper.getPostDataString(context));

            writer.flush();
            writer.close();
            os.close();

            // 响应处理
            int response = conn.getResponseCode();
            if (response == HttpURLConnection.HTTP_OK) {
                InputStream in = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                String result = reader.readLine();

                Log.i("yyy", result);

                // json 2 bean
                User user = GsonHelper.json2Bean(result, User.class);

                Log.i("xxx", result);
            }else {
                InputStream in = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                String result = reader.readLine();
                Log.i("xxx出错啦", result);
            }
        }

    }


}
