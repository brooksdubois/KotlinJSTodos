import kotlinx.browser.document
import kotlinx.html.div
import kotlinx.html.dom.append
import kotlinx.html.ul
import org.w3c.dom.events.Event

fun setResultList(filteredList: List<TodoResponseItem>? = null) {
    val resultsByPage = filteredList ?: todoListManager.resultsByPage()

    document.getElementById("mainBody")
        ?.apply {
            innerHTML = ""
            append {
                ul {
                    todoTemplate(todoList = resultsByPage)
                }
            }
        }

    val currentPageNumber = todoListManager.currentPageNumber

    val hasPrevPage = currentPageNumber != 1
    val hasNextPage = todoListManager.endPage != currentPageNumber

    document.getElementById("paginationBody")
        ?.apply {
            innerHTML = ""
            append {
                div {
                    paginationTemplate(paginationTemplateModel =
                        PaginationTemplateModel(
                            hasPrevious = hasPrevPage,
                            currentPageNumber = currentPageNumber.toString(),
                            hasNext = hasNextPage,
                            hasFetchButton = false,
                            hasSortButton = true,
                            onlyUnfinishedChecked = todoListManager.onlyUnfinished
                        )
                    )
                }
            }
        }

    if(hasPrevPage) addClickHandler("prevButton") {
        todoListManager.previousPage()
        setResultList()
    }

    if(hasNextPage) addClickHandler("nextButton") {
        todoListManager.nextPage()
        setResultList()
    }

    addClickHandler(elementId = "sortButton") {
        todoListManager.sortByName()
        setResultList()
    }

    addClickHandler(elementId = "unfinishedCheckBox") {
        setResultList(filteredList = todoListManager.onlyUnfinishedTasks())
    }
}

fun addClickHandler(elementId: String, callback: ((Event) -> Unit)? ) {
    document.getElementById(elementId = elementId)
        ?.addEventListener(type = "click", callback = callback)
}