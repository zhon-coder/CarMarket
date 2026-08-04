package com.example.carmarket;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.carmarket.common.Result;
import com.example.carmarket.database.User;
import com.example.carmarket.databinding.RegisterBinding;
import com.example.carmarket.repository.UserRepository;
import com.example.carmarket.util.ToastUtil;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private RegisterBinding registerBinding;
    private UserRepository userRepository;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerBinding = RegisterBinding.inflate(getLayoutInflater());
        setContentView(registerBinding.getRoot());

        userRepository = new UserRepository();

        registerBinding.btnRegister.setOnClickListener(this);
        registerBinding.etRegisterPassword.addTextChangedListener(passwordWatcher);
        registerBinding.etRegisterPasswordAgain.addTextChangedListener(passwordWatcher);
        registerBinding.tvBackToLogin.setOnClickListener(v-> backToLogin());
    }

    private void backToLogin() {
        // back to login activity
        finish();
    }

    @Override
    public void onClick(View view) {
        String emailText = String.valueOf(registerBinding.etRegisterEmail.getText());
        String accountText = String.valueOf(registerBinding.etRegisterAccount.getText());
        String passwordText = String.valueOf(registerBinding.etRegisterPassword.getText());

        if(emailText.isEmpty() || accountText.isEmpty() || passwordText.isEmpty()) {
            ToastUtil.show(this, "Please fill in all the fields");
            return ;
        }

        Result<User> result = userRepository.register(accountText,passwordText,emailText);
        ToastUtil.show(this, result.getMessage());
    }

    private final TextWatcher passwordWatcher = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable editable) {
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            checkPasswordMatch();
        }
    };

    void checkPasswordMatch() {
        String passwordText = String.valueOf(registerBinding.etRegisterPassword.getText());
        String passwordAgainText = String.valueOf(registerBinding.etRegisterPasswordAgain.getText());
        if(passwordAgainText.isEmpty()) {
            registerBinding.etRegisterPasswordAgain.setError(null);
            return ;
        }
        if(!passwordAgainText.equals(passwordText)) {
            registerBinding.etRegisterPasswordAgain.setError("The two passwords did not match");
        }else {
            registerBinding.etRegisterPasswordAgain.setError(null);
        }
    }
}
