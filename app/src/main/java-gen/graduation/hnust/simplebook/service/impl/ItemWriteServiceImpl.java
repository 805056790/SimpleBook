package graduation.hnust.simplebook.service.impl;

import android.content.Context;
import android.util.Log;

import graduation.hnust.simplebook.base.BaseApplication;
import graduation.hnust.simplebook.greendao.ItemDao;
import graduation.hnust.simplebook.model.Item;
import graduation.hnust.simplebook.service.ItemWriteService;

/**
 * @Author : panxin109@gmail.com
 * @Date : 6:10 PM 5/3/16
 */
public class ItemWriteServiceImpl extends BaseApplication implements ItemWriteService {

    private ItemDao itemDao;

    public ItemWriteServiceImpl(Context context) {
        this.itemDao = ((BaseApplication) context.getApplicationContext()).getDaoSession().getItemDao();
    }

    @Override
    public Boolean create(Item item) {
        try {
            itemDao.insert(item);
        }catch (Exception e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean delete(Item item) {
        try {
            itemDao.delete(item);
        }catch (Exception e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}
