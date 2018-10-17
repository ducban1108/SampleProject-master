package ducban.deptrai.comot.khonghai.sampleproject;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

import ducban.deptrai.comot.khonghai.sampleproject.adapter.BilldetailAdapter;
import ducban.deptrai.comot.khonghai.sampleproject.database.DatabaseHelper;
import ducban.deptrai.comot.khonghai.sampleproject.model.Billdetails;
import ducban.deptrai.comot.khonghai.sampleproject.model.Book;
import ducban.deptrai.comot.khonghai.sampleproject.sqlitedao.BilldetailsDAO;

import static ducban.deptrai.comot.khonghai.sampleproject.Constant.B_COLUMN_ID;
import static ducban.deptrai.comot.khonghai.sampleproject.Constant.B_COLUMN_IDBILL;
import static ducban.deptrai.comot.khonghai.sampleproject.Constant.B_COLUMN_IDBOOK;
import static ducban.deptrai.comot.khonghai.sampleproject.Constant.B_COLUMN_POS;
import static ducban.deptrai.comot.khonghai.sampleproject.Constant.COLUMN_BOOKNAME;
import static ducban.deptrai.comot.khonghai.sampleproject.Constant.COLUMN_GIA;
import static ducban.deptrai.comot.khonghai.sampleproject.Constant.COLUMN_SOLUONG;


public class BillCTActivity extends AppCompatActivity {
    private EditText edtIDBill;
    private Spinner spMaSach;
    private EditText txtSoLuong;
    private Button btnAdd;
    private Button btnThanhToan;
    private TextView txtThanhTien;
    private RecyclerView recyclerviewBilldetail;
    private LinearLayoutManager linearLayoutManager;
    private DatabaseHelper helper;
    private BilldetailAdapter billdetailAdapter;
    private ArrayList<Billdetails> billdetailsArrayList,getBilldetailsArrayList1;
    private ArrayList<Book> bookArrayList;
    private BilldetailsDAO nBilldetailsDAO;
    private int i;
    private int vitri;
    private String mahoadon2;
    private  String mhoadon1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_ct);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        anhXa();
        getdata();


        //getIDBook();
        getIDBook();
        //getDataBilldetails();
        //Toast.makeText(this, ""+i, Toast.LENGTH_SHORT).show();
        addRecyclerview();


    }

    private void anhXa(){
        helper=new DatabaseHelper(this);
        nBilldetailsDAO=new BilldetailsDAO(helper);
        edtIDBill = (EditText) findViewById(R.id.edtIDBill);
        spMaSach = (Spinner) findViewById(R.id.spMaScah);
        txtSoLuong = (EditText) findViewById(R.id.txtSoLuong);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnThanhToan = (Button) findViewById(R.id.btnThanhToan);
        txtThanhTien = (TextView) findViewById(R.id.txtThanhTien);
        recyclerviewBilldetail = (RecyclerView) findViewById(R.id.recyclerviewBilldetail);
        billdetailsArrayList=new ArrayList<>();
        bookArrayList=new ArrayList<>();
        getBilldetailsArrayList1=new ArrayList<>();

        linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
    }


    public void getDataBilldetails1(){
        billdetailAdapter=new BilldetailAdapter(getBilldetailsArrayList1,bookArrayList,BillCTActivity.this,i);
        Cursor cursor=helper.getData("SELECT * FROM HoaDonChiTiet");
        billdetailsArrayList.clear();
        Billdetails billdetails;
        if (cursor!=null&& cursor.moveToFirst()){
            do {
                int maHDCT=cursor.getInt(cursor.getColumnIndex(B_COLUMN_ID));
                String mahoadon=cursor.getString(cursor.getColumnIndex(B_COLUMN_IDBILL));
                String masach=cursor.getString(cursor.getColumnIndex(B_COLUMN_IDBOOK));
                int soluongmua=cursor.getInt(cursor.getColumnIndex(B_COLUMN_POS));
                billdetails=new Billdetails();
                billdetails.setId(maHDCT);
                billdetails.setIdbill(mahoadon);
                billdetails.setIdbook(masach);
                billdetails.setSoluong(soluongmua);
                billdetailsArrayList.add(billdetails);

            }while (cursor.moveToNext());
            for (int i=0;i<billdetailsArrayList.size();i++){
                if (billdetailsArrayList.get(i).getIdbill().equals(mhoadon1)){
                    Billdetails billd=billdetailsArrayList.get(i);
                    Billdetails billdetails1=new Billdetails();
                    Log.e("Tinh",""+i);
                    billdetails1.setId(billd.getId());
                    billdetails1.setIdbill(billd.getIdbill());
                    billdetails1.setIdbook(billd.getIdbook());
                    billdetails1.setSoluong(billd.getSoluong());
                    getBilldetailsArrayList1.add(billdetails1);
                }
            }

            txtThanhTien.setVisibility(View.VISIBLE);
            billdetailAdapter.notifyDataSetChanged();

        }
    }



    public void getdata(){
        Intent intent=getIntent();
        mahoadon2=intent.getStringExtra("mahoadon");
        mhoadon1=intent.getStringExtra("Tinh");

        if (mahoadon2==null){
            vitri=intent.getIntExtra("vitri",0);
            edtIDBill.setEnabled(false);
            edtIDBill.setText(mhoadon1);
            getDataBilldetails1();
        }else {
            edtIDBill.setEnabled(false);
            edtIDBill.setText(mahoadon2);
            getDataBilldetails1();
        }


    }

    private void getIDBook(){
        final ArrayList<String> strings=new ArrayList<>();
        final ArrayList<Float> floats=new ArrayList<>();
        final ArrayList<Integer> ints=new ArrayList<>();
        Cursor cursor=helper.getData("SELECT * FROM Sach");
        if (cursor!=null && cursor.moveToNext()){
            do {
                String masach=cursor.getString(cursor.getColumnIndex(COLUMN_BOOKNAME));
                float giabia=cursor.getFloat(cursor.getColumnIndex(COLUMN_GIA));
                int soluong=cursor.getInt(cursor.getColumnIndex(COLUMN_SOLUONG));
                Book book=new Book();
                book.setMasach(masach);
                book.setGia(giabia+"");
                bookArrayList.add(book);
                strings.add(masach);
                floats.add(giabia);
                ints.add(soluong);
            }while (cursor.moveToNext());
        }
        final ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item,strings);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spMaSach.setAdapter(adapter);

        spMaSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (txtSoLuong.getText().toString().trim().equals("")){
                            txtSoLuong.setError(getString(R.string.error_PassWord));
                            return;
                        }
                        if (ints.get(position)<Integer.parseInt(txtSoLuong.getText().toString().trim())){
                            txtSoLuong.setError(getString(R.string.error_po));
                            return;
                        }

                        Billdetails billdetails1=new Billdetails();
                        billdetails1.setIdbook(strings.get(position));
                        billdetails1.setIdbill(edtIDBill.getText().toString());
                        billdetails1.setSoluong(Integer.parseInt(txtSoLuong.getText().toString().trim()));
                        nBilldetailsDAO.insert(billdetails1);
                        txtThanhTien.setVisibility(View.VISIBLE);
                        getBilldetailsArrayList1.add(0,billdetails1);
                        billdetailAdapter.notifyDataSetChanged();
                        addRecyclerview();
                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void addRecyclerview(){
        recyclerviewBilldetail.setLayoutManager(linearLayoutManager);
        recyclerviewBilldetail.setHasFixedSize(true);
        recyclerviewBilldetail.setAdapter(billdetailAdapter);
    }

    public void delete(int i,int po){
        nBilldetailsDAO.delete(String.valueOf(i));
        getBilldetailsArrayList1.remove(po);
        addRecyclerview();

    }
}
