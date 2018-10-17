package ducban.deptrai.comot.khonghai.sampleproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ducban.deptrai.comot.khonghai.sampleproject.adapter.TheLoaiAdapter;
import ducban.deptrai.comot.khonghai.sampleproject.database.DatabaseHelper;
import ducban.deptrai.comot.khonghai.sampleproject.model.TypeBook;
import ducban.deptrai.comot.khonghai.sampleproject.sqlitedao.TypeBookDAO;

public class TypeBookActivity extends AppCompatActivity {
    private RecyclerView lvListTypeBooks;
    private LinearLayoutManager linearLayoutManager;
    private TheLoaiAdapter theLoaiAdapter;
    private List<TypeBook> typeBooks;


    private DatabaseHelper databaseHelper;
    private TypeBookDAO typeBookDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_book);
        initViews();
        initData();
    }
    private void initData() {

        typeBooks = new ArrayList<>();
        typeBooks.clear();
        databaseHelper = new DatabaseHelper(this);
        typeBookDAO = new TypeBookDAO(databaseHelper);
        typeBooks = typeBookDAO.getAllTypeBooks();


        for (int i = 0; i < 20; i++) {
            TypeBook typeBook = new TypeBook();
            typeBook.id = new Random().nextInt() + "";
            typeBook.name =  "Hip Hop";
            typeBook.pos = i + "";
            typeBook.des = "Mô tả linh ta linh tinh";

            typeBooks.add(typeBook);
        }

        theLoaiAdapter = new TheLoaiAdapter(this,typeBooks,typeBookDAO);
        lvListTypeBooks.setAdapter(theLoaiAdapter);


    }
    private void initViews() {
        lvListTypeBooks = findViewById(R.id.recyclerviewTheLoai);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        lvListTypeBooks.setLayoutManager(linearLayoutManager);
        lvListTypeBooks.setHasFixedSize(true);
    }


    public void ThemSach(View view) {
        Intent intent = new Intent(TypeBookActivity.this,AddTypeActivity.class);
        startActivity(intent);
    }
}
