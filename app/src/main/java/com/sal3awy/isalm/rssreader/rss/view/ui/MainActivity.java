package com.sal3awy.isalm.rssreader.rss.view.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.sal3awy.isalm.rssreader.R;
import com.sal3awy.isalm.rssreader.databinding.ActivityMainBinding;
import com.sal3awy.isalm.rssreader.rss.model.entities.Provider;
import com.sal3awy.isalm.rssreader.rss.view.callback.Navigator;

public class MainActivity extends AppCompatActivity implements Navigator {

    private ActivityMainBinding binding;
    private ProvidersFragment providersFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        providersFragment = new ProvidersFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.fragmentRss, providersFragment, ProvidersFragment.class.getName()).commit();

    }


    @Override
    public void openArticlesFragment(Provider provider) {
        ArticlesFragment articlesFragment = ArticlesFragment.newInstance(provider.getRssLink());
        if (!articlesFragment.isAdded()) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack(ArticlesFragment.class.getName())
                    .add(R.id.fragmentRss,
                            articlesFragment).commit();
        }
    }
}
