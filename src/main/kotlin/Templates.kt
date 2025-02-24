import kotlinx.html.*

fun DIV.paginationTemplate(paginationTemplateModel: PaginationTemplateModel)
    = paginationTemplateModel.apply {
        div {
            id = "paginator"
            if (hasPrevious) button {
                id = "prevButton"
                +"◀️"
            }
            if (currentPageNumber.isNotEmpty()) span {
                +"Page: $currentPageNumber"
            }
            if (hasNext) button {
                id = "nextButton"
                +"▶️"
            }
            if (hasFetchButton) button {
                id = "fetchButton"
                +"Fetch!"
            }
            if (hasSortButton) {
                button {
                    id = "sortButton"
                    +"Sort!"
                }
                label {
                    htmlFor = "unfinishedOnly"
                    +"Show All Unfinished"
                }
                input {
                    id = "unfinishedCheckBox"
                    type = InputType.checkBox
                    name = "unfinishedOnly"
                    checked = onlyUnfinishedChecked
                }
            }
        }
    }

fun UL.todoTemplate (todoList: List<TodoResponseItem>)
    = todoList.forEach { todo ->
        li {
            b { + todo.title }
            + if(todo.completed) "✅" else  "❌"
        }
    }
