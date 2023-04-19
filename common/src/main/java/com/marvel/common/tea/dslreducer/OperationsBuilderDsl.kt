package com.marvel.common.tea.dslreducer

@DslMarker
internal annotation class OperationsBuilderDsl

/**
 * Special Dsl allows to user '+' operator to add new entities to the list
 * More docs: https://kotlinlang.org/docs/type-safe-builders.html#scope-control-dslmarker
 */
@OperationsBuilderDsl
class OperationsBuilder<T : Any> {

    private val list = mutableListOf<T>()

    operator fun T?.unaryPlus() {
        this?.let(list::add)
    }

    internal fun build() = list
}
