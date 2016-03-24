package graduation.hnust.simplebook.service.impl;

import android.content.Context;

import graduation.hnust.simplebook.base.BaseApplication;
import graduation.hnust.simplebook.enums.Users;
import graduation.hnust.simplebook.greendao.UserDao;
import graduation.hnust.simplebook.model.User;
import graduation.hnust.simplebook.service.UserReadService;

/**
 * @Author : panxin
 * @Date : 1:47 PM 3/20/16
 * @Email : panxin109@gmail.com
 */
public class UserReadServiceImpl implements UserReadService {

    private UserDao userDao;

    public UserReadServiceImpl(Context context) {
        this.userDao = ((BaseApplication) context).getDaoSession().getUserDao();
    }

    @Override
    public User findById(Long id) {
        try {
            // 用户状态为正常, 用户类型为普通用户
            return userDao.queryBuilder().where(
                    UserDao.Properties.Id.eq(id),
                    UserDao.Properties.Status.eq(Users.Status.NORMAL.value()),
                    UserDao.Properties.Type.eq(Users.Type.NORMAL.value())).build().unique();
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public User findByMobile(String mobile) {
        try {
            // 用户状态为正常, 用户类型为普通用户
            return userDao.queryBuilder().where(
                    UserDao.Properties.Status.eq(Users.Status.NORMAL.value()),
                    UserDao.Properties.Type.eq(Users.Type.NORMAL.value()),
                    UserDao.Properties.Mobile.eq(mobile)).build().unique();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public User findByEmail(String email) {
        try {
            // 用户状态为正常, 用户类型为普通用户
            return userDao.queryBuilder().where(
                    UserDao.Properties.Status.eq(Users.Status.NORMAL.value()),
                    UserDao.Properties.Type.eq(Users.Type.NORMAL.value()),
                    UserDao.Properties.Email.eq(email)).build().unique();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public User findByQqToken(String qqToken) {
        try {
            // 用户状态为正常, 用户类型为普通用户
            return userDao.queryBuilder().where(
                    UserDao.Properties.Status.eq(Users.Status.NORMAL.value()),
                    UserDao.Properties.Type.eq(Users.Type.NORMAL.value()),
                    UserDao.Properties.QqToken.eq(qqToken)).build().unique();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public User findByQqOpenId(String openId) {
        try {
            // 用户状态为正常, 用户类型为普通用户
            return userDao.queryBuilder().where(
                    UserDao.Properties.Status.eq(Users.Status.NORMAL.value()),
                    UserDao.Properties.Type.eq(Users.Type.NORMAL.value()),
                    UserDao.Properties.QqToken.eq(openId)).build().unique();
        }catch (Exception e){
            return null;
        }
    }


}
