package sorteiren
import sorteiren.People
import sortieren.Liste


fun <E : Comparable<E>> List<E>.quickSort(): List<E> =
    when {
        size < 2 -> this
        else -> {
            val (l, h) = subList(1, size).partition { it < first() }
            l.quickSort() + first() + h.quickSort()
        }
    }
//For People
fun quickSortPeople(toSort:Liste<People>):Liste<People> {
    val temp = mutableListOf<People>()

    for (e in toSort) {
        temp.add(e)
    }
    val sortedList = temp.quickSort();

    val linkedList = Liste<People>();
    for (e in sortedList) {
        linkedList.addlast(e)
    }
    return linkedList
}
