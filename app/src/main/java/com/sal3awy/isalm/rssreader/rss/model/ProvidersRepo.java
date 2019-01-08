package com.sal3awy.isalm.rssreader.rss.model;

import android.arch.lifecycle.LiveData;

import com.sal3awy.isalm.rssreader.rss.model.entities.Provider;
import com.sal3awy.isalm.rssreader.rss.model.local.ProvidersDao;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

public class ProvidersRepo {
    private ProvidersDao providersDao;
    private Executor executor;

    @Inject
    public ProvidersRepo(ProvidersDao providersDao, Executor executor) {
        this.providersDao = providersDao;
        this.executor = executor;

    }

    public LiveData<List<Provider>> getProviders() {
        return providersDao.getProviders();
    }

    public void addProvider(Provider provider) {
        executor.execute(() -> providersDao.saveProvider(provider));
    }

    public void deleteProvider(Provider provider) {
        executor.execute(() -> providersDao.deleteProvider(provider));
    }
}
