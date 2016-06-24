package graduation.hnust.simplebook;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.common.collect.Maps;

import java.util.Map;

import graduation.hnust.simplebook.base.BaseApplication;
import graduation.hnust.simplebook.base.GsonRequest;
import graduation.hnust.simplebook.common.GsonHelper;
import graduation.hnust.simplebook.greendao.ItemDao;
import graduation.hnust.simplebook.model.Item;
import graduation.hnust.simplebook.model.User;
import graduation.hnust.simplebook.web.api.UserApi;
import graduation.hnust.simplebook.web.base.HttpUrl;

public class TestActivity extends Activity implements View.OnClickListener {

    private Button button;
    private Button btn;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        //button = (Button) findViewById(R.id.button);
        //btn = (Button) findViewById(R.id.button2);
//        textView = (TextView) findViewById(R.id.text_test);

        button.setOnClickListener(this);
        btn.setOnClickListener(this);
    }

    public void doPost() {
        final String userName = "18673231309";
        final String password = "123456";
        final String loginType = "1";
        String url = HttpUrl.DOMAIN+UserApi.LOGIN;
        RequestQueue queue = Volley.newRequestQueue(this);
        GsonRequest<User> request = new GsonRequest<User>(
                Method.POST,
                url,
                User.class,
                createMyReqSuccessListener(),
                createMyReqErrorListener(),
                GsonHelper.getBeanGson()){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = Maps.newHashMap();
                params.put("loginBy", userName);
                params.put("password", password);
                params.put("loginType", loginType);
                return params;
            }
        };
        queue.add(request);
    }

    private Response.Listener<User> createMyReqSuccessListener() {
        return new Response.Listener<User>() {
            @Override
            public void onResponse(User response) {
                textView.setText("result: "+response.getMobile() +
                        response.getPassword() + response.getId() +","+
                        response.getCreatedAt() + "," +response.getType());
            }
        };
    }


    private Response.ErrorListener createMyReqErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("Error:" +error.toString());
            }
        };
    }

    @Override
    public void onClick(View v) {
//        if (v.getId() == R.id.button) {
             testGreenDao();
//        }else {
//            try {
//                doPost();
//            }catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
    }

    private void testGreenDao() {
        try {
            ItemDao itemDao = ((BaseApplication) this.getApplicationContext()).getDaoSession().getItemDao();
            Item item = new Item();
            item.setUserId(5L);
            item.setNote("xxx");
            item.setAmount(222222);
            itemDao.insert(item);
            Log.i("item", item.getId() + "");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
