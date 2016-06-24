package graduation.hnust.simplebook.service;

import graduation.hnust.simplebook.model.Book;

/**
 * @Author : panxin109@gmail.com
 * @Date : 5:52 PM 5/10/16
 */
public interface BookReadService {

    Book findByUserId(Long userId);

}
