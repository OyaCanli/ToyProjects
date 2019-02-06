package com.enpassio.roomwithaviewcodelab

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Entity(tableName = "words")
class Word(@PrimaryKey @ColumnInfo(name = "word") val word: String)

@Dao
interface WordDao {

    @Query("SELECT * from words ORDER BY word ASC")
    fun getAllWords(): LiveData<List<Word>>
    /*Tip: For this app, ordering the words is not strictly necessary. However,
    by default, order is not guaranteed, and ordering makes testing straightforward.*/

    @Insert
    fun insert(word: Word)
    /*Tip: When inserting data, you can provide a conflict strategy.
    In this codelab, you do not need a conflict strategy, because the word is your primary key, and the default SQL behavior is ABORT, so that you cannot insert two items with the same primary key into the database.
    If the table has more than one column, you can use
    @Insert(onConflict = OnConflictStrategy.REPLACE)*/

    @Query("DELETE FROM words")
    fun deleteAll()
}

@Database(entities = [Word::class], version = 1)
abstract class WordRoomDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao

    companion object {
        @Volatile
        private var INSTANCE: WordRoomDatabase? = null

        fun getDatabase(context: Context): WordRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordRoomDatabase::class.java,
                    "Word_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}