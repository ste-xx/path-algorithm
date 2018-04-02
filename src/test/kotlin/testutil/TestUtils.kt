package testutil

import kotlinx.coroutines.experimental.promise

fun <T> runAsyncTest(block: suspend () -> T): dynamic = promise { block() }
