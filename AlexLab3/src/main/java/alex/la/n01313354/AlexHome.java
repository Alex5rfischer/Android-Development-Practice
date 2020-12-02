package alex.la.n01313354;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
//Author: Alex La
//Student Id: n01313354
//Section: RNA
public class AlexHome extends Fragment {

    Button date;
    //DatePicker View
    TextView tvw;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // DatePicker
        View view = (View) inflater.inflate(R.layout.la_home, container, false);
        date = (Button) view.findViewById(R.id.btnDatePicker);

        tvw=(TextView)view.findViewById(R.id.alexoutput);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayDatePicker(view);
            }
        });

        //Spinner
        final Spinner spin = (Spinner) view.findViewById(R.id.coursespinner);
        //Initilizing Image Button
        final ImageButton submit = (ImageButton) view.findViewById(R.id.aleximagebutton);
        submit.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //get the spinner view as text view
                TextView courseSelected = (TextView)spin.getSelectedView();
                tvw.setText("Selected:"+ courseSelected.getText());
                //get the text from the spinner view
                //Toast.makeText(getActivity(), "Item Selected:"+courseSelected.getText(), Toast.LENGTH_SHORT).show();
            }

        });
        return view;
    }
    public void displayDatePicker(View view)
    {
        Intent intent = new Intent(getActivity(), LaDatePicker.class);
        startActivity(intent);
    }
}