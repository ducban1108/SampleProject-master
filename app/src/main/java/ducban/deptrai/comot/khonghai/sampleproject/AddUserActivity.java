package ducban.deptrai.comot.khonghai.sampleproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ducban.deptrai.comot.khonghai.sampleproject.database.DatabaseHelper;
import ducban.deptrai.comot.khonghai.sampleproject.model.User;
import ducban.deptrai.comot.khonghai.sampleproject.sqlitedao.UserDAO;

public class AddUserActivity extends AppCompatActivity {

    private EditText edtUsername;
    private EditText edtPassword;
    private EditText edtRePassword;
    private EditText edtSodienthoai;
    private EditText edtDiachi;
    private Button btnDangNhap;
    private Button btnHuy;
    private UserDAO userDAO;
    private DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        databaseHelper=new DatabaseHelper(this);
        userDAO=new UserDAO(databaseHelper);

        initView();


    }

    public void addUser(View view) {

        String user_name=edtUsername.getText().toString().trim();
        String pass=edtPassword.getText().toString().trim();
        String confrimPass=edtRePassword.getText().toString().trim();
        String mobile=edtSodienthoai.getText().toString().trim();
        String diachi=edtDiachi.getText().toString().trim();

        //validate
        if(user_name.equals("")){
            edtUsername.setError("Tên đăng nhập không được trống bạn nhé!");
        }else if (pass.length()<6){
            edtPassword.setError("Mật khẩu không được nhỏ hơn 6 kí tự bạn à!");
        }else if(!pass.equals(confrimPass)){
            edtRePassword.setError("Mật khẩu không khớp nhau rồi!");
        }else if (mobile.equals("")){
            edtSodienthoai.setError("Cho tớ số điện thoại cậu nhé!");
        }else if (diachi.equals("")){
            edtDiachi.setError("Địa chỉ của cậu cũng cho tớ nhé!");
        }else{


            User userCheck=userDAO.getUserByUsername(user_name);
            if(userCheck==null){
                // kiem tra thanh cong
                User user=new User(user_name,pass,diachi,mobile);
                userDAO.insertUser(user);
                Toast.makeText(this, "Add account successfully", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Duplicate account, please try again!", Toast.LENGTH_SHORT).show();
            }

        }
    }

    public void initView(){
        edtUsername = (EditText) findViewById(R.id.edtUsername);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        edtRePassword = (EditText) findViewById(R.id.edtRePassword);
        edtSodienthoai = (EditText) findViewById(R.id.edtSodienthoai);
        edtDiachi = (EditText) findViewById(R.id.edtDiachi);
        btnDangNhap = (Button) findViewById(R.id.btnDangNhap);
        btnHuy = (Button) findViewById(R.id.btnHuy);
    }

    public void showNguoiDung(View view) {
        Intent intent = new Intent(AddUserActivity.this,UserActivity.class);
        startActivity(intent);
    }
}
