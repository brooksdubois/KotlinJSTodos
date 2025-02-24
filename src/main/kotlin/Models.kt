
import kotlinx.serialization.Serializable

@Serializable
data class TodoResponseItem (
    val userId: Int,
    val id: Int,
    val title: String,
    val completed: Boolean
)

data class PaginationTemplateModel (
    var hasPrevious: Boolean = false,
    var currentPageNumber: String = "",
    var hasNext: Boolean = false,
    var hasFetchButton: Boolean = true,
    var hasSortButton: Boolean = false,
    var onlyUnfinishedChecked: Boolean = false
)
