package coding.legaspi.unitconverterapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import coding.legaspi.unitconverterapp.data.ConversionResult
import coding.legaspi.unitconverterapp.data.dao.ConverterDAO

@Database(entities = [ConversionResult::class], version = 1)
abstract class ConvertedDatabase : RoomDatabase() {

    abstract val converterDAO : ConverterDAO

    companion object{
        @Volatile
        private var INSTANCE : ConvertedDatabase? = null
        fun getInstance(context: Context): ConvertedDatabase{
            synchronized(context){
                var instance = INSTANCE
                if (instance==null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ConvertedDatabase::class.java,
                        "converter_data_database"
                    ).build()
                }
                return instance
            }
        }
    }
}