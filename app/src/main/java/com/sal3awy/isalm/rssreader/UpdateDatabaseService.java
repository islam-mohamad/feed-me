package com.sal3awy.isalm.rssreader;

import android.app.job.JobParameters;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.firebase.jobdispatcher.JobService;
import com.sal3awy.isalm.rssreader.dagger.updateservice.DaggerUpdateServiceComponent;
import com.sal3awy.isalm.rssreader.dagger.updateservice.UpdateServiceComponent;
import com.sal3awy.isalm.rssreader.dagger.updateservice.UpdateServiceModule;
import com.sal3awy.isalm.rssreader.rss.model.ArticlesRepo;
import com.sal3awy.isalm.rssreader.rss.model.ProvidersRepo;
import com.sal3awy.isalm.rssreader.rss.model.entities.Provider;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class UpdateDatabaseService extends JobService {

    @Inject
    ArticlesRepo articlesRepo;
    @Inject
    ProvidersRepo providersRepo;

    private List<Provider> providerList = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        UpdateServiceComponent component = DaggerUpdateServiceComponent.builder()
                .applicationComponent(MyApplication.get(this).getComponent())
                .updateServiceModule(new UpdateServiceModule(this))
                .build();

        component.inject(this);
    }

    /*
    public boolean onStartJob(JobParameters jobParameters) {
        LiveData<List<Provider>> providersLiveData = providersRepo.getProviders();
        providersLiveData.observeForever(new Observer<List<Provider>>() {
            @Override
            public void onChanged(@Nullable List<Provider> providers) {
                if (providers != null) {
                    providerList.addAll(providers);
                    Single.just(providerList).flattenAsObservable(list -> providerList).doOnNext(provider -> articlesRepo.getArticles(provider.getRssLink()))
                            .subscribeOn(Schedulers.io())
                            .subscribe();
                    providersLiveData.removeObserver(this);
                }
            }
        });
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }*/

    @Override
    public boolean onStartJob(com.firebase.jobdispatcher.JobParameters job) {
        LiveData<List<Provider>> providersLiveData = providersRepo.getProviders();
        providersLiveData.observeForever(new Observer<List<Provider>>() {
            @Override
            public void onChanged(@Nullable List<Provider> providers) {
                if (providers != null) {
                    providerList.addAll(providers);
                    Single.just(providerList).flattenAsObservable(list -> providerList).doOnNext(provider -> articlesRepo.getArticles(provider.getRssLink()))
                            .subscribeOn(Schedulers.io())
                            .subscribe();
                    providersLiveData.removeObserver(this);
                }
            }
        });
        return false;
    }

    @Override
    public boolean onStopJob(com.firebase.jobdispatcher.JobParameters job) {
        return false;
    }
}
