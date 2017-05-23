package kh.edu.rupp.fe.ruppmad.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import kh.edu.rupp.fe.ruppmad.adapter.Document;

/**
 * RUPPMAD
 * Created by leapkh on 5/2/17.
 */

public class DbManager extends SQLiteOpenHelper {


    public DbManager(Context context){
        super(context, "ruppmad.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d("rupp", "DB onCreate");
        String tblDocumentSql = "create table tblDocument" +
                "(_id integer primary key autoincrement, " +
                "_title text, _thumbnail_url text, _size integer)";
        sqLiteDatabase.execSQL(tblDocumentSql);

        // Insert default documents
        /*
        Document d1 = new Document(1, "Ch. 1: Introduction to Mobile App Dev", "", 1);
        insertDocument(d1, sqLiteDatabase);
        Document d2 = new Document(2, "Ch. 2: Google Play Store", "", 2);
        insertDocument(d2, sqLiteDatabase);
        Document d3 = new Document(3, "Ch. 3: Android Studio", "", 1);
        insertDocument(d3, sqLiteDatabase);
        Document d4 = new Document(4, "Ch. 4: Drawer Layout", "", 3);
        insertDocument(d4, sqLiteDatabase);
        Document d5 = new Document(5, "Ch. 5: Recycler View", "", 2);
        insertDocument(d5, sqLiteDatabase);
        */

        createLoginHistoryTable(sqLiteDatabase);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        createLoginHistoryTable(sqLiteDatabase);
    }

    private void createLoginHistoryTable(SQLiteDatabase sqLiteDatabase){
        //Create table login history
        String tblLoginHistorySql = "create table tblLoginHistory" +
                "(_id integer primary key autoincrement, _time integer)";
        sqLiteDatabase.execSQL(tblLoginHistorySql);
    }

    public boolean insertDocument(Document document, SQLiteDatabase db) {
        ContentValues row = new ContentValues();
        row.put("_title", document.getTitle());
        row.put("_thumbnail_url", document.getThumbnailUrl());
        row.put("_size", document.getSize());
        long newId = db.insert("tblDocument", null, row);
        return (newId > -1);
    }

    public boolean insertDocument(Document document) {
        return insertDocument(document, getWritableDatabase());
    }

    public Document[] getAllDocuments() {
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {"_id", "_title", "_thumbnail_url", "_size"};
        Cursor cursor = db.query("tblDocument", columns, null, null, null, null, "_id desc");
        Document[] documents = new Document[cursor.getCount()];
        int i = 0;
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            String thumbnailUrl = cursor.getString(2);
            int size = cursor.getInt(3);
            Document document = new Document(id, title, thumbnailUrl, size, 0);
            documents[i] = document;
            i++;
        }
        return documents;
    }

    public void insertLoginHistory(long timeStamp){
        ContentValues row = new ContentValues();
        row.put("_time", timeStamp);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("tblLoginHistory", null, row);
    }

    public long[] getAllLoginHistory(){
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {"_time"};
        Cursor cursor = db.query("tblLoginHistory", columns, null, null, null, null, "_id desc");
        long[] times = new long[cursor.getCount()];
        int index = 0;
        while (cursor.moveToNext()){
            long time = cursor.getLong(0);
            times[index] = time;
            index++;
        }
        return times;
    }

}
