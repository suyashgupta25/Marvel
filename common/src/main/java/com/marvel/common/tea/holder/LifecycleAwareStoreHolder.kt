package com.marvel.common.tea.holder

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.marvel.common.tea.store.Store

/**
 * This type of [StoreHolder] uses the component's [Lifecycle] to know when to clear the [Store].
 */
class LifecycleAwareStoreHolder<Event : Any, State : Any>(
    lifecycle: Lifecycle,
    storeProvider: () -> Store<Event, State>
) : StoreHolder<Event, State> {

    override val store by lazy(LazyThreadSafetyMode.NONE) { storeProvider().start() }

    private val lifecycleObserver = object : DefaultLifecycleObserver {
        override fun onDestroy(owner: LifecycleOwner) {
            store.stop()
        }
    }

    init {
        lifecycle.addObserver(lifecycleObserver)
    }
}
