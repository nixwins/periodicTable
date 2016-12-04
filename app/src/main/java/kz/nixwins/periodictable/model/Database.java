package kz.nixwins.periodictable.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by nixwins on 11/23/16.
 */

public class Database extends SQLiteAssetHelper {

    private static final String DATABASE_NAME       = "periodicTable.db";
    private static final int    DATABASE_VERSION    = 1;


    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
    }
}
