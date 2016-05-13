package com.noiztezk.pr13.db;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by hafizhhabiby on 5/5/16.
 */
@Database(name = DzikirDatabase.NAME, version = DzikirDatabase.VERSION
        , foreignKeysSupported = true)
public class DzikirDatabase {
    public static final String NAME = "DzikirDatabase";

    public static final int VERSION = 1;
}
