package com.sal3awy.isalm.rssreader.rss.model;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.sal3awy.isalm.rssreader.rss.model.entities.Article;
import com.sal3awy.isalm.rssreader.rss.model.entities.Provider;
import com.sal3awy.isalm.rssreader.rss.model.local.ArticlesDao;
import com.sal3awy.isalm.rssreader.rss.model.remote.RssService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import me.toptas.rssconverter.RssFeed;
import me.toptas.rssconverter.RssItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticlesRepo {
    private RssService rssService;
    private ArticlesDao articlesDao;
    private Executor executor;

    @Inject
    public ArticlesRepo(RssService rssService, @Nullable ArticlesDao articlesDao, Executor executor) {
        this.rssService = rssService;
        this.articlesDao = articlesDao;
        this.executor = executor;

    }


    public LiveData<List<Article>> getArticles(String providerLink) {
        rssService.getRss(providerLink).enqueue(new Callback<RssFeed>() {
            @Override
            public void onResponse(@NonNull Call<RssFeed> call, @NonNull Response<RssFeed> response) {
                if (response.isSuccessful()) {
                    List<Article> articles = new ArrayList<>();
                    for (RssItem rssItem : response.body().getItems()) {
                        Article article = new Article(rssItem, providerLink);
                        articles.add(article);
                    }
                    executor.execute(() -> articlesDao.saveArticle(articles));
                }
            }

            @Override
            public void onFailure(@NonNull Call<RssFeed> call, @NonNull Throwable t) {
                t.getStackTrace();
            }
        });


        return articlesDao.getArticles(providerLink);
    }
}
