package coding.legaspi.unitconverterapp.screen.converter

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import coding.legaspi.unitconverterapp.data.Conversion

/**
 * Created by Karlen Legaspi
 */
@Composable
fun ConversionMenu(list: List<Conversion>, isLandScape : Boolean, modifier: Modifier = Modifier, convert : (Conversion) -> Unit){

    var displayingText by rememberSaveable{ mutableStateOf("Select the conversion type") }
    var textFieldSize by remember { mutableStateOf(Size.Zero) } // To assign the dropdown the same width as textfield

    var expanded by remember { mutableStateOf(false) }

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
        else
        Icons.Filled.KeyboardArrowDown

    //code for the drop down menu item
    Column {
        if (isLandScape){
            OutlinedTextField(
                value = displayingText,
                onValueChange = {displayingText = it},
                textStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                modifier = modifier
                    .onGloballyPositioned { coordination ->
                        textFieldSize = coordination.size.toSize()
                    },
                label = { Text(text = "Conversion type")},
                trailingIcon = {
                    Icon(icon, contentDescription = "icon",
                        modifier.clickable { expanded = !expanded }
                    )

                },
                readOnly = true
            )
        }else{
            OutlinedTextField(
                value = displayingText,
                onValueChange = {displayingText = it},
                textStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                modifier = modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordination ->
                        textFieldSize = coordination.size.toSize()
                    },
                label = { Text(text = "Conversion type")},
                trailingIcon = {
                    Icon(icon, contentDescription = "icon",
                        modifier.clickable { expanded = !expanded }
                    )

                },
                readOnly = true
            )
        }

        DropdownMenu(expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = modifier.width(with(LocalDensity.current){ textFieldSize.width.toDp() })
        )
        {
            list.forEach { conversion ->
                DropdownMenuItem(
                    text = {
                        Text(text = conversion.description, fontSize = 24.sp, fontWeight = FontWeight.Bold) },
                    onClick = {
                        displayingText = conversion.description
                        expanded = false
                        convert(conversion)
                    })
            }
        }

    }
}