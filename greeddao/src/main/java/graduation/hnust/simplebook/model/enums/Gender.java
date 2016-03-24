package graduation.hnust.simplebook.model.enums;

import com.google.common.base.Objects;

import java.io.Serializable;

/**
 * 性别
 *
 * @Author : panxin
 * @Date   : 8:12 PM 3/18/16
 * @Email  : panxin109@gmail.com
 */
public enum Gender implements Serializable {

    BOY(1,"男"),
    GIRL(2,"女");

    private final int value;

    private final String desc;

    Gender(int number, String desc) {
        this.value = number;
        this.desc = desc;
    }

    public int value(){
        return this.value;
    }

    public static Gender from(int value) {
        for (Gender gender : Gender.values()) {
            if (Objects.equal(gender.value, value)) {
                return gender;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.desc;
    }
}
