package unpas.ac.id.pencatatanpembayaranpaylater.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.room.Room
import unpas.ac.id.pencatatanpembayaranpaylater.model.PencatatanPembayaranPaylater
import unpas.ac.id.pencatatanpembayaranpaylater.persistences.AppDatabase

@Composable
fun PengelolaanPembayaranPaylater() {
    val context = LocalContext.current
    val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "Pembayaran-paylater"
    ).build()
    val pencatatanPembayaranPaylater = db.pencatatanPembayaranPaylaterDao()

    val list : LiveData<List<PencatatanPembayaranPaylater>> = pencatatanPembayaranPaylater.loadAll()
    val items: List<PencatatanPembayaranPaylater> by list.observeAsState(initial = listOf())

    Column(modifier = Modifier.fillMaxWidth()) {
        FormPencatatanPembayaranPaylater(pencatatanPembayaranPaylater)
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(items = items, itemContent = { item ->
                Row(
                    modifier = Modifier
                        .padding(15.dp)
                        .fillMaxWidth()
                ) {
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Tanggal Pembayaran", fontSize = 14.sp)
                        Text(
                            text = item.tglpembyrn, fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Total Pembayaran", fontSize = 14.sp)
                        Text(
                            text = "Rp. ${item.ttlpembyrn}", fontSize = 16.sp, fontWeight =
                            FontWeight.Bold
                        )
                    }
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Metode Pembayaran", fontSize = 14.sp)
                        Text(
                            text = item.mtodpembyrn, fontSize = 16.sp, fontWeight =
                            FontWeight.Bold
                        )
                    }
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Waktu Pembayaran", fontSize = 14.sp)
                        Text(
                            text = item.wktupesanan, fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Divider(modifier = Modifier.fillMaxWidth())
            })
        }
    }
}