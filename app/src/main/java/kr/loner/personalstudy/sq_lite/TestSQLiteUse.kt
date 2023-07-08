package kr.loner.personalstudy.sq_lite

import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns

class TestSQLiteUse(context:Context){
    private val db = FeedReaderDbHelper(context)

    fun writeDbTest(){
        val writeDb = this.db.writableDatabase
        val values = ContentValues().apply {
            put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, "title")
            put(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE, "subtitle")
        }

        writeDb?.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values)
    }

    fun readDbTest(){
        val readDb = this.db.readableDatabase
        val projection = arrayOf(BaseColumns._ID, FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE)

        val selection = "${FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE} = ?"
        val selectionArgs = arrayOf("My Title")

        val sortOrder = "${FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE} DESC"

        val cursor = readDb.query(
            FeedReaderContract.FeedEntry.TABLE_NAME,   // 테이블 이름
            projection,             // The array of columns to return (pass null to get all)
            selection,              // The columns for the WHERE clause
            selectionArgs,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            sortOrder               // The sort order
        )

        val itemIds = mutableListOf<Long>()
        with(cursor) {
            while (moveToNext()) {
                val itemId = getLong(getColumnIndexOrThrow(BaseColumns._ID))
                itemIds.add(itemId)
            }
        }
        cursor.close()
    }
}