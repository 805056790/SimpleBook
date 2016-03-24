package graduation.hnust.simplebook.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 账本信息
 *
 * @Author : panxin
 * @Date : 8:17 PM 3/22/16
 * @Email : panxin109@gmail.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book  {

    /**
     * ID
     */
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 记账类型
     */
    private Integer type;

    /**
     * 名称
     */
    private String name;

    /**
     * 是否默认
     */
    private Boolean isDefault;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 预算
     */
    private Integer budget;

    /**
     * 计算时间
     */
    private Date settlementTime;

    /**
     * 是否提醒
     */
    private Boolean idRemind;

    /**
     * 背景色
     */
    private String color;

    /**
     * 存储类型 1: 云端, 2: 本地
     */
    private Integer storeType;

    private Date createdAt;

    private Date updatedAt;

}
