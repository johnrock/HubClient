package com.jpiser.hubclient.presentation.features.main.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.widget.EditText;
import android.widget.Toast;

import com.jpiser.hubclient.R;
import com.jpiser.hubclient.data.models.shared.Credentials;
import com.jpiser.hubclient.presentation.application.HubClientApplication;
import com.jpiser.hubclient.presentation.features.profile.view.ProfileActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.userLogin) EditText userLoginEditText;
    @BindView(R.id.password) EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HubClientApplication application = (HubClientApplication) getApplication();
        application.getAppComponent().inject(this);
        application.resetCredentials();
        ButterKnife.bind(this);

    }

    @OnClick(R.id.loadCredentialsButton)
    public void loadCredentials(){

        Editable userLogin = userLoginEditText.getText();
        Editable passwordEditTextText = passwordEditText.getText();
        String password = null;

        if(userLogin != null && userLogin.length() > 0){

            if(passwordEditTextText != null && passwordEditTextText.length() > 0){
                password = passwordEditTextText.toString();
            }

            ((HubClientApplication)getApplication()).setCredentials(new Credentials(userLogin.toString(), password));

            start();
        }
        else{
            Toast.makeText(this, R.string.main_prompt_enter_login, Toast.LENGTH_SHORT).show();
        }
    }

    private void start() {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }


}
