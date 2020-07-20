package 反射

import kotlin.reflect.KClass
import kotlin.reflect.KMutableProperty1
import kotlin.reflect.full.*
import kotlin.reflect.jvm.isAccessible
import kotlin.time.measureTimedValue


//通过KClass获取类的所有信息
fun classInfo(){
    var xw = Children("jack",20)
    var clz = Father::class
    var clz2 = xw.javaClass.kotlin
    println(clz.simpleName)  //获得类名
    println(clz.qualifiedName)  //获得完整的类名 (包名.类名)
    println(clz2.supertypes)  //获得父类完整类名
    //获得自己和父类中声明的属性
    clz2.memberProperties.forEach { println(it) }
    println("------")
    //获得自己声明的属性
    clz2.declaredMemberProperties.forEach { println(it) }
    println("------")
    //获得自己和父类的所有属性和函数
    clz2.members.forEach { println(it) }
    println("------")
    //获得自己的构造函数
    println(clz.constructors)
}

//通过KClass创建对象
/**
 *KFunction
 * KCallable
 * Kparameter
 * KProperty1
 * KMutableProperty1
*/
open class Father(name:String){
    var address : String = "南京东路 "
    fun text(){
        println("调用成功！")
    }
}

class Children (var name : String):Father(name = "jack"){
    constructor():this("merry"){
        println("无参构造函数被调用！")
    }
    constructor(name:String,age:Int):this("jack"){
        println("孩子的名字是$name  年龄是$age" )
    }
    var age : Int = 20
    private  var addr : String = "南京东路1号门"
    fun show(){
        println(" show()函数被调用 ")
    }
}
fun main() {
//    获取类的类对象KClass
//    Father.KClass ->Any接受 ->as Father
//    var xw = creatObj(Father::class) as Father
//    println(xw.address)
    invokefun(Father::class,"text")
    invkoeProperty(Children::class,"addr")

}
//调用属性
fun invkoeProperty(clz:KClass<out Any>,proName:String){
    val primary = clz.primaryConstructor
    val obj = primary?.call("addr")
    clz.memberProperties.find { it.name == proName }.also {
        //调用get方法
//        val value = it?.call(obj)
//        println("获取属性$proName 的值：$value")

        //调用对象的set方法
        //将KProperty类型转化为KMutableProperty类型
        //setValue(value:String)KMutableProperty1表示函数只有一个参数
        //KMutableProperty0表示函数没有参数
        //KMutableProperty1<T,R>

        //如果属性的修饰符为private 则需要设置 isAccessible为ture
        if(it!=null){
            it.isAccessible = true
            val mpro = it as KMutableProperty1<Any,Any>
            mpro.set(obj!!,"老北京胡同")
            //如果属性是private的，用call()
            println(it.call(obj))
        }

   }
    //如果属性可以访问，用这种方法也可以
    //val c= obj as Children
   // println(c.addr)
}
//调用函数
fun invokefun(clz:KClass<out Any>,Funname:String){
    val priCon = clz.primaryConstructor
    val obj = priCon?.call("毛泽东")
    for (func in clz.functions){
        if(func.name == Funname){
            func.parameters.forEach {
                println("第${it.index}个参数的类型是${it.type}")
            }
        }
        func.call(Father("周恩来"))
        break
    }
}
//创建类的对象
fun creatObj(clz:KClass<out Any>):Any{
    //1.使用默认的无参数构造函数创建对象
    //使用createInstance时，该类必须提供一个无参的构造函数！!
    //return clz.createInstance()
    //2.有参构造函数 ->创建对象
    val priCont=clz.primaryConstructor.apply { println(this?.name) }
    val obj = priCont?.call("jack")
    return obj!!
}

