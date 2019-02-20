package com.sal3awy.isalm.rssreader.rss.model.local;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.sal3awy.isalm.rssreader.rss.model.entities.Provider;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface ProvidersDao {

    @Insert(onConflict = REPLACE)
    Completable saveProvider(Provider provider);

    @Insert(onConflict = REPLACE)
    Completable saveProvidersList(List<Provider> providers);

    @Delete
    Completable deleteProvider(Provider provider);

    @Query("SELECT * FROM Provider")
    Single<List<Provider>> getProviders();
}