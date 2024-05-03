package coding.legaspi.unitconverterapp.screen.converter

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import coding.legaspi.unitconverterapp.data.Conversion
import java.math.RoundingMode
import java.text.DecimalFormat

/**
 * Created by Karlen Legaspi
 */

@Composable
fun TopScreen(
    list: List<Conversion>,
    selectedConversion : MutableState<Conversion?>,
    inputText : MutableState<String>,
    typedValue : MutableState<String>,
    isLandscape : Boolean,
    save : (String,String) -> Unit
){

//    val selectedConversion : MutableState<Conversion?> = remember {
//        mutableStateOf(null)
//    }
//
//    val inputText : MutableState<String> = remember {
//        mutableStateOf("")
//    }
//
//    val typedValue = remember {
//        mutableStateOf("0.0")
//    }

    var toSave by remember { mutableStateOf(false) }

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {

    ConversionMenu(list = list, isLandscape){
        selectedConversion.value = it
        typedValue.value = "0.0"
    }

    selectedConversion.value?.let {
        InputBlock(conversion = it, inputText = inputText, isLandscape){input->
            Log.i("MYTAG", "User typed $input")
            typedValue.value = input
            toSave = true
        }
    }

    if (typedValue.value != "0.0"){
        val input = typedValue.value.toDouble()
        val multiply = selectedConversion.value!!.multiplyBy
        val result = input*multiply

        //rounding off the result to 4 decimal places
        val df = DecimalFormat("#.####")
        df.roundingMode = RoundingMode.DOWN
        val roundedResult = df.format(result)

        val message1 = "${typedValue.value} ${selectedConversion.value!!.convertFrom} is equal to"
        val message2 = "$roundedResult ${selectedConversion.value!!.convertTo}"
        if (toSave ){
            save(message1,message2)
            toSave = false
        }
        ResultBlock(message1 = message1, message2 = message2)
    }
    }

}