
class TodoListManager(
    var fullTodoList: List<TodoResponseItem>,
    var currentPageNumber: Int = 1,
    var currentInterval: Int = 10
) {

    var endPage: Int = fullTodoList.size/currentInterval
    var onlyUnfinished = false

    fun nextPage(): List<TodoResponseItem> {
        onlyUnfinished = false
        if(currentPageNumber != endPage) currentPageNumber += 1
        return resultsByPage()
    }

    fun previousPage(): List<TodoResponseItem> {
        onlyUnfinished = false
        if (currentPageNumber != 1) currentPageNumber -= 1
        return resultsByPage()
    }

    fun resultsByPage(filteredList: List<TodoResponseItem>? = null, adjustedInterval: Int? = null): List<TodoResponseItem> {
        val todoList = filteredList ?: fullTodoList
        val pageNum = if(currentPageNumber <  endPage) currentPageNumber else endPage
        val interval = adjustedInterval ?: currentInterval
        //1: 0..9, 2: 10..19, 3: 20..29 etc
        val rangeStart = (pageNum - 1) * interval
        val rangeEnd = rangeStart + (interval-1)
        return todoList.slice(indices = IntRange(start = rangeStart, endInclusive = rangeEnd))
    }

    fun onlyUnfinishedTasks(): List<TodoResponseItem> {
        currentPageNumber = 1
        onlyUnfinished = !onlyUnfinished
        return if (onlyUnfinished)
            fullTodoList.filterNot{ it.completed }.let {
                resultsByPage(filteredList = it, adjustedInterval = it.size)
            } else resultsByPage()
    }

    fun sortByName() {
        fullTodoList = fullTodoList.sortedBy { it.title.uppercase() }
    }
}