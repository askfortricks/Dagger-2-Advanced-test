package com.hariofspades.dagger2advanced;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hariofspades.dagger2advanced.MainActivityFeature.DaggerMainActivityComponent;
import com.hariofspades.dagger2advanced.MainActivityFeature.MainActivityComponent;
import com.hariofspades.dagger2advanced.MainActivityFeature.MainActivityModule;
import com.hariofspades.dagger2advanced.adapter.RandomUserAdapter;
import com.hariofspades.dagger2advanced.application.MyApplication;
import com.hariofspades.dagger2advanced.interfaces.RandomUsersApi;
import com.hariofspades.dagger2advanced.model.RandomUsers;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class MainActivityNew  extends AppCompatActivity{

    RecyclerView recyclerView;

    @Inject
    RandomUsersApi randomUsersApi;

    @Inject
    RandomUserAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

//        MyComponent myComponent= DaggerMyComponent.builder()
//                .contextModule(new ContextModule(this))
//                .build();
//        randomUsersApi=myComponent.getRandomUserService();
        afterActivityLevelComponent();
        populateUsers();
    }

    private void afterActivityLevelComponent() {
        MainActivityComponent mainActivityComponent= DaggerMainActivityComponent.builder()
                .mainActivityModule(new MainActivityModule(this))
                .myComponent(MyApplication.get(this).getMyComponent())
                .build();
        mainActivityComponent.injectMainActivity(this);
    }

    private void populateUsers() {

        Call<RandomUsers> randomUsersCall = getRandomUserService().getRandomUsers(10);
        randomUsersCall.enqueue(new Callback<RandomUsers>() {
            @Override
            public void onResponse(Call<RandomUsers> call, @NonNull Response<RandomUsers> response) {
                if(response.isSuccessful()) {
                   // mAdapter=new RandomUserAdapter(MainActivityNew.this);
                    mAdapter.setItems(response.body().getResults());
                    recyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<RandomUsers> call, Throwable t) {
                Timber.i(t.getMessage());
            }
        });

    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    public RandomUsersApi getRandomUserService() {
        return randomUsersApi;
    }
}
