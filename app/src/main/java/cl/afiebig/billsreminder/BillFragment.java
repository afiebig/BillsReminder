package cl.afiebig.billsreminder;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by afiebig on 5/9/15.
 * Fragment Created to display the bills in the Main List
 */
public class BillFragment extends Fragment {

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState){
        // Inflat the layout for this fragment
        return inflater.inflate(R.layout.fragment_bill_view, container, false);
    }
}
