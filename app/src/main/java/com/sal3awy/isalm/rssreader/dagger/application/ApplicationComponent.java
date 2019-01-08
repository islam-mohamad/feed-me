package com.sal3awy.isalm.rssreader.dagger.application;

import com.google.gson.Gson;
import com.sal3awy.isalm.rssreader.MyApplication;


import java.util.concurrent.Executor;

import dagger.Component;
import retrofit2.Retrofit;

@ApplicationScope
@Component(modules = {NetworkModule.class})
public interface ApplicationComponent {

    Retrofit getRetrofitClient();

    Executor getExecutor();

    Gson getGson();

}
