package unpas.ac.id.pencatatanpembayaranpaylater.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PencatatanPembayaranPaylater(
    @PrimaryKey val id: String,
    val tglpembyrn: String,
    val ttlpembyrn: String,
    val mtodpembyrn: String,
    val wktupesanan: String
)