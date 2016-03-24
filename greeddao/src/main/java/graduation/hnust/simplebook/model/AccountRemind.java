package graduation.hnust.simplebook.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 财务提醒
 *
 * @Author : panxin
 * @Date : 8:45 PM 3/22/16
 * @Email : panxin109@gmail.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountRemind {

    /**
     * ID
     */
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 账本ID
     */
    private Long bookId;

    /**
     * 收支类型 1:收入  -1:支出
     */
    private Integer type;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 名称
     */
    private String name;

    /**
     * 截止时间
     */
    private Date endTime;

    /**
     * 提醒时间
     */
    private Date remindTime;

    /**
     * 重复次数
     */
    private Integer repeatTime;

    /**
     * 金额
     */
    private Integer amount;

    /**
     * 是否自动记账
     */
    private Boolean isAutoMark;

    /**
     * 消费类型
     */
    private Integer consumeType;

    private Date createdAt;

    private Date updatedAt;

}
