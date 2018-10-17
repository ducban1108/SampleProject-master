package ducban.deptrai.comot.khonghai.sampleproject;

import android.util.StringBuilderPrinter;

public interface Constant {
//    Bảng User //
    //tên bảng
    String TABLE_USER = "User";


    //Tên cột
    String COLUMN_USERNAME = "userName";
    String COLUMN_PASSWORD = "Password";
    String COLUMN_PHONE = "Phone";
    String COLUMN_NAME = "hoTen";

    //câu lệnh tạo bảng
    String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USER + "(" +

            COLUMN_USERNAME + " NVARCHAR(50) PRIMARY KEY," +

            COLUMN_PASSWORD + " NVARCHAR(30)," +

            COLUMN_PHONE + " NVARCHAR," +

            COLUMN_NAME + " NVARCHAR" +

            ")";

//    Bang book  //
String TABLE_BOOK = "Books";


    //Tên cột
    String COLUMN_BOOKNAME = "Bookname";
    String COLUMN_GIA = "Password";
    String COLUMN_TACGIA = "Tacgia";
    String COLUMN_NXB = "NXB";
    String COLUMN_SOLUONG = "SL";
    String COLUMN_THELOAI = "TL";



    //câu lệnh tạo bảng
    String CREATE_TABLE_BOOK = "CREATE TABLE " + TABLE_BOOK + "(" +

            COLUMN_BOOKNAME + " NVARCHAR(50) PRIMARY KEY," +

            COLUMN_GIA + " NVARCHAR(30)," +

            COLUMN_TACGIA + " NVARCHAR," +

            COLUMN_NXB + " NVARCHAR," +

            COLUMN_THELOAI + " NVARCHAR," +

            COLUMN_SOLUONG + " NVARCHAR" +

            ")";


//    Bang book type  //
    //CREATE TABLE TYPE BOOK (MaTheLoai CHAR(5) PRIMARY KEY NOT NULL,
    // typeName NVARCHAR(50) NOT NULL,Description NVARCHAR(255),Position INT)

    // Ten bang
    String TABLE_TYPE_BOOK = "typeBook";

    // Ten cot

    String TB_COLUMN_ID = "MaTheLoai";
    String TB_COLUMN_NAME = "typeName";
    String TB_COLUMN_DES = "Description";
    String TB_COLUMN_POS = "Position";


    String CREATE_TABLE_TYPE_BOOK = "CREATE TABLE " + TABLE_TYPE_BOOK + "(" +
            "" + TB_COLUMN_ID + " CHAR(5) PRIMARY KEY NOT NULL," +
            "" + TB_COLUMN_NAME + " NVARCHAR(50) NOT NULL," +
            "" + TB_COLUMN_DES + " NVARCHAR(255)," +
            "" + TB_COLUMN_POS + " INT" +
            ")";



    // Bill table
    //CREATE TABLE HoaDon(MaHoaDon NCHAR(7) PRIMARY KEY NOT NULL, NgayMua LONG NOT NULL)
    String TABLE_BILL = "HoaDon";
    //khai bao cac vot

    String B_ID = "MaHoaDon";
    String B_DATE = "NgayMua";

    String CREATE_TABLE_BILL = "CREATE TABLE "+ TABLE_BILL + "(" +
            "" + B_ID + " NCHAR(7) PRIMARY KEY NOT NULL,"+
            "" + B_DATE + " LONG NOT NULL"+
            ")";


    //Bill ct

    String TABLE_HOADONCHITIET = "HoaDonChiTiet";
    String B_COLUMN_ID="MaHDCT";
    String B_COLUMN_IDBILL="MaHoaDon";
    String B_COLUMN_IDBOOK="MaSach";
    String B_COLUMN_POS="SoLuong";

    String CREATE_TABLE_HOADONCHITIET="CREATE TABLE " + TABLE_HOADONCHITIET + "(" +

            B_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +

            B_COLUMN_IDBILL + " NCHAR(7) NOT NULL," +

            B_COLUMN_IDBOOK + " NCHAR(5) NOT NULL," +

            B_COLUMN_POS + " INTEGER NOT NULL" +

            ")";


}
