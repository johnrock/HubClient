package com.jpiser.hubclient.presentation.features.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.jpiser.hubclient.R;
import com.jpiser.hubclient.presentation.application.HubClientApplication;
import com.jpiser.hubclient.presentation.features.main.model.MenuItem;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainPresenter.ViewLayer, MainRecyclerViewAdapter.MenuItemTapper {

    @Inject MainPresenter mainPresenter;

    @BindView(R.id.recyclerView) RecyclerView recyclerView;

    LinearLayoutManager linearLayoutManager;
    MainRecyclerViewAdapter mainRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((HubClientApplication)getApplication()).getAppComponent().inject(this);
        ButterKnife.bind(this);

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        mainPresenter.bind(this);
        mainPresenter.loadMenu();


    }

    @Override
    public void displayMenu(List<MenuItem> menuItems) {
        mainRecyclerViewAdapter = new MainRecyclerViewAdapter(menuItems, this);
        recyclerView.setAdapter(mainRecyclerViewAdapter);

    }

    @Override
    public void tapItem(MenuItem menuItem) {
        Toast.makeText(this, "Tapped" + menuItem.getName(), Toast.LENGTH_LONG).show();
    }
}
