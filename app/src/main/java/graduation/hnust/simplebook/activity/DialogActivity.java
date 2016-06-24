package graduation.hnust.simplebook.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import graduation.hnust.simplebook.R;
import graduation.hnust.simplebook.model.Item;
import graduation.hnust.simplebook.service.ItemWriteService;
import graduation.hnust.simplebook.service.impl.ItemWriteServiceImpl;
import graduation.hnust.simplebook.util.ToastUtil;

/**
 * @Author : panxin109@gmail.com
 * @Date : 6:21 PM 5/9/16
 */
public class DialogActivity extends Activity implements View.OnClickListener {

    private TextView txtPositive;
    private TextView txtNegative;
    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置布局内容
        setContentView(R.layout.activity_dialog);
        this.setFinishOnTouchOutside(true);

        this.item = (Item) getIntent().getSerializableExtra("item");

        txtPositive = (TextView) findViewById(R.id.txt_dialog_yes);
        txtNegative = (TextView) findViewById(R.id.txt_dialog_no);

        txtPositive.setOnClickListener(this);
        txtNegative.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_dialog_yes:
                delete();
                break;
            case R.id.txt_dialog_no:
                txtNegative.setBackgroundResource(R.color.dialogPress);
                this.finish();
                break;
            default:
                break;
        }
    }

    /**
     * 删除
     */
    private void delete() {
        try{
            txtPositive.setBackgroundResource(R.color.dialogPress);
            ItemWriteService service = new ItemWriteServiceImpl(this);
            Boolean result = service.delete(item);
            if (!result) {
                ToastUtil.show(this, "删除失败, 请重试!");
            }else {
                ToastUtil.show(this, "删除成功!");
            }
            this.finish();
        }catch (Exception e) {
            ToastUtil.show(this, "删除失败, 请重试!");
            e.printStackTrace();
        }
    }
}
