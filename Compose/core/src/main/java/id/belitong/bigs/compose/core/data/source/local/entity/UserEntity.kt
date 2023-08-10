package id.belitong.bigs.compose.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey
    @ColumnInfo(name = "userId")
    val userId: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "token")
    val token: String? = null,

    @ColumnInfo(name = "photoUrl")
    val photoUrl: String? = null,
)