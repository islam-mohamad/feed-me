package com.sal3awy.isalm.rssreader.rss.model.local;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.sal3awy.isalm.rssreader.rss.model.entities.Provider;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface ProvidersDao {

    @Insert(onConflict = REPLACE)
    void saveProvider(Provider provider);

    @Insert(onConflict = REPLACE)
    void saveProvidersList(List<Provider> providers);

    @Delete
    void deleteProvider(Provider provider);

    @Query("SELECT * FROM Provider")
    LiveData<List<Provider>> getProviders();
}