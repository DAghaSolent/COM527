
  open class Car(val make: String, val model: String, val engineCapacity: Int, val topSpeed: Int, var currentSpeed: Int){
    
    fun accelerate(speedUp: Int){
      // this.currentSpeed ++
      if(speedUp + currentSpeed < topSpeed){
        currentSpeed += speedUp
      }
      else if(speedUp + currentSpeed > topSpeed){
        currentSpeed = topSpeed
      }
    }

    fun deaccelerate(speedDown: Int){
      if(currentSpeed - speedDown < 0){
        currentSpeed = 0
      }
      else if(currentSpeed - speedDown > 0){
        currentSpeed -= speedDown
      }
    }

     override fun toString(): String {
        return "Make: $make, Model: $model, Engine Capacity in cc: $engineCapacity, Top Speed: $topSpeed, Current Speed: $currentSpeed"
    }
  }
  
open class Employee(var name: String, var jobTitle: String, var salary:Int){
      open fun display(){
        println("Name: $name Job: $jobTitle Salary: $salary")
      }
    }
  
    class Programmer(var n: String, var jt: String, var s:Int, var favLangs: Array<String>, var curProject: String) : Employee(n, jt, s){
      override fun display(){
        super.display()
        for(lang in favLangs){
          println(lang)
        }
        println("Current Project: $curProject")
      }
    }

    class Manager(n: String, jt: String, s:Int, var car:Car, var shares:Int) :  Employee(n, jt, s){
      override fun display(){
        super.display()
        println("Shares: $shares Car: $car")
      }
    }

    

fun main(args: Array<String>) {
 
    //val danny = Programmer("Danny", "Apprentice", 100000, "Java", "Mobile Development")
    //danny.display()

    //val danny = Manager("Danny", "Manager", 100000, Car("Toyota", "Supra", 40, 70, 40), 1000)
    //danny.display()
    //danny.car.accelerate(10)
    //danny.display()

    val danny = Programmer("Danny", "Apprentice", 100000, arrayOf("Java","SQL"),"Mobile Development")
    danny.display()
}