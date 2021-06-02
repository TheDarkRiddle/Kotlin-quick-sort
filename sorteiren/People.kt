package sorteiren

//Humen´s
interface People: Comparable<People>{
    val name:String
    val alter:Int
    fun sayName()
    fun sayAge()
}
//Humens with Lenguages
class German(override val name: String, override val alter: Int) : People{
    override fun sayName() { println("Hallo ich heiße $name."); }
    override fun sayAge(){ println("Ich bin $alter alt."); }
    override fun compareTo(other: People): Int {
        when{
            this.alter < other.alter -> return -1
            this.alter > other.alter -> return 1
            this.alter == other.alter-> return 0
            else ->return 0
        }
    }
}
class English(override val name: String, override val alter: Int):People{
    override fun sayName() { println("My name is $name."); }
    override fun sayAge() { println("My age is $alter.") }
    override fun compareTo(other: People): Int {
        when{
            this.alter < other.alter -> return -1
            this.alter > other.alter -> return 1
            this.alter == other.alter-> return 0
            else ->return 0
        }
    }
}