package com.example.dasarkotlin

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

var DATABASE_NAME = "MyDB"
var TABLE_NAME = "Produk"
var COL_ID = "id"
var COL_NAMA = "nama"
var COL_STOK = "stok"

class DatabaseHandler (var context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, 1){
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NAMA + " VARCHAR(256), " +
                COL_STOK + " INTEGER)"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun insertData(produk : Produk){
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COL_NAMA, produk.nama)
        cv.put(COL_STOK, produk.stok)
        val result = db.insert(TABLE_NAME, null, cv)
        if(result == -1.toLong())
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "SUccess", Toast.LENGTH_SHORT).show()
    }

    fun readData(): MutableList<Produk>{
        var list: MutableList<Produk> = ArrayList()
        var db = this.readableDatabase
        var query = "Select * from " + TABLE_NAME
        var result = db.rawQuery(query, null)

        if(result.moveToFirst()){
            do{
                var produk = Produk()
                produk.id = result.getString(result.getColumnIndex(COL_ID)).toInt()
                produk.nama = result.getString(result.getColumnIndex(COL_NAMA))
                produk.stok = result.getString(result.getColumnIndex(COL_STOK)).toInt()
                list.add(produk)
            } while (result.moveToNext())
        }

        result.close()
        db.close()
        return list
    }

    fun deleteData(){
        val db = this.writableDatabase
        db.delete(TABLE_NAME, null, null)
        db.close()
    }

    fun updateData(){
        var db = this.writableDatabase
        var query = "Select * from " + TABLE_NAME
        var result = db.rawQuery(query, null)

        if(result.moveToFirst()){
            do{
                val cv = ContentValues()
                cv.put(COL_STOK, result.getInt(result.getColumnIndex(COL_STOK)) + 1)
                db.update(TABLE_NAME, cv, COL_ID + "=? AND " + COL_NAMA + "=?",
                arrayOf(result.getString(result.getColumnIndex(COL_ID)),
                    result.getString(result.getColumnIndex(COL_NAMA))))
            } while (result.moveToNext())
        }

        result.close()
        db.close()
    }

}