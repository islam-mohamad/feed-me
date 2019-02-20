package com.sal3awy.isalm.rssreader.utils;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;
import java.lang.ref.WeakReference;
import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseViewModel extends ViewModel {


    private final ObservableBoolean mIsLoading = new ObservableBoolean(false);


    private CompositeDisposable mCompositeDisposable;

    public BaseViewModel() {
        this.mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    protected void onCleared() {
        mCompositeDisposable.dispose();
        super.onCleared();
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

    public ObservableBoolean getIsLoading() {
        return mIsLoading;
    }

    public void setIsLoading(boolean isLoading) {
        mIsLoading.set(isLoading);
    }
}
