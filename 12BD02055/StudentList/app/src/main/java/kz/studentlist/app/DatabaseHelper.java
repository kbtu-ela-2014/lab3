package kz.studentlist.app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.io.File;
import java.sql.SQLException;

/**
 * Created by aibek on 09.10.14.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {


    private static final String DATBASE_NAME = "students.db";
    private static final int DATBASE_VERSION = 1;

    private Dao<Student, Integer> dao = null;
    private RuntimeExceptionDao<Student, Integer> runtimeExceptionDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATBASE_NAME, null, DATBASE_VERSION, R.raw.ormlite_config);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {

        try {
            TableUtils.createTable(connectionSource, Student.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

        try {
            TableUtils.dropTable(connectionSource,Student.class,true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Dao<Student, Integer> getDao() throws SQLException {
        if(dao==null)
            dao = getDao(Student.class);
        return dao;
    }

    public RuntimeExceptionDao<Student, Integer> getRuntimeExceptionDao(){
        if (runtimeExceptionDao==null)
            runtimeExceptionDao = getRuntimeExceptionDao(Student.class);
        return runtimeExceptionDao;
    }
}
