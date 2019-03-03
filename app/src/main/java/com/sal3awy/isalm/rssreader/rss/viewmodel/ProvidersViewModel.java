package com.sal3awy.isalm.rssreader.rss.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.Observable;
import android.databinding.ObservableChar;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.text.Editable;
import android.text.TextWatcher;

import com.sal3awy.isalm.rssreader.rss.model.ProvidersRepo;
import com.sal3awy.isalm.rssreader.rss.model.entities.Provider;
import com.sal3awy.isalm.rssreader.base.BaseViewModel;
import com.sal3awy.isalm.rssreader.utils.CommonUtils;

import java.util.List;

import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.subscribers.DisposableSubscriber;

public class ProvidersViewModel extends BaseViewModel {
    private ProvidersRepo repo;
    private MutableLiveData<List<Provider>> providersList = new MutableLiveData<>();
    public ObservableField<Integer> nameError = new ObservableField<>();
    public ObservableField<Integer> urlError = new ObservableField<>();

    ProvidersViewModel(ProvidersRepo repo) {
        this.repo = repo;
        nameError.set(0);
        urlError.set(0);
    }


    public LiveData<List<Provider>> getProviders() {
        setIsLoading(true);
        getCompositeDisposable().add(repo.getProviders().subscribeWith(new DisposableSubscriber<List<Provider>>() {
            @Override
            public void onNext(List<Provider> providers) {
                setIsLoading(false);
                providersList.setValue(providers);
            }

            @Override
            public void onError(Throwable t) {
                setIsLoading(false);
                setErrorMessage(t.getMessage() + "");
            }

            @Override
            public void onComplete() {
                setIsLoading(false);
            }
        }));
        return providersList;
    }

    public void deleteProvider(Provider provider) {
        setIsLoading(true);
        getCompositeDisposable().add(repo.deleteProvider(provider).subscribeWith(new DisposableSingleObserver<Integer>() {
            @Override
            public void onSuccess(Integer integer) {
                setIsLoading(false);
            }

            @Override
            public void onError(Throwable e) {
                setErrorMessage(e.getMessage() + "");
                setIsLoading(false);
            }
        }));
    }


    public void addProvider(Provider provider) {
        setIsLoading(true);
        getCompositeDisposable().add(repo.addProvider(provider).subscribeWith(new DisposableSingleObserver<Long>() {
            @Override
            public void onSuccess(Long i) {
                setIsLoading(false);
            }

            @Override
            public void onError(Throwable e) {
                setErrorMessage(e.getMessage() + "");
                setIsLoading(false);
            }
        }));
    }

    public TextWatcher nameTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence name, int start, int before, int count) {
            int strResourceId = CommonUtils.isNameValid(name.toString());
            if (strResourceId != 0) {
                nameError.set(strResourceId);
            }else {
                nameError.set(0);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    public TextWatcher urlTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence url, int start, int before, int count) {
            int strResourceId = CommonUtils.isUrlValid(url.toString());
            if (strResourceId != 0) {
                urlError.set(strResourceId);
            }else {
                urlError.set(0);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public boolean isDataValid() {
        int name = nameError.get();
        int url = urlError.get();
        return nameError.get() <= 0 && urlError.get() <= 0;
    }
}
