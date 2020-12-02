package alex.la.n01313354;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;

public class LaSet extends Fragment {
    private Button saveBtn;
    private Switch mySwitch;
    private RadioButton radio_brown;
    private RadioButton radio_purple;
    private RadioButton radio_grey;
    private RadioButton radio_DEFAULT;
    private LinearLayout settings;

    private RadioGroup radioGroup;
    private RadioButton radioButton;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";
    public static final String SWITCH1 = "switch1";

    private boolean switchOnOff;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = (View) inflater.inflate(R.layout.la_set, container, false);
        //Initilizations
        saveBtn = (Button)view.findViewById(R.id.alexButtonSettingsSave);
        mySwitch = (Switch)view.findViewById(R.id.alexPortraitSwitch);

        //Used to change background color
        LinearLayout settings =(LinearLayout) view.findViewById(R.id.settings);

        radioGroup = (RadioGroup)view.findViewById(R.id.colorGroup);
        radio_brown = (RadioButton) view.findViewById(R.id.alexBrownColorRadio);
        radio_purple = (RadioButton) view.findViewById(R.id.alexPurpleColorRadio);
        radio_grey = (RadioButton) view.findViewById(R.id.alexGreyColorRadio);
        radio_DEFAULT = (RadioButton) view.findViewById(R.id.alexDefaultColorRadio);

        radio_brown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settings.setBackgroundColor(Color.rgb(150,75,0));

            }
        });

        radio_purple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settings.setBackgroundColor(Color.rgb(128,0,128));

            }
        });

        radio_grey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settings.setBackgroundColor(Color.GRAY);

            }
        });

        radio_DEFAULT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settings.setBackgroundColor(Color.GREEN);

            }
        });



        saveBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                saveData(view);
            }
        });
        loadData();
        updateViews();

        return view;
    }

    public void saveData(View v)
    {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean(SWITCH1, mySwitch.isChecked());
        editor.apply();
        Toast.makeText(getActivity(), "Data saved", Toast.LENGTH_SHORT).show();
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        //text = sharedPreferences.getString(TEXT, "");
        switchOnOff = sharedPreferences.getBoolean(SWITCH1, false);
    }
    public void updateViews() {
        //textView.setText(text);
        mySwitch.setChecked(switchOnOff);
    }

}