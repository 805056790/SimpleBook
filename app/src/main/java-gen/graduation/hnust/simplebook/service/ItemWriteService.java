package graduation.hnust.simplebook.service;

import graduation.hnust.simplebook.model.Item;

/**
 * @Author : panxin109@gmail.com
 * @Date : 6:08 PM 5/3/16
 */
public interface ItemWriteService {

    /**
     * 创建记账记录
     *
     * @param item 账目信息
     * @return 是否创建成功
     */
    Boolean create(Item item);

    /**
     * 删除
     *
     * @param item 删除Item
     * @return 是否删除成功
     */
    Boolean delete(Item item);
}
