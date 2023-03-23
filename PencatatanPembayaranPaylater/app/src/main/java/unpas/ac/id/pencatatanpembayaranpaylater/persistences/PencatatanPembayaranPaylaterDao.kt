package unpas.ac.id.pencatatanpembayaranpaylater.persistences

import androidx.lifecycle.LiveData
import androidx.room.*
import unpas.ac.id.pencatatanpembayaranpaylater.model.PencatatanPembayaranPaylater

@Dao
interface PencatatanPembayaranPaylaterDao {
    @Query("SELECT * FROM PencatatanPembayaranPaylater")
    fun loadAll(): LiveData<List<PencatatanPembayaranPaylater>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg items: PencatatanPembayaranPaylater)
}