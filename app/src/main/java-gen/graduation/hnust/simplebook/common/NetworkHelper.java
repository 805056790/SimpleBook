package graduation.hnust.simplebook.common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @Author : panxin109@gmail.com
 * @Date : 1:27 PM 4/10/16
 */
public class NetworkHelper {

    /**
     * 检查网络状态是否可用
     *
     * @param context 上下文
     * @return 网络是否可用
     */
    public static Boolean isNetworkAvailable(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

}
