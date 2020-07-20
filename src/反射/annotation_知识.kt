package 反射

/**
 *AnnotationTarget CLASS 标识一个类
 *                 CONSTRUCTOR 标识构造函数
 *                 PROPERTY  标识一个属性
 *                 FUNCTION  标识一个函数
 *                 VALUE_PARAMETER  标识函数的参数(功能函数或者构造函数)
 *AnnotationRetention  注解的生命周期（作用域）
 *                     SOURCE      写代码时期
 *                     BINARY       编译时期
 *                     RUNTIME      运行时期
 *注意！：注解类里面定义参数时只能用val类型
*/
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.ANNOTATION_CLASS)  //类
annotation class myClass

@Target(AnnotationTarget.FUNCTION)  //函数
annotation class  myFunc

@Target(AnnotationTarget.CONSTRUCTOR)  //构造函数
annotation class myCons

@Target(AnnotationTarget.PROPERTY)  //属性
annotation class myProp

@Target(AnnotationTarget.VALUE_PARAMETER)
annotation class myParm  //函数参数