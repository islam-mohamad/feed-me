package com.sal3awy.isalm.rssreader.rss.view.callback;

import com.sal3awy.isalm.rssreader.databinding.ClickCallback;
import com.sal3awy.isalm.rssreader.rss.model.entities.Provider;

public interface ProvidersCallback extends ClickCallback {
    void onDeleteProviderClicked(Provider provider);
    void onProviderClicked(Provider provider);
}
