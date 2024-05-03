package coding.legaspi.unitconverterapp.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import coding.legaspi.unitconverterapp.data.repository.ConverterRepository
import javax.inject.Inject

class ConverterViewModelFactory @Inject constructor(private val converterRepository: ConverterRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = ConverterViewModel(converterRepository) as T
}