import java.lang.Exception
import java.util.Comparator

//Humen´s
interface People{
    val name:String
    val alter:Int
    fun sayName()
    fun sayAge()
}
//Humens with Lenguages
class German(override val name: String, override val alter: Int) : People{
    override fun sayName() { println("Hallo ich heiße $name."); }
    override fun sayAge(){ println("Ich bin $alter alt."); }
}
class English(override val name: String, override val alter: Int):People{
    override fun sayName() { println("My name is $name."); }
    override fun sayAge() { println("My age is $alter.") }
}


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
    fun getFirst():Entry<T>{ return first?: throw Exception("(getFirst)No Elements in this List")}
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

            //MAIN
fun main() {
                val intComperator = Comparator<People>{ o1: People, o2:People -> when{

                    o1.alter == o2.alter -> 0
                    o1.alter < o2.alter -> -1
                    o1.alter > o2.alter -> 1
                    else -> 0
                }
                }
    val Crowd=Liste<People>();


    Crowd.addfirst(German("Guenter",1))
    Crowd.addfirst(English("Mark",2))
    Crowd.addfirst(German("Haarald",3))
    Crowd.addlast(English("Kai",4))

    Crowd.forEach { println("My name is: ${it.name}(Age: ${it.alter})") };
    println("SORTED:")
    Crowd.quickSort(intComperator);
    println()
    Crowd.forEach { println("My name is: ${it.name}(Age: ${it.alter})") };

            }
