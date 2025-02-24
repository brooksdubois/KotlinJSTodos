
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.html.*
import kotlinx.html.dom.*
import kotlinx.serialization.json.*

const val urlEndpoint = "https://jsonplaceholder.typicode.com/todos"

lateinit var todoListManager: TodoListManager

fun main() {
   window.onload = {
        document.body?.append?.div(classes = "centered"){
            h1{ + "Todos Redone in Kotlin!" }
            div{ id = "paginationBody"
                paginationTemplate(paginationTemplateModel = PaginationTemplateModel())
            }
            div{ id = "mainBody" }
        }

        addClickHandler(elementId = "fetchButton") {
            Axios.get(endPoint = urlEndpoint)
                .then {
                    val result = it.asDynamic()
                        .request.responseText as? String
                        ?: "Get Request String Issue"

                    val todoList = Json.decodeFromString<List<TodoResponseItem>>(result)

                    todoListManager = TodoListManager(fullTodoList = todoList)

                    setResultList()
                }
        }
    }
}
