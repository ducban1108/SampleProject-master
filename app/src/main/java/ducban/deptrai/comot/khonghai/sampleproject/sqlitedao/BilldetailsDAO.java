package ducban.deptrai.comot.khonghai.sampleproject.sqlitedao;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import ducban.deptrai.comot.khonghai.sampleproject.Constant;
import ducban.deptrai.comot.khonghai.sampleproject.database.DatabaseHelper;
import ducban.deptrai.comot.khonghai.sampleproject.model.Billdetails;


public class BilldetailsDAO implements Constant {

    DatabaseHelper database;

    public BilldetailsDAO(DatabaseHelper database) {
        this.database = database;
    }

    public long insert(Billdetails billdetails){
        SQLiteDatabase sqLiteDatabase=database.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(B_COLUMN_IDBILL,billdetails.getIdbill());
        values.put(B_COLUMN_IDBOOK,billdetails.getIdbook());
        values.put(B_COLUMN_POS,billdetails.getSoluong());

        long a=sqLiteDatabase.insert(TABLE_HOADONCHITIET,null,values);
        sqLiteDatabase.close();
        Log.e("Tinh",a+"");
        return a;
    }


    public long delete(String id){
        SQLiteDatabase sqLiteDatabase=database.getWritableDatabase();
        long a= sqLiteDatabase.delete(TABLE_HOADONCHITIET,B_COLUMN_ID + "=?",new String[]{String.valueOf(id)});
        Log.e("Tinhx",a+"");
        return a;
    }

    public long delete1(String idBill){
        SQLiteDatabase sqLiteDatabase=database.getWritableDatabase();
        long a= sqLiteDatabase.delete(TABLE_HOADONCHITIET,B_COLUMN_IDBILL + "=?",new String[]{idBill});
        Log.e("Tinhx",a+"");
        return a;
    }
}
