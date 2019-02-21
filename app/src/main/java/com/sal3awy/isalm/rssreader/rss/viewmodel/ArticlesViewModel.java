package com.sal3awy.isalm.rssreader.rss.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.sal3awy.isalm.rssreader.rss.model.ArticlesRepo;
import com.sal3awy.isalm.rssreader.rss.model.entities.Article;
import com.sal3awy.isalm.rssreader.base.BaseViewModel;

import java.util.List;

import io.reactivex.subscribers.DisposableSubscriber;

public class ArticlesViewModel extends BaseViewModel {
    private ArticlesRepo repo;
    private MutableLiveData<List<Article>> articlesList;

    ArticlesViewModel(ArticlesRepo repo) {
        this.repo = repo;
        this.articlesList = new MutableLiveData<>();
    }

    public LiveData<List<Article>> getArticles(String providerLink) {
        setIsLoading(true);
        getCompositeDisposable().add(repo.getArticles(providerLink)
                .subscribeWith(new DisposableSubscriber<List<Article>>() {
                    @Override
                    public void onNext(List<Article> articles) {
                        StringBuilder titles = new StringBuilder();
                        for(Article article : articles){
                            titles.append(article.getTitle()).append(" \n");
                        }
                        Log.e("articles","size: "+articles.size()+" "+titles.toString());
                        articlesList.setValue(articles);
                    }

                    @Override
                    public void onError(Throwable t) {
                        setIsLoading(false);
                        setErrorMessage(t.getMessage()+"");
                    }

                    @Override
                    public void onComplete() {
                        setIsLoading(false);
                    }
                }));
        return articlesList;
    }

}