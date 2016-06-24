package graduation.hnust.simplebook.base;

import java.util.List;

import graduation.hnust.simplebook.R;
import graduation.hnust.simplebook.model.ConsumeType;

/**
 * @Author : panxin109@gmail.com
 * @Date : 2:07 PM 5/10/16
 */
public class AppConsumeType {


    /**
     * 支出
     */
    private Integer[] expenseTypes = {
            R.drawable.type_1_default,
            R.drawable.type_1_press, R.drawable.type_2_press,
            R.drawable.type_3_press, R.drawable.type_4_press,
            R.drawable.type_5_press, R.drawable.type_8_press,
            R.drawable.type_9_press, R.drawable.type_10_press,
            R.drawable.type_11_press, R.drawable.type_12_press,
            R.drawable.type_13_press, R.drawable.type_14_press,
            R.drawable.type_15_press, R.drawable.type_13,
            R.drawable.type_6_press, R.drawable.type_7_press
    };

    /**
     * 收入
     */
    private Integer[] incomeTypes = {
            R.drawable.type_1_default,
            R.drawable.type_income_1,
            R.drawable.type_income_2
    };

    public List<ConsumeType> initExpenseType(List<ConsumeType> types) {
        ConsumeType type = new ConsumeType();
        // 支出
        type.setId(0L);
        type.setType(1);
        type.setImageId(expenseTypes[0]);
        type.setName("一般");
        types.add(type);

        type = new ConsumeType();
        type.setId(2L);
        type.setType(1);
        type.setImageId(expenseTypes[1]);
        type.setName("中餐");
        types.add(type);

        type = new ConsumeType();
        type.setId(3L);
        type.setType(1);
        type.setImageId(expenseTypes[2]);
        type.setName("公交");
        types.add(type);

        type = new ConsumeType();
        type.setId(4L);
        type.setType(1);
        type.setImageId(expenseTypes[3]);
        type.setName("零食");
        types.add(type);

        type = new ConsumeType();
        type.setId(5L);
        type.setType(1);
        type.setImageId(expenseTypes[4]);
        type.setName("衣服");
        types.add(type);

        type = new ConsumeType();
        type.setId(6L);
        type.setType(1);
        type.setImageId(expenseTypes[5]);
        type.setName("生活用品");
        types.add(type);

        type = new ConsumeType();
        type.setId(7L);
        type.setType(1);
        type.setImageId(expenseTypes[6]);
        type.setName("购物");
        types.add(type);

        type = new ConsumeType();
        type.setId(8L);
        type.setType(1);
        type.setImageId(expenseTypes[7]);
        type.setName("出租车");
        types.add(type);

        type = new ConsumeType();
        type.setId(9L);
        type.setType(1);
        type.setImageId(expenseTypes[8]);
        type.setName("快餐");
        types.add(type);

        type = new ConsumeType();
        type.setId(10L);
        type.setType(1);
        type.setImageId(expenseTypes[9]);
        type.setName("飞机");
        types.add(type);

        type = new ConsumeType();
        type.setId(11L);
        type.setType(1);
        type.setImageId(expenseTypes[10]);
        type.setName("化妆品");
        types.add(type);

        type = new ConsumeType();
        type.setId(12L);
        type.setType(1);
        type.setImageId(expenseTypes[11]);
        type.setName("电脑");
        types.add(type);

        type = new ConsumeType();
        type.setId(13L);
        type.setType(1);
        type.setImageId(expenseTypes[12]);
        type.setName("手机");
        types.add(type);

        type = new ConsumeType();
        type.setId(14L);
        type.setType(1);
        type.setImageId(expenseTypes[13]);
        type.setName("药物");
        types.add(type);

        type = new ConsumeType();
        type.setId(15L);
        type.setType(1);
        type.setImageId(expenseTypes[14]);
        type.setName("电影");
        types.add(type);

        type = new ConsumeType();
        type.setId(16L);
        type.setType(1);
        type.setImageId(expenseTypes[15]);
        type.setName("西餐");
        types.add(type);

        type = new ConsumeType();
        type.setId(17L);
        type.setType(1);
        type.setImageId(expenseTypes[16]);
        type.setName("游戏");
        types.add(type);

        return types;
    }

    public List<ConsumeType> initIncomeType(List<ConsumeType> types) {
        ConsumeType type = new ConsumeType();
        // 支出
        type.setType(1);
        type.setId(0L);
        type.setImageId(incomeTypes[0]);
        type.setName("一般");
        types.add(type);

        type = new ConsumeType();
        type.setId(1L);
        type.setType(1);
        type.setImageId(incomeTypes[1]);
        type.setName("工资");
        types.add(type);

        type = new ConsumeType();
        type.setId(2L);
        type.setType(1);
        type.setImageId(incomeTypes[2]);
        type.setName("红包");
        types.add(type);

        return types;
    }
}
