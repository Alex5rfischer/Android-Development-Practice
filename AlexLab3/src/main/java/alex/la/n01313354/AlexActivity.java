package alex.la.n01313354;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

//Author: Alex La
//Student Id: n01313354
//Section: RNA
public class AlexActivity extends AppCompatActivity {
    final Context context = this;
    Dialog myDialog;
    Button alexNoButton,alexYesButton;


    private DrawerLayout myDrawerLayout;
    private int SMS_PERMISSION_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alex_activity);

        LaHome homeFragment =  new LaHome();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.alexcontent_frame, homeFragment).commit();


        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setHomeAsUpIndicator(R.drawable.hamburger_icon);
        myDrawerLayout = findViewById(R.id.drawer_layout);

        NavigationView mNavigationView = findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment newFragment;
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                menuItem.setChecked(true);
                myDrawerLayout.closeDrawers();

                int id = menuItem.getItemId();
                if(id == R.id.alexFirstname)
                {
                    newFragment = new LaSrv();
                    transaction.replace(R.id.alexcontent_frame, newFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
//                }else if(id == R.id.nav_lastName){
//                    newFragment =  new MahadeoSrv();
//                    transaction.replace(R.id.alexcontent_frame, newFragment);
//                    transaction.addToBackStack(null);
//                    transaction.commit();
//                }else

                    if(id == R.id.alexSettings) {
                    newFragment =  new LaSet();
                    transaction.replace(R.id.alexcontent_frame, newFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
                Toast.makeText(getApplicationContext(), menuItem.getTitle(),
                        Toast.LENGTH_LONG).show();
                return false;
            }
        });

    }

    private void requestSMSPermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS))
        {

            new AlertDialog.Builder(this)
                    .setTitle(R.string.Information)
                    .setMessage(R.string.permission_required)
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            ActivityCompat.requestPermissions(AlexActivity.this, new String[] {Manifest.permission.SEND_SMS}, SMS_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .create().show();
        }
        else {

            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.SEND_SMS}, SMS_PERMISSION_CODE);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the tools bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle tools bar item clicks here. The tool bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId())
        {
            case R.id.alexHelp:
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://alex5rfischer.github.io/CSS---My-Site/"));
                startActivity(intent);
                break;
            case R.id.alexLocation:
                Toast.makeText(this, "Location button selected", Toast.LENGTH_SHORT).show();
                Intent location = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.where-am-i.net/"));
                startActivity(location);
                break;
            case R.id.alexsms:
                //Toast.makeText(this, "You selected the sms button", Toast.LENGTH_SHORT).show();
                if(ContextCompat.checkSelfPermission(AlexActivity.this,
                        Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(AlexActivity.this,"Permission Enabled", Toast.LENGTH_SHORT).show();
                }else{
                    requestSMSPermission();
                }
            case android.R.id.home:
                myDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        MyCustomAlertDialog();
    }
    public void MyCustomAlertDialog()
    {
        myDialog = new Dialog(AlexActivity.this);
        myDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        myDialog.setContentView(R.layout.alexcustomdialog);
        myDialog.setTitle("My Customer Dialog");
        alexNoButton = (Button)myDialog.findViewById(R.id.alexNoButton);
        alexYesButton = (Button)myDialog.findViewById(R.id.alexYesButton);

        alexNoButton.setEnabled(true);
        alexYesButton.setEnabled(true);

        alexYesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });

        alexNoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.cancel();
            }
        });
        myDialog.show();
    }
}