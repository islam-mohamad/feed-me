package com.sal3awy.isalm.rssreader.dagger.providers;

import com.sal3awy.isalm.rssreader.dagger.application.ApplicationComponent;
import com.sal3awy.isalm.rssreader.rss.view.ui.ProvidersFragment;

import dagger.Component;

@ProvidersFragmentScope
@Component(modules = ProvidersModule.class, dependencies = ApplicationComponent.class)
public interface ProvidersComponent {
    void inject(ProvidersFragment providersFragment);
}
