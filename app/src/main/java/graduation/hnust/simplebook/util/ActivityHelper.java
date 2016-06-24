package graduation.hnust.simplebook.util;

import android.content.Context;
import android.content.Intent;

/**
 * @Author : panxin
 * @Date : 11:34 AM 3/26/16
 * @Email : panxin109@gmail.com
 */
public final class ActivityHelper {

    public static void showActivity(Context context, Class<?> cls) {
        Intent target = new Intent(context, cls);
        context.startActivity(target);
    }

}
