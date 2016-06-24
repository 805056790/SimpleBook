package graduation.hnust.simplebook.enums;

import com.google.common.base.Objects;

/**
 * @Author : panxin109@gmail.com
 * @Date : 1:15 PM 5/2/16
 */
public class ConsumeTypes {

    public static enum Type {
        INCOME(1,"收入"),
        EXPENSE(2,"支出");

        private final int value;

        private final String desc;

        Type(int number, String desc) {
            this.value = number;
            this.desc = desc;
        }

        public int value(){
            return this.value;
        }

        public static Type from(int value) {
            for (Type type : Type.values()) {
                if (Objects.equal(type.value, value)) {
                    return type;
                }
            }
            return null;
        }

        @Override
        public String toString() {
            return this.desc;
        }
    }

}
