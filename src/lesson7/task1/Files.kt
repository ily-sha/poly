@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson7.task1

import java.io.File
import kotlin.math.max
import kotlin.math.roundToInt
import kotlin.text.Typography.less

// ���� 7: ������ � �������
// ���� ������������, ������� ��� ������ ����� ������ ����������� ���������
// ������������ ���������� ������ = 55
// ������������� ���������� ������ = 20
// ������ � ����������� ������� (���� ������, 3-7) = 55/103

/**
 * ������
 *
 * �� ������� ����� � ������ inputName ���������� ��������� �����.
 * ������� ��� � �������� ���� � ������ outputName, �������� �� ������ ����,
 * ����� ����� ������ ������ �� ������������ lineLength.
 * ����� � ������� ������� ������� ������� ���������� �� ��������� ������.
 * ������� �������� ������ ������� ��������� ������� �� ��������� ������.
 * ������ ������ �� ������� ����� ���������� ����� ������,
 * �� ������� ��������� � � �������� �����
 */
fun alignFile(inputName: String, lineLength: Int, outputName: String) {
    val writer = File(outputName).bufferedWriter()
    var currentLineLength = 0
    fun append(word: String) {
        if (currentLineLength > 0) {
            if (word.length + currentLineLength >= lineLength) {
                writer.newLine()
                currentLineLength = 0
            } else {
                writer.write(" ")
                currentLineLength++
            }
        }
        writer.write(word)
        currentLineLength += word.length
    }
    for (line in File(inputName).readLines()) {
        if (line.isEmpty()) {
            writer.newLine()
            if (currentLineLength > 0) {
                writer.newLine()
                currentLineLength = 0
            }
            continue
        }
        for (word in line.split(Regex("\\s+"))) {
            append(word)
        }
    }
    writer.close()
}

/**
 * ������� (8 ������)
 *
 * �� ������� ����� � ������ inputName ���������� ��������� �����.
 * ��������� ��� ������ �������� �� �������� ������ �������� _ (�������������).
 * ��������� � �������� ���� � ������ outputName ��� ������ �������� �����, ����� ��� ���� ���������� �� ��������.
 * ��� ��������� ������ ������ ���� ���������� ��� ���������, ������� ������ ������.
 * ������������� � �������� �/��� � ����� ����� �������� �� �����.
 */


fun deleteMarked(inputName: String, outputName: String) {
    val writer = File(outputName).bufferedWriter()
    writer.use { writer ->
        val file = File(inputName)
        for (i in file.readLines()) {
            if (i == "") {
                writer.newLine()
            } else if (i[0] != '_') {
                writer.write(i)
                writer.newLine()
            }
        }
    }

}

/**
 * ������� (14 ������)
 *
 * �� ������� ����� � ������ inputName ���������� ��������� �����.
 * �� ���� ������� ������ ����� substrings.
 * ������� ������������� ������ � ������ ��������� ������ �� ����� � �����.
 * ������� ���� ������������, �� ���� ����� � � � ������� �����������.
 *
 */
//




fun countSubstrings(inputName: String, substrings: List<String>): Map<String, Int> {
    val map = mutableMapOf<String, Int>()
    for (j in File(inputName).readLines()) {
        for (i in substrings.toSet()) {
            if (i !in map) {
                map[i] = 0
            }
            var pattern = i.lowercase().toRegex()
            if (listOf(".").contains(i)) {
                pattern = Regex("""\$i""")
            }
            var start = 0
            while (pattern.find(j.lowercase(), startIndex = start) != null) {
                start = pattern.find(j.lowercase(), startIndex = start)!!.range.first + 1
                map[i] = map[i]!! + 1

            }

        }
    }

    return map
}


/**
 * ������� (12 ������)
 *
 * � ������� �����, ��� �������, ����� ���� �, �, �, � ������� �, �, �, � �� �, �, �.
 * �� ������� ����� � ������ inputName ���������� ��������� ����� �� ������� �����.
 * ��������� ����� �� ������� ����� �� ���������� ������� ������� � ������� � ��������
 * ���� outputName ����� � ������������� ��������.
 *
 * ������� ���������� ���� ������� ���������.
 *
 * ���������� (����, �������, �������) � ������ ������� ������� ������������ �� �����
 *
 */

val map = mapOf('�' to '�', '�' to '�', '�' to '�', '�' to '�', '�' to '�', '�' to '�')
fun sibilants(inputName: String, outputName: String) {
    val outputFile = File(outputName).bufferedWriter()
    outputFile.use { outputFile ->
        for (i in File(inputName).readLines()) {
            var str = i
            for (i in Regex("""[����](?=[���])""").findAll(i.lowercase())) {
                str = str.replaceRange(i.range.last + 1, i.range.last + 2, map[str[i.range.last + 1]].toString())
            }
            outputFile.write(str)
            outputFile.newLine()

        }
    }
}

/**
 * ������� (15 ������)
 *
 * �� ������� ����� � ������ inputName ���������� ��������� ����� (� ��� �����, � �� ������� �����).
 * ������� ��� � �������� ���� � ������ outputName, �������� �� ������
 * ������������ ����� ������� ������.
 *
 * ������������ ������� ����������� ���� ���������� �������� � ������ ������.
 *
 *
 * ��������� ������� ������ ���� ���������:
 * 1) ������� � ������ � � ����� ���� ����� �� ������� ���������.
 * 2) � ������ ������������� ������������ ������ �� ������, ������ ������ ���� �������� � ����� �������
 * 3) ������ ������ �� �������� ������ �������, �� ���� ������� �����������
 * 4) ����� ����� � �������� ����� ������ ���� ����� ����� ����� �� ������� (� �. �. ������)
 *
 */
fun centerFile(inputName: String, outputName: String) {
    var maxLen = 0
    val outputFile = File(outputName).bufferedWriter()
    for (i in File(inputName).readLines()) {
        if (i.trim().length > maxLen) maxLen = i.trim().length
    }
    outputFile.use { outputFile ->
        for (i in File(inputName).readLines()) {
            var addN = maxLen - i.trim().length
            if (addN % 2 != 0) {
                addN -= 1
            }
            addN /= 2
            val newStr = " ".repeat(addN) + i.trim()
            outputFile.write(newStr)
            outputFile.newLine()

        }
    }
}

/**
 * ������� (20 ������)
 *
 * �� ������� ����� � ������ inputName ���������� ��������� ����� (� ��� �����, � �� ������� �����).
 * ������� ��� � �������� ���� � ������ outputName, �������� �� ������ � ������� ���� ������������
 * ����� ������� ������.
 * ������������ �����������, �������� �������������� ������� ����� �������: ���������� �� ���� ������
 *
 * ����� ������ ������ ���������� ���� �� ����� ����� ��� ����� ��������.
 *
 * ��������� ������� ������ ���� ���������:
 * 1) ������ ������ �������� � ��������� ����� �� ������ ���������� ��� ������������� ��������.
 * 2) ������ ������ ��� ������ �� �������� ���������������� � ������ ������ ��� ��������.
 * 3) ������ �� ������ ����� ��������� ��� ��������.
 * 4) ����� ����� � �������� ����� ������ ���� ����� ����� ����� �� ������� (� �. �. ������).
 *
 * ������������� ������������ ���������� ����������� ���������:
 * 5) ����� �������� ����� ������� ����� ������ �������� ���� �� ������ ���������� �����, ��� �� 1.
 * 6) ����� �������� ����� ����� ����� ����� �������� ���� ������ ���� ������ ��� ����� ����� ��������
 *    ����� ����� ������ ����� �������� ����.
 *
 * ������� ������, ��� ������� ���� ����� ��������� ������������������ �� ���������� ��������  ����� �������. �����
 * ������������������ ������� ��������� ��� ������������ � ��� ������������� ����������� �� ������ ��������.
 * �� ����� ������� ��������� �������:
 * 7) � ����� ������� ������ ������ ���� �������� ���� ������ ���� �������� � �������� ����� ��������
 * 8) ���� ������� ���� ������������� ����������� 1-7, �� �� ������ ���� � �������� ��������� ��������� �����
 */


fun alignFileByWidth(inputName: String, outputName: String) {
    var maxLen = 0
    val outputFile = File(outputName).bufferedWriter()
    for (i in File(inputName).readLines()) {
        val nowLen = Regex("""(\s){2,}""").replace(i.trim(), " ").length
        if (nowLen > maxLen) maxLen = nowLen
    }
    var isFirstLine = true
    outputFile.use { outputFile ->
        for (i in File(inputName).readLines()) {
            val nowLen = Regex("""\s""").replace(i.trim(), "").length
            if (nowLen != 0) {
                val nowWords = Regex("""(\s){2,}""").replace(i.trim(), " ").split(" ").size
                if (nowWords != 1) {
                    val d = (maxLen - nowLen).toDouble() / (nowWords - 1).toDouble()
//                    println("$d, ${i.trim()}, $nowLen")
                    var predominantNumber = (nowWords - 1) / 2
                    var more = d.roundToInt()
                    var less = 0
                    less = if (d % 1.0 >= 0.5) more - 1
                    else more + 1
                    while (predominantNumber * more + (nowWords - 1 - predominantNumber) * less != maxLen - nowLen) {
                        predominantNumber++
                    }
//                    println("$predominantNumber - $more, ${(nowWords - 1 - predominantNumber)} - $less")
                    var resStr = ""
                    val allWords = Regex("""(\s){2,}""").replace(i.trim(), " ").split(" ")
                    if (less > more) {
                        val less2 = less
                        less = more
                        more = less2
                        predominantNumber = nowWords - 1 - predominantNumber
                    }
                    for (j in allWords.indices) {
                        resStr += if (j < predominantNumber) {
                            allWords[j] + " ".repeat(more)
                        } else {
                            if (j != allWords.size - 1) allWords[j] + " ".repeat(less) else allWords[j]
                        }
                    }
                    if (!isFirstLine) outputFile.newLine()
                    outputFile.write(resStr)

                } else {
                    if (!isFirstLine) outputFile.newLine()

                    outputFile.write(i.trim())
                }

            } else {
                if (!isFirstLine) outputFile.newLine()
                outputFile.write("")
            }
            if (isFirstLine) isFirstLine = false
        }

    }
}

/**
 * ������� (14 ������)
 *
 * �� ������� ����� � ������ inputName ���������� ��������� ����� (� ��� �����, � �� ������� �����).
 *
 * ������� ������������� ������, ���������� 20 �������� ����� ������������� ���� � �� �����������.
 * ���� � ������ ����� 20 ��������� ����, ������� ��� �����.
 * ������� ������������� ������ � ������ ���� ������ 20, ���� 20-�, 21-�, ..., ��������� �����
 * ����� ���������� ���������� ��������� (��. ����� ���� ����� input/onegin.txt).
 *
 * ������ ��������� ����������� ������������������ �� ���� (�������������,
 * ���� ���������, ��� ������ ���������� � ����).
 * �����, �������, ����� ���������� ��������� ������������� ����:
 * ������, ������42, ������!!! -������?!
 * ^ � ���� ������� ����� ������ ����������� 4 ����.
 *
 * ������� ���� ������������, �� ���� ����� � � � ������� �����������.
 * ����� � ������������� ������� ������ ���� � ������ ��������.
 *
 */
fun main(){
    TODO()
}

fun top20Words(inputName: String): Map<String, Int> {

    val map = mutableMapOf<String, Int>()
    for (i in File(inputName).readLines()) {
        println(i)
        for (j in Regex("""[�-�A-z�]+""").findAll(i.lowercase())) {
            if (j.value != "") {
                val element = map[j.value.lowercase()]
                if (element != null) {
                    map[j.value.lowercase()] = element + 1
                } else {
                    map[j.value.lowercase()] = 1
                }
            }
        }
    }
    var resMap = mutableMapOf<String, Int>()
//    println(map)
    val list = map.entries.sortedByDescending { it.value }
//    println(list)
    if (map.keys.size > 20) {
        for (i in list.subList(0, 20)) {
            resMap[i.key] = i.value
        }
    } else resMap = map
//    println(resMap)
    return resMap
}

/**
 * ������� (14 ������)
 *
 * ����������� �������������� ������ �� �������� ����� � �������� ���� ����������� ����������� ���������� ������.

 * �� ������� ����� � ������ inputName ���������� ��������� ����� (� ��� �����, � �� ������� �����).
 *
 * � ������������� ������� dictionary ���������� �������, � ������� ��������� ��������
 * �������� � ������������ ������� �� ��������, ��������
 * mapOf('�' to "zz", '�' to "r", '�' to "d", '�' to "y", '�' to "m", '�' to "yy", '!' to "!!!")
 *
 * ���������� ������� � �������� ���� � ������ outputName
 * ���������� ������ � ������� ���� �������� �� ������� �� ��������������� �� ������.
 *
 * ��� ���� ������� �������� � ������� ������ ��������������,
 * �� ��� ������ ������ � ������� �������� ������������ � ������, ������������ � ������� � ������� ��������.
 *
 * ������.
 * ������� �����: ����������, ���!
 *
 * ���������� ��
 *
 * �������� �����: Zzdr������y, m��!!!
 *
 * ������ 2.
 *
 * ������� �����: ����������, ���!
 * �������: mapOf('�' to "zZ", '�' to "r", '�' to "d", '�' to "y", '�' to "m", '�' to "YY", '!' to "!!!")
 *
 * ���������� ��
 *
 * �������� �����: Zzdr������y, m��!!!
 *
 * �������� ��������: ������ ������� �� ����� ������������� ��������
 */
fun transliterate(inputName: String, dictionary: Map<Char, String>, outputName: String) {
    TODO()
}

/**
 * ������� (12 ������)
 *
 * �� ������� ����� � ������ inputName ������� ������� � ����� ������ � ������ �������.
 * ������� �� ������� ������� �������� ������� �����,
 * � ������� ��� ����� ������, ��������: ������������, ��������������.
 * ������� ��� � �������� ���� � ������ outputName.
 * ���� �� ������� ����� ������� ��������� ���� � ���������� ������, � ������� ��� ����� ������,
 * � �������� ���� ������� ������� �� ��� ����� �������.
 * ������� ���� ������������, �� ���� ����� � � � ������� �����������.
 *
 * ������ �������� �����:
 * ����������
 * ���������
 * ����������
 * ����������
 * ����������
 * ����������

 * ��������������� �������� ����:
 * ����������, ����������
 *
 * �������� ��������: ������ ������� �� ����� ������������� ��������
 */
fun chooseLongestChaoticWord(inputName: String, outputName: String) {
    TODO()
}

/**
 * ������� (22 �����)
 *
 * ����������� �������������� ������ � �������� ������� �������� � ������ �������� HTML.
 *
 * �� ������� ����� � ������ inputName ���������� �����, ���������� � ���� �������� ��������� �������� ��������� �����:
 * - *����� � ��������� ����������* -- ������
 * - **����� � ���������� ����������** -- ����������
 * - ~~����������� �����~~ -- ������������
 *
 * ������� ������� � �������� ���� ���� �� ����� � ������� HTML:
 * - <i>����� � ��������� ����������</i>
 * - <b>����� � ���������� ����������</b>
 * - <s>����������� �����</s>
 *
 * ����� ����, ��� ������ ��������� ������, ��������� ���� �� ����� ������� ��������, ������� �������� � ���� <p>...</p>,
 * � ���� ����� ������� � ���� <html><body>...</body></html>.
 *
 * ��� ��������� ����� ��������� ������ ������ �������� ����������� � ��������� �� ������� �������� � ��������� �����.
 * �������� ������� ��������, ��� ����������� ������������������ �� ��� �������� (***) ������ ������������ ��� "<b><i>"
 * � ����� �����.
 *
 * ��� ������� ���� � ���� ��������� ����� ������� ��������� ������ ��������� "����".
 *
 * ������ �������� �����:
Lorem ipsum *dolor sit amet*, consectetur **adipiscing** elit.
Vestibulum lobortis, ~~Est vehicula rutrum *suscipit*~~, ipsum ~~lib~~ero *placerat **tortor***,

Suspendisse ~~et elit in enim tempus iaculis~~.
 *
 * ��������������� �������� ����:
<html>
<body>
<p>
Lorem ipsum <i>dolor sit amet</i>, consectetur <b>adipiscing</b> elit.
Vestibulum lobortis. <s>Est vehicula rutrum <i>suscipit</i></s>, ipsum <s>lib</s>ero <i>placerat <b>tortor</b></i>.
</p>
<p>
Suspendisse <s>et elit in enim tempus iaculis</s>.
</p>
</body>
</html>
 *
 * (������� � �������� ����� � ������� ��������� ��� �����������, ��� ������� ������ �� ������������� �� �����������)
 */


fun markdownToHtmlSimple(inputName: String, outputName: String) {
    TODO()
//    val output = File(outputName).bufferedWriter()
//    output.newLine()
//    output.write("<html>")
//    output.newLine()
//    output.write("<body>")
//    output.newLine()
//    output.write("<p>")
//    for (i in File(inputName).readLines()) {
////        var str = i
////        var str = "j_[`F~~\\nY**\\\"L!@5R\\n3**S|:b?[LOf64Z&51A=@\\\"H:y0**Y**Ac);~~Ne\\n~~ ~~wX**b3WQsk\\nz\\n*Q}BeL*dFM **\\\"*J2*,~~p4i-{'vke6**]R*A*g*l*=*p/Py*vb9@5k7ar8Vz**`^~~QF/p**7cT**\\\"f~~3f+#Jx5VBz#qD* **R`ix? V** * **\\nYX ,O* *=\$%2]r**Q4Ks]/t~~-*\$^\\\"*Qj**:*E@* ** **Qp**M,DMD**.*/Vv5ED*qh5**BX\\nZqs,%{383ZlhNc["
//        var str = "Vestibulum lobortis, ~~Est vehicula rutrum *suscipit*~~, ipsum ~~lib~~ero *placerat **tortor***,\n"
//        if (i == "") {
//            output.newLine()
//            output.write("</p>")
//            output.newLine()
//            output.write("<p>")
//        }
//        str = Regex("""~~(?=(!|@|#|$|}|%|^|&|\+|-|]|\w))""").replace(str, "<s>")
//
//
//        str = Regex("""(?<=(!|@|#|${'$'}|}|%|^|&|\+|-|]|\w|>|<))~~""").replace(str, "</s>")
//        str = Regex("""\*\*\*(?=(!|@|#|${'$'}|}|%|^|&|\+|-|]|\w|>|<))""").replace(str, "<b><i>")
//        str = Regex("""(?<=(!|@|#|${'$'}|}|%|^|&|\+|-|]|\w))\*\*\*""").replace(str, "</b></i>")
//        str = Regex("""(?<=(!|@|#|${'$'}|}|%|^|&|\+|-|]|\w))\*\*""").replace(str, "</b>")
//        str = Regex("""\*\*(?=(!|@|#|${'$'}|}|%|^|&|\+|-|]|\w))""").replace(str, "<b>")
//        str = Regex("""(?<=(!|@|#|${'$'}|}|%|^|&|\+|-|]|\w))\*""").replace(str, "</i>")
//        str = Regex("""\*(?=(!|@|#|${'$'}|}|%|^|&|\+|-|]|\w))""").replace(str, "<i>")
//        output.newLine()
//        output.write(str)
//        println(str)
//    }
//    output.newLine()
//    output.write("</p>")
//    output.newLine()
//    output.write("</body>")
//    output.newLine()
//    output.write("</html>")
//    output.close()

}

/**
 * ������� (23 �����)
 *
 * ����������� �������������� ������ � �������� ������� �������� � ������ �������� HTML.
 *
 * �� ������� ����� � ������ inputName ���������� �����, ���������� � ���� ����� ��������� ���� � ����� �������.
 * ������ ������ ���� �����: ������������ � ��������������.
 *
 * ������ ������� ��������������� ������ ���������� � ����� ������ � ������� '*', ������ ������� ������������� ������ --
 * � ����� ������, ����� � �����. ������ ������� ���������� ������ ���������� � ������� �� ��������, �� 4 ������� ��������,
 * ��� ������-��������. ����������� ������� ����������� ������� ����� ��������� 6. "�������" ������ ����� ���������
 * ����� � ������ ������.
 *
 * ������� ������� ���� �� ����� � �������� ���� � ������� HTML:
 * ������������ ������:
 * <ol>
 *     <li>���</li>
 *     <li>���</li>
 *     <li>���</li>
 * </ol>
 *
 * �������������� ������:
 * <ul>
 *     <li>���</li>
 *     <li>���</li>
 *     <li>���</li>
 * </ul>
 *
 * ����� ����, ���� ����� ������� ������� �������� � ���� <html><body><p>...</p></body></html>
 *
 * ��� ��������� ����� ��������� ������ ������ �������� ����������� � ��������� �� ������� �������� � ��������� �����.
 *
 * ������ �������� �����:
///////////////////////////////������ �����/////////////////////////////////////////////////////////////////////////////
 * ���� ��-��������
 * ����
 * ����
 * ����� ������
1. ����
 * ��� �������
2. �������
3. ���������
4. ���-�� ��� ���
 * ��������
 * ������
1. ������
23. ������
1. �������
2. ������
///////////////////////////////����� �����//////////////////////////////////////////////////////////////////////////////
 *
 *
 * ��������������� �������� ����:
///////////////////////////////������ �����/////////////////////////////////////////////////////////////////////////////
<html>
<body>
<p>
<ul>
<li>
���� ��-��������
<ul>
<li>����</li>
<li>����</li>
</ul>
</li>
<li>
����� ������
<ol>
<li>����
<ul>
<li>��� �������</li>
</ul>
</li>
<li>�������</li>
<li>���������</li>
<li>���-�� ��� ���</li>
</ol>
</li>
<li>��������</li>
<li>������
<ol>
<li>������</li>
<li>������
<ol>
<li>�������</li>
<li>������</li>
</ol>
</li>
</ol>
</li>
</ul>
</p>
</body>
</html>
///////////////////////////////����� �����//////////////////////////////////////////////////////////////////////////////
 * (������� � �������� ����� � ������� ��������� ��� �����������, ��� ������� ������ �� ������������� �� �����������)
 */
fun markdownToHtmlLists(inputName: String, outputName: String) {
    TODO()
}

/**
 * ����� ������� (30 ������)
 *
 * ����������� �������������� �� ���� ���������� ����� ������������ ��� ����� � ��� �� ������.
 * ������� �������, ���:
 * - ������, ��������� ���� �� ����� ������ �������, �������� ������� � ������ ��������� � ������ ���������� ��������� �����.
 *
 */
fun markdownToHtml(inputName: String, outputName: String) {
    TODO()
}

/**
 * ������� (12 ������)
 *
 * ������� � �������� ���� ������� ��������� ��������� ����� lhv (> 0) �� ����� rhv (> 0).
 *
 * ������ (��� lhv == 19935, rhv == 111):
19935
 *    111
--------
19935
+ 19935
+19935
--------
2212785
 * ������������ �������, ������� � ������ ������ � �������� ��������������� �������.
 * ���� � ��������� ������������ ��� ��, ��� � ��������� �����:
235
 *  10
-----
0
+235
-----
2350
 *
 */
fun printMultiplicationProcess(lhv: Int, rhv: Int, outputName: String) {
    TODO()
}


/**
 * ������� (25 ������)
 *
 * ������� � �������� ���� ������� ������� ��������� ����� lhv (> 0) �� ����� rhv (> 0).
 *
 * ������ (��� lhv == 19935, rhv == 22):
19935 | 22
-198     906
----
13
-0
--
135
-132
----
3

 * ������������ �������, ������� � ������ ������ � �������� ��������������� �������.
 *
 */
fun printDivisionProcess(lhv: Int, rhv: Int, outputName: String) {
    TODO()
}

