package com.sal3awy.isalm.rssreader.rss.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.sal3awy.isalm.rssreader.rss.model.ArticlesRepo;
import com.sal3awy.isalm.rssreader.rss.model.entities.Article;
import com.sal3awy.isalm.rssreader.utils.BaseViewModel;

import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.functions.Consumer;
import io.reactivex.subscribers.DisposableSubscriber;

public class ArticlesViewModel extends BaseViewModel {
    private ArticlesRepo repo;
    private MutableLiveData<List<Article>> articlesList;

    public ArticlesViewModel(ArticlesRepo repo) {
        getIsLoading().set(true);
        this.repo = repo;
        this.articlesList = new MutableLiveData<>();
    }

    public LiveData<List<Article>> getArticles(String providerLink) {
        getCompositeDisposable().add(repo.getArticles(providerLink).subscribeWith(new DisposableSubscriber<List<Article>>() {
            @Override
            public void onNext(List<Article> articles) {
                getIsLoading().set(false);
                articlesList.setValue(articles);
            }

            @Override
            public void onError(Throwable t) {
                getIsLoading().set(false);
            }

            @Override
            public void onComplete() {
                getIsLoading().set(false);
            }
        }));
        return articlesList;
    }

}