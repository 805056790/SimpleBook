package graduation.hnust.simplebook.enums;

import com.google.common.base.Objects;

/**
 * 用户枚举
 *
 * @Author : panxin
 * @Date : 4:16 PM 3/20/16
 * @Email : panxin109@gmail.com
 */
public class Users {

    /**
     * 性别枚举
     */
    public static enum Gender{
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

    /**
     * 状态枚举
     * 1:正常, 0:未激活, -1:删除, -2:冻结
     */
    public static enum Status{
        NORMAL(1, "正常"),
        INIT(0, "未激活"),
        DELETE(-1, "删除"),
        FROZEN(-2, "冻结");

        private final int value;

        private final String desc;

        Status(int number, String desc) {
            this.value = number;
            this.desc = desc;
        }

        public int value(){
            return this.value;
        }

        public static Status from(int value) {
            for (Status status : Status.values()) {
                if (Objects.equal(status.value, value)) {
                    return status;
                }
            }
            return null;
        }

        @Override
        public String toString() {
            return this.desc;
        }
    }

    /**
     * 用户类型枚举
     */
    public static enum Type{
        ADMIN(1, "管理员"),
        NORMAL(2, "普通用户");

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
