package graduation.hnust.simplebook.view.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import graduation.hnust.simplebook.MainActivity;
import graduation.hnust.simplebook.R;
import graduation.hnust.simplebook.common.DateHelper;
import graduation.hnust.simplebook.dto.UserDto;
import graduation.hnust.simplebook.enums.ConsumeTypes;
import graduation.hnust.simplebook.model.ChildEntity;
import graduation.hnust.simplebook.model.GroupEntity;
import graduation.hnust.simplebook.model.Item;
import graduation.hnust.simplebook.model.User;
import graduation.hnust.simplebook.service.ItemReadService;
import graduation.hnust.simplebook.service.impl.ItemReadServiceImpl;
import graduation.hnust.simplebook.view.adatper.TimeLineAdapter;

/**
 * 收入
 *
 * @Author : panxin109@gmail.com
 * @Date : 10:42 AM 5/8/16
 */
public class FragmentExpense extends Fragment{

    private Context context;
    private View view;
    private UserDto user;
    private ItemReadService readService;

    private ExpandableListView expandableListView;
    private List<GroupEntity> lists;
    private TimeLineAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_income, container, false);
        context = inflater.getContext();
        user = MainActivity.instance.getCurrentUser();
        readService = new ItemReadServiceImpl(context.getApplicationContext());
        initView(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initView(view);
    }

    private void initView(View view) {
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
    }

    /**
     * 初始化数据
     *
     * @return data
     */
    private List<GroupEntity> initItemList() {

        List<GroupEntity> groupList = Lists.newArrayList();
        List<Item> itemList = getData();
        Map<String, List<Item>> itemMap = Maps.newLinkedHashMap();

        // 设置标题
        for (Item item : itemList) {
            String date = DateHelper.date2String(item.getDate());
            if (!itemMap.containsKey(date)) {
                if (user == null) {
                    itemMap.put(date, readService.findItemsByUserIdAndDateAndType(
                            0L, item.getDate(), ConsumeTypes.Type.EXPENSE.value()));
                }else {
                    itemMap.put(date, readService.findItemsByUserIdAndDateAndType(
                            user.getUser().getId(), item.getDate(), ConsumeTypes.Type.EXPENSE.value()));
                }
            }
        }

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
    private List<Item> getData() {
        // 没有登录则取默认数据
        if (user == null) {
            return readService.findItemsByUserIdAndType(0L, ConsumeTypes.Type.EXPENSE.value());
        }
        List<Item> respList = readService.findItemsByUserIdAndType(user.getUser().getId(), ConsumeTypes.Type.EXPENSE.value());
        Log.i("item", respList.toString());
        return respList;
    }
}
