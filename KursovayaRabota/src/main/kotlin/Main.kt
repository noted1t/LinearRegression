import java.util.*
import kotlin.io.path.Path
import kotlin.math.pow
import kotlin.math.round
import kotlin.math.roundToInt

class Main {
    companion object {
        @JvmStatic fun main(args: Array<String>) {
            val values1 = Main().loadFromFile("Стоимость контракта(x).txt", "Приход аудитории (y).txt")
            val mattrObj1 = mattr()
            mattrObj1.setup(values1)

            val values2 = Main().loadFromFile("стоимость2.txt", "приходАудитории2.txt")
            val mattrObj2 = mattr()
            mattrObj2.setup(values2)
            val a1 = Main().getA(mattrObj1)
            val b1 = Main().getB(mattrObj1, a1)
            val a2 = Main().getA(mattrObj2)
            val b2 = Main().getB(mattrObj2, a2)

            
            

            println("A коэффициент первой линейной регрессии:  $a1")
            println("B коэффициент первой линейной регрессии:  $b1")
            println("A коэффициент второй линейной регрессии:  $a2")
            println("B коэффициент второй линейной регрессии:  $b2")
            println("С 20000 долларов с первой компании придёт ${Main().countSubs(a1, b1, 20000)} аудитории")
            println("С 20000 долларов со второй компании придёт ${Main().countSubs(a2, b2, 20000)} аудитории")

        }
    }

    fun loadFromFile(xPath: String, yPath: String): Array<DoubleArray> {
        var count = 0
        
        val xRead = Scanner(Path(xPath))
        val yRead = Scanner(Path(yPath))

        val a: Array<DoubleArray> = Array(2) { DoubleArray(100) }

        while (xRead.hasNext()){
            a[0][count] = xRead.nextDouble()
            count++
        }

        count = 0

        while (yRead.hasNext()) {
            a[1][count] = yRead.nextDouble()
            count++
        }

        return a
    }

    private fun getA(obj: mattr): Double{
        //Получаем А (путем получения числителя и знаменателя через функции
        val sumXY = obj.getSize()*obj.getSumXY()
        val sumX = obj.getSumX()
        val sumY = obj.getSumY()
        val chisl = sumXY - sumX*sumY

        val sumSQX = obj.getSize()*obj.getSumSQX()
        val SQSum = obj.getSumX().pow(2.0)
        val znam = sumSQX-SQSum

        return chisl/znam
    }

    private fun getB(obj: mattr, a: Double): Double {
        //Получаем B путем получения суммы всех игриков, из этого вычитаем сумму иксов
        // умножив на коэф A и подели на количество элементов
        val sumY = obj.getSumY()
        val sumX = a*obj.getSumX()

        return(sumY-sumX)/obj.getSize()
    }

    private fun countSubs(a: Double, b: Double, money: Int): Int{
        return (a*money+b).roundToInt()
    }

    private fun countMoney(a: Double, b: Double, subs: Int): Double{
        return (subs-b)/a
    }
}
