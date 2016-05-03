package graduation.hnust.simplebook.activity.listener;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.GridView;

import graduation.hnust.simplebook.R;
import graduation.hnust.simplebook.activity.KeepAccountActivity;
import graduation.hnust.simplebook.model.ConsumeType;
import graduation.hnust.simplebook.model.Item;
import graduation.hnust.simplebook.util.ToastUtil;

/**
 * @Author : panxin109@gmail.com
 * @Date : 1:07 PM 5/2/16
 */
public class KeepAccountListener implements View.OnClickListener {

    private Context context;

    public KeepAccountListener(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        Integer id = v.getId();
        switch (id) {
            case R.id.txt_save:
                saveItem();
                break;
            case R.id.txt_cancel:
                back();
                break;
            default:
                break;
        }
    }

    /**
     * 取消
     */
    private void back() {
        KeepAccountActivity.activityInstance.finish();
    }

    /**
     * 保存数据
     */
    private void saveItem() {
        try {
            // 获得选中的消费类型
            GridView gridView = KeepAccountActivity.activityInstance.getGridViewItemIcons();
            ConsumeType type = (ConsumeType) gridView.getItemAtPosition(gridView.getCheckedItemPosition());
            // 设置记账信息
            Item item = new Item();
            item.setType(KeepAccountActivity.activityInstance.getType());
            item.setImageId(type.getImageId());
            item.setConsumeType(type.getId().intValue());

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
