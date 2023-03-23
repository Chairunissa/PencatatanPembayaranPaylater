package unpas.ac.id.pencatatanpembayaranpaylater.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.benasher44.uuid.uuid4
import kotlinx.coroutines.launch
import unpas.ac.id.pencatatanpembayaranpaylater.persistences.PencatatanPembayaranPaylaterDao
import unpas.ac.id.pencatatanpembayaranpaylater.model.PencatatanPembayaranPaylater
import unpas.ac.id.pencatatanpembayaranpaylater.ui.theme.Purple700
import unpas.ac.id.pencatatanpembayaranpaylater.ui.theme.Teal200

@Composable
fun FormPencatatanPembayaranPaylater(pencatatanPembayaranPaylaterDao: PencatatanPembayaranPaylaterDao) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val tglpembyrn = remember { mutableStateOf(TextFieldValue("")) }
    val ttlpembyrn = remember { mutableStateOf(TextFieldValue("")) }
    val mtodpembyrn = remember { mutableStateOf(TextFieldValue("")) }
    val wktupesanan = remember { mutableStateOf(TextFieldValue("")) }
    val kata = "Data Pencatatan Pembayaran Paylater"

    println(kata)
    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()) {
        Text(text = "Daftar Pencatatan Pembayaran Paylater", style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
            ,modifier = Modifier.padding(2.dp))
        OutlinedTextField(
            label = { Text(text = "Tanggal Pembayaran") },
            value = tglpembyrn.value,
            onValueChange = {
                tglpembyrn.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            placeholder = { Text(text = "DD-MM-YYYY") }
        )
        OutlinedTextField(
            label = { Text(text = "Total Pembayaran") },
            value = ttlpembyrn.value,
            onValueChange = {
                ttlpembyrn.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType =
            KeyboardType.Decimal),
            placeholder = { Text(text = "") }
        )
        OutlinedTextField(
            label = { Text(text = "Metode") },
            value = mtodpembyrn.value,
            onValueChange = {
                mtodpembyrn.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            placeholder = { Text(text = "BCA") }
        )
        OutlinedTextField(
            label = { Text(text = "Waktu Pesanan") },
            value = wktupesanan.value,
            onValueChange = {
                wktupesanan.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            placeholder = { Text(text = "DD-MM-YYYY") }
        )
        val loginButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Purple700,
            contentColor = Teal200
        )
        val resetButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Teal200,
            contentColor = Purple700
        )
        Row (modifier = Modifier.padding(4.dp).fillMaxWidth()) {
            Button(modifier = Modifier.weight(5f), onClick = {

                if(tglpembyrn.value.text !== "" && ttlpembyrn.value.text !== ""){
                    if (mtodpembyrn.value.text !== "" && wktupesanan.value.text !== "" ){
                        val id = uuid4().toString()
                        val item = PencatatanPembayaranPaylater(id, tglpembyrn.value.text,
                            ttlpembyrn.value.text,mtodpembyrn.value.text, wktupesanan.value.text)
                        scope.launch {
                            pencatatanPembayaranPaylaterDao.insertAll(item)
                        }
                        mtodpembyrn.value = TextFieldValue("")
                        tglpembyrn.value = TextFieldValue("")
                        ttlpembyrn.value = TextFieldValue("")
                        wktupesanan.value = TextFieldValue("")
                    }else{
                        Toast.makeText(context, "Metode Pembayaran Harus di Isi",
                            Toast.LENGTH_LONG).show()
                    }
                }else{
                    Toast.makeText(context, "Metode Pembayaran Harus di Isi",
                        Toast.LENGTH_LONG).show()
                }



            }, colors = loginButtonColors) {
                Text(
                    text = "Simpan",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp,
                    ), modifier = Modifier.padding(8.dp)
                )
            }
            Button(modifier = Modifier.weight(5f), onClick = {
                mtodpembyrn.value = TextFieldValue("")
                tglpembyrn.value = TextFieldValue("")
                ttlpembyrn.value = TextFieldValue("")
                wktupesanan.value = TextFieldValue("")
            }, colors = resetButtonColors) {
                Text(
                    text = "Reset",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}