package com.epf.ontracks.lineslist

data class Line (
    val code: String,
    val name: String,
    val directions: String,
    val id: String
)

// all lines
data class LinesResult(
    val result: Lines
)

data class Lines(
    val metros: List<Line>,
    val rers: List<Line>,
    val tramways: List<Line>,
    val buses: List<Line>,
    val noctiliens: List<Line>
)



