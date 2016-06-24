package graduation.hnust.simplebook.common;

import android.content.Context;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import graduation.hnust.simplebook.util.ToastUtil;

/**
 * @Author : panxin109@gmail.com
 * @Date : 8:37 PM 5/8/16
 */
public class DateHelper {

    private static SimpleDateFormat SDF_LOCAL = new SimpleDateFormat("yyyy-M-d", Locale.CHINA);
    private static SimpleDateFormat SDF_VIEW = new SimpleDateFormat("M月d日", Locale.CHINA);

    /**
     * 日期转字符串<br/>
     * 为了列表展示用
     *
     * @param date 日期
     * @return 结果
     */
    public static String date2String4View(Date date) {
        return SDF_VIEW.format(date);
    }

    /**
     * 日期转字符串
     *
     * @param date 日期
     * @return 结果
     */
    public static String date2String(Date date) {
        return SDF_LOCAL.format(date);
    }

    /**
     * 字符串转日期
     *
     * @param source 字符串
     * @param context 上下文
     * @return 日期
     */
    public static Date string2Date(String source, Context context) {
        try {
            if ("今天".equals(source)) {
                source = date2String(new Date());
            }
            return SDF_LOCAL.parse(source);
        } catch (ParseException e) {
            ToastUtil.show(context, "时间格式解析错误!");
            Log.e("app", e.toString());
            e.printStackTrace();
        }
        return null;
    }

}
