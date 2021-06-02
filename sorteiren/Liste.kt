package sortieren
import java.lang.Exception
import java.util.Comparator

class Liste<T>:Iterable<T>{

    class Entry<T>(val content:T, var next:Entry<T>?)

    private var first :Entry<T>? = null;

    //Adder
    fun addfirst(content:T) { first = Entry(content, first); }
    fun addlast(content:T){
        var run=first;

        while (run?.next!=null){ run=run?.next; }

        run?.next= Entry(content,run?.next);
    }

    //Getter
    fun getFirst():Entry<T>{ return first?: throw Exception("(getFirst)No Elements in this List")
    }
    fun getLast(): Entry<T> {
        var run = first;
        while (run?.next!=null){ run=run?.next}
        return run?:throw Exception("(getLast)No Elements in this List");
    }

    fun test(mainList: Liste<T>, toAdd: Liste<T>): Liste<T> {

        mainList.getLast().next = toAdd.first;

        return mainList
    }
    //Helper
    //Simple
    fun size():Int{
        var temp=0;
        this.forEach { temp++ };
        return temp;
    }
    fun conect(mainList: Liste<T>, toAdd: Liste<T>): Liste<T> {
        mainList.getLast().next = toAdd.first;
        return mainList
    }
    fun clearAll(){first=null}

    //Complexer
    fun forEach(action :(Entry<T>)->Unit){
        var run = first

        while (run!=null){

            action(run)
            run =run.next
        }
    }
    /* fun <E : Comparable<E>> List<E>.quickSort(): List<E> =
         when {
             size < 2 -> this
             else -> {
                 val (l, h) = subList(1, size).partition { it < first() }
                 l.quickSort() + first() + h.quickSort()
             }
         }*/
    fun quickSort(comparator: Comparator<T>){
        if (this.size() < 2)return;

        val pivot = this.getFirst().content;     //Comparison element

        val less = Liste<T>();
        val equal= Liste<T>();
        val more = Liste<T>();

        for (element in this){

            val compared = comparator.compare(pivot,element)
            when{
                compared >  0 -> less.addfirst(element);
                compared == 0 -> equal.addfirst(element);
                compared <  0 -> more.addfirst(element);
            }
        }
        less.quickSort(comparator)
        more.quickSort(comparator)

        this.first = conect(this,conect(equal,less)).first
    }

    //iterator
    inner class ElementIterator : Iterator<T>{
        private var run = first

        override fun hasNext(): Boolean = run != null

        override fun next(): T {
            val result = run?.content?: throw Exception("Liste Leer")
            run = run?.next
            return result
        }
    }

    override fun iterator(): Iterator<T> = ElementIterator()
}
