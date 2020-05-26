package com.epf.ontracks.network

data class LinesResult(
    val result : Lines
)
data class Lines(
    val metros : List<Transport>,
    val rers : List<Transport>,
    val tramways : List<Transport>,
    val buses : List<Transport>,
    val noctiliens : List<Transport>
)

data class Transport (
    val code : String,
    val name : String,
    val directions : String,
    val id : String
)