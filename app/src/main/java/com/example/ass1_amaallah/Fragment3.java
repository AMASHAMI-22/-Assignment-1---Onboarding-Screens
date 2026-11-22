package com.example.ass1_amaallah;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.ass1_amaallah.databinding.Fragment3Binding;

public class Fragment3 extends Fragment {

    public interface OnConfirmListener {
        void onCheckChanged(boolean isChecked);
        void onFinishClicked();
    }

    private static final String ARG_NAME = "name";

    private Fragment3Binding binding;
    private OnConfirmListener listener;

    public Fragment3() {
    }

    public static Fragment3 newInstance(String name) {
        Fragment3 fragment = new Fragment3();
        Bundle args = new Bundle();
        args.putString(ARG_NAME, name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnConfirmListener) {
            listener = (OnConfirmListener) context;
        } else {
            throw new RuntimeException(context + " must implement OnConfirmListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = Fragment3Binding.inflate(inflater, container, false);

        String name = getArguments() != null ? getArguments().getString(ARG_NAME) : "";
        binding.textName.setText("Welcome, " + name);

        binding.checkConfirm.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (listener != null) {
                listener.onCheckChanged(isChecked);
            }
        });

        binding.buttonContinueF3.setOnClickListener(v -> {
            if (listener != null) {
                listener.onFinishClicked();
            }
        });

        return binding.getRoot();
    }

    public void setButtonState(boolean enabled) {
        if (binding == null) return;
        binding.buttonContinueF3.setEnabled(enabled);
        binding.buttonContinueF3.setText(enabled ? "Finish" : "Continue");
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