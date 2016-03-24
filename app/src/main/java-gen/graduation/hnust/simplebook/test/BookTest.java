package graduation.hnust.simplebook.test;

import de.greenrobot.dao.test.AbstractDaoTestLongPk;

import graduation.hnust.simplebook.model.Book;
import graduation.hnust.simplebook.greendao.BookDao;

public class BookTest extends AbstractDaoTestLongPk<BookDao, Book> {

    public BookTest() {
        super(BookDao.class);
    }

    @Override
    protected Book createEntity(Long key) {
        Book entity = new Book();
        entity.setId(key);
        return entity;
    }

}
