package id.belitong.bigs.core.domain.model

data class Login(
    val user: User? = null,
    val token: String? = null,
    val message: String? = null,
)
