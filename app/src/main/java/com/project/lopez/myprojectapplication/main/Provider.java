package com.project.lopez.myprojectapplication.main;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
/**
 * Created by Administrator on 7/2/2015.
 */
public class Provider extends ContentProvider {

    private SQLiteDatabase db;

    static final String PROVIDER_NAME = "com.example.item.Provider";
    static final String URL = "content://" + PROVIDER_NAME + "/item";
    static final Uri CONTENT_URI = Uri.parse(URL);
    static final UriMatcher uriMatch;
    static final int uriItem=1;

    static
    {
        uriMatch = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatch.addURI(PROVIDER_NAME, "item", uriItem);
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        DbHelper data = new DbHelper(context);
        db = data.getWritableDatabase();
        if (db !=null)
        {
            return true;
        }
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        Cursor cursor = null;

        switch (uriMatch.match(uri)) {
            case uriItem:
                qb.setTables(DbHelper.TABLE_NAME);
                cursor = qb.query(db, null, null, null, null, null,
                        null);
                break;

            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }


        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;

    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        switch (uriMatch.match(uri))
        {

            case uriItem:
                long ID = db.insert(DbHelper.TABLE_NAME,null,values);

                if (ID>0)
                {
                    Uri _uri = ContentUris.withAppendedId(CONTENT_URI, ID);
                    getContext().getContentResolver().notifyChange(_uri, null);
                    return _uri;
                }
                break;


            default:
                break;
        }

        throw new SQLException("Task is not added"+ uri);

    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
