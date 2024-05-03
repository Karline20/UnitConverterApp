package coding.legaspi.unitconverterapp.screen.base

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coding.legaspi.unitconverterapp.screen.converter.TopScreen
import coding.legaspi.unitconverterapp.screen.history.HistoryScreen
import coding.legaspi.unitconverterapp.utils.ConverterViewModel
import coding.legaspi.unitconverterapp.utils.ConverterViewModelFactory

/**
 * Created by Karlen Legaspi
 */

@Composable
fun BaseScreen(
    factory: ConverterViewModelFactory,
    modifier: Modifier = Modifier,
    converterViewModel: ConverterViewModel = viewModel(factory = factory)
){

    val list = converterViewModel.getConversion()
    val historyList = converterViewModel.resultList
        .collectAsState(initial = emptyList())
    
    val configuration = LocalConfiguration.current
    var isLandScape = remember{ mutableStateOf(false) }

    when(configuration.orientation){
        Configuration.ORIENTATION_LANDSCAPE -> {
            isLandScape.value = true
            Row(
                modifier = modifier.padding(30.dp).fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                TopScreen(list,
                    converterViewModel.selectedConversion,
                    converterViewModel.inputText,
                    converterViewModel.typedValue,
                    isLandScape.value
                ){ message1, message2 ->
                    converterViewModel.addResult(message1, message2)
                }
                Spacer(modifier = modifier.width(10.dp))

                HistoryScreen(
                    historyList, { item ->
                        converterViewModel.removeResult(item)
                    },
                    {
                        converterViewModel.clearAll()
                    }
                )
                Spacer(modifier = modifier.height(30.dp))
            }
        }else -> {
            isLandScape.value = false
            Column(modifier = modifier.padding(30.dp)) {
                TopScreen(list,
                    converterViewModel.selectedConversion,
                    converterViewModel.inputText,
                    converterViewModel.typedValue,
                    isLandScape.value
                ){ message1, message2 ->
                    converterViewModel.addResult(message1, message2)
                }
                Spacer(modifier = modifier.height(30.dp))

                HistoryScreen(
                    historyList, { item ->
                        converterViewModel.removeResult(item)
                    },
                    {
                        converterViewModel.clearAll()
                    }
                )
                Spacer(modifier = modifier.height(30.dp))
            }
        }
    }
}