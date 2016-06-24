package graduation.hnust.simplebook.service.impl;

import android.content.Context;

import graduation.hnust.simplebook.base.BaseApplication;
import graduation.hnust.simplebook.greendao.BookDao;
import graduation.hnust.simplebook.model.Book;
import graduation.hnust.simplebook.service.BookWriteService;

/**
 * @Author : panxin109@gmail.com
 * @Date : 5:53 PM 5/10/16
 */
public class BookWriteServiceImpl implements BookWriteService {

    private BookDao bookDao;

    public BookWriteServiceImpl(Context context) {
        this.bookDao = ((BaseApplication)context).getDaoSession().getBookDao();
    }

    @Override
    public Long create(Book book) {
        try {
            return bookDao.insert(book);
        }catch (Exception e) {
            e.printStackTrace();
            return -1L;
        }
    }

    @Override
    public Boolean update(Book book) {
        try {
            bookDao.update(book);
        }catch (Exception e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}
