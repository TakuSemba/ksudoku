import kotlin.math.sqrt

class BackTrackingSolver : Solver {

    override fun solve(board: Array<Array<Char>>) {
        solve(0, 0, board)
    }

    private fun solve(row: Int, col: Int, board: Array<Array<Char>>): Boolean {

        if (row == board.size) {
            return true
        }

        if (col == board[row].size) {
            return solve(row + 1, 0, board)
        }

        if (board[row][col] != '0') {
            return solve(row, col + 1, board)
        }

        for (i in 1 until board.size + 1) {
            val char = '0'.plus(i)
            if (canBeFilled(row, col, board, char)) {
                board[row][col] = char
                if (solve(row, col + 1, board)) {
                    return true
                }
            }
        }
        board[row][col] = '0'
        return false
    }

    private fun canBeFilled(row: Int, col: Int, board: Array<Array<Char>>, char: Char): Boolean {

        for (i in 0 until board.size) {
            if (board[i][col] == char) {
                return false
            }
        }

        for (i in 0 until board[row].size) {
            if (board[row][i] == char) {
                return false
            }
        }

        val size = sqrt(board.size.toFloat()).toInt()
        val topLeftRowIndex = size * (row / size)
        val topLeftColIndex = size * (col / size)

        for (i in 0 until size) {
            for (j in 0 until size) {
                if (board[topLeftRowIndex + i][topLeftColIndex + j] == char) {
                    return false
                }
            }
        }
        return true
    }

}