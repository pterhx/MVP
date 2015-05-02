package co.pterhx.mvp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class LoginActivity extends Activity implements LoginView {

    @InjectView(R.id.username)
    EditText username;

    @InjectView(R.id.password)
    EditText password;

    @InjectView(R.id.progressBar)
    ProgressBar progressBar;

    @InjectView(R.id.status)
    TextView status;

    /**
     * LoginPresenter is static so that it survives configuration changes.
     *
     * Care must be taken so that the LoginPresenter is not leaked because it
     * holds a reference to LoginActivity. We ensure it is garbage collected by
     * setting it to null in the onDestroy callback.
     */
    private static LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getLoginPresenter().setLoginView(this);
        ButterKnife.inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isFinishing()) {
            getLoginPresenter().setLoginView(null);
            mLoginPresenter = null;
        }
    }

    public LoginPresenter getLoginPresenter() {
        if (mLoginPresenter == null) {
            mLoginPresenter = new LoginPresenter();
            mLoginPresenter.setLoginView(this);
        }
        return mLoginPresenter;
    }

    @OnClick(R.id.login)
    public void login() {
        getLoginPresenter().login(username.getText().toString(), password.getText().toString());
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showSuccess() {
        progressBar.setVisibility(View.GONE);
        status.setText("Success!");
    }

    @Override
    public void showError(String msg) {
        progressBar.setVisibility(View.GONE);
        status.setText(msg);
    }
}
