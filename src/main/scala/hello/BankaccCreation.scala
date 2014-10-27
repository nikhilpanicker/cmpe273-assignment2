
package hello

import com.fasterxml.jackson.annotation.{JsonIgnoreProperties, JsonProperty}
import org.hibernate.validator.constraints.NotBlank
import org.springframework.data.annotation.Id;

@JsonIgnoreProperties(ignoreUnknown = true)
class BankaccCreation {
  @Id
  @JsonProperty("bankId")
  var bankId : Int = _
  @NotBlank(message = "Account Name cannot be blank!")
  @JsonProperty("account_name")
  var account_name: String = _
  @NotBlank(message = "Routing Number cannot be blank!")
  @JsonProperty("routing_number")
  var routing_number:String = _
  @NotBlank(message = "Account Number cannot be blank!")
  @JsonProperty("account_number")
  var account_number:String  = _
  @JsonProperty("bankUserid")
  var bankUserid:Int = _
  @JsonProperty("customer_name")
  var customer_name:String = _
  
  
  def this(account_name:String,routing_number:String,account_number:String,bankId : Int,bankUserid:Int,customer_name:String) {
    this()
    this.account_name = account_name
    this.routing_number = routing_number
    this.account_number = account_number
    this.bankId = bankId
	  this.bankUserid = bankUserid
    this.customer_name=customer_name
}
  
}