package id.belitong.bigs.core.domain.model

data class User(
    val userId: String,
    val name: String,
    val token: String? = null,
)