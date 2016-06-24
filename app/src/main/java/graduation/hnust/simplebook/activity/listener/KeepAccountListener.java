package graduation.hnust.simplebook.activity.listener;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import java.util.Date;
import java.util.Objects;

import cn.aigestudio.datepicker.cons.DPMode;
import cn.aigestudio.datepicker.views.DatePicker;
import graduation.hnust.simplebook.R;
import graduation.hnust.simplebook.activity.KeepAccountActivity;
import graduation.hnust.simplebook.common.DateHelper;
import graduation.hnust.simplebook.constants.KeyConstants;
import graduation.hnust.simplebook.dto.UserDto;
import graduation.hnust.simplebook.greendao.ItemDao;
import graduation.hnust.simplebook.model.ConsumeType;
import graduation.hnust.simplebook.model.Item;
import graduation.hnust.simplebook.model.User;
import graduation.hnust.simplebook.service.ItemWriteService;
import graduation.hnust.simplebook.service.impl.ItemWriteServiceImpl;
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
            case R.id.txt_date:
                showDatePicker();
                break;
            case R.id.img_date:
                showDatePicker();
                break;
            default:
                break;
        }
    }

    /**
     * 日期选择器
     */
    private void showDatePicker() {
        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.show();
        DatePicker picker = new DatePicker(context);
        picker.setDate(2016, 5);
        picker.setMode(DPMode.SINGLE);
        picker.setOnDatePickedListener(new DatePicker.OnDatePickedListener() {
            @Override
            public void onDatePicked(String date) {
                if (date.equals(DateHelper.date2String(new Date()))) {
                    KeepAccountActivity.activityInstance.getTxtDate().setText("今天");
                }else {
                    KeepAccountActivity.activityInstance.getTxtDate().setText(date);
                }
                dialog.dismiss();
            }
        });
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setContentView(picker, params);
        dialog.getWindow().setGravity(Gravity.CENTER);
    }

    /**
     * 取消
     */
    private void back() {
        KeepAccountActivity.activityInstance.finish();
    }

    /**
     * 保存数据.
     */
    private void saveItem() {
        try {
            KeepAccountActivity activity = KeepAccountActivity.activityInstance;
            String amountStr = activity.getEditAmount().getText().toString().trim();
            if (TextUtils.isEmpty(amountStr)) {
                ToastUtil.show(context, "请填写金额!");
                return;
            }
            try {
                // 金额, 日期, 备注
                Double dAmount = Double.parseDouble(amountStr) * 100;
                Integer amount = dAmount.intValue();
                String date = (String) activity.getTxtDate().getText();
                String note = activity.getEditNote().getText().toString().trim();
                // 用户信息
                UserDto user = activity.getUser();
                // 获得选中的消费类型
                GridView gridView = activity.getGridViewItemIcons();
                ConsumeType type = (ConsumeType) gridView.getItemAtPosition(gridView.getCheckedItemPosition());
                // 设置记账信息
                Item item = new Item();
                // 用户未登录则取用户ID为 0
                item.setUserId(user == null ? 0L : user.getUser().getId());
                item.setType(KeepAccountActivity.activityInstance.getType());
                item.setStatus(1);
                item.setImageId(type.getImageId());
                item.setConsumeType(type.getId().intValue());
                item.setConsumeTypeName(type.getName());
                item.setAmount(amount);
                item.setDate(DateHelper.string2Date(date, context));
                item.setNote(note);
                item.setCreatedAt(new Date());
                // 写数据
                ItemWriteService writeService = new ItemWriteServiceImpl(context);
                Boolean result = writeService.create(item);
                if (result) {
                    // 返回主界面
                    activity.finish();
                }else {
                    ToastUtil.show(context, "保存失败!");
                }
            }catch (NumberFormatException e){
                ToastUtil.show(context, "金额请填写数字!");
                e.printStackTrace();
            }catch (Exception e) {
                ToastUtil.show(context, "保存失败!");
                e.printStackTrace();
            }
        }catch (Exception e) {
            ToastUtil.show(context, "失败啦~");
            e.printStackTrace();
        }
    }
}
