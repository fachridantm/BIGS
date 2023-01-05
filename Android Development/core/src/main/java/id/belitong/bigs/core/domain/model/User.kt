package id.belitong.bigs.core.domain.model

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val password: String,
    val phoneNumber: String,
){
    fun toMap(): Map<String, Any?> =
        mapOf(
            "id" to id,
            "name" to name,
            "email" to email
        )
}
