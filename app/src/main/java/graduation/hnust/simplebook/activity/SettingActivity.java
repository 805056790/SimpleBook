package graduation.hnust.simplebook.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import graduation.hnust.simplebook.R;
import graduation.hnust.simplebook.base.GsonRequest;
import graduation.hnust.simplebook.common.DateHelper;
import graduation.hnust.simplebook.common.GsonHelper;
import graduation.hnust.simplebook.common.NetworkHelper;
import graduation.hnust.simplebook.dto.UserDto;
import graduation.hnust.simplebook.model.Item;
import graduation.hnust.simplebook.service.ItemReadService;
import graduation.hnust.simplebook.service.impl.ItemReadServiceImpl;
import graduation.hnust.simplebook.util.ToastUtil;
import graduation.hnust.simplebook.web.api.BookApi;
import graduation.hnust.simplebook.web.api.UserApi;
import graduation.hnust.simplebook.web.base.HttpUrl;

/**
 * @Author : panxin109@gmail.com
 * @Date : 10:48 PM 5/10/16
 */
public class SettingActivity extends Activity implements View.OnClickListener {


    private TextView txtExport;
    private TextView txtSync;
    private TextView txtShare;
    private TextView txtFeedback;
    private TextView txtAboutUs;
    private TextView txtUpdate;

    private ImageView imgExport;
    private ImageView imgSync;
    private ImageView imgShare;
    private ImageView imgFeedBack;
    private ImageView imgAboutUs;
    private ImageView imgUpdate;

    private UserDto userDto;
    private List<Item> itemList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initViews();

        userDto = (UserDto) getIntent().getSerializableExtra("user");
    }

    /**
     * init views
     */
    private void initViews() {
        txtExport = (TextView) findViewById(R.id.txt_export);
        txtSync = (TextView) findViewById(R.id.txt_sync);
        txtShare = (TextView) findViewById(R.id.txt_share);
        txtFeedback = (TextView) findViewById(R.id.txt_feed_back);
        txtAboutUs = (TextView) findViewById(R.id.txt_about_us);
        txtUpdate = (TextView) findViewById(R.id.txt_update);

        txtExport.setOnClickListener(this);
        txtSync.setOnClickListener(this);
        txtShare.setOnClickListener(this);
        txtFeedback.setOnClickListener(this);
        txtAboutUs.setOnClickListener(this);
        txtUpdate.setOnClickListener(this);

        imgExport = (ImageView) findViewById(R.id.img_export);
        imgSync = (ImageView) findViewById(R.id.img_sync);
        imgShare = (ImageView) findViewById(R.id.img_share);
        imgFeedBack = (ImageView) findViewById(R.id.img_feed_back);
        imgAboutUs = (ImageView) findViewById(R.id.img_about_us);
        imgUpdate = (ImageView) findViewById(R.id.img_update);
    }

    /**
     * ...
     * @param view ..
     */
    public void back2Main2(View view) {
        this.finish();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.txt_export:
                export(v);
                break;
            case R.id.txt_sync:
                sync(v);
                break;
            case R.id.txt_share:
                share(v);
                break;
            case R.id.txt_feed_back:
                feedback(v);
                break;
            case R.id.txt_about_us:
                aboutUs(v);
                break;
            case R.id.txt_update:
                update(v);
                break;
            default:
                break;
        }
    }

    public void export(View v) {
        Log.i("ss", "export" + v.getId());
        try{
            doExport();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sync(View v) {
        Log.i("ss", "sync" + v.getId());
        if(!NetworkHelper.isNetworkAvailable(SettingActivity.this)) {
            ToastUtil.show(SettingActivity.this, "请先连接网络!");
            return;
        }
        try{
            ItemReadService service = new ItemReadServiceImpl(this.getApplicationContext());
            if (userDto == null) {
                // ToastUtil.show(SettingActivity.this, "用户未登录!");
                itemList = service.findItemsByUserId(0L);
            }else {
                itemList = service.findItemsByUserId(userDto.getUser().getId());
            }
            doSync();
        }catch (Exception e) {
            ToastUtil.show(SettingActivity.this, "同步失败, 请重试!");
            e.printStackTrace();
        }
    }

    private void doSync() {
        String url = HttpUrl.DOMAIN + BookApi.SYNC;
        RequestQueue queue = Volley.newRequestQueue(SettingActivity.this);
        GsonRequest<String> request = new GsonRequest<String>(
                Request.Method.POST,
                url,
                String.class,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if ("error".equals(response)) {
                            ToastUtil.show(SettingActivity.this, "同步失败!请检查您的网络状态!");
                        }else {
//                            Gson gsonList = new Gson();
//                            Type listType = new TypeToken<List<Item>>(){
//                            }.getType();
//                            itemList = gsonList.fromJson(response, listType);
                            Log.i("app", response + "");
                            ToastUtil.show(SettingActivity.this, "同步成功, 返回信息: " + response);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        ToastUtil.show(SettingActivity.this, "同步失败, 请重试!");
                    }
                },
                GsonHelper.getBeanGson()){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = Maps.newHashMap();
                params.put("itemJson", GsonHelper.bean2Json(itemList));
                params.put("userName", "18673231309");
                return params;
            }
        };
        queue.add(request);
    }

    public void share(View v) {
        Log.i("ss", "share" + v.getId());
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        share.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
        share.putExtra(Intent.EXTRA_TEXT, "SimpleBook" + "\n" +
                "GitHub Page :  https://github.com/805056790/SimpleBook\n");
        startActivity(Intent.createChooser(share, getString(R.string.app_name)));
    }

    public void feedback(View v) {
        Log.i("ss", "feedback" + v.getId());

    }

    public void aboutUs(View v) {
        Log.i("ss", "aboutUs" + v.getId());
        String appUrl = "https://play.google.com";
        Intent rateIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(appUrl));
        startActivity(rateIntent);
    }

    public void update(View v) {
        Log.i("ss", "update" + v.getId());
        ToastUtil.show(this, "已是最新版本!");
    }

    private void doExport() {
        FileOutputStream output = null;
        try {
            ItemReadService service = new ItemReadServiceImpl(this.getApplicationContext());
            if (userDto == null) {
                itemList = service.findItemsByUserId(0L);
            }else {
                itemList = service.findItemsByUserId(userDto.getUser().getId());
            }
            File file = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS), "账本导出.txt");
            output = new FileOutputStream(file);

            Boolean read = isExternalStorageReadable();
            Boolean write = isExternalStorageWritable();
            if (!read && !write) {
                ToastUtil.show(SettingActivity.this, "内存卡无效, 无法导出!");
                return;
            }

            String type = null;
            String result = null;
            for (Item item : itemList) {
                if (item.getType() == 1) {
                    type = "收入";
                }else {
                    type = "支出";
                }
                result = type + ":\t" + item.getConsumeTypeName() + ", " + item.getAmount() + "" +
                        item.getDate() + ", " + item.getNote();
                output.write(result.getBytes());
            }
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (output != null) output.close();
                ToastUtil.show(SettingActivity.this, "账本导出完成!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

//    private void doExport() {
//        ItemReadService service = new ItemReadServiceImpl(this.getApplicationContext());
//        if (userDto == null) {
//            itemList = service.findItemsByUserId(0L);
//        }else {
//            itemList = service.findItemsByUserId(userDto.getUser().getId());
//        }
//        try{
//            XSSFWorkbook wb = new XSSFWorkbook();
//            XSSFSheet s = wb.createSheet("账本导出");
//            s.setColumnWidth(0, 18 * 100);
//            s.setColumnWidth(1, 18 * 100);
//            s.setColumnWidth(2, 18 * 100);
//            s.setColumnWidth(3, 18 * 100);
//            s.setColumnWidth(4, 18 * 100);
//            s.setColumnWidth(5, 18 * 100);
//            s.setColumnWidth(6, 18 * 100);
//
//            File file = new File(Environment.getExternalStoragePublicDirectory(
//                    Environment.DIRECTORY_DOWNLOADS), "账本导出.xlsx");
//            FileOutputStream output = new FileOutputStream(file);
//
//            // title
//            XSSFRow row = s.createRow(0);
//            setTitle(row);
//
//            if (itemList.size() > 0) {
//                for (int i = 0; i < itemList.size(); i++) {
//                    Row r = s.createRow(i+1);
//                    setContent(r, i, itemList.get(i));
//                }
//            }
//            Boolean read = isExternalStorageReadable();
//            Boolean write = isExternalStorageWritable();
//            if (!read && !write) {
//                ToastUtil.show(SettingActivity.this, "内存卡无效, 无法导出!");
//                return;
//            }
//            wb.write(output);
//            ToastUtil.show(SettingActivity.this, "账本导出完成!");
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void setTitle(Row row) {
//        Cell cell = row.createCell(0);
//        cell.setCellValue("序号");
//
//        cell = row.createCell(1);
//        cell.setCellValue("类型");
//
//        cell = row.createCell(2);
//        cell.setCellValue("消费类型");
//
//        cell = row.createCell(3);
//        cell.setCellValue("金额");
//
//        cell = row.createCell(4);
//        cell.setCellValue("日期");
//
//        cell = row.createCell(5);
//        cell.setCellValue("备注");
//
//        cell = row.createCell(6);
//        cell.setCellValue("其他");
//    }
//
//    private void setContent(Row row, int i, Item item) {
//        Cell cell = row.createCell(0);
//        cell.setCellValue(i+1);
//
//        cell = row.createCell(1);
//        if (item.getType() == 1) {
//            cell.setCellValue("收入");
//        }else {
//            cell.setCellValue("支出");
//        }
//
//        cell = row.createCell(2);
//        cell.setCellValue(item.getConsumeTypeName());
//
//        cell = row.createCell(3);
//        cell.setCellValue(item.getAmount() / 100 + "元");
//
//        cell = row.createCell(4);
//        cell.setCellValue(DateHelper.date2String(item.getDate()));
//
//        cell = row.createCell(5);
//        cell.setCellValue(item.getNote() + "");
//
//        cell = row.createCell(6);
//        cell.setCellValue("无");
//    }

    /**
     * Checks if external storage is available for read and write
     */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /**
     *  Checks if external storage is available to at least read
     */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void logout(View view) {
        if (userDto == null) {
            return;
        }
        ObjectOutputStream out = null;
        try {
            File file = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS), "user.tmp");
            out = new ObjectOutputStream(new FileOutputStream(file));
            UserDto userDto = new UserDto();
            out.writeObject(userDto);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                if (out != null) out.close();
                this.finish();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
