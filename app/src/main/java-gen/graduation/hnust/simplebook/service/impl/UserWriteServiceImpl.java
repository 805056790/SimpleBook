package graduation.hnust.simplebook.service.impl;

import android.content.Context;

import graduation.hnust.simplebook.base.BaseApplication;
import graduation.hnust.simplebook.greendao.UserDao;
import graduation.hnust.simplebook.model.User;
import graduation.hnust.simplebook.service.UserWriteService;

/**
 * @Author : panxin
 * @Date : 1:47 PM 3/20/16
 * @Email : panxin109@gmail.com
 */
public class UserWriteServiceImpl implements UserWriteService {

    private UserDao userDao;

    public UserWriteServiceImpl(Context context) {
        this.userDao = ((BaseApplication)context.getApplicationContext()).getDaoSession().getUserDao();
    }

    @Override
    public Long create(User user) {
        try {
            return userDao.insert(user);
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public Boolean update(User user) {
        try {
            userDao.update(user);
            return Boolean.TRUE;
        }catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    @Override
    public Boolean deleteById(Long id) {
        try{
            userDao.deleteByKey(id);
            return Boolean.TRUE;
        }catch (Exception e) {
            return Boolean.FALSE;
        }
    }
}
