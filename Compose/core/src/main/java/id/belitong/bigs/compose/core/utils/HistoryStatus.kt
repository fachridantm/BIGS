package id.belitong.bigs.compose.core.utils

enum class HistoryStatus(val value: String) {
    PENDING("Pending"),
    CONFIRMED("Confirmed"),
    REJECTED("Rejected"),
    CANCELLED("Cancelled"),
    COMPLETED("Completed")
}