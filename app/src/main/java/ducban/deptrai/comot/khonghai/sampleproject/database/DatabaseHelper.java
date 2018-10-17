package ducban.deptrai.comot.khonghai.sampleproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ducban.deptrai.comot.khonghai.sampleproject.Constant;
import ducban.deptrai.comot.khonghai.sampleproject.model.User;

public class DatabaseHelper extends SQLiteOpenHelper implements Constant{




    public DatabaseHelper(Context context) {
        super(context, "BookManager", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_USER);
        sqLiteDatabase.execSQL(CREATE_TABLE_TYPE_BOOK);
        sqLiteDatabase.execSQL(CREATE_TABLE_BOOK);
        sqLiteDatabase.execSQL(CREATE_TABLE_HOADONCHITIET);
        sqLiteDatabase.execSQL(CREATE_TABLE_BILL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+ TABLE_USER);
        sqLiteDatabase.execSQL("drop table if exists "+ TABLE_TYPE_BOOK);
        sqLiteDatabase.execSQL("drop table if exists "+ TABLE_BOOK);
        sqLiteDatabase.execSQL("drop table if exists "+ TABLE_BILL);
        sqLiteDatabase.execSQL("drop table if exists "+ TABLE_HOADONCHITIET);

        onCreate(sqLiteDatabase);
    }

    public Cursor getData(String sql){
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        return sqLiteDatabase.rawQuery(sql,null);
    }
}
