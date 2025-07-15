package ati.su.horseraces.domain.common.utils

import ati.su.horseraces.domain.common.core.DataState
import ati.su.horseraces.domain.common.core.UIComponent

fun <T> handleLocalException(e: Exception, defaultDescription: String): DataState.Response<T> {
    e.printStackTrace()

    return DataState.Response(
        uiComponent = UIComponent.DialogSimple(
            title = "Ошибка",
            description = defaultDescription
        )
    )
}
