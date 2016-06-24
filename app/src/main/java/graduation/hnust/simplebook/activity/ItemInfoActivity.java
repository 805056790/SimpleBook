package graduation.hnust.simplebook.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import graduation.hnust.simplebook.R;
import graduation.hnust.simplebook.common.DateHelper;
import graduation.hnust.simplebook.enums.ConsumeTypes;
import graduation.hnust.simplebook.model.Item;
import graduation.hnust.simplebook.service.ItemReadService;
import graduation.hnust.simplebook.service.impl.ItemReadServiceImpl;

/**
 * @Author : panxin109@gmail.com
 * @Date : 7:48 PM 5/10/16
 */
public class ItemInfoActivity extends Activity {


    private Item item;
    private TextView txtType;
    private TextView txtAmount;
    private TextView txtDate;
    private TextView txtConsumeTypeName;
    private TextView txtConsumeAmount;
    private TextView txtNote;
    private ImageView imgType;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_info);

        item = (Item) getIntent().getSerializableExtra("item");
        if (item == null) {
            Long itemId = getIntent().getLongExtra("itemId", 1);
            ItemReadService service = new ItemReadServiceImpl(this.getApplicationContext());
            item = service.findById(itemId);
        }

        txtType = (TextView) findViewById(R.id.txt_type);
        txtAmount = (TextView) findViewById(R.id.txt_amount_of_info);
        txtDate = (TextView) findViewById(R.id.txt_date_of_info);
        txtConsumeTypeName = (TextView) findViewById(R.id.txt_type_name_of_info);
        txtConsumeAmount = (TextView) findViewById(R.id.txt_amount_of_info_2);
        txtNote = (TextView) findViewById(R.id.txt_note_of_info);
        imgType = (ImageView) findViewById(R.id.img_type_img);

        if (item.getType() == ConsumeTypes.Type.EXPENSE.value()) {
            txtType.setText("支出");
        }else {
            txtType.setText("收入");
        }
        imgType.setImageResource(item.getImageId());
        txtAmount.setText(item.getAmount() / 100 + "元");
        txtDate.setText(DateHelper.date2String4View(item.getDate()) + "");
        txtConsumeAmount.setText(item.getAmount() /100 + "元");
        txtConsumeTypeName.setText(item.getConsumeTypeName());
        txtNote.setText(item.getNote() == null ? "无" : item.getNote());
    }

    public void back2Main(View view) {
        this.finish();
    }

}
