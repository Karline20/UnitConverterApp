package coding.legaspi.unitconverterapp.data.repositoryImpl

import coding.legaspi.unitconverterapp.data.ConversionResult
import coding.legaspi.unitconverterapp.data.dao.ConverterDAO
import coding.legaspi.unitconverterapp.data.repository.ConverterRepository
import kotlinx.coroutines.flow.Flow

class ConverterRepositoryImpl(private val converterDao : ConverterDAO) : ConverterRepository {

    override suspend fun insertResult(converterResult: ConversionResult) {
        converterDao.insertResult(converterResult)
    }

    override suspend fun deleteResult(converterResult: ConversionResult) {
        converterDao.deleteResult(converterResult)
    }

    override suspend fun deleteAll() {
        converterDao.deleteAll()
    }

    override fun getResult(): Flow<List<ConversionResult>> {
        return converterDao.getResult()
    }
}