package coding.legaspi.unitconverterapp.screen.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coding.legaspi.unitconverterapp.data.ConversionResult
import coding.legaspi.unitconverterapp.ui.theme.UnitConverterAppTheme

/**
 * Created by Karlen Legaspi
 */

@Composable
fun HistoryScreen(
    list: State<List<ConversionResult>>,
    onClickTask : (ConversionResult) -> Unit,
    onClearAllTask : () -> Unit,
    modifier : Modifier = Modifier
){
    Column {
        if ((list.value).isNotEmpty()){
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = "History",
                    color = Color.Gray
                )
                OutlinedButton(
                    onClick = {
                        onClearAllTask()
                    },
                    //shape = CutCornerShape(10.dp),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = "Clear All",
                        color = Color.Gray
                    )
                }
            }
        }
        HistoryList(
            list = list,
            onCloseTask = {item ->
                onClickTask(item)
            }
        )
    }

}
