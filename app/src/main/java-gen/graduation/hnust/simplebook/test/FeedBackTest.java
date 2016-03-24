package graduation.hnust.simplebook.test;

import de.greenrobot.dao.test.AbstractDaoTestLongPk;

import graduation.hnust.simplebook.model.FeedBack;
import graduation.hnust.simplebook.greendao.FeedBackDao;

public class FeedBackTest extends AbstractDaoTestLongPk<FeedBackDao, FeedBack> {

    public FeedBackTest() {
        super(FeedBackDao.class);
    }

    @Override
    protected FeedBack createEntity(Long key) {
        FeedBack entity = new FeedBack();
        entity.setId(key);
        return entity;
    }

}
