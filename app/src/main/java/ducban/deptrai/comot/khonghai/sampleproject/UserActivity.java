package ducban.deptrai.comot.khonghai.sampleproject;

import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ducban.deptrai.comot.khonghai.sampleproject.adapter.NguoiDungAdapter;
import ducban.deptrai.comot.khonghai.sampleproject.database.DatabaseHelper;
import ducban.deptrai.comot.khonghai.sampleproject.model.User;
import ducban.deptrai.comot.khonghai.sampleproject.sqlitedao.UserDAO;

public class UserActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private NguoiDungAdapter nguoiDungAdapter;
    private List<User> userList;

    private DatabaseHelper databaseHelper;
    private UserDAO userDAO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        initViews();
        databaseHelper = new DatabaseHelper(this);
        userDAO = new UserDAO(databaseHelper);


        for (int i = 0; i < 10; i++) {
            User user = new User(
                    "duc " + new Random().nextInt(),
                    "duc123",
                    "Lê Việt Đức ",
                    0,
                    "01674178795");

            userDAO.insertUser(user);
        }


        AddRecyclerview();
    }

    void initViews(){
        recyclerView = findViewById(R.id.recyclerviewNguoiDung);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        userList = new ArrayList<>();
        userList.clear();
    }

    void AddRecyclerview(){
        userList = userDAO.getListAllUser();
        nguoiDungAdapter = new NguoiDungAdapter(this, userList, userDAO);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(nguoiDungAdapter);
    }
    public void addUser(View view) {

        final Dialog dialog = new Dialog(this);
        dialog.setTitle("Add User");

        dialog.setContentView(R.layout.activity_add_user);

        final EditText edtPassWord;
        EditText edtConfirmPassword;
        final EditText edtName;
        final EditText edtPhone;
        final EditText edtUserName;

        edtUserName = dialog.findViewById(R.id.edtUsername);
        edtPassWord = dialog.findViewById(R.id.edtPassWord);
        edtConfirmPassword = dialog.findViewById(R.id.edtConfirmPassword);
        edtName = dialog.findViewById(R.id.edtName);
        edtPhone = dialog.findViewById(R.id.edtPhone);


        dialog.findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                User user = new User();
                user.setUsername(edtUserName.getText().toString().trim());
                user.setName(edtName.getText().toString().trim());
                user.setPhonenumber(edtPhone.getText().toString().trim());
                user.setPassword(edtPassWord.getText().toString().trim());

                userDAO.insertUser(user);

                // cap nhat len giao dien
                // add vao vi tri dau tien
                userList.add(0,user);
                nguoiDungAdapter.notifyDataSetChanged();


                Toast.makeText(UserActivity.this,
                        getString(R.string.notify_add_successful), Toast.LENGTH_SHORT).show();
                dialog.dismiss();

            }
        });
        dialog.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();


    }

    public void themUser(View view) {
        Intent intent = new Intent(UserActivity.this,AddUserActivity.class);
        startActivity(intent);
    }


}
