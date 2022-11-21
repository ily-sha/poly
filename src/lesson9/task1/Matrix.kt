@file:Suppress("UNUSED_PARAMETER", "unused")

package lesson9.task1

// Урок 9: проектирование классов
// Максимальное количество баллов = 40 (без очень трудных задач = 15)

/**
 * Ячейка матрицы: row = ряд, column = колонка
 */
data class Cell(val row: Int, val column: Int)

/**
 * Интерфейс, описывающий возможности матрицы. E = тип элемента матрицы
 */

interface Matrix<E> {
    /** Высота */
    val height: Int

    /** Ширина */
    val width: Int

    /**
     * Доступ к ячейке.
     * Методы могут бросить исключение, если ячейка не существует или пуста
     */
    operator fun get(row: Int, column: Int): E

    operator fun get(cell: Cell): E

    /**
     * Запись в ячейку.
     * Методы могут бросить исключение, если ячейка не существует
     */
    operator fun set(row: Int, column: Int, value: E)

    operator fun set(cell: Cell, value: E)


}

/**
 * Простая (2 балла)
 *
 * Метод для создания матрицы, должен вернуть РЕАЛИЗАЦИЮ Matrix<E>.
 * height = высота, width = ширина, e = чем заполнить элементы.
 * Бросить исключение IllegalArgumentException, если height или width <= 0.
 */
fun <E> createMatrix(height: Int, width: Int, e: E): Matrix<E> =
    if (height <= 0 || width <= 0) throw java.lang.IllegalArgumentException("") else MatrixImpl<E>(height, width, e)

/**
 * Средняя сложность (считается двумя задачами в 3 балла каждая)
 *
 * Реализация интерфейса "матрица"
 */
class MatrixImpl<E>(override val height: Int, override val width: Int, val e: E) : Matrix<E> {
    private var list: MutableList<MutableList<E>> = MutableList(height) { MutableList(width) { e } }


    override fun get(row: Int, column: Int): E = list[row][column]

    override fun get(cell: Cell): E = list[cell.row][cell.column]

    override fun set(row: Int, column: Int, value: E) {
        list[row][column] = value
    }

    override fun set(cell: Cell, value: E) {
        list[cell.row][cell.column] = value
    }


    override fun equals(other: Any?): Boolean {
        if (other is Matrix<*> && other.height == this.height && other.width == this.width){
            for (i in 0 until height) {
                for (j in 0 until width) {
                    if (get(i, j) != other[i, j]) return false
                }
            }
            return true
        }
        return false
    }


    override fun toString() = buildString {
        append("[")
        for (row in 0 until height) {
            append("[")
            for (column in 0 until width) {

                append(get(row, column))
                if (column != width - 1){
                    append(",")
                }
            }
            if (row != height - 1){
                append("]\n")
            } else append("]")
        }
        append("]")
    }


    override fun hashCode(): Int {
        var result = height
        result = 31 * result + width
        result = 31 * result + (e?.hashCode() ?: 0)
        result = 31 * result + list.hashCode()
        return result
    }
}

