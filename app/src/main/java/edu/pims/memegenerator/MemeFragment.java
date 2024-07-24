package edu.pims.memegenerator;

import android.app.Activity;
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


        editTopText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Activity activity=getActivity();

                if (activity instanceof MemeGenerator){
                    ((MemeGenerator)getActivity()).updateTopText((s.toString()));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //initial commit

        editBottomText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Activity activity=getActivity();

                if (activity instanceof MemeGenerator){
                    ((MemeGenerator)getActivity()).updateBottomText((charSequence.toString()));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return view;
    }

}