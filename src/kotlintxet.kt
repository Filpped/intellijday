interface onclickListener1{
    fun alreadyclick(type: String)
}

fun touchlistener(Listener: onclickListener1){
    Listener.alreadyclick("手指按下")
}
/**
  *匿名类(实现接口，仅使用一次)
  * 匿名类：object:接口类型（数据类型）{重写接口方法}
  * fun main(){
 var jack =person("jack",20)
 println("person的名字是${jack.toString()}")
 jack.text()

 var s1= person.student("merry")
 println(s1.name)
 s1.show()

 //匿名类
 touchlistener(object : onclickListener{
 override fun alreadyclick(type: String) {
 println("监听到的动作为 $type")
 }
 } )
 }
  */

 /** 次构造函数必须显示调用主构造函数，内部类
  * constructor( name:String,age:Int):this("merry"){}
  *
 class person constructor(private val name:String){
 constructor( name:String,age:Int):this(name){
 println("次构造函数被调用")
 }
 fun text(){
 println("text（）函数被调用")
 }
 override fun toString(): String {
 return "person(name='$name')"
 }
 //内部类（跟完全写在person类外的类差不多，独立存在，
 // 但是创建对象的时候必须用person.student()）
 class student(val name : String){
 val string : String = " 我是一个学生 "
 fun show(){
 println("$name 说$string")
 }
 }
 }
 */

 /**
  * 集合Collection{
  *     可变 和 不可变
  *     接口Collection <- MutableCollection
  * 子类：
  * List     MutableList
 //如何创建List
        val names : List<String?> = List(3){ null }
        val names2:List<String> = listOf()
        val names3:List<String> = listOf("jack","rose")
        val names4:List<String> = emptyList()
 //如何创建MutableList
        val names1: MutableList<Nothing?> = MutableList(3) { null }
        val names2: MutableList<String> = mutableListOf()
        val names3: MutableList<String> = mutableListOf("jack", "merry", "rose")
 //使用listIterator方法
        names3.listIterator().also {
            while (it.hasNext()) {
                println(it.next())
                if (it.nextIndex() == 2)
                    it.remove()
            }
         }
        for (i in names3) {
            println(i)
        }

  * Set      MutableSet
  *
  * }
  *
  * Array
  * //1.创建Array
    val name = Array<String>(5) {
        "$it-str"
    }

    for(i in name.indices)
       { println(name[i]) }

    for (str in name){
         println(str) }

    for ((i,str) in name.withIndex()){
         println(str) }

 //用iterator遍历器遍历一个数组（java多用）
    val iterator = name.iterator()
      while (iterator.hasNext()){
        println(iterator.next())
      }

 //kotlin多用
    name.iterator().also {
        while (it.hasNext()){
            println(it.next())
         }
    }
 //2.arrayOf()创建
    val names = arrayOf("jack","rose")
 //3.arrayOfNulls<T>创建
    val names2 = arrayOfNulls<String>(3)
 //4.emptyArray<T>创建
    val names4 = emptyArray<String>()
  *
  * Set
  */

 /** 映射 Map     <- MutableMap
  * 子类
  *     HashMap         MutableHashMap
  *     LinkedMap       MutableLinkedMap
  *    //Map创建
 //emptyMap
 val dic = mapOf<String,String>()
 //Pair(K,V)  键值对
 val dic2 = mapOf<String,String>(Pair("English","英文"),
 Pair("Chinese","中文"))

 val dic3 = hashMapOf<String,String>()
 val dic4 = hashMapOf<String,String>(Pair("chinese","中文"))

 val dic5 = linkedMapOf<String,String>()
 val dic6 = linkedMapOf<String,String>(Pair("English","英文"))

 val dic7 = mutableMapOf<String,String>()
 val dic8 = mutableMapOf<String,String>(Pair("我的","你的"))
 //mutableMapOf 增加一对键值对
 dic8["chinese"] = "english"

 println(dic8.keys)
 println(dic5.keys)
 println(dic2.keys)
 println(dic2.values)
 println(dic2["English"])

 dic2.entries.forEach {
 println("${it.key} -- ${it.value}")
 }
 */

 /**
  *什么时候用到泛型类：当自己定义一个类来操作数据时 如果数据类型是变化的
  *                  不确定的 就要用到泛型
  *   fun main() {
    var contan1 = contain<Int>()
    contan1.add(20)
    contan1.add(24)
    contan1.add(1)
    contan1.get(1).also { println(it) }

     }
 class contain<T>(){
    private var list : MutableList<T> = mutableListOf()
    fun add(e:T){
        list.add(e)
    }
     fun get(index : Int):T{
         return list[index]
    }
 }
  */

  /** 泛型接口//回调模式
 fun main() {
    var btn = Button("Login")
    btn.listener=object : OnClickListener<Button> {
        override fun clickdown(v: Button) {
        println("${v.Title} 被点击了")
        }
     }
     btn.userclickdown()

    var img = Image("我的图片")
    img.listener= object :OnClickListener<Image>{
        override fun clickdown(v: Image) {
        println("${v.imgName} 被点击了")
        }
     }
    img.userclickdown()
 }

 interface OnClickListener<T>{
     fun clickdown(v:T)
 }

 class Button(var Title : String){
    var listener : OnClickListener<Button>? = null
    fun userclickdown(){
        listener?.clickdown(this)
    }
 }

 class Image(var imgName:String){
    var listener : OnClickListener<Image>? = null
    fun userclickdown(){
        listener?.clickdown(this)
    }
 }
   */

  /** 泛型函数
  * 泛型的约束 in out
   * out T （设置上界是T）: T以及T的子类
   * out “输出”  用out修饰 只能取值get
   *
   * in  “输入”  用in修饰  只能add
   * in T (设置下界是T) ： T以及T的父类
   * fun main() {
        compare(1,5).also { println(it) }
        compare(3,3).also { println(it) }
        val also = compare2(1, "22").also { println(it) }
        tiaoyong(mutableListOf(Father()))

        tiaoyong(mutableListOf(Child()))
     }

  fun<T> compare(a:T,b:T) = a==b
  fun<T,R> compare2(a:T,b:R) = a==b

  open class Father
  class Child : Father()
  fun tiaoyong(v : MutableList<Father>){
        println("调试成功")
     }
 */












