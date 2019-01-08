package com.sal3awy.isalm.rssreader.rss.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.sal3awy.isalm.rssreader.rss.model.ArticlesRepo;
import com.sal3awy.isalm.rssreader.rss.model.entities.Article;

import java.util.List;

public class ArticlesViewModel extends ViewModel {
    private ArticlesRepo repo;

    public ArticlesViewModel(ArticlesRepo repo) {
        this.repo = repo;
    }

    public LiveData<List<Article>> getArticles(String providerLink) {
        return repo.getArticles(providerLink);
    }

}