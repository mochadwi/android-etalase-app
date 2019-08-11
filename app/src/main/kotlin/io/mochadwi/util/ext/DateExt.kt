package io.mochadwi.util.ext

import org.joda.time.DateTime

/**
 *
 * In syaa Allah created or modified by @mochadwi
 * On 10/08/19 for etalase-app
 */

fun String.toDays() = DateTime(this).dayOfWeek().asText