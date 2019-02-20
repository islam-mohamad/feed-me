package com.sal3awy.isalm.rssreader.rss.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.sal3awy.isalm.rssreader.rss.model.ProvidersRepo;
import com.sal3awy.isalm.rssreader.rss.model.entities.Provider;
import com.sal3awy.isalm.rssreader.utils.BaseViewModel;

import java.util.List;

import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.subscribers.DisposableSubscriber;

public class ProvidersViewModel extends BaseViewModel {
    private ProvidersRepo repo;
    private MutableLiveData<List<Provider>> providersList = new MutableLiveData<>();

    ProvidersViewModel(ProvidersRepo repo) {
        this.repo = repo;
    }


    public LiveData<List<Provider>> getProviders() {
        setIsLoading(true);
        getCompositeDisposable()
                .add(repo.getProviders().subscribeWith(new DisposableSingleObserver<List<Provider>>() {
                    @Override
                    public void onSuccess(List<Provider> providers) {
                        setIsLoading(false);
                        providersList.setValue(providers);
                    }

                    @Override
                    public void onError(Throwable e) {
                        setIsLoading(false);
                    }
                }));
        return providersList;
    }

    public void deleteProvider(Provider provider) {
        setIsLoading(true);
        getCompositeDisposable().add(repo.deleteProvider(provider).subscribeWith(new DisposableCompletableObserver() {
            @Override
            public void onComplete() {
                setIsLoading(false);
            }

            @Override
            public void onError(Throwable e) {
                setErrorMessage(e.getMessage()+"");
                setIsLoading(false);
            }
        }));
    }


    public void addProvider(Provider provider) {
        setIsLoading(true);
        getCompositeDisposable().add(repo.addProvider(provider).subscribeWith(new DisposableCompletableObserver() {
            @Override
            public void onComplete() {
                setIsLoading(false);
            }

            @Override
            public void onError(Throwable e) {
                setErrorMessage(e.getMessage()+"");
                setIsLoading(false);
            }
        }));
    }
}
