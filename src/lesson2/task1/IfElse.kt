@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.pow
import kotlin.math.sqrt

// Урок 2: ветвления (здесь), логический тип (см. 2.2).
// Максимальное количество баллов = 6
// Рекомендуемое количество баллов = 5
// Вместе с предыдущими уроками = 9/12
fun main(){
    println(ageDescription(52));
}
/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String =
    when (grade) {
        5 -> "отлично"
        4 -> "хорошо"
        3 -> "удовлетворительно"
        2 -> "неудовлетворительно"
        else -> "несуществующая оценка $grade"
    }

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) {
            return Double.NaN
        } // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) {
            return Double.NaN
        } // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая (2 балла)
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String {
    val newAge: Int = age % 100
    if (newAge % 10 == 1 && newAge != 11) {
        return "$age год"
    } else if (newAge in 2..4 || newAge in 22..24 || newAge in 32..34 || newAge in 42..44) {
        return "$age года"
    } else {
        return "$age лет"
    }
}

/**
 * Простая (2 балла)
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(
    t1: Double, v1: Double,
    t2: Double, v2: Double,
    t3: Double, v3: Double
): Double {
    val s1: Double = t1 * v1;
    val s2: Double = t2 * v2;
    val s3: Double = t3 * v3;
    val averageS: Double = (s1 + s2 + s3) / 2

    if (s1 <= averageS) {
        if (s1 + s2 >= averageS) {
            return t1 + (averageS - s1) / v2
        } else {
            return t1 + t2 + (averageS - s1 - s2) / v3
        }
    } else {

        return averageS / v1
    }


}

/**
 * Простая (2 балла)
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(
    kingX: Int, kingY: Int,
    rookX1: Int, rookY1: Int,
    rookX2: Int, rookY2: Int): Int {
    var count: Int = 0
    if (kingX == rookX1 || kingY == rookY1) {
        count++;
    }
    if (kingX == rookX2 || kingY == rookY2) {
        if (count == 1) {
            return 3
        }
        return 2
    }
    return count
}

/**
 * Простая (2 балла)
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(
    kingX: Int, kingY: Int,
    rookX: Int, rookY: Int,
    bishopX: Int, bishopY: Int): Int {
    var count: Int = 0;
    if (kingX == rookX || kingY == rookY) {
        count++;
    }
    if (abs(kingX - bishopX) == abs(kingY - bishopY)){
        if (count == 1) {
            return 3
        }
        return 2
    }
    return count
}

/**
 * Простая (2 балла)
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
    val minSide: Double = minOf(a, b, c)
    val maxSide: Double = maxOf(a, b, c)
    val averageSide: Double = a + b + c - maxSide - minSide
    if (maxSide < minSide + averageSide && a > 0.0 && b > 0.0 && c > 0.0) {
        if (maxSide.pow(2) == minSide.pow(2) + averageSide.pow(2)) {
            return 1
        } else if (maxSide.pow(2) < minSide.pow(2) + averageSide.pow(2)) {
            return 0
        } else if (maxSide.pow(2) > minSide.pow(2) + averageSide.pow(2)) {
            return 2
        }
    }
    return -1
}

/**
 * Средняя (3 балла)
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int {
    if (a in c..d && b in c..d) {
        return b - a
    }
    if (c in a..b && d in a..b){
        return d - c
    }
    if (c in a..b) {
        return b - c
    }
    if (a in c..d) {
        return d - a
    }
    return -1
}
