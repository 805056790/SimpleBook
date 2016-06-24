package graduation.hnust.simplebook.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.common.collect.Lists;
import com.suru.myp.MonthYearPicker;
import com.xiaoqi.piechart.PieChart;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import cn.aigestudio.datepicker.cons.DPMode;
import cn.aigestudio.datepicker.views.DatePicker;
import graduation.hnust.simplebook.R;
import graduation.hnust.simplebook.common.DateHelper;
import graduation.hnust.simplebook.dto.UserDto;
import graduation.hnust.simplebook.enums.ConsumeTypes;
import graduation.hnust.simplebook.model.Item;
import graduation.hnust.simplebook.service.ItemReadService;
import graduation.hnust.simplebook.service.impl.ItemReadServiceImpl;
import graduation.hnust.simplebook.util.ToastUtil;
import graduation.hnust.simplebook.view.adatper.ItemAdapter;
import graduation.hnust.simplebook.view.adatper.ItemDto;

/**
 * @Author : panxin109@gmail.com
 * @Date : 12:44 PM 5/10/16
 */
public class IncomeStatisticsActivity extends Activity implements View.OnClickListener {

    private PieChart chart;
    private Integer flag = 0;
    private Date dayDate;
    private Date dateMonth;
    private MonthYearPicker myp;

    private UserDto user;
    private Integer type;
    private ItemReadService readService;

    private ListView listView;
    private List<ItemDto> itemList;
    private ItemAdapter adapter;

    private TextView txtMonth;
    private TextView txtAll;
    private TextView txtDay;
    private TextView txtAllAmount;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_statistics);
        chart = (PieChart) findViewById(R.id.pie_income);
        try {
            readService = new ItemReadServiceImpl(getApplicationContext());
            Intent intent = getIntent();
            getExtras(intent);
            initViews();
            initEvents();

            itemList = getDtoData();
            adapter = new ItemAdapter(IncomeStatisticsActivity.this, itemList);
            listView.setAdapter(adapter);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取参数
     *
     * @param intent intent
     */
    private void getExtras(Intent intent) {
        type = intent.getIntExtra("type", 1);
        user = (UserDto) intent.getSerializableExtra("user");
    }

    /**
     * 初始化界面组件
     */
    private void initViews() {
        listView = (ListView) findViewById(R.id.lv_statistics_income);
        txtAll = (TextView) findViewById(R.id.txt_all);
        txtMonth = (TextView) findViewById(R.id.txt_month);
        txtDay = (TextView) findViewById(R.id.txt_day);
        txtAllAmount = (TextView) findViewById(R.id.txt_all_amount);

        myp = new MonthYearPicker(this);
        myp.build(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setSelectedDate(myp.getSelectedMonth(), myp.getSelectedYear());
            }
        }, null);
    }

    private void initEvents() {
        txtAll.setOnClickListener(this);
        txtMonth.setOnClickListener(this);
        txtDay.setOnClickListener(this);
    }

    /**
     * 获取数据
     *
     * @return data
     */
    private List<Item> getData() {
        // 没有登录则取默认数据
        if (user == null) {
            return readService.findItemsByUserIdAndType(0L, type);
        }
        List<Item> respList = readService.findItemsByUserIdAndType(user.getUser().getId(), type);
        Log.i("item", respList.toString());
        return respList;
    }

    /**
     * xxx
     *
     * @return list
     */
    private List<ItemDto> getDtoData() {
        List<ItemDto> dtoList = Lists.newArrayList();
        List<Item> list = null;
        ItemDto dto = null;
        float[] amounts = null;
        int total = 0;
        int types = 0;
        int index = 0;
        if (type == ConsumeTypes.Type.INCOME.value()) {
            types = 3;
            amounts = new float[3];
        }else {
            types = 17;
            amounts = new float[17];
        }
        for (Item item : getData()) {
            total += item.getAmount();
        }
        for (int i = 0; i < types; i++) {
            // 查找
            dto = new ItemDto();
            if (user == null) {
                list = readService.findItemByConsumeType(0L, type, i);
            }else {
                list = readService.findItemByConsumeType(user.getUser().getId(), type, i);
            }
            if (list.size() == 0) {
                continue;
            }
            // 记录
            int amount = 0;
            for (Item item : list) {
                amount += item.getAmount();
            }
            amounts[index] = (float) (amount/100);
            index++;
            // 设置
            int percent = amount/(total/100);
            dto.setAmount(amount);
            dto.setPercent(percent == 0 ? 1 + "" : percent + "");
            dto.setImageId(list.get(0).getImageId());
            dto.setTypeId(i);
            dto.setTypeName(list.get(0).getConsumeTypeName());
            dtoList.add(dto);
        }
        Collections.sort(dtoList);
        if (total == 0) {
            amounts[0] = 0;
        }
        txtAllAmount.setText("总金额: " +total / 100 + "元");
        initChart(amounts, PieChart.DEFAULT_ITEMS_COLORS);
        return dtoList;
    }

    /**
     * new float[]{20f, 30f, 40f, 80f},
     * new String[]{"#ff80FF", "#ffFF00", "#6A5ACD", "#de3322"}
     *
     * @param amount xx
     * @param colors xx
     */
    private void initChart(float[] amount, String[] colors) {
        chart.initSrc(amount, colors, new PieChart.OnItemClickListener() {
            @Override
            public void click(int position) {
                ToastUtil.show(IncomeStatisticsActivity.this, "haha");
            }
        });
    }

    public void backImg(View view) {
        this.finish();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.txt_all:
                txtAll.setTextColor(getResources().getColor(R.color.fontAfterSelected));
                txtAll.setBackgroundResource(R.drawable.text_border_after_selected);
                txtDay.setTextColor(getResources().getColor(R.color.fontBeforeSelected));
                txtDay.setBackgroundResource(R.drawable.text_border_before_selected);
                txtMonth.setTextColor(getResources().getColor(R.color.fontBeforeSelected));
                txtMonth.setBackgroundResource(R.drawable.text_border_before_selected);
                flag = 0;
                all();
                break;
            case R.id.txt_month:
                txtMonth.setTextColor(getResources().getColor(R.color.fontAfterSelected));
                txtMonth.setBackgroundResource(R.drawable.text_border_after_selected);
                txtDay.setTextColor(getResources().getColor(R.color.fontBeforeSelected));
                txtDay.setBackgroundResource(R.drawable.text_border_before_selected);
                txtAll.setTextColor(getResources().getColor(R.color.fontBeforeSelected));
                txtAll.setBackgroundResource(R.drawable.text_border_before_selected);
                flag = 1;
                selecteMonth();
                break;
            case R.id.txt_day:
                txtDay.setTextColor(getResources().getColor(R.color.fontAfterSelected));
                txtDay.setBackgroundResource(R.drawable.text_border_after_selected);
                txtAll.setTextColor(getResources().getColor(R.color.fontBeforeSelected));
                txtAll.setBackgroundResource(R.drawable.text_border_before_selected);
                txtMonth.setTextColor(getResources().getColor(R.color.fontBeforeSelected));
                txtMonth.setBackgroundResource(R.drawable.text_border_before_selected);
                flag = 2;
                day();
                break;
            default:
                break;
        }
    }

    /**
     * 总览
     */
    private void all() {
        Log.i("bbb", "all");
        itemList = getDtoData();
        adapter = new ItemAdapter(IncomeStatisticsActivity.this, itemList);
        listView.setAdapter(adapter);
    }

    /**
     * 天
     */
    private void day() {
        getSelectedDate();
    }

    /**
     * 获取数据
     *
     * @return data
     */
    private List<Item> getDayData(Date date) {
        // 没有登录则取默认数据
        if (user == null) {
            return readService.findByDay(0L, date);
        }
        return readService.findByDay(0L, date);
    }

    /**
     * 选择日期
     */
    public void getSelectedDate() {
        final AlertDialog dialog = new AlertDialog.Builder(IncomeStatisticsActivity.this).create();
        dialog.show();
        DatePicker picker = new DatePicker(IncomeStatisticsActivity.this);
        picker.setDate(2016, 5);
        picker.setMode(DPMode.SINGLE);
        picker.setOnDatePickedListener(new DatePicker.OnDatePickedListener() {
            @Override
            public void onDatePicked(String date) {
                dayDate = DateHelper.string2Date(date, IncomeStatisticsActivity.this);
                dialog.dismiss();
                setDayData(dayDate);
            }
        });
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setContentView(picker, params);
        dialog.getWindow().setGravity(Gravity.CENTER);
    }

    private void setDayData(Date dayDate) {
        Log.i("bbb", "day");
        List<ItemDto> dtoList = Lists.newArrayList();
        List<Item> list = null;
        ItemDto dto = null;
        float[] amounts = null;
        int total = 0;
        int types = 0;
        int index = 0;
        if (type == ConsumeTypes.Type.INCOME.value()) {
            types = 3;
            amounts = new float[3];
        }else {
            types = 17;
            amounts = new float[17];
        }
        for (Item item : getDayData(dayDate)) {
            total += item.getAmount();
        }
        for (int i = 0; i < types; i++) {
            // 查找
            dto = new ItemDto();
            if (user == null) {
                list = readService.findItemByConsumeTypeAndDate(0L, type, i, dayDate);
            }else {
                list = readService.findItemByConsumeTypeAndDate(user.getUser().getId(), type, i, dayDate);
            }
            if (list.size() == 0) {
                continue;
            }
            // 记录
            int amount = 0;
            for (Item item : list) {
                amount += item.getAmount();
            }
            amounts[index] = (float) (amount/100);
            index++;
            // 设置
            int percent = amount/(total/100);
            dto.setAmount(amount);
            dto.setPercent(percent == 0 ? 1 + "" : percent + "");
            dto.setImageId(list.get(0).getImageId());
            dto.setTypeId(i);
            dto.setTypeName(list.get(0).getConsumeTypeName());
            dtoList.add(dto);
        }
        Collections.sort(dtoList);
        if (total == 0) {
            amounts[0] = 0;
        }
        initChart(amounts, PieChart.DEFAULT_ITEMS_COLORS);
        adapter = new ItemAdapter(IncomeStatisticsActivity.this, dtoList);
        listView.setAdapter(adapter);
        txtAllAmount.setText("总金额: " +total / 100 + "元");
    }

    /**
     * 选择月份
     */
    private void selecteMonth() {
        myp.show();
    }

    private void setSelectedDate(int month, int year) {
        month = month + 1;
        Log.i("bbb", "month = " + month + ", year = " + year);
        int day = 0;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                day = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                day = 30;
                break;
            case 2:
                // just let it go
                day = 28;
                break;
            default:
                break;
        }
        String start = year + "-" + month + "-1";
        String end = year + "-" + month + "-" + day;
        Date startDate = DateHelper.string2Date(start, null);
        Date endDate = DateHelper.string2Date(end, null);

        Log.i("date", start + " -- " + end);

        Log.i("date", startDate + " -- " + endDate);

        List<ItemDto> dtoList = Lists.newArrayList();
        List<Item> list = null;
        ItemDto dto = null;
        float[] amounts = null;
        int total = 0;
        int types = 0;
        int index = 0;
        if (type == ConsumeTypes.Type.INCOME.value()) {
            types = 3;
            amounts = new float[3];
        } else {
            types = 17;
            amounts = new float[17];
        }

        List<Item> tmpItem = null;
        if (user == null) {
            tmpItem = readService.findByMonth(0L, type, startDate, endDate);
        }else {
            tmpItem = readService.findByMonth(user.getUser().getId(), type, startDate, endDate);
        }

        for (Item item : tmpItem) {
            total += item.getAmount();
        }
        for (int i = 0; i < types; i++) {
            // 查找
            dto = new ItemDto();
            if (user == null) {
                list = readService.findItemByConsumeType(0L, type, i, startDate, endDate);
            } else {
                list = readService.findItemByConsumeType(user.getUser().getId(), type, i, startDate, endDate);
            }
            if (list.size() == 0) {
                continue;
            }
            // 记录
            int amount = 0;
            for (Item item : list) {
                amount += item.getAmount();
            }
            amounts[index] = (float) (amount / 100);
            index++;
            // 设置
            int percent = amount / (total / 100);
            dto.setAmount(amount);
            dto.setPercent(percent == 0 ? 1 + "" : percent + "");
            dto.setImageId(list.get(0).getImageId());
            dto.setTypeId(i);
            dto.setTypeName(list.get(0).getConsumeTypeName());
            dtoList.add(dto);
        }
        Collections.sort(dtoList);
        if (total == 0) {
            amounts[0] = 0;
        }
        initChart(amounts, PieChart.DEFAULT_ITEMS_COLORS);
        adapter = new ItemAdapter(IncomeStatisticsActivity.this, dtoList);
        listView.setAdapter(adapter);
        txtAllAmount.setText("总金额: " +total / 100 + "元");
    }
}
