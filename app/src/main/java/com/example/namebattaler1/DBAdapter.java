package com.example.namebattaler1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DBAdapter {
    private final static String DB_NAME = "ppp";
    private final static String CHARACTERS = "CHARACTERS";
    private final static int DB_VERSION = 1;

    private final static String ID = "id";
    private final static String NAME = "name";
    private final static String JOB = "job";
    private final static String HP = "hp";
    private final static String MP = "mp";
    private final static String STR = "str";
    private final static String DEF = "def";
    private final static String AGI = "agi";
    private final static String LUCK = "luck";
    private final static String CREATE_AT = "create_at";

    private SQLiteDatabase db = null;
    private DBHelper dbHelper;
    protected Context context;

    DBAdapter(Context context){
        this.context = context;
        dbHelper = new DBHelper(this.context);
    }

    public DBAdapter openDB(){
        db = dbHelper.getWritableDatabase();
        return this;
    }

    void closeDB(){
        db.close();
        db = null;
    }


    void saveDB(String name, int job, int hp, int mp, int str, int def, int agi, int luck, int create_at){

        db.beginTransaction();

        try{
            ContentValues values = new ContentValues();
            values.put(NAME, name);
            values.put(JOB, job);
            values.put(HP, hp);
            values.put(MP, mp);
            values.put(STR, str);
            values.put(DEF, def);
            values.put(AGI, agi);
            values.put(LUCK, luck);
            values.put(CREATE_AT, create_at);

            db.insert(CHARACTERS, null, values);

            db.setTransactionSuccessful();

        } catch(Exception e){
            e.printStackTrace();

        } finally{
            db.endTransaction();
        }
    }

    Cursor getDB(String[] columns){
        return db.query(CHARACTERS, columns, null, null, null, null, null);
    }

    void selectDelete(String position){
        db.beginTransaction();
        try{
            db.delete(CHARACTERS, NAME + "=?", new String[]{position});
            db.setTransactionSuccessful();
        } catch(Exception e){
            e.printStackTrace();
        } finally{
            db.endTransaction();
        }
    }

    private static class DBHelper extends SQLiteOpenHelper {

        DBHelper(Context context){

            super(context, DB_NAME, null, DB_VERSION);//
        }

        @Override
        public void onCreate(SQLiteDatabase db){
            String createTbl = "CREATE TABLE " + CHARACTERS + " ("
                    + ID + "INTEGER NOT NULL,"
                    + NAME + " TEXT NOT NULL,"
                    + JOB + " INTEGER NOT NULL,"
                    + HP + " INTEGER NOT NULL,"
                    + MP + " INTEGER NOT NULL,"
                    + STR + " INTEGER NOT NULL,"
                    + DEF + " INTEGER NOT NULL,"
                    + AGI + " INTEGER NOT NULL,"
                    + LUCK + " INTEGER NOT NULL,"
                    + CREATE_AT + " INTEGER NOT NULL"
                    + ");";
            db.execSQL(createTbl);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            db.execSQL("DROP TABLE IF EXISTS " + CHARACTERS);
            onCreate(db);
        }
    }


}
