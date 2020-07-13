import java.util.*
import kotlin.system.exitProcess

fun main() {
    var old = 123//原始密码
    var oldnum = 5000//原始存款
    val wrongTime = 3//错误次数
    val scanner = Scanner(System.`in`)

    println("**********************")
    println(" 欢迎使用中国建设银行系统 ")
    println("**********************")


    print("请输入密码：")
    for (i in 1..wrongTime) {
        val num = scanner.nextInt()//输入密码
        if (i == wrongTime) {
            println("错误次数过多，请于30分钟后再次尝试！")
            exitProcess(1)
        } else if (num == old) break; else print("密码错误,请重新输入密码：")
    }
    println("密码正确！")


    loop@do {
        println("**********************")
        println("请选择您想进行的操作：")
        println("1.存款")
        println("2.取款")
        println("3.修改密码")
        println("4.查询余额")
        println("5.退出登录")
        println("**********************")
        val num2 = scanner.nextInt()
          when (num2) {
            1 -> {
                do {
                    print("请输入您想存入的金额：")
                    val num3 = scanner.nextInt()
                    print("\n")
                    println("存款成功！您现在的余额为${oldnum + num3}元")
                    oldnum += num3
                    println("是否继续取款？1.继续 2退出")
                    val input3= readLine()
                }while(input3=="1")
            }
            2 -> {
                do {
                    print("请输入您想取出的金额：")
                    val num4 = scanner.nextInt()
                    if (num4 <= oldnum) {
                        print("\n取款成功！您现在的余额为：${oldnum - num4}元")
                        oldnum -= num4
                    } else println("您的余额不足！请充值后再进行该操作！")
                    print("是否继续存款：1.继续  2.退出")
                    val num5 = scanner.nextInt()
                }while(num5==1)
            }
            3 -> {
                print("请输入您想要修改的密码：")
                val input = readLine()
            }
            4 -> {
                println("您当前的余额为$oldnum 元")
            }
            5 -> {
                exitProcess(1)
            }
        }
        println("是否返回主菜单：y or n")
        val input2 = readLine()
    } while (input2 == "y")
}





//    if (input2 == "y") {
//        println("**********************")
//        println("请选择您想进行的操作：")
//        println("1.存款")
//        println("2.取款")
//        println("3.修改密码")
//        println("4.查询余额")
//        println("5.退出登录")
//        println("**********************")
//        val num2 = scanner.nextInt()
//
//        when (num2) {
//            1 -> {
//                print("请输入您想存入的金额：")
//                val num3 = scanner.nextInt()
//                print("\n")
//                println("存款成功！您现在的余额为${oldnum + num3}元")
//
//            }
//            2 -> {
//                print("请输入您想取出的金额：")
//                val num4 = scanner.nextInt()
//                if (num4 <= oldnum) print("\n取款成功！您现在的余额为：${oldnum - num4}元")
//                else println("您的余额不足！请充值后再进行该操作！")
//                print("是否继续存款：1.继续  2.退出")
//                val num5 = scanner.nextInt()
//
//            }
//            3 -> {
//            }
//            4 -> {
//            }
//            5 -> {
//                exitProcess(1)
//            }
//        }
//    } else {
//        exitProcess(1)
//    }
//}



