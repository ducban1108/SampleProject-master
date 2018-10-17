package ducban.deptrai.comot.khonghai.sampleproject.sqlitedao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ducban.deptrai.comot.khonghai.sampleproject.Constant;
import ducban.deptrai.comot.khonghai.sampleproject.database.DatabaseHelper;
import ducban.deptrai.comot.khonghai.sampleproject.model.TypeBook;
import ducban.deptrai.comot.khonghai.sampleproject.model.User;

public class TypeBookDAO implements Constant {
    private DatabaseHelper databaseHelper;

    public TypeBookDAO(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public long insertTypeBook(TypeBook typeBook) {

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(TB_COLUMN_NAME, typeBook.name);
        contentValues.put(TB_COLUMN_ID, typeBook.id);
        contentValues.put(TB_COLUMN_DES, typeBook.des);
        contentValues.put(TB_COLUMN_POS, typeBook.pos);

        // tao cau lenh insert

        long result = sqLiteDatabase.insert(TABLE_TYPE_BOOK, null, contentValues);

        Log.e("insertTypeBook", "insertTypeBook : " + result);

        sqLiteDatabase.close();

        return result;
    }

    public long updateTypeBook(TypeBook typeBook) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(TB_COLUMN_NAME, typeBook.name);
        contentValues.put(TB_COLUMN_DES, typeBook.des);
        contentValues.put(TB_COLUMN_POS, typeBook.pos);

        long result = sqLiteDatabase.update(TABLE_TYPE_BOOK,
                contentValues, TB_COLUMN_ID + "=?",
                new String[]{typeBook.id});

        sqLiteDatabase.close();

        return result;
    }

    public long deleteTypeBook(String typeID) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        long result = sqLiteDatabase.delete(TABLE_TYPE_BOOK,
                TB_COLUMN_ID + "=?", new String[]{typeID});

        sqLiteDatabase.close();

        return result;
    }

    public List<TypeBook> getAllTypeBooks() {
        List<TypeBook> typeBooks = new ArrayList<>();

        String SELECT_ALL_TYPE_BOOK = "SELECT * FROM " + TABLE_TYPE_BOOK;

        Log.e("getAllUsers", SELECT_ALL_TYPE_BOOK);

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL_TYPE_BOOK, null);


        Log.e("SIZE", cursor.getCount() + "");


        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {

                String id = cursor.getString(cursor.getColumnIndex(TB_COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndex(TB_COLUMN_NAME));
                String des = cursor.getString(cursor.getColumnIndex(TB_COLUMN_DES));
                String pos = cursor.getString(cursor.getColumnIndex(TB_COLUMN_POS));

                TypeBook typeBook = new TypeBook();
                typeBook.id = id;
                typeBook.name = name;
                typeBook.des = des;
                typeBook.pos = pos;

                // them user vao List< User >
                typeBooks.add(typeBook);


            } while (cursor.moveToNext());

        }
        cursor.close();
        sqLiteDatabase.close();

        return typeBooks;
    }

    public TypeBook getTypeBookByID(String typeID) {
        TypeBook typeBook = null;

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.query(TABLE_USER,
                new String[]{COLUMN_USERNAME, COLUMN_PASSWORD, COLUMN_PHONE, COLUMN_NAME},
                COLUMN_USERNAME + "=?",
                new String[]{typeID}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {

            String id = cursor.getString(cursor.getColumnIndex(TB_COLUMN_ID));
            String pos = cursor.getString(cursor.getColumnIndex(TB_COLUMN_POS));
            String des = cursor.getString(cursor.getColumnIndex(TB_COLUMN_DES));
            String name = cursor.getString(cursor.getColumnIndex(TB_COLUMN_NAME));

            typeBook = new TypeBook();
            typeBook.id = id;
            typeBook.name = name;
            typeBook.des = des;
            typeBook.pos = pos;

        }

        return typeBook;

    }
}
