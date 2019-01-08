package com.sal3awy.isalm.rssreader.rss.view.callback;

import com.sal3awy.isalm.rssreader.databinding.ClickCallback;
import com.sal3awy.isalm.rssreader.rss.model.entities.Article;

public interface ArticlesCallback extends ClickCallback {
    void onArticleClicked(Article article);
}
