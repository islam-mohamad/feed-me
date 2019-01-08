package com.sal3awy.isalm.rssreader.rss.model.remote;

import me.toptas.rssconverter.RssFeed;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface RssService {
    @GET
    Call<RssFeed> getRss(@Url String url);
}
