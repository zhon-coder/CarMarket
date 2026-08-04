package com.example.carmarket;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.carmarket.common.Result;
import com.example.carmarket.database.User;
import com.example.carmarket.databinding.LoginBinding;
import com.example.carmarket.repository.UserRepository;
import com.example.carmarket.util.ToastUtil;
import com.example.carmarket.util.UserPreferenceUtil;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private LoginBinding loginBinding;
    private UserRepository userRepository;
    private GoogleAuthManager googleAuthManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 有用户登录过，直接跳到主页
        int saveUserId = UserPreferenceUtil.getUserId(this);
        if(saveUserId != -1 && saveUserId != 0) {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra("userId", saveUserId);
            startActivity(intent);
            finish();
            return;
        }

        loginBinding = LoginBinding.inflate(getLayoutInflater());
        setContentView(loginBinding.getRoot());

        userRepository = new UserRepository();

        googleAuthManager = new GoogleAuthManager(this, new GoogleAuthManager.AuthCallback() {
            @Override
            public void onSuccess(FirebaseUser user) {
                handleAppLoginSuccess(user);
            }

            @Override
            public void onFailure(String message) {
                ToastUtil.show(LoginActivity.this, message);
            }
        });

        initViews();
    }

    private void initViews() {
        loginBinding.btnLogin.setOnClickListener(this::login);
        loginBinding.tvRegister.setOnClickListener(this::openRegister);
        loginBinding.tvForgot.setOnClickListener(this::openForgot);
        loginBinding.btnGoogle.setOnClickListener(v -> googleAuthManager.launchSignIn());
//        loginBinding.btnFacebook.setOnClickListener(this::launchFacebookSignIn);

        boolean isRemember = UserPreferenceUtil.getRememberPassword(this);
        if(isRemember) {
            String username = UserPreferenceUtil.getUsername(this);
            String password = UserPreferenceUtil.getPassword(this);

            loginBinding.etUser.setText(username);
            loginBinding.etPassword.setText(password);
        }
        loginBinding.cbRememberPassword.setChecked(isRemember);
    }

    private void handleAppLoginSuccess(FirebaseUser firebaseUser) {
        if(firebaseUser == null) return;

        Result<User> result = userRepository.loginWithThirdParty(firebaseUser.getEmail()
        , firebaseUser.getDisplayName());
        User user = result.getData();
        UserPreferenceUtil.saveLogin(this, user.getId(),user.getUsername(), "" ,false);

        navigateToHome(user.getId());
    }

    private void navigateToHome(int id) {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("userId", id);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    private void login(View v) {
        String username = loginBinding.etUser.getText().toString();
        String password = loginBinding.etPassword.getText().toString();
        if(username.isEmpty() || password.isEmpty()) {
            ToastUtil.show(this, "Please fill in all the fields");
            return ;
        }
        Result<User> result = userRepository.login(username, password);
         if (result.isSuccess()) {
             boolean rememberPassword = loginBinding.cbRememberPassword.isChecked();
            User user = result.getData();

             UserPreferenceUtil.saveLogin(this, user.getId(),
                     user.getUsername(), user.getPassword(), rememberPassword);

            ToastUtil.show(this, result.getMessage());

            // start home activity
             Intent intent = new Intent(this, HomeActivity.class);
             intent.putExtra("userId",user.getId());
             intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |
                     Intent.FLAG_ACTIVITY_NEW_TASK);
             startActivity(intent);
             finish();
             overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
         }else {
             ToastUtil.show(this, result.getMessage());
         }
    }

    private void openRegister(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    private void openForgot(View view) {
        Intent intent = new Intent(this, ForgotActivity.class);
        startActivity(intent);
    }
}
