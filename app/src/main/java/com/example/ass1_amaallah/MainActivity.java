package com.example.ass1_amaallah;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ass1_amaallah.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity
        implements Fragment2.OnFragment2Listener, Fragment3.OnConfirmListener {

    private ActivityMainBinding binding;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, Fragment1.newInstance(null, null))
                            .commit();
                }

                getSupportFragmentManager().addOnBackStackChangedListener(() -> {
                    boolean onFirstScreen =
                            getSupportFragmentManager().getBackStackEntryCount() == 0;
            binding.buttonContinue.setVisibility(onFirstScreen ? View.VISIBLE : View.GONE);
        });

        binding.buttonContinue.setOnClickListener(view ->
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, Fragment2.newInstance(null, null))
                        .addToBackStack(null)
                        .commit());
    }

    @Override
    public void onContinueClicked(String name, String email, String gender, String age) {
        this.name = name;

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, Fragment3.newInstance(name))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onCheckChanged(boolean isChecked) {
        Fragment3 fragment3 = (Fragment3) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_container);
        if (fragment3 != null) {
            fragment3.setButtonState(isChecked);
        }
    }

    @Override
    public void onFinishClicked() {
        Toast.makeText(this, "Done, " + name + "!", Toast.LENGTH_SHORT).show();
    }
}