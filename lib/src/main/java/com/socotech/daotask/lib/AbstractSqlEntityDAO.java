package com.socotech.daotask.lib;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created with IntelliJ IDEA.
 * User: marc
 * Date: 8/29/14
 * Time: 11:27 AM
 */
public abstract class AbstractSqlEntityDAO<F, T> implements SqlEntityDAO<F, T> {
    @Override
    public void addAll(F... arg) {
        DatabaseHelper dh = this.getDatabaseHelper();
        SQLiteDatabase db = dh.getWritableDatabase();
        // wrap in transaction
        db.beginTransaction();
        try {
            for (F f : arg) {
                add(f);
            }
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e(LogTag.DATA, e.getMessage());
        } finally {
            db.endTransaction();
        }
        dh.close(db);
    }

    @Override
    public void updateAll(F... arg) {
        DatabaseHelper dh = this.getDatabaseHelper();
        SQLiteDatabase db = dh.getWritableDatabase();
        // wrap in transaction
        db.beginTransaction();
        try {
            for (F f : arg) {
                update(f);
            }
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e(LogTag.DATA, e.getMessage());
        } finally {
            db.endTransaction();
        }
        dh.close(db);
    }
}
