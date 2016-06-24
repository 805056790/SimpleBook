package graduation.hnust.simplebook.common;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Http工具类
 *
 * @Author : panxin109@gmail.com
 * @Date : 4:45 PM 4/4/16
 */
public class HttpHelper {

    /**
     * Http POST获取请求参数的String形式
     *
     * @param context 参数
     * @return 请求参数字符创
     * @throws UnsupportedEncodingException
     */
    public static String getPostDataString(Map<String, Object> context) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, Object> entry : context.entrySet()){
            if (first) {
                first = false;
            }else {
                result.append("&");
            }
            // 设置参数
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue().toString(), "UTF-8"));
        }

        return result.toString();
    }
}
