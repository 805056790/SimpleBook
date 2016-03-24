package graduation.hnust.simplebook.model;

import java.io.Serializable;
import java.util.Date;

import graduation.hnust.simplebook.model.enums.Gender;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 平台注册用户基本信息
 *
 * @Author : panxin
 * @Date   : 4:05 PM 3/17/16
 * @Email  : panxin109@gmail.com
 */
@Data
@ToString
@NoArgsConstructor
public class User implements Serializable{

    private static final long serialVersionUID = 4028913688931417121L;

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 密码
     */
    private String password;

    /**
     * 状态
     */
    private Integer status;

    /**
     * QQToken, 冗余至QQToken
     */
    private String QqToken;

    /**
     * TODO 微信
     */
    private String weixinToken;

    /**
     * TODO 微博
     */
    private String weiboToken;

    /**
     * 昵称(默认优先取这个字段, 其次取第三方信息的昵称)
     */
    private String nickname;

    /**
     * 用户类型
     */
    private Integer type;

    /**
     * 性别
     * @see Gender
     */
    private Integer gender;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 头像地址, 本地地址, 云端地址
     * JSON {local:xxx.xxx, cloud:xxx.xxx}
     */
    private String image;

    private Date createdAt;
    private Date updatedAt;
}
