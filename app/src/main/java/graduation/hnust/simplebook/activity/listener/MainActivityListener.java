package graduation.hnust.simplebook.activity.listener;

import android.content.Context;
import android.view.View;

import graduation.hnust.simplebook.MainActivity;
import graduation.hnust.simplebook.R;
import graduation.hnust.simplebook.activity.LoginActivity;
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
                ActivityHelper.showActivity(context, LoginActivity.class);
                break;
            default:
                break;
        }
    }
}
