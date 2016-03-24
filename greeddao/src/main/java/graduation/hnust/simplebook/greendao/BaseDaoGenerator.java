package graduation.hnust.simplebook.greendao;

import java.lang.reflect.Field;
import java.util.Date;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;
import graduation.hnust.simplebook.model.AccountRemind;
import graduation.hnust.simplebook.model.Book;
import graduation.hnust.simplebook.model.ConsumeType;
import graduation.hnust.simplebook.model.FeedBack;
import graduation.hnust.simplebook.model.Item;
import graduation.hnust.simplebook.model.User;
import graduation.hnust.simplebook.model.third.QQInfoModel;
import graduation.hnust.simplebook.model.third.QQTokenModel;

/**
 * @Author : panxin
 * @Date : 12:36 AM 3/20/16
 * @Email : panxin109@gmail.com
 */
public class BaseDaoGenerator {

    public static void main(String[] args) throws Exception{
        // package path, model, dao, test
        String modelPackage = "graduation.hnust.simplebook.model";
        String daoPackage = "graduation.hnust.simplebook.greendao";
        String testPackage = "graduation.hnust.simplebook.test";

        // create schema and set package path
        Schema schema = new Schema(1, modelPackage);
        schema.setDefaultJavaPackageDao(daoPackage);
        schema.setDefaultJavaPackageTest(testPackage);

        // set table model
        addTableModel(User.class, schema);
        addTableModel(QQInfoModel.class, schema);
        addTableModel(QQTokenModel.class, schema);
        addTableModel(AccountRemind.class, schema);
        addTableModel(Book.class, schema);
        addTableModel(ConsumeType.class, schema);
        addTableModel(FeedBack.class, schema);
        addTableModel(Item.class, schema);

        // output path
        String outDir = "app/src/main/java-gen";
        String outDirEntity = "app/src/main/java-gen";
        String outDirTest = "app/src/main/java-gen";

        // generate green dao
        DaoGenerator generator = new DaoGenerator();
        generator.generateAll(schema, outDir, outDirEntity, outDirTest);
    }

    private static void addTableModel(Class cls, Schema schema){
        // 获得类名
        String className = cls.getSimpleName();
        // 一个实体（类）就关联到数据库中的一张表，此处表名为「className」（既类名）
        Entity entity = schema.addEntity(className);
        // 获得类属性字段
        Field[] fields = cls.getDeclaredFields();
        // 属性名
        String fieldName = null;
        // 属性类型
        Class type = null;
        /**
         * 根据属性类型创建表字段
         * 与在 Java 中使用驼峰命名法不同，默认数据库中的命名是使用大写和下划线来分割单词的。
         * For example, a property called “creationDate” will become a database column “CREATION_DATE”.
         */
        for (Field field : fields) {
            fieldName = field.getName();
            if ("serialVersionUID".equals(fieldName)) {
                continue;
            }
            type = field.getType();
            // 设置主键 ID, 自增长.
            if ("id".equals(fieldName)) {
                entity.addIdProperty().autoincrement();
                continue;
            }
            // 其他属性
            // wtf  low !!!  too bad to read ~
            // should use switch or more reflect
            if (type.equals(String.class)) {
                entity.addStringProperty(fieldName);
            }else if (type.equals(Integer.class)) {
                entity.addIntProperty(fieldName);
            }else if (type.equals(Double.class)) {
                entity.addDoubleProperty(fieldName);
            }else if (type.equals(Float.class)) {
                entity.addFloatProperty(fieldName);
            }else if (type.equals(Long.class)) {
                entity.addLongProperty(fieldName);
            }else if (type.equals(Byte.class)) {
                entity.addByteProperty(fieldName);
            }else if (type.equals(Short.class)) {
                entity.addShortProperty(fieldName);
            }else if (type.equals(Boolean.class)) {
                entity.addBooleanProperty(fieldName);
            }else if (type.equals(Character.class)) {
                entity.addStringProperty(fieldName);
            }else if (type.equals(Date.class)) {
                entity.addDateProperty(fieldName);
            }
        }
    }

}
