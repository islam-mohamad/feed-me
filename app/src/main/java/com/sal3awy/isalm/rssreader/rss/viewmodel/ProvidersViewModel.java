package com.sal3awy.isalm.rssreader.rss.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.sal3awy.isalm.rssreader.rss.model.ProvidersRepo;
import com.sal3awy.isalm.rssreader.rss.model.entities.Provider;

import java.util.List;

public class ProvidersViewModel extends ViewModel {
    private ProvidersRepo repo;

    public ProvidersViewModel(ProvidersRepo repo){
        this.repo = repo;
    }


    public LiveData<List<Provider>> getProviders() {
        return repo.getProviders();
    }

    public void deleteProvider(Provider provider) {
        repo.deleteProvider(provider);
    }


    public void addProvider(Provider provider) {
       repo.addProvider(provider);
    }
}
