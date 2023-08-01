package id.belitong.bigs.core.utils

enum class HistoryStatus(val value: String) {
    PENDING("Pending"),
    ACCEPTED("Accepted"),
    REJECTED("Rejected"),
    CANCELLED("Cancelled"),
    COMPLETED("Completed")
}