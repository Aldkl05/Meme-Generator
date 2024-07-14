package edu.pims.memegenerator;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


public class MemeFragment extends Fragment {

    EditText editTopText, editBottomText;

    public MemeFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_meme, container, false);
        editTopText = view.findViewById(R.id.topTextEdit);
        editBottomText = view.findViewById(R.id.bottomTextEdit);







        return view;
    }
}