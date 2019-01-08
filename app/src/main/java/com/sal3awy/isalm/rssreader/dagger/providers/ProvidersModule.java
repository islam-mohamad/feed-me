package com.sal3awy.isalm.rssreader.dagger.providers;

import android.arch.lifecycle.ViewModelProviders;

import com.sal3awy.isalm.rssreader.rss.model.local.ProvidersDao;
import com.sal3awy.isalm.rssreader.rss.model.local.RSSDatabase;
import com.sal3awy.isalm.rssreader.rss.model.remote.RssService;
import com.sal3awy.isalm.rssreader.rss.view.ui.MainActivity;
import com.sal3awy.isalm.rssreader.rss.viewmodel.ProvidersViewModel;
import com.sal3awy.isalm.rssreader.rss.viewmodel.ProvidersViewModelFactory;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class ProvidersModule {

    private final MainActivity mainActivity;

    public ProvidersModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Provides
    @ProvidersFragmentScope
    public ProvidersViewModel providersViewModel(MainActivity mainActivity, ProvidersViewModelFactory factory){
        return ViewModelProviders.of(mainActivity, factory).get(ProvidersViewModel.class);
    }

    @Provides
    @ProvidersFragmentScope
    public MainActivity mainActivity() {
        return mainActivity;
    }

    @Provides
    @ProvidersFragmentScope
    public RssService rssService(Retrofit retrofit){
        return retrofit.create(RssService.class);
    }

    @Provides
    @ProvidersFragmentScope
    public ProvidersDao providersDao(MainActivity mainActivity){
        return RSSDatabase.getInstance(mainActivity).providersDao();
    }
}
