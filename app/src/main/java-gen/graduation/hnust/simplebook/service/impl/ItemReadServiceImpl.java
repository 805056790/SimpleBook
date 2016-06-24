package graduation.hnust.simplebook.service.impl;

import android.content.Context;
import android.util.Log;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import graduation.hnust.simplebook.base.BaseApplication;
import graduation.hnust.simplebook.dto.ItemDto;
import graduation.hnust.simplebook.greendao.ConsumeTypeDao;
import graduation.hnust.simplebook.greendao.ItemDao;
import graduation.hnust.simplebook.model.ConsumeType;
import graduation.hnust.simplebook.model.Item;
import graduation.hnust.simplebook.service.ItemReadService;

/**
 * @Author : panxin109@gmail.com
 * @Date : 6:30 PM 5/3/16
 */
public class ItemReadServiceImpl extends BaseApplication implements ItemReadService {

    private ItemDao itemDao;

    public ItemReadServiceImpl(Context context) {
        this.itemDao = ((BaseApplication) context).getDaoSession().getItemDao();
    }

    @Override
    public List<Item> findItemsByUserId(Long userId) {
        try {
            return itemDao.queryBuilder()
                    .where(ItemDao.Properties.UserId.eq(userId))
                    .orderDesc(ItemDao.Properties.Date)
                    .build()
                    .list();
        }catch (Exception e) {
            Log.e("app", "failed to find items by userId = " + userId + ", cause " + e.toString());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<Item> findItemsByUserIdAndDate(Long userId, Date date) {
        try {
            return itemDao.queryBuilder()
                    .where(ItemDao.Properties.UserId.eq(userId), ItemDao.Properties.Date.eq(date))
                    .orderDesc(ItemDao.Properties.Id)
                    .build()
                    .list();
        }catch (Exception e) {
            Log.e("app", "failed to find items by userId = " + userId + " and date = " + date + ", cause " + e.toString());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<Item> findItemsByUserIdAndType(Long userId, Integer type) {
        try {
            return itemDao.queryBuilder()
                    .where(ItemDao.Properties.UserId.eq(userId), ItemDao.Properties.Type.eq(type))
                    .orderDesc(ItemDao.Properties.Date)
                    .build()
                    .list();
        }catch (Exception e) {
            Log.e("app", "failed to find items by userId = " + userId + " and type = " + type + ", cause " + e.toString());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<Item> findItemsByUserIdAndDateAndType(Long userId, Date date, Integer type) {
        try {
            return itemDao.queryBuilder()
                    .where(ItemDao.Properties.UserId.eq(userId), ItemDao.Properties.Date.eq(date), ItemDao.Properties.Type.eq(type))
                    .orderDesc(ItemDao.Properties.Id)
                    .build()
                    .list();
        }catch (Exception e) {
            Log.e("app", "failed to find items by userId = " + userId + ", date = " + date + ", and type = " + type +
                    "cause " + e.toString());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<Item> findItemByConsumeType(Long userId, Integer type, Integer consumeType) {
        try {
            return itemDao.queryBuilder()
                    .where(ItemDao.Properties.UserId.eq(userId), ItemDao.Properties.ConsumeType.eq(consumeType), ItemDao.Properties.Type.eq(type))
                    .orderDesc(ItemDao.Properties.Id)
                    .build()
                    .list();
        }catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Item findById(Long itemId) {
        try {
            return itemDao.queryBuilder()
                    .where(ItemDao.Properties.Id.eq(itemId))
                    .orderDesc(ItemDao.Properties.Id)
                    .build()
                    .unique();
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Item> findByMonth(Date start, Date end) {
        try {
            return itemDao.queryBuilder()
                    .where(ItemDao.Properties.Date.between(start, end))
                    .orderDesc(ItemDao.Properties.Id)
                    .build()
                    .list();
        }catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<Item> findByDay(Date date) {
        try {
            return itemDao.queryBuilder()
                    .where(ItemDao.Properties.Date.eq(date))
                    .orderDesc(ItemDao.Properties.Id)
                    .build()
                    .list();
        }catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<Item> findItemByConsumeTypeAndDate(Long userId, Integer type, Integer consumeType, Date date) {
        try {
            return itemDao.queryBuilder()
                    .where(ItemDao.Properties.UserId.eq(userId),
                            ItemDao.Properties.ConsumeType.eq(consumeType),
                            ItemDao.Properties.Type.eq(type),
                            ItemDao.Properties.Date.eq(date))
                    .orderDesc(ItemDao.Properties.Id)
                    .build()
                    .list();
        }catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<Item> findByMonth(Long userId, Integer type, Date startDate, Date endDate) {
        try {
            return itemDao.queryBuilder()
                    .where(ItemDao.Properties.Date.between(startDate, endDate),
                            ItemDao.Properties.UserId.eq(userId),
                            ItemDao.Properties.Type.eq(type))
                    .orderDesc(ItemDao.Properties.Id)
                    .build()
                    .list();
        }catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<Item> findItemByConsumeType(Long userId, Integer type, Integer consumerType, Date startDate, Date endDate) {
        try {
            return itemDao.queryBuilder()
                    .where(ItemDao.Properties.Date.between(startDate, endDate),
                            ItemDao.Properties.UserId.eq(userId),
                            ItemDao.Properties.Type.eq(type),
                            ItemDao.Properties.ConsumeType.eq(consumerType))
                    .orderDesc(ItemDao.Properties.Id)
                    .build()
                    .list();
        }catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<Item> findByDay(Long userId, Date date) {
        try {
            return itemDao.queryBuilder()
                    .where(ItemDao.Properties.Date.eq(date),
                            ItemDao.Properties.UserId.eq(userId))
                    .build()
                    .list();
        }catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
