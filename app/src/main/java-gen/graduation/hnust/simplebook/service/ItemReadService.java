package graduation.hnust.simplebook.service;

import java.util.Date;
import java.util.List;

import graduation.hnust.simplebook.model.Item;
import graduation.hnust.simplebook.model.User;

/**
 * @Author : panxin109@gmail.com
 * @Date : 6:09 PM 5/3/16
 */
public interface ItemReadService {

    List<Item> findItemsByUserId(Long userId);

    List<Item> findItemsByUserIdAndDate(Long userId, Date date);

    List<Item> findItemsByUserIdAndType(Long userId, Integer type);

    List<Item> findItemsByUserIdAndDateAndType(Long userId, Date date, Integer type);

    List<Item> findItemByConsumeType(Long userId, Integer type, Integer consumeType);

    Item findById(Long itemId);

    List<Item> findByMonth(Date start, Date end);

    List<Item> findByDay(Date date);

    List<Item> findItemByConsumeTypeAndDate(Long userId, Integer type, Integer consumeType, Date date);

    List<Item> findByMonth(Long userId, Integer type, Date startDate, Date endDate);

    List<Item> findItemByConsumeType(Long userId, Integer type, Integer consumerType, Date startDate, Date endDate);

    List<Item> findByDay(Long userId, Date date);
}
