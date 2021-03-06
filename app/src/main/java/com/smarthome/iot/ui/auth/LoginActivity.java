package com.smarthome.iot.ui.auth;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.smarthome.iot.R;
import com.smarthome.iot.data.repository.LoginRepository;
import com.smarthome.iot.data.source.local.LoginLocalDataSource;
import com.smarthome.iot.data.source.remote.LoginRemoteDataSource;
import com.smarthome.iot.ui.base.BaseActivity;
import com.smarthome.iot.ui.main.MainActivity;
import com.smarthome.iot.utils.navigator.Navigator;
import com.smarthome.iot.utils.rx.SchedulerProvider;

public class LoginActivity extends BaseActivity implements LoginContract.View {

    private LoginContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
    }

    private void init(){
        LoginRepository loginRepository = LoginRepository.getInstance(LoginLocalDataSource.getInstance(),
                LoginRemoteDataSource.getInstance(this));
        mPresenter = new LoginPresenter(this, loginRepository, SchedulerProvider.getInstance());
        mPresenter.setView(this);

        dialogProgress = msgUtil.initCustomDialogProgress(this, null);
    }

    @Override
    public void showLoadingIndicator() {
        dialogProgress.show();
    }

    @Override
    public void hideLoadingIndicator() {
        dialogProgress.dismiss();
    }

    @Override
    public void showLoginError(Throwable throwable) {
        Log.i("Login:", throwable.getMessage());
    }

    @Override
    public void startDashboardActivity() {
        Navigator navigator = new Navigator(this);
        Bundle bundle = new Bundle();
        navigator.startActivity(MainActivity.class, bundle);
    }

    public void clickHandle(View view){
        if(view.getId() == R.id.rl_button){
            EditText editLogin = findViewById(R.id.edit_login);
            String login = editLogin.getText().toString();

            EditText editPassword = findViewById(R.id.edit_password);
            String password = editPassword.getText().toString();

            mPresenter.doLogin(login, password);
        }
    }
}
