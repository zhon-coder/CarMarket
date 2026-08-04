package com.example.carmarket;

import android.app.Activity;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.credentials.Credential;
import androidx.credentials.CredentialManager;
import androidx.credentials.CredentialManagerCallback;
import androidx.credentials.CustomCredential;
import androidx.credentials.GetCredentialRequest;
import androidx.credentials.GetCredentialResponse;
import androidx.credentials.exceptions.GetCredentialException;
import com.google.android.libraries.identity.googleid.GetGoogleIdOption;
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class GoogleAuthManager {
    private final Activity activity;
    private final CredentialManager credentialManager;
    private final FirebaseAuth mAuth;
    private final AuthCallback callback;

    public interface AuthCallback {
        void onSuccess(FirebaseUser user);
        void onFailure(String message);
    }

    public GoogleAuthManager(Activity activity, AuthCallback callback) {
        this.activity = activity;
        this.callback = callback;
        this.credentialManager = CredentialManager.create(activity);
        this.mAuth = FirebaseAuth.getInstance();
    }

    public void launchSignIn() {
        String clientId = activity.getString(R.string.google_client_id);
        GetGoogleIdOption googleIdOption = new GetGoogleIdOption.Builder()
                .setFilterByAuthorizedAccounts(false)
                .setServerClientId(clientId)
                .setAutoSelectEnabled(false)
                .setNonce("random_nonce_for_testing_" +
                        System.currentTimeMillis())
                .build();

        GetCredentialRequest request = new GetCredentialRequest.Builder()
                .addCredentialOption(googleIdOption)
                .build();

        // 获取Google返回的凭证信息
        credentialManager.getCredentialAsync(
            activity,
            request,
            null,
//            Executors.newSingleThreadExecutor(),
            activity.getMainExecutor(),
            new
            CredentialManagerCallback<GetCredentialResponse, GetCredentialException>() {
                @Override
                public void onResult(GetCredentialResponse getCredentialResponse) {
                    handleCredential(getCredentialResponse.getCredential());
                }

                @Override
                public void onError(@NonNull GetCredentialException e) {
                    Log.e("Auth","Google Sign In Error: " + e.getMessage());
                }
            }
        );
    }

    private void handleCredential(Credential credential) {
        // 如果返回的是谷歌类型的凭证
        if(credential instanceof CustomCredential && credential.getType().equals(GoogleIdTokenCredential
                .TYPE_GOOGLE_ID_TOKEN_CREDENTIAL)) {

            try {
                GoogleIdTokenCredential googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.getData());
                // 把加密字符串idToken包装为Firebase能识别的格式
                AuthCredential firebaseCredential = GoogleAuthProvider.getCredential(googleIdTokenCredential.getIdToken(),null);
                // Firebase 验证
                signInToFirebase(firebaseCredential, "google");
            }catch (Exception e) {
                callback.onFailure("Token parse error");
            }
        }
    }

    private void signInToFirebase(AuthCredential firebaseCredential, String google) {
        mAuth.signInWithCredential(firebaseCredential).addOnCompleteListener(activity, task -> {
            if(task.isSuccessful()) {
                FirebaseUser firebaseUser = mAuth.getCurrentUser();
                // 本地UserRepository同步 调用传入回调
                callback.onSuccess(mAuth.getCurrentUser());
            }else {
                callback.onFailure("Firebase Authentication Failed");
            }
        });
    }
}
