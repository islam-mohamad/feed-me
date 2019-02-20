package com.sal3awy.isalm.rssreader;

import com.firebase.jobdispatcher.JobParameters;
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
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UpdateDatabaseService extends JobService {

    @Inject
    ArticlesRepo articlesRepo;
    @Inject
    ProvidersRepo providersRepo;

    private Disposable disposable;

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

    @Override
    public boolean onStartJob(JobParameters job) {
        providersRepo.getProviders()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new SingleObserver<List<Provider>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        UpdateDatabaseService.this.disposable = d;
                    }

                    @Override
                    public void onSuccess(List<Provider> providers) {
                        if (providers != null) {
                            providerList.addAll(providers);
                            Single.just(providerList).flattenAsObservable(list -> providerList).doOnNext(provider -> articlesRepo.getArticles(provider.getRssLink()))
                                    .subscribeOn(Schedulers.io())
                                    .subscribe();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        if (disposable != null) {
            disposable.dispose();
        }
        return false;
    }
}
