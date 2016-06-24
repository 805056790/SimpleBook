package graduation.hnust.simplebook.dto;

import graduation.hnust.simplebook.model.ConsumeType;
import graduation.hnust.simplebook.model.Item;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author : panxin109@gmail.com
 * @Date : 8:31 AM 5/4/16
 */
@Data
public class ItemDto {

    private Item item;

    private ConsumeType consumeType;
}
