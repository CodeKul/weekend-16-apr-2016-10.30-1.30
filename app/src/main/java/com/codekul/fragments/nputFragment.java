package com.codekul.fragments;


import android.os.Bundle;
//import android.Fragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class nputFragment extends Fragment {


    private static final String KEY_HEADING = "heading" ;

    public nputFragment() {
        // Required empty public constructor
    }

    /* dont do this thing
    public nputFragment(String heading) {
        this.heading = heading;
    }*/

    public static nputFragment getInstance(String heading){

        nputFragment fragment = new nputFragment();

        Bundle bundle = new Bundle();
        bundle.putString(KEY_HEADING,heading);

        fragment.setArguments(bundle);

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle args = getArguments();

       final View rootView = inflater.inflate(R.layout.fragment_nput, container, false);
        if(args != null) {
            final TextView textHeading = (TextView) rootView.findViewById(R.id.textHeading);
            textHeading.setText(args.getString(KEY_HEADING,"codekul.com"));

        }

        return rootView;
    }
}
