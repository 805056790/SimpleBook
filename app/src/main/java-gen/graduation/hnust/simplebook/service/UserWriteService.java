package graduation.hnust.simplebook.service;

import graduation.hnust.simplebook.model.User;

/**
 * 用户信息写服务
 *
 * @Author : panxin
 * @Date : 1:46 PM 3/20/16
 * @Email : panxin109@gmail.com
 */
public interface UserWriteService {

    /**
     * 创建用户
     *
     * @param user 用户信息
     * @return 用户ID
     */
    Long create(User user);

    /**
     * 更新用户信息
     *
     * @param user 用户信息
     * @return
     */
    Boolean update(User user);

    /**
     * 删除用户信息
     *
     * @param id 用户ID
     * @return
     */
    Boolean deleteById(Long id);

}
