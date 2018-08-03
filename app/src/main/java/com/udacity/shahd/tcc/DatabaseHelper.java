package com.udacity.shahd.tcc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "containers_db";
    public  List<Container> containers = new ArrayList<>();


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        // create notes table
        db.execSQL(Container.CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Container.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public long insertContainer( String lont, String lat, String region, String containerName, int status) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(Container.COLUMN_NAME, containerName);
        values.put(Container.COLUMN_LAT, lat);
        values.put(Container.COLUMN_LONT, lont);
        values.put(Container.COLUMN_REGION, region);
        values.put(Container.COLUMN_STATUS, status);

        // insert row
        long id = db.insert(Container.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public Container getContainer(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Container.TABLE_NAME,
                new String[]{Container.COLUMN_ID, Container.COLUMN_NAME,Container.COLUMN_LAT, Container.COLUMN_LONT, Container.COLUMN_REGION,Container.COLUMN_STATUS},
                Container.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        Container container = new Container(
                cursor.getInt(cursor.getColumnIndex(Container.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(Container.COLUMN_NAME)),
                cursor.getString(cursor.getColumnIndex(Container.COLUMN_LAT)),
                cursor.getString(cursor.getColumnIndex(Container.COLUMN_LONT)),
                cursor.getString(cursor.getColumnIndex(Container.COLUMN_REGION)),
                cursor.getInt(cursor.getColumnIndex(Container.COLUMN_STATUS)));

        // close the db connection
        cursor.close();

        return container;
    }

    public List<Container> getAllContainers() {

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Container.TABLE_NAME + "";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Container container = new Container();
                container.setId(cursor.getInt(cursor.getColumnIndex(Container.COLUMN_ID)));
                container.setStatus(cursor.getInt(cursor.getColumnIndex(Container.COLUMN_STATUS)));
                container.setContainerName(cursor.getString(cursor.getColumnIndex(Container.COLUMN_NAME)));
                container.setLat(cursor.getString(cursor.getColumnIndex(Container.COLUMN_LAT)));
                container.setLont(cursor.getString(cursor.getColumnIndex(Container.COLUMN_LONT)));
                container.setRegion(cursor.getString(cursor.getColumnIndex(Container.COLUMN_REGION)));


                containers.add(container);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return containers;
    }

    public int getContainersCount() {
        String countQuery = "SELECT  * FROM " + Container.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }

    public int updateContainer(Container container) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Container.COLUMN_STATUS, container.getStatus());

        // updating row
        return db.update(Container.TABLE_NAME, values, Container.COLUMN_ID + " = ?",
                new String[]{String.valueOf(container.getId())});
    }

    public void deleteContainer(Container container) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Container.TABLE_NAME, container.COLUMN_ID + " = ?",
                new String[]{String.valueOf(container.getId())});
        db.close();
    }
}