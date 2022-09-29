@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import lesson3.task1.digitNumber
import kotlin.math.pow
import kotlin.math.sqrt

// Урок 4: списки
// Максимальное количество баллов = 12
// Рекомендуемое количество баллов = 8
// Вместе с предыдущими уроками = 24/33

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}


/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.lowercase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая (2 балла
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    var abs = 0.0
    for (i in v) {
        abs += i.pow(2)
    }
    return sqrt(abs)
}

/**
 * Простая (2 балла)
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    var res = 0.0
    for (i in list){
        res += i
    }
    return when (res) {
        0.0 -> 0.0
        else -> res / list.size
    }
}

/**
 * Средняя (3 балла)
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val mean = mean(list)
    for (i in list.toMutableList().indices) {
        list[i] = list[i] - mean
    }
    return list
}

/**
 * Средняя (3 балла)
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int {
    var c = 0
    for (i in a.indices) {
        c += a[i] * b[i]
    }
    return c
}
/**
 * Средняя (3 балла)
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многчлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */


fun polynom(p: List<Int>, x: Int): Int {
    if (p.isEmpty()) return 0
    var res = p[0]
    for (i in 1 until p.size){
        res += p[i] * x.toDouble().pow(i).toInt()
    }
    return res
}

/**
 * Средняя (3 балла)
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */




fun accumulate(list: MutableList<Int>): MutableList<Int> {
    for (i in list.size downTo 1){
        var sum = 0
        for (j in 0 until i){
            sum += list[j]
        }
        list[i - 1] = sum
    }
    return list
}

/**
 * Средняя (3 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */


fun factorize(n: Int): List<Int> {
    var list = mutableListOf<Int>()
    var n = n
    var i = 2
    while (!lesson3.task1.isPrime(n)){
        if (n % i == 0 && lesson3.task1.isPrime(i)) {
            list.add(i)
            n /= i
            i = 1
        }
        i++
    }
    list.add(n)
    return list

}

/**
 * Сложная (4 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = factorize(n).joinToString("*")

/**
 * Средняя (3 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */


fun convert(n: Int, base: Int): List<Int> {
    var list = mutableListOf<Int>()
    var n = n
    if (n == 0) return listOf(0)
    while (n > 0) {
        list.add(n % base)
        n /= base
    }
    return list.toList().reversed()
}

/**
 * Сложная (4 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */


fun convertToString(n: Int, base: Int): String {
    var res = ""
    var n = n
    if (n == 0) return "0"
    while (n > 0) {
        if (n % base >= 10) {
            res += 'a' + (n % base) - 10
        } else {
            res += (n % base)
        }
        n /= base
    }
    return res.reversed()
}

/**
 * Средняя (3 балла)
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var res = 0
    for (i in digits.indices){
        res += digits[i] * base.toDouble().pow(digits.size - i - 1).toInt()
    }
    return res
}

/**
 * Сложная (4 балла)
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int {
    var res = 0
    for (i in str.indices){
        if (str[i] in listOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')){
            res += str[i].digitToInt() * base.toDouble().pow(str.length - i - 1).toInt()
        } else {
            res += (str[i] - 'a' + 10) * base.toDouble().pow(str.length - i - 1).toInt()
        }

    }
    return res
}

/**
 * Сложная (5 баллов)
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String = TODO()

/**
 * Очень сложная (7 баллов)
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */

fun edin_10_0(n: Int): String {
    return when (n) {
        1 -> "один"
        2 -> "два"
        3 -> "три"
        4 -> "четыре"
        5 -> "пять"
        6 -> "шесть"
        7 -> "семь"
        8 -> "восемь"
        9 -> "девять"
        else -> ""
    }
}
fun dec_10_1(n: Int) : String{
    return when (n % 10) {
        2 -> "двадцать"
        3 -> "тридцать"
        4 -> "сорок"
        5 -> "пятьдесят"
        6 -> "шестьдесят"
        7 -> "семьдесят"
        8 -> "восемьдесят"
        9 -> "девяносто"
        else -> ""
    }
}


fun decatki(n: Int): List<String> {
    return when (n % 100) {
        10 -> listOf("десять")
        11 -> listOf("одиннадцать")
        12 -> listOf("двенадцать")
        13 -> listOf("тринадцать")
        14 -> listOf("четырнадцать")
        15 -> listOf("пятьнадцать")
        16 -> listOf("шестьнадцать")
        17 -> listOf("семьнадцать")
        18 -> listOf("восемьнадцать")
        19 -> listOf("девятнадцать")
        else -> listOf(dec_10_1(n / 10), edin_10_0(n % 10))
    }
}

fun cotie(n: Int): String {
    return when (n % 10) {
        1 -> "сто"
        2 -> "двести"
        3 -> "триста"
        4 -> "четыреста"
        5 -> "пятьсот"
        6 -> "шестьсот"
        7 -> "семьсот"
        8 -> "восемьсот"
        9 -> "девятьсот"
        else -> ""
    }
}

fun main(){
    val i = 712851
    println("$i - ${russian(i)}")
//    for (i in 1..999){
//        println("$i - ${russian(i)}")
//    }

}

fun russian(n: Int): String {
    val len = digitNumber(n)
    var res = mutableListOf<String>()
    var n = n
    for (i in decatki(n)){
        if (i != ""){
            res.add(i)
        }
    }
    n /= 100
    if (cotie(n) != ""){
        res.add(0, cotie(n))
    }
    n /= 10
    if (len > 3){
        var local = mutableListOf<String>()
        for (i in decatki(n)){
            if (i != ""){
                local.add(when (i) {
                    "один" -> "одна"
                    "два" -> "две"
                    else -> i
                })
            }
        }
        if (n % 10 in 2..4 && n % 100 !in 12..14){
            local.add("тысячи")
        } else if (n == 1) {
            local.add("тысяча")
        } else {
            local.add("тысяч")
        }
        res.add(0, local.joinToString(" "))
        n /= 100
        if (cotie(n) != ""){
            res.add(0, cotie(n))
        }
    }
    return res.joinToString(" ")
}