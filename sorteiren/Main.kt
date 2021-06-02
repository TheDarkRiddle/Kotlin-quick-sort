package sortieren
import sorteiren.*
import java.util.Comparator

//MAIN
fun main() {              
    val Crowd = Liste<People>();

    Crowd.addfirst(German("Guenter",10))
    Crowd.addfirst(English("Mark",2))
    Crowd.addfirst(German("Haarald",3))
    Crowd.addlast(English("Kai",4))
    
    Crowd.forEach { println("My name is: ${it.name}(Age: ${it.alter})") };
    println("SORTED:")
    Crowd.replace(quickSortPeople(Crowd))
    Crowd.forEach { println("My name is: ${it.name}(Age: ${it.alter})") };

    }
