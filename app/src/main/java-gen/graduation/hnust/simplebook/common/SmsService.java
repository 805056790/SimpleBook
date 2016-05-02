package graduation.hnust.simplebook.common;

import android.content.Context;
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

import graduation.hnust.simplebook.util.ToastUtil;
import graduation.hnust.simplebook.web.api.MessageApi;
import graduation.hnust.simplebook.web.base.HttpUrl;
import graduation.hnust.simplebook.web.base.RequestMethod;

/**
 * 发送短信服务, 用于注册.
 *
 * @Author : panxin
 * @Date : 9:00 PM 3/27/16
 * @Email : panxin109@gmail.com
 */
public final class SmsService {

    private String mobile;
    private Context context;

    /**
     * 发送短信
     *
     * @param context 上下文
     * @param mobile 手机号
     * @return 发送结果
     */
    public Boolean sendSms(Context context, String mobile) {
        // TODO 发短信需要网络?? ...
        if (!NetworkHelper.isNetworkAvailable(context)) {
            ToastUtil.show(context, "网络不可用!");
            return Boolean.FALSE;
        }
        this.mobile = mobile;
        this.context = context;
        new HttpRequest().execute(HttpUrl.DOMAIN+ MessageApi.SEND_SMS);
        return Boolean.TRUE;
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

                Log.i("xxx", result);
                //ToastUtil.show(SmsService.this.context, result);
            }
        }

    }


}
