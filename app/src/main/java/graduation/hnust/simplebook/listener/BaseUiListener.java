package graduation.hnust.simplebook.listener;

import android.content.Context;
import android.util.Log;

import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import graduation.hnust.simplebook.model.QQInfoModel;
import lombok.Getter;

/**
 * 腾讯QQ监听类
 *
 * @Author : panxin
 * @Date   : 11:08 PM 3/18/16
 * @Email  : panxin109@gmail.com
 */
public class BaseUiListener implements IUiListener {

    @Getter
    private QQInfoModel model;
    private Tencent tencent;
    private Context context;

    public BaseUiListener(Tencent tencent, Context context) {
        this.tencent = tencent;
        this.context = context;
    }

    @Override
    public void onComplete(Object o) {
        doComplete(o);
    }

    @Override
    public void onError(UiError uiError) {
        Log.i("qq.error", uiError.toString());
    }

    @Override
    public void onCancel() {

    }

    private void doComplete(Object o) {
        JSONObject object = null;
        try{
            object = new JSONObject(o.toString());
            Iterator<String> it = object.keys();
            while (it.hasNext()){
                String key = it.next();
                Log.i("qq_info", "JSON  key=" + key + ", value=" + object.get(key));
            }
            getUserInfo(o);
        }catch (Exception e){
            Log.e("failed to login by qq.", e.toString());
        }
    }

    /**
     * 获取用户详细信息
     *
     * @param o
     * @throws JSONException
     */
    private void getUserInfo(Object o) throws JSONException {
        JSONObject obj = new JSONObject(o.toString());
        tencent.setAccessToken(obj.getString(Constants.PARAM_ACCESS_TOKEN), obj.getString(Constants.PARAM_EXPIRES_IN));
        tencent.setOpenId(obj.getString(Constants.PARAM_OPEN_ID));

        UserInfo info = new UserInfo(context, tencent.getQQToken());
        info.getUserInfo(new IUiListener() {
            @Override
            public void onComplete(Object o) {
                /*
                  JSONObject obj = (JSONObject) msg.obj;
                try{
                    String imgUrl = obj.getString("figureurl_qq_1");
                    String nickName = obj.getString("nickname");

                    Log.i("qq_info", "imageUrl="+imgUrl);
                    Log.i("qq_info", "nickName="+nickName);

                    txt.setText(nickName);
                    Picasso.with(MainActivity.this).load(imgUrl).resize(100,100).centerCrop().into(qqHeadImage);
                }catch (Exception e){
                    e.printStackTrace();
                }
                   */
                Log.i("qq_info", "最后一次尝试 data="+o.toString());
                JSONObject object = null;
                try {
                    object = new JSONObject(o.toString());
                    Iterator<String> it = object.keys();
                    while (it.hasNext()){
                        String key = it.next();
                        Log.i("qq_info", "JSON  key="+key+", value="+object.get(key));
                    }
                    String imgUrl = object.getString("figureurl_qq_1");
                    String nickName = object.getString("nickname");
                    model = new QQInfoModel();
                    model.setFigureUrlQq1(imgUrl);
                    model.setNickname(nickName);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(UiError uiError) {

            }

            @Override
            public void onCancel() {

            }
        });
    }

}
