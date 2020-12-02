package alex.la.n01313354;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
//Author: Alex La
//Student Id: n01313354
//Section: RNA
public class LaDatePicker extends AppCompatActivity {
    private DatePicker datePicker;

    private int yr, month, day;
    private TextView textView;

    //DatePicker View
    DatePicker picker;
    Button btnGet;
    public TextView tvw;
    public String selectedDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.la_date_picker);

        //instantiate the text view
        textView = (TextView)findViewById(R.id.alextopDate);
        tvw=(TextView)findViewById(R.id.textView1);
        //instantiate the date and time picker

        datePicker = (DatePicker) findViewById(R.id.datePicker);
        btnGet=(Button)findViewById(R.id.alexDataBtn);

        Calendar today = Calendar.getInstance();
        yr = today.get(Calendar.YEAR);
        month = today.get(Calendar.MONTH);
        day = today.get(Calendar.DAY_OF_MONTH);
        //
        datePicker.init(datePicker.getYear(), datePicker.getMonth(),
                datePicker.getDayOfMonth(),new DatePicker.OnDateChangedListener() {

                    @Override
                    public void onDateChanged(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                        // TODO Auto-generated method stub
                        textView.setText(" "+monthOfYear+ " / "+ dayOfMonth + " / "+year);
                        btnGet.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                tvw.setText(textView.getText());
                            }
                        });
                    }
                } );
    }
}