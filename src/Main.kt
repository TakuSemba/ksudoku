import java.io.File
import kotlin.system.measureTimeMillis

fun main() {

    val problems = File("./src/problems.csv").readLines()
    val backtracking = BackTrackingSolver()

    problems.map { problem -> problem.split(',').let { Pair(it[0], it[1]) } }
        .map { (question, answer) ->
            Pair(
                Array(9) { i -> Array(9) { j -> question[i * 9 + j] } },
                Array(9) { i -> Array(9) { j -> answer[i * 9 + j] } }
            )
        }
        .forEach { (board, expected) ->
            println("========= question ========= \n${board.joinToString("\n") { it.contentToString() }}")
            val time = measureTimeMillis { backtracking.solve(board) }
            check(board.contentDeepEquals(expected)) { "wrong answer" }
            println("========= answer ========= \n${board.joinToString("\n") { it.contentToString() }}")
            println("took $time ms")
        }
}
