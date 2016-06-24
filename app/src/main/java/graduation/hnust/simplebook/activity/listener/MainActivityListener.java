package graduation.hnust.simplebook.activity.listener;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import graduation.hnust.simplebook.MainActivity;
import graduation.hnust.simplebook.R;
import graduation.hnust.simplebook.activity.LoginActivity;
import graduation.hnust.simplebook.activity.UserInfoActivity;
import graduation.hnust.simplebook.constants.KeyConstants;
import graduation.hnust.simplebook.util.ActivityHelper;

/**
 * Event Listener for the MainActivity
 * @see graduation.hnust.simplebook.MainActivity
 *
 * @Author : panxin
 * @Date : 11:29 AM 3/26/16
 * @Email : panxin109@gmail.com
 */
public class MainActivityListener implements View.OnClickListener {

    private Context context;
    private Object element;

    public MainActivityListener() {}

    public MainActivityListener(Object element) {
        this.element = element;
    }

    @Override
    public void onClick(View view) {
        context = view.getContext();
        int id = view.getId();
        switch (id) {
            case R.id.side_menu_image_head:
                start2Login();
                break;
            default:
                break;
        }
    }

    /**
     * 用户未登录, 进入登录界面<br/>
     * 用户已登录, 进入个人信息界面
     */
    private void start2Login() {
        if (MainActivity.instance.getCurrentUser() == null) {
            Intent intent = new Intent(context, LoginActivity.class);
            MainActivity.instance.startActivityForResult(intent, MainActivity.LOGIN_RESULT);
        }else {
            Intent intent = new Intent(context, UserInfoActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(KeyConstants.LOGIN_USER, MainActivity.instance.getCurrentUser());
            intent.putExtras(bundle);
            MainActivity.instance.startActivityForResult(intent, MainActivity.ADD_ACCOUNT_ITEM);
        }
    }
}
