package com.fenniao.a3kezhi.SQL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.fenniao.a3kezhi.Been.MessageBean;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/12 0012.
 */

public class DBHelper extends OrmLiteSqliteOpenHelper {
    private static DBHelper dbHelper;
    private Map<String,Dao> daos=new HashMap<>();
    private DBHelper(Context context) {
        super(context, "_3kezhi.db", null, 1);
    }

    public static DBHelper getInstance(Context context){
        if(dbHelper==null){
            dbHelper=new DBHelper(context);
        }
        return dbHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource,MessageBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource,MessageBean.class,true);
            onCreate(database,connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Dao getDao(Class clazz) throws SQLException{
        Dao dao = null;
        String className = clazz.getSimpleName();
        if (!daos.containsKey(className)) {
            dao = super.getDao(clazz);
            daos.put(className, dao);
        }else{
            dao=daos.get(className);
        }
        return dao;
    }

    @Override
    public void close() {
        super.close();
        for (String key : daos.keySet()) {
            Dao dao = daos.get(key);
            dao = null;
        }
    }
}
