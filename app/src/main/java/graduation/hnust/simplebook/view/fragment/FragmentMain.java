package graduation.hnust.simplebook.view.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.yalantis.phoenix.PullToRefreshView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import graduation.hnust.simplebook.MainActivity;
import graduation.hnust.simplebook.R;
import graduation.hnust.simplebook.activity.IncomeStatisticsActivity;
import graduation.hnust.simplebook.activity.KeepAccountActivity;
import graduation.hnust.simplebook.common.DateHelper;
import graduation.hnust.simplebook.constants.KeyConstants;
import graduation.hnust.simplebook.dto.UserDto;
import graduation.hnust.simplebook.enums.ConsumeTypes;
import graduation.hnust.simplebook.model.Book;
import graduation.hnust.simplebook.model.ChildEntity;
import graduation.hnust.simplebook.model.GroupEntity;
import graduation.hnust.simplebook.model.Item;
import graduation.hnust.simplebook.model.User;
import graduation.hnust.simplebook.service.BookReadService;
import graduation.hnust.simplebook.service.ItemReadService;
import graduation.hnust.simplebook.service.impl.BookReadServiceImpl;
import graduation.hnust.simplebook.service.impl.ItemReadServiceImpl;
import graduation.hnust.simplebook.util.ActivityHelper;
import graduation.hnust.simplebook.util.ToastUtil;
import graduation.hnust.simplebook.view.WaterWaveView;
import graduation.hnust.simplebook.view.adatper.ItemAdapter;
import graduation.hnust.simplebook.view.adatper.TimeLineAdapter;
import graduation.hnust.simplebook.view.dialog.BudgetDialog;

/**
 * The main view of app
 *
 * @Author : panxin
 * @Date : 10:39 PM 3/24/16
 * @Email : panxin109@gmail.com
 */
public class FragmentMain extends Fragment implements View.OnClickListener {

    public static FragmentMain FRAGMENT_INSTANCE;

    private Context context;

    // 刷新时延
    private static int REFRESH_DELAY = 1200;

    private PullToRefreshView mPullToRefreshView;
    private ListView mListView;
    private ItemAdapter itemAdapter;

    private Book book;
    private View view;
    private UserDto user;
    private Integer totalExpense;
    private ItemReadService readService;

    private ExpandableListView expandableListView;
    private List<GroupEntity> lists;
    private TimeLineAdapter adapter;

    private TextView txtIncome;
    private TextView txtExpense;
    private TextView txtIncomeTxt;
    private TextView txtExpenseTxt;

    private WaterWaveView waterWaveView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);
        context = inflater.getContext();
        FRAGMENT_INSTANCE = this;
        // itemAdapter = new ItemAdapter(context, getData());
        user = MainActivity.instance.getCurrentUser();
        readService = new ItemReadServiceImpl(context.getApplicationContext());

        initView(view);
        initEvents(view);

        setPullRefresh(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initView(view);
        txtExpense.setBackgroundResource(R.color.txtNoPress);
        txtExpenseTxt.setBackgroundResource(R.color.txtNoPress);
        txtIncome.setBackgroundResource(R.color.txtNoPress);
        txtIncomeTxt.setBackgroundResource(R.color.txtNoPress);
    }

    /**
     * events
     * @param view view
     */
    private void initEvents(View view) {
        txtIncome.setOnClickListener(this);
        txtExpense.setOnClickListener(this);
        waterWaveView.setOnClickListener(this);
    }

    /**
     * 设置下拉刷新
     *
     * @param view view of container
     */
    private void setPullRefresh(final View view) {
        mPullToRefreshView = (PullToRefreshView) view.findViewById(R.id.pull_to_refresh);

        mPullToRefreshView = (PullToRefreshView) view.findViewById(R.id.pull_to_refresh);
        mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPullToRefreshView.setRefreshing(false);// 为ListView生成数据
                        initView(view);
                    }
                }, REFRESH_DELAY);
            }
        });
    }

    @Deprecated
    private List<Item> getData() {
        UserDto user = MainActivity.instance.getCurrentUser();
        ItemReadService readService = new ItemReadServiceImpl(context.getApplicationContext());
        // 没有登录则取默认数据
        if (user == null) {
            return readService.findItemsByUserId(0L);
        }
        List<Item> respList = readService.findItemsByUserId(user.getUser().getId());
        Log.i("item", respList.toString());
        return respList;
    }

    /**
     * 初始化数据
     *
     * @param view view
     */
    public void initView(View view) {
        txtIncome = (TextView) view.findViewById(R.id.txt_income);
        txtExpense = (TextView) view.findViewById(R.id.txt_expense);
        txtIncomeTxt = (TextView) view.findViewById(R.id.txt_income_txt);
        txtExpenseTxt = (TextView) view.findViewById(R.id.txt_expense_txt);

        lists = initItemList();
        adapter = new TimeLineAdapter(context, lists);
        expandableListView = (ExpandableListView) view.findViewById(R.id.expandableListView);
        expandableListView.setAdapter(adapter);
        expandableListView.setGroupIndicator(null); // 去掉默认带的箭头
        expandableListView.setSelection(0);// 设置默认选中项
        // 遍历所有group,将所有项设置成默认展开
        int groupCount = expandableListView.getCount();
        for (int i = 0; i < groupCount; i++) {
            expandableListView.expandGroup(i);
        }
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });

        book = getBook();
        double percent = 0;
        if (book != null) {
            Log.i("book", totalExpense + ", " + book.getBudget());
            percent = (double)totalExpense/(double)book.getBudget();
            percent = percent * 100;
            Log.i("book", "比例1 = " + percent);
            if (percent <= 0 ) {
                percent = 1;
            }
        }
        Log.i("book", "比例2 = " + percent);
        waterWaveView = (WaterWaveView) view.findViewById(R.id.waterWaveView);
        waterWaveView.setProgress((int)percent);
    }

    /**
     * 初始化数据
     *
     * @return data
     */
    private List<GroupEntity> initItemList() {

        List<GroupEntity> groupList = Lists.newArrayList();
        List<Item> itemList = getData2();
        Map<String, List<Item>> itemMap = Maps.newLinkedHashMap();

        int income = 0;
        int expense = 0;
        // 设置标题
        for (Item item : itemList) {
            String date = DateHelper.date2String(item.getDate());
            if (!itemMap.containsKey(date)) {
                if (user == null) {
                    itemMap.put(date, readService.findItemsByUserIdAndDate(0L, item.getDate()));
                }else {
                    itemMap.put(date, readService.findItemsByUserIdAndDate(user.getUser().getId(), item.getDate()));
                }
            }
            if (item.getType() == ConsumeTypes.Type.INCOME.value()) {
                income += item.getAmount();
            }else {
                expense += item.getAmount();
            }
        }
        txtIncome.setText(income / 100 + "元");
        txtExpense.setText(expense / 100 + "元");
        this.totalExpense = expense / 100;

        for (Map.Entry<String, List<Item>> entry : itemMap.entrySet()) {
            GroupEntity groupEntity = new GroupEntity(entry.getKey());
            List<ChildEntity> childList = Lists.newArrayList();
            for (Item item : entry.getValue()) {
                ChildEntity child = new ChildEntity(item.getConsumeTypeName());
                child.setItem(item);
                childList.add(child);
            }
            groupEntity.setChildEntities(childList);
            groupList.add(groupEntity);
        }

        return groupList;
    }

    /**
     * 获取数据
     *
     * @return data
     */
    private List<Item> getData2() {
        // 没有登录则取默认数据
        if (user == null) {
            return readService.findItemsByUserId(0L);
        }
        List<Item> respList = readService.findItemsByUserId(user.getUser().getId());
        Log.i("item", respList.toString());
        return respList;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.txt_income:
                txtIncome.setBackgroundResource(R.color.txtPress);
                txtIncomeTxt.setBackgroundResource(R.color.txtPress);
                goSomeWhere(ConsumeTypes.Type.INCOME.value());
                break;
            case R.id.txt_income_txt:
                txtIncome.setBackgroundResource(R.color.txtPress);
                txtIncomeTxt.setBackgroundResource(R.color.txtPress);
                goSomeWhere(ConsumeTypes.Type.INCOME.value());
                break;
            case R.id.txt_expense_txt:
                txtExpense.setBackgroundResource(R.color.txtPress);
                txtExpenseTxt.setBackgroundResource(R.color.txtPress);
                goSomeWhere(ConsumeTypes.Type.EXPENSE.value());
                break;
            case R.id.txt_expense:
                txtExpense.setBackgroundResource(R.color.txtPress);
                txtExpenseTxt.setBackgroundResource(R.color.txtPress);
                goSomeWhere(ConsumeTypes.Type.EXPENSE.value());
                break;
            case R.id.waterWaveView:
                setOrUpdateBudget(view);
            default:
                break;
        }
    }

    /**
     * ..
     *
     * @param type ..
     */
    private void goSomeWhere(int type) {
        Intent intent = new Intent(context, IncomeStatisticsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("user", user);
        intent.putExtras(bundle);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }

    /**
     * 获取预算
     *
     * @return 信息
     */
    private Book getBook() {
        Book book = null;
        try {
            BookReadService service = new BookReadServiceImpl(context.getApplicationContext());
            if (user == null) {
                book = service.findByUserId(0L);
            }else {
                book = service.findByUserId(user.getUser().getId());
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return book;
    }

    /**
     * 更新
     * @param view view
     */
    public void setOrUpdateBudget(View view) {
        Intent intent = new Intent(context, BudgetDialog.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("book", book);
        if(user != null) {
            bundle.putSerializable("user", user);
        }
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
