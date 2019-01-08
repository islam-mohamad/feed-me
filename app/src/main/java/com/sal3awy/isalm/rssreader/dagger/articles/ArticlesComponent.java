package com.sal3awy.isalm.rssreader.dagger.articles;

import com.sal3awy.isalm.rssreader.dagger.application.ApplicationComponent;
import com.sal3awy.isalm.rssreader.rss.view.ui.ArticlesFragment;

import dagger.Component;

@ArticlesFragmentScope
@Component(modules = ArticlesModule.class, dependencies = ApplicationComponent.class)
public interface ArticlesComponent {
    void inject(ArticlesFragment articlesFragment);
}
