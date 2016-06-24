package graduation.hnust.simplebook.dto;

import java.io.Serializable;

import graduation.hnust.simplebook.model.QQInfoModel;
import graduation.hnust.simplebook.model.QQTokenModel;
import graduation.hnust.simplebook.model.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author : panxin109@gmail.com
 * @Date : 8:29 AM 5/4/16
 */
@Data
public class UserDto implements Serializable{

    private User user;

    private QQInfoModel qqInfoModel;

    private QQTokenModel qqTokenModel;

}
