package com.example.carmarket;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.carmarket.common.Result;
import com.example.carmarket.database.User;
import com.example.carmarket.databinding.ForgotPasswordBinding;
import com.example.carmarket.repository.UserRepository;
import com.example.carmarket.util.ToastUtil;

public class ForgotActivity extends AppCompatActivity {
    private ForgotPasswordBinding forgotBinding;
    private UserRepository userRepository;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        forgotBinding = ForgotPasswordBinding.inflate(getLayoutInflater());
        setContentView(forgotBinding.getRoot());

        userRepository = new UserRepository();

        forgotBinding.tvBack.setOnClickListener(v ->backToLogin());
        forgotBinding.btnForgotDone.setOnClickListener(v->Done());

        forgotBinding.etNewPassword.addTextChangedListener(passwordWatcher);
        forgotBinding.etNewPasswordAgain.addTextChangedListener(passwordWatcher);
    }

    private void Done() {
        String email = forgotBinding.etForgotEmail.getText().toString();
        if(email.isEmpty()) {
            ToastUtil.show(this, "Please fill in all the fields");
            return ;
        }
        String newPassword = String.valueOf(forgotBinding.etNewPassword.getText());
        Result<User> result = userRepository.resetPassword(email, newPassword);
        ToastUtil.show(this, result.getMessage());
        finish();
    }

    private void backToLogin() {
        finish();
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
    private void checkPasswordMatch() {
        String passwordText = String.valueOf(forgotBinding.etNewPassword.getText());
        String passwordAgainText = String.valueOf(forgotBinding.etNewPasswordAgain.getText());
        if(passwordAgainText.isEmpty()) {
            forgotBinding.etNewPasswordAgain.setError(null);
        }
        if(!passwordAgainText.equals(passwordText)) {
            forgotBinding.etNewPasswordAgain.setError("The two passwords did not match");
        }else {
            forgotBinding.etNewPasswordAgain.setError(null);
        }
    }
}
