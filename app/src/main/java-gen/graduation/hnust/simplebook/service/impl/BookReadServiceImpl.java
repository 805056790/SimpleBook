package graduation.hnust.simplebook.service.impl;

import android.content.Context;

import graduation.hnust.simplebook.base.BaseApplication;
import graduation.hnust.simplebook.greendao.BookDao;
import graduation.hnust.simplebook.model.Book;
import graduation.hnust.simplebook.service.BookReadService;

/**
 * @Author : panxin109@gmail.com
 * @Date : 5:52 PM 5/10/16
 */
public class BookReadServiceImpl implements BookReadService {

    private BookDao bookDao;

    public BookReadServiceImpl(Context context) {
        this.bookDao = ((BaseApplication)context).getDaoSession().getBookDao();
    }

    @Override
    public Book findByUserId(Long userId) {
        try {
            return bookDao.queryBuilder().where(BookDao.Properties.UserId.eq(userId)).build().unique();
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
