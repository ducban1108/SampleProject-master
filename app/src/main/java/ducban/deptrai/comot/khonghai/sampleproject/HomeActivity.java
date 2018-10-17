package ducban.deptrai.comot.khonghai.sampleproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


//        if (savedInstanceState == null)
//        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ChangePassActivity()).commit();
//
//        navigationView.setCheckedItem(R.id.nav_changepass);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.option, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()){
            case R.id.nav_user:
                Intent intenttt = new Intent(HomeActivity.this,AddUserActivity.class);
                startActivity(intenttt);
                break;
            case R.id.nav_type:
                Intent intent = new Intent(HomeActivity.this,AddTypeActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_book:
                Intent intentttt = new Intent(HomeActivity.this,AddBookActivity.class);
                startActivity(intentttt);
                break;
            case R.id.nav_changepass:
                Intent intentt = new Intent(HomeActivity.this,ChangePassActivity.class);
                startActivity(intentt);
                break;
            case R.id.nav_about:
                Toast.makeText(this, "Giới thiệu", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_logout:
                Toast.makeText(this, "Đăng xuất", Toast.LENGTH_SHORT).show();
                break;
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void NguoiDung(View view) {
        Intent intent = new Intent(HomeActivity.this,UserActivity.class);
        startActivity(intent);
    }

    public void TheLoai(View view) {
        Intent intent = new Intent(HomeActivity.this,TypeBookActivity.class);
        startActivity(intent);
    }



    public void hoadon(View view) {
        Intent intent = new Intent(HomeActivity.this,BillActivity.class);
        startActivity(intent);
    }

    public void sach(View view) {
        Intent intent = new Intent(HomeActivity.this,BookActivity.class);
        startActivity(intent);
    }
}
