package graduation.hnust.simplebook.base;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import graduation.hnust.simplebook.R;
import graduation.hnust.simplebook.constants.AppConstants;
import graduation.hnust.simplebook.greendao.ConsumeTypeDao;
import graduation.hnust.simplebook.greendao.DaoMaster;
import graduation.hnust.simplebook.greendao.DaoSession;
import graduation.hnust.simplebook.model.ConsumeType;

/**
 * This way use can use the database/DAO objects across Activities.
 * Leaving the database open for the life time of the application’s process makes things simple and efficient.
 *
 * @Author : panxin
 * @Date : 11:00 AM 3/20/16
 * @Email : panxin109@gmail.com
 */
public class BaseApplication extends Application {

    public DaoSession daoSession;

    public DaoMaster daoMaster;

    public DaoMaster.DevOpenHelper openHelper;

    public SQLiteDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        setDatabase();
    }

    private void setDatabase() {
        /**
         * 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
         * 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
         * 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
         * 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
         */
        openHelper = new DaoMaster.DevOpenHelper(this, AppConstants.DB_NAME, null);
        database = openHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        daoMaster = new DaoMaster(database);
        daoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return this.daoSession;
    }

    public SQLiteDatabase getDatabase() {
        return this.database;
    }


}
