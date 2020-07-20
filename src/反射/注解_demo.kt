package 反射

import kotlin.reflect.KClass
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.primaryConstructor

//类注解
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class Entity

//属性注解
@Retention
@Target(AnnotationTarget.PROPERTY)
annotation class  ColumnInfo


//模型类
@Entity class User(
        @ColumnInfo var id : Int,
        @ColumnInfo var name : String,
        @ColumnInfo var icon : String
){
    override fun toString(): String {
        return "User(id=$id, name='$name', icon='$icon')"
    }
}

@Entity class Student(
        @ColumnInfo var sID : Int,
        @ColumnInfo var name : String,
        @ColumnInfo var addr : String
){
    override fun toString(): String {
        return "Student(sID=$sID, name='$name', addr='$addr')"
    }
}

//模拟数据查询函数
fun selectData() : Map<String,Map<String,Any>>{
    //模拟有两个表 User Student
    //使用Map封装数据 k-v键值对对应
    val userDate = mapOf(
            Pair("id",1),
            Pair("name","penny"),
            Pair("icon","https://flipped.blue")
    )
    val studentdata = mapOf(
            Pair("sID",1),
            Pair("name","merry"),
            Pair("addr","西南大学")
    )

    //封装这两个表
    val datas = mapOf(
            Pair("User",userDate),
            Pair("Student",studentdata)
    )

    return datas
}

//写一个方法自动创建对象
fun autoParseFromTable(clz:KClass<out Any>):Any?{
    //先从数据库里面读取出表对应的数据
    val datas = selectData()
    val entity = clz.findAnnotation<Entity>()
    if (entity == null) {
        //判断传过来的类是否用Entity注解
        //如果没有，就不能转换
        return null
    }else{
        //这个类可以被转换
        //获取表名-- ；类名
        val tableName = clz.simpleName
        //使用这个表名去数据中获取这个表对应的数据
        val info = datas[tableName]

        //创建一个对象来接受从表中获取的信息，一一对应的填充到对象属性中
        //使用默认的主构造函数创建
        val cons = clz.primaryConstructor

        //创建一个数组来保存解析获取的属性的值
        //创建的数组元素个数 和 构造函数中参数的个数相同 初始值为null
        var params = arrayOfNulls<Any>(cons?.parameters?.size!!)

        //遍历构造函数的参数
        cons.parameters.forEach {
            //从数据源中获取这个参数对应的值
            val value = info?.get(it.name)
            //将这个值保存到数组中
            params[it.index] = value
        }
        //调用构造函数 创建对象
        val obj = cons?.call(*params)
        return obj
    }
}
fun main() {
    val user = autoParseFromTable(Student::class)
    println(user)
}