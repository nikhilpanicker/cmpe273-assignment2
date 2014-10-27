package hello

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.NotBlank
import org.springframework.data.annotation.Id;
//import java.util.Calendar


class UserCreation(){
   
 // @JsonProperty("id")
 /* var start = 10000
  var end   = 200000
  var rnd = new scala.util.Random
	
  @JsonProperty("id")
  var id : Int = start + rnd.nextInt( (end - start) + 1 )
  */
  @Id
  @JsonProperty("id")
  var id: Int = _
  @NotBlank(message = "Email cannot be blank!") 
  @JsonProperty("email")
  var email:String = _
  @NotBlank(message = "Password cannot be blank!")
  @JsonProperty("password")
  var password:String  = _
 @JsonProperty("creationdate")
 var creationdt : String = _
 @JsonProperty("updateddate")
 var updateddt : String = _
  
  
  def this(email:String,password:String,creationdt:String,id: Int,updateddt : String) {
    this()
   
    this.email = email
	System.out.println("email"+this.email);
    this.password = password
	System.out.println("password"+this.password);
    this.creationdt = creationdt
	System.out.println("creationdt"+this.creationdt);
	this.id = id
	System.out.println("id"+this.id);
	this.updateddt = updateddt
	System.out.println("updateddt"+this.updateddt);
  }
  
  
  
}


/*package hello

class UserCreation() {
  
  
    val start = 10000
	val end   = 200000
	val rnd = new scala.util.Random
	
	val userid = start + rnd.nextInt( (end - start) + 1 ) 
	
	val creationtimestamp: Long = System.currentTimeMillis / 1000

  
     def addtohash(userid: Int, email : String, password: String, creationdate : Long)
  {
         //val useridval : Int = userid
         var passwordval : String = password
         var creationval : Long = creationdate
         
         var userid = Map("password" -> passwordval,
                       "creationdate" -> creationval)
  }
  
  def returnhashvalues()
  {
    val info : String = ""
      
    if (userid.contains(userid))
    {
      userid.keys.foreach{
        i =>	info += i +":"+useridval(i)
      }
      return info
    }
    }
  }

}*/