package kz.studentlist.app;

import android.app.Activity;
import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by aibek on 16.10.14.
 */
public class StudentMapper {

    RuntimeExceptionDao<Student,Integer> runtimeExceptionDao;
    DatabaseHelper databaseHelper;

    public StudentMapper(Context context){
        databaseHelper = (DatabaseHelper) OpenHelperManager.getHelper(context, DatabaseHelper.class);
        runtimeExceptionDao = databaseHelper.getRuntimeExceptionDao();
    }
    public void insert(Student student){
        runtimeExceptionDao.create(student);

    }

    public void delete(Student student){
        runtimeExceptionDao.delete(student);

    }

    public void update(Student student) {
        runtimeExceptionDao.update(student);
     }

    public List<Student> select(){
        List<Student> students = runtimeExceptionDao.queryForAll();
        return students;

    }
}
