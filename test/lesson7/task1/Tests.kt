package lesson7.task1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import java.io.File

class Tests {

    private fun assertFileContent(name: String, expectedContent: String) {
        val file = File(name)
        val content = file.readLines().joinToString("\n")
        assertEquals(expectedContent, content)
    }

    @Test
    @Tag("Example")
    fun alignFile() {
        alignFile("input/align_in1.txt", 50, "temp.txt")
        assertFileContent(
            "temp.txt",
            """��� ��������� ������ ����� �������� ������
����������� ������ ����� ����������������.
��������, � ����� ��������� �������� ������ ������
��� ����� Swift (��������� ���������� ���
����������� iOS) � Java (���������� ���
����������� Android). ��������� ���������, ���
�������, ������� �� ������ C ��� {cpp}. ��� ��
����� ������ ����� �������������� � ��� ��������
������������ ��������, �� � ��������� ���� � ����
������� �������� ������������ ���� Java. ���
��������� web-�������� ����� ������������
JavaScript, � � ������� ������� -- ���� ��������
������� HTML. Web-������� ���������� �����-����
Java (� ������� �������), � ����� Python � PHP (�
����� �������). �������, ������� desktop-���������
������ ����� ���� �������� �� ����� ������ ������,
� ����� �� ������ ������� �� ��������� ���������,
������� � �������������, ��������������
������������ �������. � ������ ������� �������
������� ����� Java, {cpp}, C#, Python, Visual
Basic, Ruby, Swift.

����� ������������� � ������������ �����
��������������� ������ ���������������� �� ������
������ ������� ������� ���� Java. Java � �������
������ -- �� ������ ����, �� � ��������� ���
���������� �������� ��� ������ �������
������������� ��������� � �� ������ ����������.
����� ��������������� �������������� ��������
����������� ������ Java -- ��������� ���������,
���������������� Java ����-��� � �������� ����
����������� ���������� ��� �������. Java �����
�������� ���������� ����� ��������� ���
����������."""
        )
        File("temp.txt").delete()
    }

    @Test
    @Tag("8")
    fun deleteMarked() {
        deleteMarked("input/delete_in1.txt", "temp.txt")
        assertFileContent(
            "temp.txt",
            """������ _����_ ������ ���������,

� �� ���� ��� ���� ������ ����������___
            """.trimIndent()
        )
        File("temp.txt").delete()
    }

    @Test
    @Tag("14")
    fun countSubstrings() {
//        assertEquals(
//            mapOf("���" to 1),
//            countSubstrings("input/substrings_in1.txt", listOf("���"))
//        )
//        assertEquals(
//            mapOf("��" to 2),
//            countSubstrings("input/substrings_in1.txt", listOf("��", "��"))
//        )
//        assertEquals(
//            mapOf("������" to 2, "���" to 2, "������������" to 1, "�" to 49, "��������" to 0),
//            countSubstrings("input/substrings_in1.txt", listOf("������", "���", "������������", "�", "��������"))
//        )
//        assertEquals(
//            mapOf("��" to 2),
//            countSubstrings("input/substrings_in1.txt", listOf("��", "��"))
//        )
//        assertEquals(
//            mapOf("����������" to 2, "����������" to 2, "����������" to 1),
//            countSubstrings("input/substrings_in1.txt", listOf("����������", "����������", "����������"))
//        )
        assertEquals(
            mapOf("--" to 4, "��" to 2, "��������" to 2, "." to 2),
            countSubstrings("input/substrings_in2.txt", listOf("--", "��", "��������", "."))
        )
    }

    @Test
    @Tag("12")
    fun sibilants() {
        sibilants("input/sibilants_in1.txt", "temp.txt")
        assertFileContent(
            "temp.txt",
            """/**
 * �������
 *
 * � ������� �����, ��� �������, ����� ���� �, �, �, � ������� �, �, �, � �� �, �, �.
 * �� ������� ����� � ������ inputName ���������� ��������� �����.
 * ��������� ����� �� ������� ����� �� ���������� ������� ������� � ������� � ��������
 * ���� outputName ����� � ������������� ��������.
 *
 * ������� ��������� ���� ������� ���������.
 *
 * ���������� (����, �������, �������) � ������ ������� ������� ������������ �� �����
 *
 * �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� ��
 */"""
        )
        File("temp.txt").delete()
    }

    @Test
    @Tag("15")
    fun centerFile() {
        centerFile("input/center_in1.txt", "temp.txt")
        assertFileContent(
            "temp.txt",
            """              ����� �� ��� ���� ������ ����������� �����, �� ����� ���.
������� �������������� ����� �������� ���� ������ ������ ������� ��������� ���������.
                                        ����
                                          """ +  // Avoiding trailing whitespaces problem
                    """
                                     Hello World
           �� ������� ����� � ������ inputName ���������� ��������� �����.
        ������� ��� � �������� ���� � ������ outputName, �������� �� ������."""
        )
        File("temp.txt").delete()

    }

    @Test
    @Tag("20")
    fun alignFileByWidth() {
        alignFileByWidth("input/width_in1.txt", "temp.txt")
        assertFileContent(
            "temp.txt",
            """�������

��       �������       �����       �       ������       inputName       ����������       ���������      �����.
�������   ���  �  ��������  ����  �  ������  outputName,  ��������  ��  ������  �  �������  ����  ������������
�����                                              �������                                             ������.
������������   �����������,   ��������  ��������������  �������  �����  �������:  ����������  ��  ����  ������

�����     ������     ������     ����������     ����     ��     �����     �����     ���     �����     ��������.

���������                   �������                   ������                  ����                  ���������:
1)     ������     ������     ��������    �    ���������    �����    ��    ������    �������������    ��������.
2) ������ ������ ��� ������ �� �������� �� ������� ����� ������ ������������ � ������ ������ � �������� �����.
3)   �����   �����   �   ��������  �����  ������  ����  �����  �����  �����  ��  �������  (�  �.  �.  ������).

�������������              ������������              ����������             �����������             ���������:
1)  �����  ��������  �����  �������  �����  ������  ��������  ����  ��  ������  ����������  �����,  ���  �� 1.
2)  �����  ��������  �����  �����  �����  �����  ��������  ����  ������  ����  ������ ��� ����� ����� ��������
�����                �����               ������               �����               ��������               ����."""
        )
        File("temp.txt").delete()

    }

    @Test
    @Tag("14")
    fun top20Words() {
//        assertEquals(mapOf<String, Int>(), top20Words("input/empty.txt"))

        assertEquals(mapOf(
            "������" to 4,
            "���" to 3,
            "�" to 3,
            "�����" to 3,
            "��" to 2,
            "let" to 2,
            "us" to 2,
            "write" to 2,
            "some" to 2,
            "digits" to 2
        ), top20Words("input/top20.txt").filter { it.value > 1 })
        assertEquals(
            mapOf(
                "�" to 1106,
                "�" to 674,
                "��" to 411,
                "��" to 306,
                "��" to 290,
                "�" to 261,
                "�" to 261,
                "���" to 211,
                "��" to 210,
                "���" to 187,
                "���" to 131,
                "�" to 130,
                "���" to 126,
                "���" to 109,
                "��" to 105,
                "��" to 104,
                "�" to 98,
                "��" to 95,
                "���" to 95,
                "��" to 95,
                "��" to 95
            ), top20Words("input/onegin.txt")
        )
    }

    @Test
    @Tag("14")
    fun transliterate() {
        transliterate(
            "input/trans_in1.txt",
            mapOf('�' to "zz", '�' to "r", '�' to "d", '�' to "y", '�' to "m", '�' to "yy", '!' to "!!!"),
            "temp.txt"
        )
        assertFileContent("temp.txt", "Zzdr������y,\nmyyr!!!")
        File("temp.txt").delete()

        transliterate(
            "input/trans_in1.txt",
            mapOf('�' to "zZ", '�' to "r", '�' to "d", '�' to "y", '�' to "m", '�' to "YY", '!' to "!!!"),
            "temp.txt"
        )
        assertFileContent("temp.txt", "Zzdr������y,\nmyyr!!!")
        File("temp.txt").delete()
    }

    @Test
    @Tag("12")
    fun chooseLongestChaoticWord() {
        chooseLongestChaoticWord("input/chaotic_in1.txt", "temp.txt")
        assertFileContent("temp.txt", "����������, ����������")
        File("temp.txt").delete()
    }


    private fun checkHtmlSimpleExample() {
        val result = File("temp.html").readText().replace(Regex("[\\s\\n\\t]"), "")
        val expected =
            """
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
                    """.trimIndent().replace(Regex("[\\s\\n\\t]"), "")
        assertEquals(expected, result)

        File("temp.html").delete()
    }

    @Test
    @Tag("22")
    fun markdownToHtmlSimple() {
        markdownToHtmlSimple("input/markdown_simple.md", "temp.html")
        checkHtmlSimpleExample()
    }

    private fun checkHtmlListsExample() {
        val result = File("temp.html").readText().replace(Regex("[\\s\\n\\t]"), "")
        val expected =
            """
                    <html>
                      <body>
                        <p>
                          <ul>
                            <li>���� ��-��������
                              <ul>
                                <li>����</li>
                                <li>����</li>
                              </ul>
                            </li>
                            <li>����� ������
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
                    """.trimIndent().replace(Regex("[\\s\\n\\t]"), "")
        assertEquals(expected, result)

        File("temp.html").delete()
    }

    @Test
    @Tag("23")
    fun markdownToHtmlLists() {
        markdownToHtmlLists("input/markdown_lists.md", "temp.html")
        checkHtmlListsExample()
    }

    @Test
    @Tag("30")
    fun markdownToHtml() {
        markdownToHtml("input/markdown_simple.md", "temp.html")
        checkHtmlSimpleExample()

        markdownToHtml("input/markdown_lists.md", "temp.html")
        checkHtmlListsExample()
    }

    @Test
    @Tag("12")
    fun printMultiplicationProcess() {
        fun test(lhv: Int, rhv: Int, res: String) {
            printMultiplicationProcess(lhv, rhv, "temp.txt")
            assertFileContent("temp.txt", res.trimIndent())
            File("temp.txt").delete()
        }

        test(
            19935,
            111,
            """
                19935
             *    111
             --------
                19935
             + 19935
             +19935
             --------
              2212785
             """
        )

        test(
            12345,
            76,
            """
               12345
             *    76
             -------
               74070
             +86415
             -------
              938220
             """
        )

        test(
            12345,
            6,
            """
              12345
             *    6
             ------
              74070
             ------
              74070
             """
        )

    }

    @Test
    @Tag("25")
    fun printDivisionProcess() {

        fun test(lhv: Int, rhv: Int, res: String) {
            printDivisionProcess(lhv, rhv, "temp.txt")
            assertFileContent("temp.txt", res.trimIndent())
            File("temp.txt").delete()
        }

        test(
            19935,
            22,
            """
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
             """
        )

        test(
            2,
            20,
            """
              2 | 20
             -0   0
             --
              2
             """
        )

        test(
            99999,
            1,
            """
              99999 | 1
             -9       99999
             --
              09
              -9
              --
               09
               -9
               --
                09
                -9
                --
                 09
                 -9
                 --
                  0
             """
        )

        File("temp.txt").delete()
    }
}
