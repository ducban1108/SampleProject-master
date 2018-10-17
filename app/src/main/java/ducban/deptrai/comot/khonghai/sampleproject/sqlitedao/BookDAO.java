package ducban.deptrai.comot.khonghai.sampleproject.sqlitedao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ducban.deptrai.comot.khonghai.sampleproject.Constant;
import ducban.deptrai.comot.khonghai.sampleproject.database.DatabaseHelper;
import ducban.deptrai.comot.khonghai.sampleproject.model.Book;
import ducban.deptrai.comot.khonghai.sampleproject.model.TypeBook;

public class BookDAO implements Constant {
    private DatabaseHelper databaseHelper;

    public BookDAO(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }


    public void insertBook(Book book) {


        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        Book book1 = getBookByName(book.getTensach());
        if (book1 == null){
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_BOOKNAME, book.getTensach());
            contentValues.put(COLUMN_SOLUONG, book.getSoluong());
            contentValues.put(COLUMN_GIA, book.getGia());
            contentValues.put(COLUMN_TACGIA, book.getTacgia());
            contentValues.put(COLUMN_NXB,book.getNxb());
            contentValues.put(COLUMN_THELOAI,book.getLoaisach());
            // tao cau lenh insert

            long id = sqLiteDatabase.insert(TABLE_BOOK, null, contentValues);

            Log.e("insertBook", "insertBook : " + id);

            sqLiteDatabase.close();

        }else {

        }





    }

    public Book getBookByName(String bookname) {

        Book book = null;

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.query(TABLE_BOOK,
                new String[]{COLUMN_BOOKNAME, COLUMN_SOLUONG, COLUMN_GIA, COLUMN_TACGIA, COLUMN_NXB, COLUMN_THELOAI},
                COLUMN_BOOKNAME + "=?",
                new String[]{bookname}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {

            String name = cursor.getString(cursor.getColumnIndex(COLUMN_BOOKNAME));

            String sl = cursor.getString(cursor.getColumnIndex(COLUMN_SOLUONG));
            String gia = cursor.getString(cursor.getColumnIndex(COLUMN_GIA));
            String tacgia = cursor.getString(cursor.getColumnIndex(COLUMN_TACGIA));
            String nxb = cursor.getString(cursor.getColumnIndex(COLUMN_NXB));
            String tl = cursor.getString(cursor.getColumnIndex(COLUMN_THELOAI));

            book = new Book();
            book.setTensach(name);
            book.setSoluong(sl);
            book.setGia(gia);
            book.setTacgia(tacgia);
            book.setNxb(nxb);
            book.setLoaisach(tl);


        }

        return book;
    }

    public List<Book> getAllBooks(){


        List<Book> userList = new ArrayList<>();

        String SELECT_ALL_USER = "SELECT * FROM " + TABLE_BOOK;

        Log.e("getAllUsers", SELECT_ALL_USER);

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL_USER, null);


        if (cursor.moveToFirst()) {

            do {

                String name = cursor.getString(cursor.getColumnIndex(COLUMN_BOOKNAME));

                String sl = cursor.getString(cursor.getColumnIndex(COLUMN_SOLUONG));
                String gia = cursor.getString(cursor.getColumnIndex(COLUMN_GIA));
                String tacgia = cursor.getString(cursor.getColumnIndex(COLUMN_TACGIA));
                String nxb = cursor.getString(cursor.getColumnIndex(COLUMN_NXB));

                Book book = new Book();
                book.setTensach(name);
                book.setSoluong(sl);
                book.setGia(gia);
                book.setTacgia(tacgia);
                book.setNxb(nxb);


                userList.add(book);


            } while (cursor.moveToNext());

        }


        sqLiteDatabase.close();

        return userList;
    }


    public int deleteBook(String book) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        return sqLiteDatabase.delete(TABLE_BOOK,
                COLUMN_USERNAME + "=?", new String[]{book});

    }

    public int updateBook(Book book) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_BOOKNAME, book.getTensach());
        contentValues.put(COLUMN_SOLUONG, book.getSoluong());
        contentValues.put(COLUMN_GIA, book.getGia());
        contentValues.put(COLUMN_THELOAI, book.getLoaisach());
        contentValues.put(COLUMN_TACGIA, book.getTacgia());
        contentValues.put(COLUMN_NXB,book.getNxb());

        return sqLiteDatabase.update(TABLE_BOOK,
                contentValues, COLUMN_BOOKNAME + "=?",
                new String[]{book.getTensach()});

    }
}
