
@JsModule("axios")
@JsNonModule
@JsName("axios")
external class Axios {
    companion object {
        @JsName("get")
        fun get(endPoint: String): Axios
    }

    @JsName("then")
    fun then(unt:  (Axios) -> Unit)
}
