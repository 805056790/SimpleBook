package graduation.hnust.simplebook.test;

import de.greenrobot.dao.test.AbstractDaoTestLongPk;

import graduation.hnust.simplebook.model.User;
import graduation.hnust.simplebook.greendao.UserDao;

public class UserTest extends AbstractDaoTestLongPk<UserDao, User> {

    public UserTest() {
        super(UserDao.class);
    }

    @Override
    protected User createEntity(Long key) {
        User entity = new User();
        entity.setId(key);
        return entity;
    }

}
