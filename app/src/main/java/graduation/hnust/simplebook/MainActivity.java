package graduation.hnust.simplebook;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import graduation.hnust.simplebook.activity.KeepAccountActivity;
import graduation.hnust.simplebook.activity.LoginActivity;
import graduation.hnust.simplebook.activity.SettingActivity;
import graduation.hnust.simplebook.activity.listener.MainActivityListener;
import graduation.hnust.simplebook.base.BaseApplication;
import graduation.hnust.simplebook.constants.KeyConstants;
import graduation.hnust.simplebook.dto.UserDto;
import graduation.hnust.simplebook.greendao.ConsumeTypeDao;
import graduation.hnust.simplebook.greendao.ItemDao;
import graduation.hnust.simplebook.model.ConsumeType;
import graduation.hnust.simplebook.model.Item;
import graduation.hnust.simplebook.model.QQInfoModel;
import graduation.hnust.simplebook.model.User;
import graduation.hnust.simplebook.service.ItemReadService;
import graduation.hnust.simplebook.service.UserReadService;
import graduation.hnust.simplebook.service.impl.ItemReadServiceImpl;
import graduation.hnust.simplebook.service.impl.UserReadServiceImpl;
import graduation.hnust.simplebook.util.ActivityHelper;
import graduation.hnust.simplebook.view.fragment.FragmentExpense;
import graduation.hnust.simplebook.view.fragment.FragmentIncome;
import graduation.hnust.simplebook.view.fragment.FragmentMain;
import graduation.hnust.simplebook.view.fragment.FragmentSetting;
import lombok.Getter;
import lombok.Setter;

/**
 * Main activity of the app
 *
 * @Author : panxin
 * @Date : 10:33 AM 3/20/16
 * @Email : panxin109@gmail.com
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static MainActivity instance;
    public static Integer LOGIN_RESULT = 10;
    public static Integer REGISTER_RESULT = 11;
    public static Integer ADD_ACCOUNT_ITEM = 30;

    @Setter @Getter
    private UserDto currentUser;

    // 头像, 昵称
    private ImageView imgHead;
    private TextView txtNickname;

    private UserReadService userReadService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        instance = this;

        // floating action button
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 添加账本
                Intent intent = new Intent(MainActivity.this, KeepAccountActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(KeyConstants.LOGIN_USER, currentUser);
                intent.putExtras(bundle);
                startActivityForResult(intent, ADD_ACCOUNT_ITEM);
                // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

        // the layout of the side menu
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        // navigation setting
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // default view
        setNavigationFragments(new FragmentMain());
        // get the views of navigationView
        View headerView = navigationView.getHeaderView(0);

        // headImage, nickname, desc
        imgHead = (ImageView) headerView.findViewById(R.id.side_menu_image_head);
        txtNickname = (TextView) headerView.findViewById(R.id.side_menu_nickname);

        if (currentUser == null) {
            //getUser();
        }

        // bind event
        initEvents();
        // test();
    }

    private void getUser() {
        File file = null;
        ObjectInputStream input = null;
        try {
            file = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS), "user.tmp");
            input = new ObjectInputStream(new FileInputStream(file));
            currentUser = (UserDto) input.readObject();
            if (currentUser.getUser() == null) {
                Log.i("user", "用户数据为空!");
            }else {
                Log.i("user", currentUser.toString());
                txtNickname.setText(currentUser.getUser().getMobile() + "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (input != null)  input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Handle navigation view item clicks here.
     *
     * @param item navigation item
     * @return result
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_overview:
                setNavigationFragments(new FragmentMain());
                break;
            case R.id.nav_income:
                setNavigationFragments(new FragmentIncome());
                break;
            case R.id.nav_expense:
                setNavigationFragments(new FragmentExpense());
                break;
            case R.id.nav_settings:
                goSettingActivity();
                break;
            case R.id.nav_share:
                share();
                break;
            case R.id.nav_send:
                send();
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * what a fucking name
     */
    private void goSettingActivity() {
        Intent intent = new Intent(MainActivity.this, SettingActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("user", currentUser);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("result", requestCode + ", " + resultCode);
        if (requestCode == LOGIN_RESULT) {
            // 平台账号登录成功或者注册登录成功
            if (resultCode == LoginActivity.LOGIN_SUCCESS || resultCode == LoginActivity.REGISTER_SUCCESS) {
                UserDto user = (UserDto) data.getSerializableExtra(KeyConstants.LOGIN_USER);
                setUserInfo(user);
            }
            // QQ登录成功
            if (resultCode == LoginActivity.QQ_LOGIN_SUCCESS) {
                UserDto userDto = (UserDto) data.getSerializableExtra(KeyConstants.LOGIN_USER_BY_QQ);
                Log.i("user_dto", userDto.toString());
                Picasso.with(MainActivity.this).load(userDto.getQqInfoModel().getFigureUrlQq1()).resize(100, 100).centerCrop().into(imgHead);
                txtNickname.setText(userDto.getQqInfoModel().getNickname());
            }
        }
    }

    /**
     * 设置用户信息
     *
     * @param user 用户信息
     */
    private void setUserInfo(UserDto user) {
        if (user != null) {
            this.currentUser = user;
            txtNickname.setText(user.getUser().getMobile());
            File file = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS), "user.tmp");
            ObjectOutputStream out = null;
            try{
                out = new ObjectOutputStream(new FileOutputStream(file));
                out.writeObject(user);
                out.flush();
            }catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (out != null) out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * init data necessary
     */
    private void init(){
        userReadService = new UserReadServiceImpl(MainActivity.this);
    }

    /**
     * ... wtf
     */
    private void initEvents() {
        imgHead.setOnClickListener(new MainActivityListener());
    }

    /**
     * set fragment of the main view.
     *
     * @param fragment fragment
     */
    private void setNavigationFragments(Fragment fragment) {
        this.getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    /**
     * share app
     */
    private void share() {
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        share.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
        share.putExtra(Intent.EXTRA_TEXT, "SimpleBook" + "\n" +
                "GitHub Page :  https://github.com/805056790/SimpleBook\n");
        startActivity(Intent.createChooser(share, getString(R.string.app_name)));
    }

    /**
     * send app
     */
    private void send() {
        String appUrl = "https://play.google.com/store/apps/details?id=" + getPackageName();
        Intent rateIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(appUrl));
        startActivity(rateIntent);
    }
}
