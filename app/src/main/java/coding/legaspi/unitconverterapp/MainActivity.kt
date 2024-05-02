package coding.legaspi.unitconverterapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import coding.legaspi.unitconverterapp.data.database.ConvertedDatabase
import coding.legaspi.unitconverterapp.data.repository.ConverterRepository
import coding.legaspi.unitconverterapp.data.repositoryImpl.ConverterRepositoryImpl
import coding.legaspi.unitconverterapp.screen.base.BaseScreen
import coding.legaspi.unitconverterapp.ui.theme.UnitConverterAppTheme
import coding.legaspi.unitconverterapp.utils.ConverterViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val converterDAO = ConvertedDatabase.getInstance(application).converterDAO
        val repository= ConverterRepositoryImpl(converterDAO)
        val factory = ConverterViewModelFactory(repository)

        setContent {
            UnitConverterAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BaseScreen(factory)
                }
            }
        }

    }
}

