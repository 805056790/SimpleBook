package graduation.hnust.simplebook.model.third;

import java.io.Serializable;
import java.util.Date;

import graduation.hnust.simplebook.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 用户QQ 详细信息
 *
 * @Author : panxin
 * @Date   : 6:18 PM 3/17/16
 * @Email  : panxin109@gmail.com
 */
@Data
@ToString
@NoArgsConstructor
public class QQInfoModel implements Serializable{

    private static final long serialVersionUID = 8294335940819185247L;

    private Long id;

    /**
     * 关联用户ID
     * @see User#id
     */
    private Long userId;

    /**
     * 未知
     */
    private String ret;

    /**
     * 位置消息
     */
    private String msg;

    /**
     * 是否遗失
     */
    private String isLost;

    /**
     * QQ昵称
     */
    private String nickname;

    /**
     * 性别
     */
    private String gender;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 出生年
     */
    private String year;

    /**
     * 空间头像 30*30
     */
    private String figureUrl;

    /**
     * 空间头像 50*50
     */
    private String figureUrl1;

    /**
     * 空间头像 100*100
     */
    private String figureUrl2;

    /**
     * QQ头像 40*40
     */
    private String figureUrlQq1;

    /**
     * QQ头像 100*100
     */
    private String figureUrlQq2;

    /**
     * 是否黄钻
     */
    private String isYellowVip;

    /**
     * 是否会员
     */
    private String vip;

    /**
     * 黄钻年会员
     */
    private String yellowVipLevel;

    /**
     * 未知等级
     */
    private String level;

    /**
     * 是否黄钻年会员
     */
    private String isYellowYearVip;

    private Date createdAt;

    private Date updatedAt;

}
