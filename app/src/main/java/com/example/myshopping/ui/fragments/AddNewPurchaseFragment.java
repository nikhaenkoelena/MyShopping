package com.example.myshopping.ui.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myshopping.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddNewPurchaseFragment extends Fragment {


    public AddNewPurchaseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_new_purchase, container, false);
    }

}
