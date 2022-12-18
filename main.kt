package parking

fun main() {
    var work = true
    val parking = Parking()

    while(work) {
        val command = readln().split(' ')
        when (command[0].toLowerCase()) {
            "create" -> parking.create(command[1].toInt()) // Созаем парковку с указаным количеством мест
            "park" -> parking.park(command[1].toUpperCase(), command[2].toLowerCase())   //command 1 = number car...command 2 = color_car
            "leave" -> parking.leave(command[1].toInt()) // Освободить парковочное место по указанному номеру
            "reg_by_color" -> parking.reg_by_color((command[1]).toLowerCase()) // Найти номера машин с выбранным цветом
            "spot_by_color" -> parking.spot_by_color((command[1]).toLowerCase()) // Найти машины на парковке по заданному цвету
            "spot_by_reg" -> parking.spot_by_reg((command[1]).toUpperCase()) //  Найти машины на парковке по номеру
            "status" -> parking.status() //  Статус парковки
            "exit" -> work = false  //  Точка выхода из программы
            else -> println("Not function")
        }
    }

}

class Parking {
    val lot = mutableMapOf<Int,String>()

    fun create(create_lot_park:Int){
        lot.clear()
        var start = lot.size + 1
        if(lot.isEmpty()){
            while (start <= create_lot_park) {
                lot.put(start, "free")
                start++
            }
        } else {
            repeat(create_lot_park){
                lot.put(start, "free")
                start++
            }
        }
        println("Created a parking lot with $create_lot_park spots.")
    }

    fun status(){
        if(lot.isNotEmpty()){
            var a = 0
            for ((k, v) in lot) {
                if (v != "free") {
                    println("${k} ${lot[k]}")
                    a++
                }
            }
            if(a == 0){
                println("Parking lot is empty.")
            }
        } else {
            println("Sorry, a parking lot has not been created.")
        }
    }

    fun reg_by_color(color_car: String){
        val b = lot.toString()
        val short_list = mutableListOf<String>()
        if(b.contains(color_car)){
            for((k,v) in lot){
                if(v.contains(color_car)){
                    short_list.add(v.split(' ')[0])
                }
            }
            println(short_list.joinToString())
        } else if (lot.isNotEmpty()){
            println("No cars with color $color_car were found.")
        } else {
            println("Sorry, a parking lot has not been created.")
        }
    }

    fun spot_by_color(color_car: String){
        val b = lot.toString()
        val short_list = mutableListOf<String>()
        if(b.contains(color_car)){
            for((k,v) in lot){
                if(v.contains(color_car)){
                    short_list.add(k.toString())
                }
            }
            println(short_list.joinToString())
        } else if (lot.isNotEmpty()){
            println("No cars with color $color_car were found.")
        } else {
            println("Sorry, a parking lot has not been created.")
        }
    }

    fun spot_by_reg(number_car:String){
        val b = lot.toString()
        if(b.contains(number_car)){
            Loop@    for((k,v) in lot){
                if(v.contains(number_car)){
                    println(k)
                    break@Loop
                }
            }
        } else if (lot.isNotEmpty()){
            println("No cars with registration number $number_car were found.")
        } else {
            println("Sorry, a parking lot has not been created.")
        }
    }

    fun park(number_car:String,color_car:String = "unknown"){
        try {
            val b = lot.toString()
            if(b.contains(number_car)){
                for((k,v) in lot){
                    if(v.contains(number_car)){
                        lot[k]="$number_car $color_car"
                        println("$color_car car parked in spot $k.")
                    }
                }
            }  else if (lot.isNotEmpty()){
                Loop@  for((k,v) in lot){
                    if(v == "free"){
                        lot[k]="$number_car $color_car"
                        println("$color_car car parked in spot $k.")
                        break@Loop
                    } else if(k == lot.size){
                        println("Sorry, the parking lot is full.")
                    }
                }
            }else {
                println("Sorry, a parking lot has not been created.")
            }
        }
        catch(e:Exception){
            println(e)
        }
    }

    fun leave(number_park_lot:Int){
        try {
            if(lot.isNotEmpty()){
                if(lot[number_park_lot] != "free"){
                    lot[number_park_lot] = "free"
                    println("Spot $number_park_lot is free.")
                } else{
                    println("There is no car in spot $number_park_lot.")
                }
            } else {
                println("Sorry, a parking lot has not been created.")
            }
        }
        catch(e:Exception){
            println(e)
        }

    }


}
