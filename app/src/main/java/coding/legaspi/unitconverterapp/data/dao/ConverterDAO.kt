package coding.legaspi.unitconverterapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import coding.legaspi.unitconverterapp.data.ConversionResult
import kotlinx.coroutines.flow.Flow

@Dao
interface ConverterDAO {

    @Insert
    suspend fun insertResult(conversionResult : ConversionResult)
    @Delete
    suspend fun deleteResult(conversionResult: ConversionResult)
    @Query("DELETE FROM result_table")
    suspend fun deleteAll()
    @Update
    suspend fun updateResult(conversionResult: ConversionResult)
    @Query("SELECT * FROM result_table")
    fun getResult(): Flow<List<ConversionResult>>


}