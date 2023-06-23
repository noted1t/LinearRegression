import kotlin.math.pow

class mattr{
    //Класс матрицы(двумерного массива
    private var a: Array<DoubleArray> = Array(2) { DoubleArray(100) }
    private var sumX: Double = 0.0
    private var sumY: Double = 0.0
    private var sumXY: Double = 0.0
    private var size: Int = 0
    private var sumSQX: Double = 0.0

    fun setup(arr: Array<DoubleArray>){
        //инициализация объекта в виде двумерного списка
        a = arr
        size = arr[0].size
    }

    fun getSumX(): Double{
        //сумма всех иксов
        if (sumX != 0.0){
            return sumX
        }
        for (arr in a[0]){
            sumX += arr
        }
        return sumX
    }
    fun getSumY(): Double{
        //сумма всех игриков
        if (sumY != 0.0){
            return sumY
        }
        for (arr in a[1]){
            sumY += arr
        }
        return sumY
    }
    fun getSumXY():Double{
        //сумма всех иксов умноженных на игрики
        if (sumXY != 0.0){
            return sumXY
        }
        var count = 0;
        a[0].forEach { _ ->
            sumXY += a[0][count]*a[1][count]
            count++
        }
        return sumXY
    }
    fun getSize():Int{
        // Размер массива
        return size
    }

    fun getSumSQX(): Double{
        //Получение суммы квадратов иксов (знаменатель)
        if(sumSQX!=0.0){
            return sumSQX
        }
        for (arr in a[0]){
            sumSQX += arr.pow(2.0)
        }
        return sumSQX
    }
}

