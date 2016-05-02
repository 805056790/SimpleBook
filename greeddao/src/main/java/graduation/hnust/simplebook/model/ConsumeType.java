package graduation.hnust.simplebook.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 消费类型
 *
 * @Author : panxin
 * @Date : 8:52 PM 3/22/16
 * @Email : panxin109@gmail.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsumeType {

    /**
     * ID
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 图片ID
     */
    private Integer imageId;

    /**
     * 图片名称
     */
    private String imageName;

    private Date createdAt;

    private Date updatedAt;

}
