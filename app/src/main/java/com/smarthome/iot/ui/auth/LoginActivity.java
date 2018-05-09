package com.smarthome.iot.ui.auth;

import android.os.Bundle;
import android.view.View;

import com.smarthome.iot.MainActivity;
import com.smarthome.iot.R;
import com.smarthome.iot.data.repository.LoginRepository;
import com.smarthome.iot.data.source.local.LoginLocalDataSource;
import com.smarthome.iot.data.source.remote.LoginRemoteDataSource;
import com.smarthome.iot.ui.base.BaseActivity;
import com.smarthome.iot.utils.navigator.Navigator;
import com.smarthome.iot.utils.rx.SchedulerProvider;

public class LoginActivity extends BaseActivity implements LoginContract.IView {
    private LoginContract.IPresenter mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
    }

    private void init(){
        LoginRepository loginRepository = LoginRepository.getInstance(LoginLocalDataSource.getInstance(),
                LoginRemoteDataSource.getInstance(this));
        mPresenter = new LoginPresenter(loginRepository, SchedulerProvider.getInstance());
        mPresenter.setView(this);
    }

    @Override
    public void showLoadingIndicator() {

    }

    @Override
    public void hideLoadingIndicator() {

    }

    @Override
    public void showLoginError(Throwable throwable) {

    }

    @Override
    public void startDashboardActivity() {
        Navigator navigator = new Navigator(this);
        Bundle bundle = new Bundle();
        navigator.startActivity(MainActivity.class, bundle);
    }

    public void clickHandle(View view){
        if(view.getId() == R.id.btn){
            mPresenter.doLogin("root", "123456");
        }
    }
}
