package graduation.hnust.simplebook.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 反馈信息
 *
 * @Author : panxin
 * @Date : 8:55 PM 3/22/16
 * @Email : panxin109@gmail.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedBack {

    /**
     * ID
     */
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 反馈内容
     */
    private String content;

    private Date createdAt;

    private Date updatedAt;

}
