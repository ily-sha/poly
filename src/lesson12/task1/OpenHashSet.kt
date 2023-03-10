@file:Suppress("UNUSED_PARAMETER")

package lesson12.task1

import lesson5.task1.containsIn

/**
 * Класс "хеш-таблица с открытой адресацией"
 *
 * Общая сложность задания -- сложная, общая ценность в баллах -- 20.
 * Объект класса хранит данные типа T в виде хеш-таблицы.
 * Хеш-таблица не может содержать равные по equals элементы.
 * Подробности по организации см. статью википедии "Хеш-таблица", раздел "Открытая адресация".
 * Методы: добавление элемента, проверка вхождения элемента, сравнение двух таблиц на равенство.
 * В этом задании не разрешается использовать библиотечные классы HashSet, HashMap и им подобные,
 * а также любые функции, создающие множества (mutableSetOf и пр.).
 *
 * В конструктор хеш-таблицы передаётся её вместимость (максимальное количество элементов)
 */
class OpenHashSet<T>(val capacity: Int) {

    /**
     * Массив для хранения элементов хеш-таблицы
     */
    internal val elements = Array<Any?>(capacity) { null }
//    private fun currentSize() = elements.filterNotNull().size


    /**
     * Число элементов в хеш-таблице
     */
    val size: Int get() = elements.filterNotNull().size

    /**
     * Признак пустоты
     */
    fun isEmpty(): Boolean = elements.filterNotNull().isEmpty()
    /**
     * Добавление элемента.
     * Вернуть true, если элемент был успешно добавлен,
     * или false, если такой элемент уже был в таблице, или превышена вместимость таблицы.
     */
    fun add(element: T): Boolean {
        if (element in elements || elements.filterNotNull().size >= capacity) return false
        elements[elements.filterNotNull().size] = element
        return true
    }

    /**
     * Проверка, входит ли заданный элемент в хеш-таблицу
     */
    operator fun contains(element: T): Boolean = elements.contains(element)

    /**
     * Таблицы равны, если в них одинаковое количество элементов,
     * и любой элемент из второй таблицы входит также и в первую
     */
    override fun equals(other: Any?): Boolean {
        if (other is OpenHashSet<*> && other.elements.filterNotNull().size == elements.filterNotNull().size) {
            for (i in other.elements) {
                if (i !in elements) return false
            }
            return true
        }
        return false
    }


    override fun hashCode(): Int = super.hashCode()
}