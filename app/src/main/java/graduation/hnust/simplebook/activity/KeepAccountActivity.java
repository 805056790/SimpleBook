package graduation.hnust.simplebook.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.common.collect.Lists;

import java.util.List;

import graduation.hnust.simplebook.R;
import graduation.hnust.simplebook.model.ConsumeType;
import graduation.hnust.simplebook.view.adatper.ImageAdapter;

/**
 * 添加账本页面
 *
 * @Author : panxin109@gmail.com
 * @Date : 1:02 PM 5/1/16
 */
public class KeepAccountActivity extends Activity implements View.OnClickListener {

    // 收支类型选择框
    private TextView txtIncomeSelect;
    private TextView txtExpenseSelect;
    private TextView txtItemSelectedName;
    private ImageView imgItemSelected;

    // 消费类型列表
    private GridView gridViewItemIcons;
    private ImageAdapter imageAdapter;

    private List<ConsumeType> consumeTypes;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keep_account);

        initViews();
        initEvents();
        initData();

        gridViewItemIcons.setAdapter(imageAdapter);
        gridViewItemIcons.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                imgItemSelected.setImageResource(consumeTypes.get(position).getImageId());
                txtItemSelectedName.setText(consumeTypes.get(position).getName());
            }
        });

    }

    /**
     * 初始化控件信息
     */
    private void initViews() {
        // 文本框
        txtIncomeSelect = (TextView) findViewById(R.id.txt_income_select);
        txtExpenseSelect = (TextView) findViewById(R.id.txt_expense_select);
        txtItemSelectedName = (TextView) findViewById(R.id.txt_item_selected_name);
        // 选中的item
        imgItemSelected = (ImageView) findViewById(R.id.image_view_item_selected);
        // 图片列表
        gridViewItemIcons = (GridView) findViewById(R.id.grid_view_item_icons);
    }

    /**
     * 初始化控件监听事件
     */
    private void initEvents() {
        txtIncomeSelect.setOnClickListener(this);
        txtExpenseSelect.setOnClickListener(this);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        consumeTypes = setExpenseTypes();
        imageAdapter = new ImageAdapter(this, consumeTypes);
    }

    /**
     * 支出类型
     *
     * @return 数据
     */
    private List<ConsumeType> setExpenseTypes() {
        List<ConsumeType> types = Lists.newArrayList();
        for (int i = 0; i < expenseTypes.length; i++) {
            ConsumeType type = new ConsumeType();
            type.setId((long)i);
            if (i == 0) {
                type.setName("一般" + i);
            }else {
                type.setName("消费" + i);
            }
            type.setImageId(expenseTypes[i]);
            types.add(type);
        }
        return types;
    }

    /**
     * 收入类型
     *
     * @return 数据
     */
    private List<ConsumeType> setIncomeTypes() {
        List<ConsumeType> types = Lists.newArrayList();
        for (int i = 0; i < incomeTypes.length; i++) {
            ConsumeType type = new ConsumeType();
            type.setId((long)i);
            if (i == 0) {
                type.setName("一般" + i);
            }else {
                type.setName("消费" + i);
            }
            type.setImageId(incomeTypes[i]);
            types.add(type);
        }
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
                // 刷新数据
                this.consumeTypes = setIncomeTypes();
                imageAdapter = new ImageAdapter(this, consumeTypes);
                gridViewItemIcons.setAdapter(imageAdapter);
                break;
            case R.id.txt_expense_select:
                // 选中支出 1. 背景色变蓝, 2. 字体变白
                txtExpenseSelect.setTextColor(getResources().getColor(R.color.fontAfterSelected));
                txtExpenseSelect.setBackgroundResource(R.drawable.text_border_after_selected);
                txtIncomeSelect.setTextColor(getResources().getColor(R.color.fontBeforeSelected));
                txtIncomeSelect.setBackgroundResource(R.drawable.text_border_before_selected);
                // 刷新数据
                this.consumeTypes = setExpenseTypes();
                imageAdapter = new ImageAdapter(this, consumeTypes);
                gridViewItemIcons.setAdapter(imageAdapter);
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
            R.drawable.type_17, R.drawable.type_6_press,
            R.drawable.type_7_press
    };

    /**
     * 收入
     */
    private Integer[] incomeTypes = {
            R.drawable.type_income_1,
            R.drawable.type_income_2
    };

}
