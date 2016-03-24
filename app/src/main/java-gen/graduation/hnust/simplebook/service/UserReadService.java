package graduation.hnust.simplebook.service;

import graduation.hnust.simplebook.model.User;

/**
 * @Author : panxin
 * @Date : 1:46 PM 3/20/16
 * @Email : panxin109@gmail.com
 */
public interface UserReadService {

    /**
     * 通过ID 查找用户信息
     *
     * @param id 用户ID
     * @return 用户信息
     */
    User findById(Long id);

    /**
     * 通过手机号查找
     *
     * @param mobile 手机号
     * @return
     */
    User findByMobile(String mobile);

    /**
     * 通过邮箱查找
     *
     * @param email 邮箱
     * @return
     */
    User findByEmail(String email);

    /**
     * 通过QQToken查找用户信息
     *
     * @param qqToken qqToken
     * @return 用户信息
     */
    User findByQqToken(String qqToken);

    /**
     * 通过QQ openId 查找用户信息
     *
     * @param openId openId
     * @return 用户信息
     */
    User findByQqOpenId(String openId);

}
