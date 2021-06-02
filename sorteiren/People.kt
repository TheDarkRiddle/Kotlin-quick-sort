package sorteiren

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
