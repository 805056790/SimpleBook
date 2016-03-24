package graduation.hnust.simplebook.test;

import de.greenrobot.dao.test.AbstractDaoTestLongPk;

import graduation.hnust.simplebook.model.ConsumeType;
import graduation.hnust.simplebook.greendao.ConsumeTypeDao;

public class ConsumeTypeTest extends AbstractDaoTestLongPk<ConsumeTypeDao, ConsumeType> {

    public ConsumeTypeTest() {
        super(ConsumeTypeDao.class);
    }

    @Override
    protected ConsumeType createEntity(Long key) {
        ConsumeType entity = new ConsumeType();
        entity.setId(key);
        return entity;
    }

}
