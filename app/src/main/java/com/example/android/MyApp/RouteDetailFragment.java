package com.example.android.MyApp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.MyApp.content.RouteUtility;
//import com.example.android.songs.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RouteDetailFragment extends Fragment {


    public RouteDetailFragment() {
        // Required empty public constructor
    }

    public static RouteDetailFragment newInstance(int selectedSong) {
        RouteDetailFragment fragment = new RouteDetailFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(RouteUtility.ROUTE_ID_KEY, selectedSong);
        fragment.setArguments(arguments);
        return fragment;
    }

    public RouteUtility.Route mSong;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments().containsKey(RouteUtility.ROUTE_ID_KEY)) {
            mSong = RouteUtility.ROUTE_ITEM.get(getArguments().getInt(RouteUtility.ROUTE_ID_KEY));
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.route_detail, container, false);
        if (mSong != null) {
            ((TextView) rootView.findViewById(R.id.route_detail))
                    .setText(mSong.stop_title);
        }
        return rootView;
    }

}
