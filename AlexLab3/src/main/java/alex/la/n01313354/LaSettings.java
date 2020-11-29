package alex.la.n01313354;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class LaSettings extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.la_settings, container, false);
        fragmentAction(root);
        return root;
    }
    private void fragmentAction(final View view) {

        Spinner spin = (Spinner) view.findViewById(R.id.fontSpinner);

        ArrayAdapter<String> madapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.fontSize));
        madapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(madapter);
    }
}