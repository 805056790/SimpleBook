package graduation.hnust.simplebook.model.third;

import java.io.Serializable;
import java.util.Date;

import graduation.hnust.simplebook.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 用户QQ 登录信息
 *
 * @Author : panxin
 * @Date   : 6:17 PM 3/17/16
 * @Email  : panxin109@gmail.com
 */
@Data
@ToString
@NoArgsConstructor
public class QQTokenModel implements Serializable {

    private static final long serialVersionUID = -5770297866345904541L;

    private Long id;

    /**
     * 关联用户ID
     * @see User#id
     */
    private Long userId;

    /**
     * 唯一ID
     */
    private String openId;

    /**
     * 登录session, 三个月
     */
    private String accessToken;

    private String ret;

    private String payToken;

    private String pf;

    private String queryAuthorityCost;

    private String expiresIn;

    private String pfKey;

    private String msg;

    private String loginCost;

    private Date createdAt;

    private Date updatedAt;

}
