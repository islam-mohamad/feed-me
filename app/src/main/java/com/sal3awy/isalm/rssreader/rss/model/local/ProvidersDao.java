package com.sal3awy.isalm.rssreader.rss.model.local;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.sal3awy.isalm.rssreader.rss.model.entities.Provider;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface ProvidersDao {

    @Insert(onConflict = REPLACE)
    long saveProvider(Provider provider);

    @Insert(onConflict = REPLACE)
    List<Long> saveProvidersList(List<Provider> providers);

    @Delete
    int deleteProvider(Provider provider);

    @Query("SELECT * FROM Provider")
    Flowable<List<Provider>> getProviders();
}