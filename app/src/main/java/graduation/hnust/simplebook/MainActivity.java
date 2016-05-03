package graduation.hnust.simplebook;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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

import graduation.hnust.simplebook.activity.KeepAccountActivity;
import graduation.hnust.simplebook.activity.LoginActivity;
import graduation.hnust.simplebook.activity.listener.MainActivityListener;
import graduation.hnust.simplebook.model.User;
import graduation.hnust.simplebook.service.UserReadService;
import graduation.hnust.simplebook.service.impl.UserReadServiceImpl;
import graduation.hnust.simplebook.util.ActivityHelper;
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

    @Setter @Getter
    private User currentUser;

    // 额 ...
    private int flag = 1;

    // 头像, 昵称, 一些描述
    private ImageView imgHead;
    private TextView txtNickname;
    // private TextView txtDescription;

    private UserReadService userReadService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        instance = this;

        //init();

        // floating action button
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag == 1) {
                    fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_close_24dp));
                    flag = 0;
                }else {
                    fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_add_24dp));
                    flag = 1;
                }
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
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
        //txtDescription = (TextView) headerView.findViewById(R.id.side_menu_user_desc);

        // bind event
        initEvents();
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
                ActivityHelper.showActivity(MainActivity.this, KeepAccountActivity.class);
                break;
            case R.id.nav_expense:
                ActivityHelper.showActivity(MainActivity.this, TestActivity.class);
                break;
            case R.id.nav_settings:
                setNavigationFragments(new FragmentSetting());
                break;
            case R.id.nav_share:
                //share();
                break;
            case R.id.nav_send:
                //send();
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == LOGIN_RESULT) {
            if (resultCode == LoginActivity.LOGIN_SUCCESS || resultCode == LoginActivity.REGISTER_SUCCESS) {
                User user = (User) data.getSerializableExtra("user");
                setUserInfo(user);
            }
        }
    }

    /**
     * 设置用户信息
     *
     * @param user 用户信息
     */
    private void setUserInfo(User user) {
        if (user != null) {
            this.currentUser = user;
            //txtDescription.setText(user.getMobile());
            txtNickname.setText(user.getMobile());
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
