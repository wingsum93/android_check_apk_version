package com.ericho.android.checkapkversion.DB;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by EricH on 26/11/2015.
 * a db model
 */
@Database(name = ColonyDatabase.NAME, version = ColonyDatabase.VERSION)
public class ColonyDatabase {

    public static final String NAME = "Colonies";

    public static final int VERSION = 1;
}
