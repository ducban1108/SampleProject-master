package ducban.deptrai.comot.khonghai.sampleproject.sqlitedao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ducban.deptrai.comot.khonghai.sampleproject.Constant;
import ducban.deptrai.comot.khonghai.sampleproject.database.DatabaseHelper;
import ducban.deptrai.comot.khonghai.sampleproject.model.User;

public class UserDAO implements Constant {
    private DatabaseHelper databaseHelper;

    public UserDAO(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public void insertUser(User user){
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USERNAME,user.getUsername());
        contentValues.put(COLUMN_PASSWORD,user.getPassword());
        contentValues.put(COLUMN_PHONE,user.getPhonenumber());
        contentValues.put(COLUMN_NAME,user.getName());

        //tạo câu lệnh insert

        long id =  sqLiteDatabase.insert(TABLE_USER,null, contentValues);

        Log.e("insertUser","insertUser: "+ id);
        sqLiteDatabase.close();
    }

    public List<User> getListAllUser(){
        List<User> users=new ArrayList<>();
        SQLiteDatabase sqLiteDatabase=databaseHelper.getWritableDatabase();
        String sql="SELECT * FROM "+ TABLE_USER;
        Cursor cursor=sqLiteDatabase.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            do {
                User user=new User();
                user.setUsername(cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME)));
                user.setPhonenumber(cursor.getString(cursor.getColumnIndex(COLUMN_PHONE)));
                user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)));

                users.add(user);

            }while (cursor.moveToNext());
        }
        return users;
    }

    public User getUserByUsername(String username){

        User user = null;

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.query(TABLE_USER,
                new String[]{COLUMN_USERNAME,COLUMN_PASSWORD,COLUMN_NAME,COLUMN_PHONE},
                COLUMN_USERNAME +"=?",
                new String[]{username},null,null,null);

        if (cursor !=null && cursor.moveToFirst()){

            String user_name = cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME));
            String pass_word = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD));
            String phone = cursor.getString(cursor.getColumnIndex(COLUMN_PHONE));
            String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));


            user = new User();

            user.setUsername(user_name);
            user.setPassword(pass_word);
            user.setPhonenumber(phone);
            user.setName(name);


        }

        return user;
    }

    public List<User> getAllUsers(){
        List<User> userList = new ArrayList<>();

        String SELECT_ALL_USER = "SELECT * FROM " + TABLE_USER;

        Log.e("getAllUsers",SELECT_ALL_USER);

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL_USER,null);

        cursor.moveToFirst();

        do {

            String user_name = cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME));
            String pass_word = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD));
            String phone = cursor.getString(cursor.getColumnIndex(COLUMN_PHONE));
            String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));


            User user = new User();
            user.setUsername(user_name);
            user.setPassword(pass_word);
            user.setPhonenumber(phone);
            user.setName(name);

            //them user vào List<User>
            userList.add(user);

        } while (cursor.moveToNext());

        cursor.close();
        sqLiteDatabase.close();


        return userList;
    }

    public int deleteUser(String username){
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        return sqLiteDatabase.delete(TABLE_USER,COLUMN_USERNAME + "=?", new String[]{username});
    }

    public int updateUser(User user){
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USERNAME,user.getUsername());
        contentValues.put(COLUMN_PASSWORD,user.getPassword());
        contentValues.put(COLUMN_PHONE,user.getPhonenumber());
        contentValues.put(COLUMN_NAME,user.getName());
        return sqLiteDatabase.update(TABLE_USER,contentValues,COLUMN_USERNAME + "=?",new String[]{user.getUsername()});
    }
}
