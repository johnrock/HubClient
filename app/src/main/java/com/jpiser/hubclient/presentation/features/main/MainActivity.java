package com.jpiser.hubclient.presentation.features.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.widget.EditText;
import android.widget.Toast;

import com.jpiser.hubclient.R;
import com.jpiser.hubclient.presentation.application.HubClientApplication;
import com.jpiser.hubclient.presentation.features.profile.view.ProfileActivity;
import com.jpiser.hubclient.presentation.util.Extras;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.userLogin) EditText userLoginEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((HubClientApplication)getApplication()).getAppComponent().inject(this);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.loadProfileButton)
    public void loadProfile(){

        Editable userLogin = userLoginEditText.getText();

        if(userLogin != null && userLogin.length() > 0){
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.putExtra(Extras.USER_LOGIN, userLogin.toString());
            startActivity(intent);
        }
        else{
            Toast.makeText(this, R.string.main_prompt_enter_login, Toast.LENGTH_SHORT).show();
        }
    }





}
