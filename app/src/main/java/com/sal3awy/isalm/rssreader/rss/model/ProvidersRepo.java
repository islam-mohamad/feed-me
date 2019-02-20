package com.sal3awy.isalm.rssreader.rss.model;

import com.sal3awy.isalm.rssreader.rss.model.entities.Provider;
import com.sal3awy.isalm.rssreader.rss.model.local.ProvidersDao;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ProvidersRepo {
    private ProvidersDao providersDao;
    private Executor executor;

    @Inject
    public ProvidersRepo(ProvidersDao providersDao, Executor executor) {
        this.providersDao = providersDao;
        this.executor = executor;

    }

    public Single<List<Provider>> getProviders() {
        return providersDao.getProviders()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Completable addProvider(Provider provider) {
        return providersDao.saveProvider(provider)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Completable deleteProvider(Provider provider) {
        return providersDao.deleteProvider(provider)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
