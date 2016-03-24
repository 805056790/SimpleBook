package graduation.hnust.simplebook.test;

import de.greenrobot.dao.test.AbstractDaoTestLongPk;

import graduation.hnust.simplebook.model.Item;
import graduation.hnust.simplebook.greendao.ItemDao;

public class ItemTest extends AbstractDaoTestLongPk<ItemDao, Item> {

    public ItemTest() {
        super(ItemDao.class);
    }

    @Override
    protected Item createEntity(Long key) {
        Item entity = new Item();
        entity.setId(key);
        return entity;
    }

}
