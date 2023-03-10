@file:Suppress("UNUSED_PARAMETER")

package lesson11.task1
import ru.spbstu.wheels.defaultCopy
import kotlin.math.pow

/**ё
 * Класс "полином с вещественными коэффициентами".
 *
 * Общая сложность задания -- средняя, общая ценность в баллах -- 16.
 * Объект класса -- полином от одной переменной (x) вида 7x^4+3x^3-6x^2+x-8.
 * Количество слагаемых неограничено.
 *
 * Полиномы можно складывать -- (x^2+3x+2) + (x^3-2x^2-x+4) = x^3-x^2+2x+6,
 * вычитать -- (x^3-2x^2-x+4) - (x^2+3x+2) = x^3-3x^2-4x+2,
 * умножать -- (x^2+3x+2) * (x^3-2x^2-x+4) = x^5+x^4-5x^3-3x^2+10x+8,
 * делить с остатком -- (x^3-2x^2-x+4) / (x^2+3x+2) = x-5, остаток 12x+16
 * вычислять значение при заданном x: при x=5 (x^2+3x+2) = 42.
 *
 * В конструктор полинома передаются его коэффициенты, начиная со старшего.
 * Нули в середине и в конце пропускаться не должны, например: x^3+2x+1 --> Polynom(1.0, 2.0, 0.0, 1.0)
 * Старшие коэффициенты, равные нулю, игнорировать, например Polynom(0.0, 0.0, 5.0, 3.0) соответствует 5x+3
 */
class Polynom(vararg val coeffs: Double) {
    val size = coeffs.size
    /**
     * Геттер: вернуть значение коэффициента при x^i
     */
    fun coeff(i: Int): Double = coeffs[coeffs.size - i - 1]

    /**
     * Расчёт значения при заданном x
     */
    fun getValue(x: Double): Double =
        coeffs.foldIndexed(0.0) { index, previousResult, element ->
            previousResult + element * x.pow(size - index - 1) }

    /**
     * Степень (максимальная степень x при ненулевом слагаемом, например 2 для x^2+x+1).
     *
     * Степень полинома с нулевыми коэффициентами считать равной 0.
     * Слагаемые с нулевыми коэффициентами игнорировать, т.е.
     * степень 0x^2+0x+2 также равна 0.
     */
    fun degree(): Int =  coeffs.dropWhile { it == 0.0 }.size - if (size == 1) 0 else 1

    /**
     * Сложение
     */
    operator fun plus(other: Polynom): Polynom {
        val (biggest, least) = if (other.size > size) Pair(other, this) else Pair(this, other)
        val diff = biggest.size - least.size
        return Polynom(coeffs = biggest.coeffs.mapIndexed { i, element ->
            if (i >= diff)
                least.coeffs[i - diff] + element else element
        }.toDoubleArray())
    }

    /**
     * Смена знака (при всех слагаемых)
     */
    operator fun unaryMinus(): Polynom = Polynom(coeffs = coeffs.map { -it }.toDoubleArray())

    /**
     * Вычитание
     */
    operator fun minus(other: Polynom): Polynom = plus(-other)

    /**
     * Умножение
     */
    operator fun times(other: Polynom): Polynom {
        val newPolynomMaxDegree = degree() + other.degree()
        val newCoeffs = DoubleArray(newPolynomMaxDegree + 1) { 0.0 }
        for (i in other.coeffs.indices) {
            for (j in coeffs.indices) {
                if (other.coeffs[i] != 0.0 && coeffs[j] != 0.0) {
                    val firstDegree = if (other.coeffs[i] == 0.0) 0 else other.coeffs.size - 1 - i
                    val secondDegree = if (coeffs[i] == 0.0) 0 else coeffs.size - 1 - j
                    val newDegree = firstDegree + secondDegree
                    newCoeffs[newCoeffs.size - newDegree - 1] += coeffs[j] *
                            other.coeffs[i]
                }
            }
        }
        return Polynom(coeffs = newCoeffs)
    }

    /**
     * Деление
     *
     * Про операции деления и взятия остатка см. статью Википедии
     * "Деление многочленов столбиком". Основные свойства:
     *
     * Если A / B = C и A % B = D, то A = B * C + D и степень D меньше степени B
     */
    operator fun div(other: Polynom): Polynom {
        var divisible = copy()
        val currentPrivate = DoubleArray(divisible.degree() - other.degree() + 1) { 0.0 }
        val result = mutableListOf<Double>()
        var currentDegree = 0
        while (divisible.degree() >= other.degree()) {
            val nowCoeff = divisible.coeff(divisible.degree()) / other.coeff(other.degree())
            currentPrivate[currentDegree] = nowCoeff
            result.add(nowCoeff)
            divisible -= other * Polynom(coeffs = currentPrivate)
            currentPrivate[currentDegree++] = 0.0
        }
        return Polynom(coeffs = result.toDoubleArray())
    }

    /**
     * Взятие остатка
     */
    operator fun rem(other: Polynom): Polynom = this - other * (this / other)

    /**
     * Сравнение на равенство
     */
    override fun equals(other: Any?): Boolean = other is Polynom && other.coeffs.contentEquals(
        coeffs
    )

    /**
     * Получение хеш-кода
     */
    override fun hashCode(): Int = super.hashCode()

    fun copy(): Polynom = Polynom(coeffs = coeffs)
}


