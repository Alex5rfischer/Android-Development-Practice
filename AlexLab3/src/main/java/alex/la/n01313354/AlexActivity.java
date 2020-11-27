package alex.la.n01313354;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

//Author: Alex La
//Student Id: n01313354
//Section: RNA
public class AlexActivity extends AppCompatActivity {
    final Context context = this;
    Dialog myDialog;
    Button alexNoButton,alexYesButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void onBackPressed() {
        MyCustomAlertDialog();
    }
    public void MyCustomAlertDialog()
    {
        myDialog = new Dialog(AlexActivity.this);
        myDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        myDialog.setContentView(R.layout.customdialog);
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