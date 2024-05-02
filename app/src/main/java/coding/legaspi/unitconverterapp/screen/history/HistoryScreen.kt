package coding.legaspi.unitconverterapp.screen.history

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import coding.legaspi.unitconverterapp.data.ConversionResult

/**
 * Created by Karlen Legaspi
 */

@Composable
fun HistoryScreen(
    list: State<List<ConversionResult>>,
    modifier : Modifier = Modifier
){
    HistoryList(
        list = list, onCloseTask = {

    })
}