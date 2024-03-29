@file:Suppress("UNUSED_PARAMETER")

package lesson9.task2

import lesson9.task1.Matrix
import lesson9.task1.createMatrix

// Все задачи в этом файле требуют наличия реализации интерфейса "Матрица" в Matrix.kt

/**
 * Пример
 *
 * Транспонировать заданную матрицу matrix.
 * При транспонировании строки матрицы становятся столбцами и наоборот:
 *
 * 1 2 3      1 4 6 3
 * 4 5 6  ==> 2 5 5 2
 * 6 5 4      3 6 4 1
 * 3 2 1
 */
fun <E> transpose(matrix: Matrix<E>): Matrix<E> {
    if (matrix.width < 1 || matrix.height < 1) return matrix
    val result = createMatrix(height = matrix.width, width = matrix.height, e = matrix[0, 0])
    for (i in 0 until matrix.width) {
        for (j in 0 until matrix.height) {
            result[i, j] = matrix[j, i]
        }
    }
    return result
}

/**
 * Пример
 *
 * Сложить две заданные матрицы друг с другом.
 * Складывать можно только матрицы совпадающего размера -- в противном случае бросить IllegalArgumentException.
 * При сложении попарно складываются соответствующие элементы матриц
 */
operator fun Matrix<Int>.plus(other: Matrix<Int>): Matrix<Int> {
    require(!(width != other.width || height != other.height))
    if (width < 1 || height < 1) return this
    val result = createMatrix(height, width, this[0, 0])
    for (i in 0 until height) {
        for (j in 0 until width) {
            result[i, j] = this[i, j] + other[i, j]
        }
    }
    return result
}

/**
 * Сложная (5 баллов)
 *
 * Заполнить матрицу заданной высоты height и ширины width
 * натуральными числами от 1 до m*n по спирали,
 * начинающейся в левом верхнем углу и закрученной по часовой стрелке.
 *
 * Пример для height = 3, width = 4:
 *  1  2  3  4
 * 10 11 12  5
 *  9  8  7  6
 */

/**
 * На вход функции подается список строк в формате
 * “Иванов Петр: улица Ленина, 41, кв. 2”
 * Каждая строка начинается с фамилии и имени человека (разделенных
 * одним пробелом), далее через запятую пробел(ы) следует адрес:
 * название улицы (может состоять из нескольких слов через
 * один пробел),
 * номер дома (целое число) и номер квартиры
 * (с префиксом “кв.”; целое число).
 *
 * На вход также подается имя человека.
 *
 * Вернуть список людей, которые являются соседями
 * указанного человека
 * (соседями считаются люди, которые живут в одном доме).
 *
 * Имя функции и тип результата функции предложить самостоятельно;
 * в задании указан тип Collection<Any>,
 * то есть коллекция объектов произвольного типа,
 * можно (и нужно) изменить как вид коллекции,
 * так и тип её элементов.
 *
 * При нарушении формата входной строки,
 * бросить IllegalArgumentException
 *
 * Кроме функции, следует написать тесты,
 * подтверждающие её работоспособность.
 */
fun myFun(table: Map<String, Int>, taxes: String): Collection<Any> {
    val result = mutableMapOf<String, Int>()
    for (i in taxes.split("\n")) {
        if (Regex("""(([А-я])+ *)+ - (([А-я])+ *)+ - \d+""").matches(i)) {
            val (name, type, profit) = i.split(" - ")
            result[name] = profit.toInt() * (table[type] ?: 13) / 100
        } else throw IllegalArgumentException("")
    }
    return result.entries.sortedByDescending { it.value }.map { it.value }
}

fun main() {
//    println(myFun(listOf("Иванов Петр: улица Ленина, 41, кв. 2", "Иванов Илья: улица Ленина, 41, кв. 2"),"Иванов Илья"))
//    println(generateSpiral(8, 8))
//    println()


//    println(generateSpiral(7, 5))
//    println()
//    println(generateSpiral(8, 5))
//    println()
//    println(generateSpiral(9, 7))
//    println()

//    println(generateSpiral(3, 3))
//    println()
//    println(generateSpiral(4, 7))
//    println()
////
//    println(generateSpiral(6, 3))
//    println()
//    println(generateSpiral(46, 5))
//    println()

//    println(generateSpiral(3, 2))
//    println()
//    println(generateSpiral(3, 4))
//    println()
//    println(generateSpiral(3, 5))
//    println()
//    println(generateSpiral(3, 6))
//    println()
//    println(generateSpiral(5, 3))
    println()
    println(generateSpiral(6, 2))


}

fun generateSpiral(height: Int, width: Int): Matrix<Int> {
    // происходит заполнение спирали 4 фрагментами
    // первый фрагмент
    // 1 2  3  4
    //   13 14
    // второй фрагмент
    //   16 15
    //10  9 8  7
    // третий фрагмент
    //         5
    //         6
    // четвертый фрагмент
    //  12
    //  13

    //var start - число, с которой начинается заполнение
    //var horizon - количество чисел в ряду
    //var vertical - количество чисел в столбце
    val matrix = createMatrix(height, width, 0)
    if (height == 1) {
        for (j in 0 until width) matrix[0, j] = j + 1
    }
    if (width == 1) {
        for (j in 0 until height) matrix[j, 0] = j + 1
        return matrix
    }
    var horizon = width
    var vertical = height - 1
    var start = 1
    var last = 0
    for (i in 0 until height / 2) {
        var x = 0
        for (j in start until start + horizon) {
            matrix[i, i + x] = j
            last = j
            x++
        }
        horizon -= 2
        start = last + 2 * vertical + horizon + 1
        vertical -= 2

    }
    horizon = width
    vertical = height - 2
    start = height - 1 + width
    last = 0
    for (i in 0 until height / 2) {
        var x = 0
        for (j in start until start + horizon) {
            matrix[height - i - 1, width - x - i - 1] = j
            last = j
            x++
        }
        horizon -= 2
        start = last + 2 * vertical + horizon - 1
        vertical -= 2
        if (height <= width && height % 2 != 0 && i == height / 2 - 1) {
            last += 2
            x = 0
            for (h in last + horizon - 1 downTo last) {
                matrix[height / 2, width - x - i - 2] = h
                x++
            }
        }
    }
    start = width + 1
    horizon = width - 1
    vertical = height - 2
    last = 0
    for (i in 1..width / 2) {
        var y = 0
        for (j in start until start + vertical) {
            matrix[i + y, width - i] = j
            last = j
            y++
        }

        start = last + vertical + 1 + 2 * horizon
        vertical -= 2
        horizon -= 2
    }
    start = 2 * width + height - 1
    horizon = width - 1
    vertical = height - 2
    last = 0
    for (i in 1..width / 2) {
        var y = 1
        for (j in start until start + vertical) {
            matrix[height - i - y, i - 1] = j
            last = j
            y++
        }
        vertical -= 2
        start = last + vertical - 1 + 2 * horizon
        horizon -= 2
        if (height > width && width % 2 != 0 && i == width / 2) {
            last += 2
            y = (height - y + 1) / 2 + 1
            for (h in last until last + vertical) {
                matrix[y, i] = h
                y++
            }
        }
    }
    return matrix
}

/**
 * Сложная (5 баллов)
 *
 * Заполнить матрицу заданной высоты height и ширины width следующим образом.
 * Элементам, находящимся на периферии (по периметру матрицы), присвоить значение 1;
 * периметру оставшейся подматрицы – значение 2 и так далее до заполнения всей матрицы.
 *
 * Пример для height = 5, width = 6:
 *  1  1  1  1  1  1
 *  1  2  2  2  2  1
 *  1  2  3  3  2  1
 *  1  2  2  2  2  1
 *  1  1  1  1  1  1
 */
fun generateRectangles(height: Int, width: Int): Matrix<Int> = TODO()

/**
 * Сложная (5 баллов)
 *
 * Заполнить матрицу заданной высоты height и ширины width диагональной змейкой:
 * в левый верхний угол 1, во вторую от угла диагональ 2 и 3 сверху вниз, в третью 4-6 сверху вниз и так далее.
 *
 * Пример для height = 5, width = 4:
 *  1  2  4  7
 *  3  5  8 11
 *  6  9 12 15
 * 10 13 16 18
 * 14 17 19 20
 */
fun generateSnake(height: Int, width: Int): Matrix<Int> = TODO()

/**
 * Средняя (3 балла)
 *
 * Содержимое квадратной матрицы matrix (с произвольным содержимым) повернуть на 90 градусов по часовой стрелке.
 * Если height != width, бросить IllegalArgumentException.
 *
 * Пример:    Станет:
 * 1 2 3      7 4 1
 * 4 5 6      8 5 2
 * 7 8 9      9 6 3
 */
fun <E> rotate(matrix: Matrix<E>): Matrix<E> = TODO()

/**
 * Сложная (5 баллов)
 *
 * Проверить, является ли квадратная целочисленная матрица matrix латинским квадратом.
 * Латинским квадратом называется матрица размером n x n,
 * каждая строка и каждый столбец которой содержат все числа от 1 до n.
 * Если height != width, вернуть false.
 *
 * Пример латинского квадрата 3х3:
 * 2 3 1
 * 1 2 3
 * 3 1 2
 */
fun isLatinSquare(matrix: Matrix<Int>): Boolean = TODO()

/**
 * Средняя (3 балла)
 *
 * В матрице matrix каждый элемент заменить суммой непосредственно примыкающих к нему
 * элементов по вертикали, горизонтали и диагоналям.
 *
 * Пример для матрицы 4 x 3: (11=2+4+5, 19=1+3+4+5+6, ...)
 * 1 2 3       11 19 13
 * 4 5 6  ===> 19 31 19
 * 6 5 4       19 31 19
 * 3 2 1       13 19 11
 *
 * Поскольку в матрице 1 х 1 примыкающие элементы отсутствуют,
 * для неё следует вернуть как результат нулевую матрицу:
 *
 * 42 ===> 0
 */
fun sumNeighbours(matrix: Matrix<Int>): Matrix<Int> = TODO()

/**
 * Средняя (4 балла)
 *
 * Целочисленная матрица matrix состоит из "дырок" (на их месте стоит 0) и "кирпичей" (на их месте стоит 1).
 * Найти в этой матрице все ряды и колонки, целиком состоящие из "дырок".
 * Результат вернуть в виде Holes(rows = список дырчатых рядов, columns = список дырчатых колонок).
 * Ряды и колонки нумеруются с нуля. Любой из спискоов rows / columns может оказаться пустым.
 *
 * Пример для матрицы 5 х 4:
 * 1 0 1 0
 * 0 0 1 0
 * 1 0 0 0 ==> результат: Holes(rows = listOf(4), columns = listOf(1, 3)): 4-й ряд, 1-я и 3-я колонки
 * 0 0 1 0
 * 0 0 0 0
 */
fun findHoles(matrix: Matrix<Int>): Holes = TODO()

/**
 * Класс для описания местонахождения "дырок" в матрице
 */
data class  Holes(val rows: List<Int>, val columns: List<Int>)

/**
 * Средняя (3 балла)
 *
 * В целочисленной матрице matrix каждый элемент заменить суммой элементов подматрицы,
 * расположенной в левом верхнем углу матрицы matrix и ограниченной справа-снизу данным элементом.
 *
 * Пример для матрицы 3 х 3:
 *
 * 1  2  3      1  3  6
 * 4  5  6  =>  5 12 21
 * 7  8  9     12 27 45
 *
 * К примеру, центральный элемент 12 = 1 + 2 + 4 + 5, элемент в левом нижнем углу 12 = 1 + 4 + 7 и так далее.
 */
fun sumSubMatrix(matrix: Matrix<Int>): Matrix<Int> = TODO()

/**
 * Простая (2 балла)
 *
 * Инвертировать заданную матрицу.
 * При инвертировании знак каждого элемента матрицы следует заменить на обратный
 */
operator fun Matrix<Int>.unaryMinus(): Matrix<Int> = TODO(this.toString())

/**
 * Средняя (4 балла)
 *
 * Перемножить две заданные матрицы друг с другом.
 * Матрицы можно умножать, только если ширина первой матрицы совпадает с высотой второй матрицы.
 * В противном случае бросить IllegalArgumentException.
 * Подробно про порядок умножения см. статью Википедии "Умножение матриц".
 */
operator fun Matrix<Int>.times(other: Matrix<Int>): Matrix<Int> = TODO(this.toString())

/**
 * Сложная (7 баллов)
 *
 * Даны мозаичные изображения замочной скважины и ключа. Пройдет ли ключ в скважину?
 * То есть даны две матрицы key и lock, key.height <= lock.height, key.width <= lock.width, состоящие из нулей и единиц.
 *
 * Проверить, можно ли наложить матрицу key на матрицу lock (без поворота, разрешается только сдвиг) так,
 * чтобы каждой единице в матрице lock (штырь) соответствовал ноль в матрице key (прорезь),
 * а каждому нулю в матрице lock (дырка) соответствовала, наоборот, единица в матрице key (штырь).
 * Ключ при сдвиге не может выходить за пределы замка.
 *
 * Пример: ключ подойдёт, если его сдвинуть на 1 по ширине
 * lock    key
 * 1 0 1   1 0
 * 0 1 0   0 1
 * 1 1 1
 *
 * Вернуть тройку (Triple) -- (да/нет, требуемый сдвиг по высоте, требуемый сдвиг по ширине).
 * Если наложение невозможно, то первый элемент тройки "нет" и сдвиги могут быть любыми.
 */

fun canOpenLock(key: Matrix<Int>, lock: Matrix<Int>): Triple<Boolean, Int, Int> {

    fun cutLockMatrix(startH: Int, startW: Int): Boolean {
        for (h in startH until startH + key.height) {
            for (w in startW until startW + key.width) {
                if (lock[h, w] + key[h - startH, w - startW] != 1) return false
            }
        }
        return true
    }

    for (h in 0..lock.height - key.height) {
        for (w in 0..lock.width - key.width) {
            if (cutLockMatrix(h, w)) return Triple(true, h, w)
        }
    }
    return Triple(false, -1, -1)
}

/**
 * Сложная (8 баллов)
 *
 * В матрице matrix размером 4х4 дана исходная позиция для игры в 15, например
 *  5  7  9  1
 *  2 12 14 15
 *  3  4  6  8
 * 10 11 13  0
 *
 * Здесь 0 обозначает пустую клетку, а 1-15 – фишки с соответствующими номерами.
 * Напомним, что "игра в 15" имеет квадратное поле 4х4, по которому двигается 15 фишек,
 * одна клетка всегда остаётся пустой. Цель игры -- упорядочить фишки на игровом поле.
 *
 * В списке moves задана последовательность ходов, например [8, 6, 13, 11, 10, 3].
 * Ход задаётся номером фишки, которая передвигается на пустое место (то есть, меняется местами с нулём).
 * Фишка должна примыкать к пустому месту по горизонтали или вертикали, иначе ход не будет возможным.
 * Все номера должны быть в пределах от 1 до 15.
 * Определить финальную позицию после выполнения всех ходов и вернуть её.
 * Если какой-либо ход является невозможным или список содержит неверные номера,
 * бросить IllegalStateException.
 *
 * В данном случае должно получиться
 * 5  7  9  1
 * 2 12 14 15
 * 0  4 13  6
 * 3 10 11  8
 */
fun findDigitCoordinate(digit: Int, matrix: Matrix<Int>): Pair<Int, Int> {
    for (c in 0..3) {
        for (r in 0..3) {
            if (matrix[c, r] == digit) {
                return Pair(c, r)
            }
        }
    }
    return Pair(-1, -1)
}

fun fifteenGameMoves(matrix: Matrix<Int>, moves: List<Int>): Matrix<Int> {
    for (i in moves) {
        val digitCoordinate = findDigitCoordinate(i, matrix)
        val zeroPos = when {
            digitCoordinate.first != 3 && matrix[digitCoordinate.first + 1, digitCoordinate.second] == 0 -> Pair(
                digitCoordinate.first + 1, digitCoordinate.second)
            digitCoordinate.first != 0 && matrix[digitCoordinate.first - 1, digitCoordinate.second] == 0 -> Pair(
                digitCoordinate.first - 1, digitCoordinate.second)
            digitCoordinate.second != 0 && matrix[digitCoordinate.first, digitCoordinate.second - 1] == 0 -> Pair(
                digitCoordinate.first, digitCoordinate.second - 1)
            digitCoordinate.second != 3 && matrix[digitCoordinate.first, digitCoordinate.second + 1] == 0 -> Pair(
                digitCoordinate.first, digitCoordinate.second + 1)
            else -> throw IllegalStateException("")
        }
        matrix[zeroPos.first, zeroPos.second] = i
        matrix[digitCoordinate.first, digitCoordinate.second] = 0
    }
    return matrix
}

/**
 * Очень сложная (32 балла)
 *
 * В матрице matrix размером 4х4 дана исходная позиция для игры в 15, например
 *  5  7  9  2
 *  1 12 14 15
 *  3  4  6  8
 * 10 11 13  0
 *
 * Здесь 0 обозначает пустую клетку, а 1-15 – фишки с соответствующими номерами.
 * Напомним, что "игра в 15" имеет квадратное поле 4х4, по которому двигается 15 фишек,
 * одна клетка всегда остаётся пустой.
 *
 * Цель игры -- упорядочить фишки на игровом поле, приведя позицию к одному из следующих двух состояний:
 *
 *  1  2  3  4          1  2  3  4
 *  5  6  7  8   ИЛИ    5  6  7  8
 *  9 10 11 12          9 10 11 12
 * 13 14 15  0         13 15 14  0
 *
 * Можно математически доказать, что РОВНО ОДНО из этих двух состояний достижимо из любой исходной позиции.
 *
 * Вернуть решение -- список ходов, приводящих исходную позицию к одной из двух упорядоченных.
 * Каждый ход -- это перемена мест фишки с заданным номером с пустой клеткой (0),
 * при этом заданная фишка должна по горизонтали или по вертикали примыкать к пустой клетке (но НЕ по диагонали).
 * К примеру, ход 13 в исходной позиции меняет местами 13 и 0, а ход 11 в той же позиции невозможен.
 *
 * Одно из решений исходной позиции:
 *
 * [8, 6, 14, 12, 4, 11, 13, 14, 12, 4,
 * 7, 5, 1, 3, 11, 7, 3, 11, 7, 12, 6,
 * 15, 4, 9, 2, 4, 9, 3, 5, 2, 3, 9,
 * 15, 8, 14, 13, 12, 7, 11, 5, 7, 6,
 * 9, 15, 8, 14, 13, 9, 15, 7, 6, 12,
 * 9, 13, 14, 15, 12, 11, 10, 9, 13, 14,
 * 15, 12, 11, 10, 9, 13, 14, 15]
 *
 * Перед решением этой задачи НЕОБХОДИМО решить предыдущую
 */
fun fifteenGameSolution(matrix: Matrix<Int>): List<Int> = TODO()
