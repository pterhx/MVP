package co.pterhx.mvp;

import android.os.Handler;
import android.text.TextUtils;

public class LoginPresenter {

    private LoginView mLoginView;

    public LoginPresenter() {
    }

    public void setLoginView(LoginView loginView) {
        mLoginView = loginView;
    }

    public void login(final String username, final String password) {
        mLoginView.showLoading();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (TextUtils.isEmpty(username)) {
                    mLoginView.showError("You must enter a username!");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    mLoginView.showError("You must enter a password!");
                    return;
                }

                mLoginView.showSuccess();

            }
        }, 2000);
    }
}
