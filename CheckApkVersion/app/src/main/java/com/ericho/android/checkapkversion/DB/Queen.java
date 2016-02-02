package com.ericho.android.checkapkversion.DB;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.ForeignKeyReference;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by EricH on 26/11/2015.
 */
@Table(databaseName = ColonyDatabase.NAME)
public class Queen extends BaseModel {

    @Column
    @PrimaryKey(autoincrement = true)
    long id;

    @Column
    String name;

    @Column
    @ForeignKey(
            references = {@ForeignKeyReference(columnName = "colony_id",
                    columnType = Long.class,
                    foreignColumnName = "id")},
            saveForeignKeyModel = false)
    Colony colony;
}