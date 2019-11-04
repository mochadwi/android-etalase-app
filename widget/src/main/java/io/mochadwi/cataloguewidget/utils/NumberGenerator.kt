package io.mochadwi.cataloguewidget.utils

import java.util.*

object NumberGenerator {

    fun Generate(max: Int): Int {

        val random = Random()
        return random.nextInt(max)
    }
}
