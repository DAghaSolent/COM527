fun main(args: Array<String>) {
  println("Hello world!")

  // Answer all questions in this main

  println("Enter your name")
  val n = readLine()

  println("How many times would you like to repeat your name")
  var requestCount = readLine()
  var newCount = requestCount?.toInt() ?: 0

  //Count from 1 to 10
  for (i in 1..newCount){
    println(n)
  }

  val artists = arrayOf("Kanye West", "Limp Bizkit", "Drake", "Slipknot")

  println("Please guess an artist")

  var guess = readLine()

  while (!artists.contains(guess)){
    // error
    // another readLine() to give the user another chance
    println("Incorrect Guess have another go")
    var anotherGuess = readLine()
    if (artists.contains(anotherGuess)){
      break
    }  
  } 
  val correctMsg = "You have guessed correctly"
  println(correctMsg)

  println("Please input your total academic score.")

  var gscore = readLine()
  var gradeScore = gscore?.toInt() ?: 0 

  val finalGrade = when(gradeScore) {
    in 70..100 -> "A"
    in 60..70 -> "B"
    in 50..60 -> "C"
    in 40..50 -> "D"
    in 30..39 -> "E"
    in 0..29 -> "F"
    else -> "Error incorrect input"
  }
  println("Grade Awarded:$finalGrade")

  class Car(val make: String, val model: String, val engineCapacity: Int, val topSpeed: Int, var currentSpeed: Int){
    
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

  val toyota = Car("Toyota", "Supra", 40, 70, 40)
  //toyota.accelerate(45)
  // toyota.accelerate()
  //toyota.deaccelerate(55)
  println(toyota)

  
 
}