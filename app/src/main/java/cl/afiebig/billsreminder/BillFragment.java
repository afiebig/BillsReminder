package cl.afiebig.billsreminder;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * BillsReminder
 * cl.afiebig.billsreminder created with Android Studio
 * Created by afiebig on 7/28/15.
 * Alfredo Fiebig C. - afiebigc[AT]gmail[DOT]com
 */

public class BillFragment extends Fragment {

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState){
        // Inflat the layout for this fragment
        return inflater.inflate(R.layout.fragment_bill_view, container, false);
    }
}
