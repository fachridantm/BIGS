package id.belitong.bigs.core.domain.model

sealed class HistoryListItem {
    data class OrderItem(val order: Order) : HistoryListItem()
    data class ReportItem(val report: Report) : HistoryListItem()
}