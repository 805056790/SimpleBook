package graduation.hnust.simplebook.test;

import de.greenrobot.dao.test.AbstractDaoTestLongPk;

import graduation.hnust.simplebook.model.QQTokenModel;
import graduation.hnust.simplebook.greendao.QQTokenModelDao;

public class QQTokenModelTest extends AbstractDaoTestLongPk<QQTokenModelDao, QQTokenModel> {

    public QQTokenModelTest() {
        super(QQTokenModelDao.class);
    }

    @Override
    protected QQTokenModel createEntity(Long key) {
        QQTokenModel entity = new QQTokenModel();
        entity.setId(key);
        return entity;
    }

}
