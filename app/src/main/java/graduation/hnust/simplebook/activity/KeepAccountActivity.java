package graduation.hnust.simplebook.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.common.collect.Lists;

import java.util.List;

import graduation.hnust.simplebook.R;
import graduation.hnust.simplebook.activity.listener.KeepAccountListener;
import graduation.hnust.simplebook.constants.KeyConstants;
import graduation.hnust.simplebook.dto.UserDto;
import graduation.hnust.simplebook.enums.ConsumeTypes;
import graduation.hnust.simplebook.model.ConsumeType;
import graduation.hnust.simplebook.model.User;
import graduation.hnust.simplebook.view.adatper.ImageAdapter;
import lombok.Getter;

/**
 * 添加账本页面
 *
 * @Author : panxin109@gmail.com
 * @Date : 1:02 PM 5/1/16
 */
public class KeepAccountActivity extends Activity implements View.OnClickListener {

    public static KeepAccountActivity activityInstance;
    @Getter
    private UserDto user;

    @Getter
    private Integer type = ConsumeTypes.Type.EXPENSE.value();

    // 收支类型选择框
    private TextView txtSave;
    private TextView txtCancel;
    private TextView txtIncomeSelect;
    private TextView txtExpenseSelect;
    @Getter
    private TextView txtItemSelectedName;
    @Getter
    private ImageView imgItemSelected;
    @Getter
    private EditText editAmount;
    @Getter
    private TextView txtDate;
    private ImageView imgDate;
    @Getter
    private EditText editNote;

    // 消费类型列表
    @Getter
    private GridView gridViewItemIcons;
    @Getter
    private ImageAdapter imageAdapter;

    // 消费类型列表数据
    private List<ConsumeType> consumeTypes;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keep_account);
        activityInstance = this;

        initViews();
        initEvents();
        initData();
        getIntentExtras(getIntent());

        gridViewItemIcons.setAdapter(imageAdapter);
        gridViewItemIcons.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                imgItemSelected.setImageResource(consumeTypes.get(position).getImageId());
                txtItemSelectedName.setText(consumeTypes.get(position).getName());
                gridViewItemIcons.setItemChecked(position, true);
            }
        });
        gridViewItemIcons.setItemChecked(0, true);
    }

    private void getIntentExtras(Intent intent) {
        user = (UserDto) intent.getSerializableExtra(KeyConstants.LOGIN_USER);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 初始化控件信息
     */
    private void initViews() {
        editAmount = (EditText) findViewById(R.id.edit_amount);
        // 文本框
        txtSave = (TextView) findViewById(R.id.txt_save);
        txtCancel = (TextView) findViewById(R.id.txt_cancel);
        txtIncomeSelect = (TextView) findViewById(R.id.txt_income_select);
        txtExpenseSelect = (TextView) findViewById(R.id.txt_expense_select);
        txtItemSelectedName = (TextView) findViewById(R.id.txt_item_selected_name);
        // 选中的item
        imgItemSelected = (ImageView) findViewById(R.id.image_view_item_selected);
        // 图片列表
        gridViewItemIcons = (GridView) findViewById(R.id.grid_view_item_icons);
        // 日期
        txtDate = (TextView) findViewById(R.id.txt_date);
        imgDate = (ImageView) findViewById(R.id.img_date);
        editNote = (EditText) findViewById(R.id.edit_note);
    }

    /**
     * 初始化控件监听事件
     */
    private void initEvents() {
        txtIncomeSelect.setOnClickListener(this);
        txtExpenseSelect.setOnClickListener(this);
        txtSave.setOnClickListener(new KeepAccountListener(this.getApplication()));
        txtCancel.setOnClickListener(new KeepAccountListener(this));
        txtDate.setOnClickListener(new KeepAccountListener(this));
        imgDate.setOnClickListener(new KeepAccountListener(this));
    }

    /**
     * 初始化数据
     */
    private void initData() {
        consumeTypes = setExpenseTypes();
        imageAdapter = new ImageAdapter(this, consumeTypes);
        // 设置默认值
        imgItemSelected.setImageResource(consumeTypes.get(0).getImageId());
        txtItemSelectedName.setText(consumeTypes.get(0).getName());
    }

    /**
     * 支出类型
     *
     * @return 数据
     */
    private List<ConsumeType> setExpenseTypes() {
        List<ConsumeType> types = Lists.newArrayList();
        types = initExpenseType(types);
        return types;
    }

    /**
     * 收入类型
     *
     * @return 数据
     */
    private List<ConsumeType> setIncomeTypes() {
        List<ConsumeType> types = Lists.newArrayList();
        types = initIncomeType(types);
        return types;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_income_select:
                // 选中收入 1. 背景色变蓝, 2. 字体变白
                txtIncomeSelect.setTextColor(getResources().getColor(R.color.fontAfterSelected));
                txtIncomeSelect.setBackgroundResource(R.drawable.text_border_after_selected);
                txtExpenseSelect.setTextColor(getResources().getColor(R.color.fontBeforeSelected));
                txtExpenseSelect.setBackgroundResource(R.drawable.text_border_before_selected);
                type = ConsumeTypes.Type.INCOME.value();
                // 刷新数据
                this.consumeTypes = setIncomeTypes();
                imageAdapter = new ImageAdapter(this, consumeTypes);
                gridViewItemIcons.setAdapter(imageAdapter);
                gridViewItemIcons.setItemChecked(0, true);
                imgItemSelected.setImageResource(consumeTypes.get(0).getImageId());
                txtItemSelectedName.setText(consumeTypes.get(0).getName());
                break;
            case R.id.txt_expense_select:
                // 选中支出 1. 背景色变蓝, 2. 字体变白
                txtExpenseSelect.setTextColor(getResources().getColor(R.color.fontAfterSelected));
                txtExpenseSelect.setBackgroundResource(R.drawable.text_border_after_selected);
                txtIncomeSelect.setTextColor(getResources().getColor(R.color.fontBeforeSelected));
                txtIncomeSelect.setBackgroundResource(R.drawable.text_border_before_selected);
                type = ConsumeTypes.Type.EXPENSE.value();
                // 刷新数据
                this.consumeTypes = setExpenseTypes();
                imageAdapter = new ImageAdapter(this, consumeTypes);
                gridViewItemIcons.setAdapter(imageAdapter);
                gridViewItemIcons.setItemChecked(0, true);
                imgItemSelected.setImageResource(consumeTypes.get(0).getImageId());
                txtItemSelectedName.setText(consumeTypes.get(0).getName());
                break;
            default:
                break;
        }
    }

    /**
     * 支出
     */
    private Integer[] expenseTypes = {
            R.drawable.type_1_default,
            R.drawable.type_1_press, R.drawable.type_2_press,
            R.drawable.type_3_press, R.drawable.type_4_press,
            R.drawable.type_5_press, R.drawable.type_8_press,
            R.drawable.type_9_press, R.drawable.type_10_press,
            R.drawable.type_11_press, R.drawable.type_12_press,
            R.drawable.type_13_press, R.drawable.type_14_press,
            R.drawable.type_15_press, R.drawable.type_13,
            R.drawable.type_6_press, R.drawable.type_7_press
    };

    /**
     * 收入
     */
    private Integer[] incomeTypes = {
            R.drawable.type_1_default,
            R.drawable.type_income_1,
            R.drawable.type_income_2
    };


    private List<ConsumeType> initExpenseType(List<ConsumeType> types) {
        ConsumeType type = new ConsumeType();
        // 支出
        type.setId(0L);
        type.setType(1);
        type.setImageId(expenseTypes[0]);
        type.setName("一般");
        types.add(type);

        type = new ConsumeType();
        type.setId(2L);
        type.setType(1);
        type.setImageId(expenseTypes[1]);
        type.setName("中餐");
        types.add(type);

        type = new ConsumeType();
        type.setId(3L);
        type.setType(1);
        type.setImageId(expenseTypes[2]);
        type.setName("公交");
        types.add(type);

        type = new ConsumeType();
        type.setId(4L);
        type.setType(1);
        type.setImageId(expenseTypes[3]);
        type.setName("零食");
        types.add(type);

        type = new ConsumeType();
        type.setId(5L);
        type.setType(1);
        type.setImageId(expenseTypes[4]);
        type.setName("衣服");
        types.add(type);

        type = new ConsumeType();
        type.setId(6L);
        type.setType(1);
        type.setImageId(expenseTypes[5]);
        type.setName("生活用品");
        types.add(type);

        type = new ConsumeType();
        type.setId(7L);
        type.setType(1);
        type.setImageId(expenseTypes[6]);
        type.setName("购物");
        types.add(type);

        type = new ConsumeType();
        type.setId(8L);
        type.setType(1);
        type.setImageId(expenseTypes[7]);
        type.setName("出租车");
        types.add(type);

        type = new ConsumeType();
        type.setId(9L);
        type.setType(1);
        type.setImageId(expenseTypes[8]);
        type.setName("快餐");
        types.add(type);

        type = new ConsumeType();
        type.setId(10L);
        type.setType(1);
        type.setImageId(expenseTypes[9]);
        type.setName("飞机");
        types.add(type);

        type = new ConsumeType();
        type.setId(11L);
        type.setType(1);
        type.setImageId(expenseTypes[10]);
        type.setName("化妆品");
        types.add(type);

        type = new ConsumeType();
        type.setId(12L);
        type.setType(1);
        type.setImageId(expenseTypes[11]);
        type.setName("电脑");
        types.add(type);

        type = new ConsumeType();
        type.setId(13L);
        type.setType(1);
        type.setImageId(expenseTypes[12]);
        type.setName("手机");
        types.add(type);

        type = new ConsumeType();
        type.setId(14L);
        type.setType(1);
        type.setImageId(expenseTypes[13]);
        type.setName("药物");
        types.add(type);

        type = new ConsumeType();
        type.setId(15L);
        type.setType(1);
        type.setImageId(expenseTypes[14]);
        type.setName("电影");
        types.add(type);

        type = new ConsumeType();
        type.setId(16L);
        type.setType(1);
        type.setImageId(expenseTypes[15]);
        type.setName("西餐");
        types.add(type);

        type = new ConsumeType();
        type.setId(17L);
        type.setType(1);
        type.setImageId(expenseTypes[16]);
        type.setName("游戏");
        types.add(type);

        return types;
    }

    private List<ConsumeType> initIncomeType(List<ConsumeType> types) {
        ConsumeType type = new ConsumeType();
        // 支出
        type.setType(1);
        type.setId(0L);
        type.setImageId(incomeTypes[0]);
        type.setName("一般");
        types.add(type);

        type = new ConsumeType();
        type.setId(1L);
        type.setType(1);
        type.setImageId(incomeTypes[1]);
        type.setName("工资");
        types.add(type);

        type = new ConsumeType();
        type.setId(2L);
        type.setType(1);
        type.setImageId(incomeTypes[2]);
        type.setName("红包");
        types.add(type);

        return types;
    }

}
