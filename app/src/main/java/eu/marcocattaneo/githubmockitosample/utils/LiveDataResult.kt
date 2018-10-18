package eu.marcocattaneo.githubmockitosample.utils

class LiveDataResult<T>(val status: Status, val data: T? = null, val err: Throwable? = null) {
    companion object {
        fun <T> succes(data: T?) = LiveDataResult(Status.SUCCESS, data)
        fun <T> loading() = LiveDataResult<T>(Status.LOADING)
        fun <T> error(err: Throwable?) = LiveDataResult<T>(Status.ERROR, null, err)
    }

    enum class Status {
        SUCCESS, ERROR, LOADING
    }

}
