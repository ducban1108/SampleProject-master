package ducban.deptrai.comot.khonghai.sampleproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ducban.deptrai.comot.khonghai.sampleproject.adapter.TheLoaiAdapter;
import ducban.deptrai.comot.khonghai.sampleproject.database.DatabaseHelper;
import ducban.deptrai.comot.khonghai.sampleproject.model.TypeBook;
import ducban.deptrai.comot.khonghai.sampleproject.sqlitedao.TypeBookDAO;
import ducban.deptrai.comot.khonghai.sampleproject.sqlitedao.UserDAO;

public class AddTypeActivity extends AppCompatActivity {
    private EditText edtMaloai;
    private EditText edtTenLoai;
    private EditText edtVitri;
    private EditText edtMota;
    private Button btnThem;
    List<TypeBook> typeBooks;
    private TheLoaiAdapter theLoaiAdapter;
    private Button btnHuy;
    private DatabaseHelper databaseHelper;
    private TypeBookDAO typeBookDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_type);

        edtMaloai = findViewById(R.id.edtMatheloai);
        edtTenLoai = findViewById(R.id.edtTentheloai);
        edtVitri = findViewById(R.id.edtVitri);
        edtMota = findViewById(R.id.edtMota);

        databaseHelper=new DatabaseHelper(this);
        typeBookDAO=new TypeBookDAO(databaseHelper);
             typeBooks=new ArrayList<>();
        theLoaiAdapter=new TheLoaiAdapter(this,typeBooks,typeBookDAO);
        btnThem = findViewById(R.id.btnThemtheloai);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = edtMaloai.getText().toString().trim();
                String name = edtTenLoai.getText().toString().trim();
                String des = edtMota.getText().toString().trim();
                String pos = edtVitri.getText().toString().trim();
                TypeBook typeBook = new TypeBook(id, name, des, pos);

                long result =typeBookDAO.insertTypeBook(typeBook);
                if (result < 0) {
                    Toast.makeText(AddTypeActivity.this, "Mã loại sách đã tồn tại!!!", Toast.LENGTH_SHORT).show();
                } else {
                    typeBooks.add(0, typeBook);

                    theLoaiAdapter.notifyDataSetChanged();

                    Toast.makeText(AddTypeActivity.this, "Thêm thể loại thành công!!!", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

}
