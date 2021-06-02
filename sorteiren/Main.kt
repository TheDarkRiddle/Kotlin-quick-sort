package sortieren
import java.util.Comparator
import  sorteiren.People
import sorteiren.English
import sorteiren.German
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

    var test=German("Guenter",1)
    Crowd.addfirst(test)
    Crowd.addfirst(English("Mark",2))
    Crowd.addfirst(German("Haarald",3))
    Crowd.addlast(English("Kai",4))

    Crowd.forEach { println("My name is: ${it.name}(Age: ${it.alter})") };
    println("SORTED:")
    //   Crowd.quickSort(intComperator);
    println()
    Crowd.forEach { println("My name is: ${it.name}(Age: ${it.alter})") };

            }