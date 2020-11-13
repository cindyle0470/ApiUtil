package com.example.myapipractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapipractice.databinding.ActivityMainBinding;

public class MainView extends AppCompatActivity implements IMainView {
    private ActivityMainBinding binding;
    private MyPresenter presenter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setOnClickListener();
    }

    private void setOnClickListener() {
        String name = binding.editName.getText().toString();
        String password = binding.editPassword.getText().toString();

        login(name, password);
        logout();
        getDeviceCount();
        getOnboardStatus();

        String newPsw = binding.editNewPassword.getText().toString();
        setPassword(newPsw);

        setOnboardStatus();
    }

    private void setOnboardStatus() {
        String status = binding.spinner.getSelectedItem().toString();
        String setStatus = null;
        if(status.equals("ON")) {
            setStatus = "link";
        } else {
            setStatus = "unlink";
        }
        presenter.setOnboardStatus(setStatus);
    }

    public void login(String name, String psw) {
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.login(name, psw);
            }
        });
    }

    public void logout() {
        binding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.logout();
            }
        });
    }

    public void setPassword(String newPsw) {
        binding.btnChangePsw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.changePsw(newPsw);
            }
        });
    }

    private void getDeviceCount() {
        binding.btnCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getDeviceCount();
            }
        });
    }

    private void getOnboardStatus() {
        binding.btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getOnboardStatus();
            }
        });
    }

    @Override
    public void updateOnboardStatus(boolean isOboard) {
        if (isOboard) {
            binding.txtOnboard.setText("ON");
        } else {
            binding.txtOnboard.setText("OFF");
        }
    }

    @Override
    public void toast(String msg) {
        Toast.makeText(MainView.this, msg, Toast.LENGTH_LONG);
    }

    @Override
    public void runOnMainThread(Runnable task) {
        MainView.this.runOnUiThread(task);
    }

    @Override
    public void appFinish() {
        finish();
    }

    @Override
    public void updateDeviceCount(int count) {
        binding.txtCount.setText(count);
    }

}