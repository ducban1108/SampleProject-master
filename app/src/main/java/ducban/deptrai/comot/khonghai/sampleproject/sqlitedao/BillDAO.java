package ducban.deptrai.comot.khonghai.sampleproject.sqlitedao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import ducban.deptrai.comot.khonghai.sampleproject.Constant;
import ducban.deptrai.comot.khonghai.sampleproject.database.DatabaseHelper;
import ducban.deptrai.comot.khonghai.sampleproject.model.Bill;

import java.util.ArrayList;
import java.util.List;

import ducban.deptrai.comot.khonghai.sampleproject.Constant;
import ducban.deptrai.comot.khonghai.sampleproject.database.DatabaseHelper;
import ducban.deptrai.comot.khonghai.sampleproject.model.Bill;

import static ducban.deptrai.comot.khonghai.sampleproject.Constant.B_ID;


public class BillDAO implements Constant {

    private DatabaseHelper databaseHelper;

    public BillDAO(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }


    public long insertBill(Bill bill) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(B_ID, bill.id);
        contentValues.put(B_DATE, bill.date);

        long result = sqLiteDatabase.insert(TABLE_BILL, null, contentValues);

        sqLiteDatabase.close();

        return result;
    }

    public long updateBill(Bill bill) {

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(B_DATE, bill.date);

        long result = sqLiteDatabase.update(TABLE_BILL, contentValues,
                B_ID + "=?", new String[]{bill.id});

        sqLiteDatabase.close();

        return result;

    }

    public long delBill(String id) {

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        long result = sqLiteDatabase.delete(TABLE_BILL,
                B_ID + "=?", new String[]{id});
        Log.e("ii",result+"");
        sqLiteDatabase.close();

        return result;
    }

    public List<Bill> getAllBills() {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        List<Bill> bills = new ArrayList<>();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_BILL,
                null);

        // kiem tra cursor co du lieu hay ko
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                // thiet lap con tro ve vi tri dau tien
                cursor.moveToFirst();
                do {
                    String id = cursor.getString(0);
                    long date = cursor.getLong(1);

                    Bill bill = new Bill(id, date);

                    // them Bill vua lay duoc vao arraylist Bills
                    bills.add(bill);

                } while (cursor.moveToNext());
            }
            sqLiteDatabase.close();
        }

        return bills;

    }

    public Bill getBillByID(String id) {

        Bill bill = null;
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.query(TABLE_BILL, new String[]{B_ID, B_DATE},
                B_ID + "=?",
                new String[]{id}, null, null, null);

        // kiem tra cursor co du lieu hay ko
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                // thiet lap con tro ve vi tri dau tien
                cursor.moveToFirst();
                String id_ = cursor.getString(0);
                long date = cursor.getLong(1);

                bill = new Bill(id, date);

                return bill;

            }
            sqLiteDatabase.close();
        }

        return null;
    }

}
