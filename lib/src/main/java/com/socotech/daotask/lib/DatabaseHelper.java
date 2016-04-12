package com.socotech.daotask.lib;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with IntelliJ IDEA.
 * User: marc
 * Date: 11/25/14
 * Time: 10:23 AM
 */
public abstract class DatabaseHelper extends SQLiteOpenHelper {
    /**
     * Keep count of opened database connections
     */
    private AtomicInteger counter = new AtomicInteger();

    /**
     * Default constructor
     *
     * @param context Android context
     */
    public DatabaseHelper(Context context, String name, int version) {
        super(context, name, null, version);
    }

    /**
     * Close database if no outstanding references exist
     *
     * @param db sqlite database
     */
    public void close(SQLiteDatabase db) {
        if (counter.decrementAndGet() == 0) {
            db.close();
        }
    }

    @Override
    public SQLiteDatabase getWritableDatabase() {
        counter.incrementAndGet();
        return super.getWritableDatabase();
    }
}
