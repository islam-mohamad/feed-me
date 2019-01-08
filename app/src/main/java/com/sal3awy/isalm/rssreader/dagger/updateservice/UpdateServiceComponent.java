package com.sal3awy.isalm.rssreader.dagger.updateservice;

import com.sal3awy.isalm.rssreader.UpdateDatabaseService;
import com.sal3awy.isalm.rssreader.dagger.application.ApplicationComponent;

import dagger.Component;

@UpdateServiceScope
@Component(modules = UpdateServiceModule.class, dependencies = ApplicationComponent.class)
public interface UpdateServiceComponent {
    void inject(UpdateDatabaseService updateDatabaseService);
}
