package id.belitong.bigs.compose.core.domain.model

data class User(
    val userId: String,
    val name: String,
    val token: String? = null,
    val photoUrl: String? = null,
)