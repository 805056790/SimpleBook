package graduation.hnust.simplebook.view.adatper;

import android.support.annotation.NonNull;

import java.io.Serializable;

import lombok.Data;

/**
 * @Author : panxin109@gmail.com
 * @Date : 1:43 PM 5/10/16
 */
@Data
public class ItemDto implements Serializable, Comparable {

    private Integer typeId;

    private String typeName;

    private Integer imageId;

    private String percent;

    private Integer amount;

    @Override
    public int compareTo(@NonNull Object obj) {
        ItemDto dto = (ItemDto) obj;
        return dto.getAmount() - this.amount;
    }
}
