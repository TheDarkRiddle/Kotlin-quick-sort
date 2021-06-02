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
    fun replace(replacement:Liste<T>){this.first = replacement.first}
    fun clearAll(){first=null}

    //Complexer
    fun forEach(action :(Entry<T>)->Unit){
        var run = first

        while (run!=null){

            action(run)
            run =run.next
        }
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
