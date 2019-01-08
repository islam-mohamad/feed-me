package com.sal3awy.isalm.rssreader.dagger.articles;

import android.arch.lifecycle.ViewModelProviders;

import com.sal3awy.isalm.rssreader.rss.model.local.ArticlesDao;
import com.sal3awy.isalm.rssreader.rss.model.local.RSSDatabase;
import com.sal3awy.isalm.rssreader.rss.model.remote.RssService;
import com.sal3awy.isalm.rssreader.rss.view.ui.MainActivity;
import com.sal3awy.isalm.rssreader.rss.viewmodel.ArticlesViewModel;
import com.sal3awy.isalm.rssreader.rss.viewmodel.ArticlesViewModelFactory;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class ArticlesModule {
    private final MainActivity mainActivity;

    public ArticlesModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Provides
    @ArticlesFragmentScope
    public ArticlesViewModel articlesViewModel(MainActivity mainActivity, ArticlesViewModelFactory factory) {
        return ViewModelProviders.of(mainActivity, factory).get(ArticlesViewModel.class);
    }

    @Provides
    @ArticlesFragmentScope
    public MainActivity mainActivity() {
        return mainActivity;
    }

    @Provides
    @ArticlesFragmentScope
    public RssService rssService(Retrofit retrofit) {
        return retrofit.create(RssService.class);
    }

    @Provides
    @ArticlesFragmentScope
    public ArticlesDao articlesDao(MainActivity mainActivity) {
        return RSSDatabase.getInstance(mainActivity).articlesDao();
    }
}
