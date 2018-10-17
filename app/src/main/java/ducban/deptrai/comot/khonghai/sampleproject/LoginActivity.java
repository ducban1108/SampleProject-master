package ducban.deptrai.comot.khonghai.sampleproject;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ducban.deptrai.comot.khonghai.sampleproject.database.DatabaseHelper;
import ducban.deptrai.comot.khonghai.sampleproject.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ducban.deptrai.comot.khonghai.sampleproject.HomeActivity;
import ducban.deptrai.comot.khonghai.sampleproject.R;
import ducban.deptrai.comot.khonghai.sampleproject.database.DatabaseHelper;
import ducban.deptrai.comot.khonghai.sampleproject.model.User;
import ducban.deptrai.comot.khonghai.sampleproject.sqlitedao.UserDAO;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity{

    private EditText edtUsername;
    private EditText edtPassword;

    private DatabaseHelper databaseHelper;

    private UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        databaseHelper = new DatabaseHelper(this);
        userDAO = new UserDAO(databaseHelper);
        initViews();


        //thêm user

        User user = new User("duclv" + new Random().nextInt(),"ducdeptrai","Lê Việt Đức",0,"01674178795");
        userDAO.insertUser(user);




    }

    public void initViews(){
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);

    }


    public void Check(){
        String name = edtUsername.getText().toString().trim();
        String pass = edtPassword.getText().toString().trim();

        if (name.equals("")) {
            edtUsername.setError("Không được để trống!");
            return;
        }

        String[] b = {"!", "~", "@", "#", "$", "%", "^", "&", "*", "*", "(", ")", "_", "-", "=", "+", "[", "]", ";", ":", "\\", "|", "?", "/", "<", ">", ".", ",", "'"};
        for (int i = 0; i < b.length; i++) {
            if (name.indexOf(b[i]) > -1) {
                edtUsername.setError("Không chứa kí tự đặc biệt!");
                return;
            }

        }

        if (pass.equals("")) {
            edtPassword.setError("Không được để trống!");
            return;
        }

        for (int i = 0; i < b.length; i++) {
            if (pass.indexOf(b[i]) > -1) {
                edtPassword.setError("Không chứa kí tự đặc biệt!");
                return;
            }

        }

        if (pass.length() <6) {

            edtPassword.setError("Mật khẩu của bạn cần nhiều hơn 3 kí tự cơ!");
            return;
        }

        User user = userDAO.getUserByUsername(name);

        if (user == null){
            Toast.makeText(LoginActivity.this,
                    getString(R.string.notify_wrong_username_or_password),
                    Toast.LENGTH_SHORT).show();
        }else {

            String passwordInDB = user.getPassword();
            if (passwordInDB.equals(pass)){
                startActivity(new Intent(this, HomeActivity.class));
                finish();
            }else {
                Toast.makeText(LoginActivity.this,
                        getString(R.string.notify_wrong_username_or_password),
                        Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void DangNhap(View view) {
        Check();
    }

    public void Huy(View view) {
        edtUsername.setText("");
        edtPassword.setText("");
    }


}

