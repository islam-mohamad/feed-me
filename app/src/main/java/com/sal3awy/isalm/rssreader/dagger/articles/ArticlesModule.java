package com.sal3awy.isalm.rssreader.dagger.articles;

import android.arch.lifecycle.ViewModelProviders;

import com.sal3awy.isalm.rssreader.rss.model.local.ArticlesDao;
import com.sal3awy.isalm.rssreader.rss.model.local.RSSDatabase;
import com.sal3awy.isalm.rssreader.rss.model.remote.RssService;
import com.sal3awy.isalm.rssreader.rss.view.ui.ArticlesFragment;
import com.sal3awy.isalm.rssreader.rss.viewmodel.ArticlesViewModel;
import com.sal3awy.isalm.rssreader.rss.viewmodel.ArticlesViewModelFactory;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class ArticlesModule {
    private final ArticlesFragment articlesFragment;

    public ArticlesModule(ArticlesFragment articlesFragment) {
        this.articlesFragment = articlesFragment;
    }

    @Provides
    @ArticlesFragmentScope
    public ArticlesViewModel articlesViewModel(ArticlesFragment articlesFragment, ArticlesViewModelFactory factory) {
        return ViewModelProviders.of(articlesFragment, factory).get(ArticlesViewModel.class);
    }

    @Provides
    @ArticlesFragmentScope
    public ArticlesFragment articlesFragment() {
        return articlesFragment;
    }

    @Provides
    @ArticlesFragmentScope
    public RssService rssService(Retrofit retrofit) {
        return retrofit.create(RssService.class);
    }

    @Provides
    @ArticlesFragmentScope
    public ArticlesDao articlesDao(ArticlesFragment articlesFragment) {
        return RSSDatabase.getInstance(articlesFragment.getContext()).articlesDao();
    }
}
