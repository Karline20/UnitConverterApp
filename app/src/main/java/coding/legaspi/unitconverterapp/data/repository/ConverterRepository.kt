package coding.legaspi.unitconverterapp.data.repository

import coding.legaspi.unitconverterapp.data.ConversionResult
import coding.legaspi.unitconverterapp.data.dao.ConverterDAO
import kotlinx.coroutines.flow.Flow

interface ConverterRepository {

    suspend fun insertResult(converterResult: ConversionResult)
    suspend fun deleteResult(converterResult: ConversionResult)
    suspend fun deleteAll(converterResult: ConversionResult)
    fun getResult(): Flow<List<ConversionResult>>

}