package graduation.hnust.simplebook.test;

import de.greenrobot.dao.test.AbstractDaoTestLongPk;

import graduation.hnust.simplebook.model.AccountRemind;
import graduation.hnust.simplebook.greendao.AccountRemindDao;

public class AccountRemindTest extends AbstractDaoTestLongPk<AccountRemindDao, AccountRemind> {

    public AccountRemindTest() {
        super(AccountRemindDao.class);
    }

    @Override
    protected AccountRemind createEntity(Long key) {
        AccountRemind entity = new AccountRemind();
        entity.setId(key);
        return entity;
    }

}
