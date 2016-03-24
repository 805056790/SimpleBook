package graduation.hnust.simplebook.test;

import de.greenrobot.dao.test.AbstractDaoTestLongPk;

import graduation.hnust.simplebook.model.QQInfoModel;
import graduation.hnust.simplebook.greendao.QQInfoModelDao;

public class QQInfoModelTest extends AbstractDaoTestLongPk<QQInfoModelDao, QQInfoModel> {

    public QQInfoModelTest() {
        super(QQInfoModelDao.class);
    }

    @Override
    protected QQInfoModel createEntity(Long key) {
        QQInfoModel entity = new QQInfoModel();
        entity.setId(key);
        return entity;
    }

}
