package io.mochadwi.util.base

import androidx.databinding.ObservableField
import io.mochadwi.util.mvvm.RxViewModel
import io.mochadwi.util.rx.SchedulerProvider
import kotlinx.coroutines.channels.Channel

/**
 *
 * In syaa Allah created or modified by @mochadwi
 * On 2019-05-20 for etalase-app
 */

open class BaseViewModel(
        schedulerProvider: SchedulerProvider
) : RxViewModel(schedulerProvider) {

    val keywords = Channel<String>(Channel.UNLIMITED)
    val progress = ObservableField<Boolean>(false)
    val isRefreshing = ObservableField<Boolean>(false)
    val isError = ObservableField<Boolean>(false)
    val errMsg = ObservableField<String>("")
}
