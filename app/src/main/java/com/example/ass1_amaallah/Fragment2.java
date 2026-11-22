package com.example.ass1_amaallah;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.ass1_amaallah.databinding.Fragment2Binding;

public class Fragment2 extends Fragment {


    public interface OnFragment2Listener {
        void onContinueClicked(String name, String email, String gender, String age);
    }

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private Fragment2Binding binding;
    private OnFragment2Listener listener;

    public Fragment2() {
    }

    public static Fragment2 newInstance(String param1, String param2) {
        Fragment2 fragment = new Fragment2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragment2Listener) {
            listener = (OnFragment2Listener) context;
        } else {
            throw new RuntimeException(context + " must implement OnFragment2Listener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = Fragment2Binding.inflate(inflater, container, false);

        binding.buttonContinueF2.setOnClickListener(v -> {
            String name = binding.editText.getText().toString().trim();
            String email = binding.editEmail.getText().toString().trim();
            String age = binding.editAge.getText().toString().trim();

            String gender = "";
            if (binding.radioMale.isChecked()) {
                gender = "Male";
            } else if (binding.radioFemale.isChecked()) {
                gender = "Female";
            }

            if (TextUtils.isEmpty(name)) {
                Toast.makeText(getContext(), "Enter name!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(email)) {
                Toast.makeText(getContext(), "Enter email!", Toast.LENGTH_SHORT).show();
                return;
            }

            listener.onContinueClicked(name, email, gender, age);
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}