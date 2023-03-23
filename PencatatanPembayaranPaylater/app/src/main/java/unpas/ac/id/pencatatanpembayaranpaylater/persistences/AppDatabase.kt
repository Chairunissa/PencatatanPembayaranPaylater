package unpas.ac.id.pencatatanpembayaranpaylater.persistences

import androidx.room.Database
import androidx.room.RoomDatabase
import unpas.ac.id.pencatatanpembayaranpaylater.model.PencatatanPembayaranPaylater

@Database(entities = [PencatatanPembayaranPaylater::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pencatatanPembayaranPaylaterDao(): PencatatanPembayaranPaylaterDao
}
