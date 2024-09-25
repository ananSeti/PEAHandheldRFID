package com.anan.peahandheld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anan.peahandheld.ui.theme.PEAHandheldRFIDTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PEAHandheldRFIDTheme {
               Surface(
                        modifier = Modifier.fillMaxSize(),
                    ){
                   RFIDLayout()
               }
            }
        }
    }
}
@Composable
fun RFIDLayout(){
    val image = painterResource(R.drawable.bg_compose_background)
    var barcodeInput by remember { mutableStateOf("1060050001|1,6200015451|6200015451|||4") }
    var barcode = barcodeInput.toString()
    var rfidInput by remember { mutableStateOf("0") }
    val rfid = convertBarcodeToRFID(barcode)
    Column (
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 16.dp)
            .safeDrawingPadding()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
       // verticalArrangement = Arrangement.Center
    ) {
        Image (
            painter = image,
            contentDescription = null
        )
        Text(
            text = stringResource(R.string.Title),
            modifier = Modifier
                .padding(bottom = 16.dp, top = 16.dp)
                .align(alignment = Alignment.Start)
        )

        Text(
            text = stringResource(R.string.barcodeData),
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(alignment = Alignment.Start)
        )
        EditBarcode(
            value = barcodeInput,
            onValueChange = { barcodeInput = it },
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
        )
        //Spacer(modifier = Modifier.height(16.dp))
        EditRFID(
            value = rfid,
            onValueChange = { rfidInput = it },
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
        )
       // Spacer(modifier = Modifier.height(40.dp))
        Row {

            Button(onClick = { /*TODO*/ }) {
                Text(text = "Read Barcode")
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Write RFID")
            }
        }
    }
}
@Composable
fun EditBarcode(
    value: String = "",
    onValueChange: (String) -> Unit = {},
    modifier: Modifier = Modifier
){
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(stringResource(R.string.barcodeData)) },
        modifier = modifier.fillMaxWidth(),
        singleLine = false,
    )
}
@Composable
fun EditRFID(
    value: String = "",
    onValueChange: (String) -> Unit = {},
    modifier: Modifier = Modifier
){
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(stringResource(R.string.RFIDData))},
        modifier = modifier.fillMaxWidth(),
        singleLine = false,
    )

}
@OptIn(ExperimentalStdlibApi::class)
private  fun convertBarcodeToRFID(
    barcode: String

):String
{
    var rfid = barcode
    var epc: String
    val epcanduseer = rfid.split(",").toTypedArray()
    epc = epcanduseer[0].toString()
    //epc ="1060050001|1"
    var toB = epc.toByteArray().toHexString().padEnd(32,'0')
    return toB
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun RFIDLayoutPreview() {
    PEAHandheldRFIDTheme {
        RFIDLayout()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PEAHandheldRFIDTheme {
        Greeting("Android")
    }
}