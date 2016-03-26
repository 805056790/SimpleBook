package graduation.hnust.simplebook.util;

import com.google.gson.Gson;

/**
 * 腾讯QQ监听类
 *
 * @Author : panxin
 * @Date   : 05:31 PM 3/26/16
 * @Email  : panxin109@gmail.com
 */
public class GsonHelper {

    private static class SingletonHolder {
        private static Gson _instance = new Gson();
    }

    private GsonHelper() {
    }

    public static <T> T jsonToBean(String json, Class<T> clazz) {
        return SingletonHolder._instance.fromJson(json, clazz);
    }

    public static <T> String beanToJson(T t) {
        return SingletonHolder._instance.toJson(t);
    }
}
