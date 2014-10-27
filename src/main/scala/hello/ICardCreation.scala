package hello

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.NotBlank
import org.springframework.data.annotation.Id;

class ICardCreation {
 
 
  @Id
  @JsonProperty("iCardid")
  var iCardid : Int = _
  @JsonProperty("iCardUserId")
  var iCardUserId:Int = _
  @NotBlank(message = "Card Name cannot be blank!")
  @JsonProperty("card_name")
  var card_name:String = _
  @NotBlank(message = "Card Number cannot be blank!")
  @JsonProperty("card_number")
  var card_number:String  = _
  @NotBlank(message = "Expiration Date cannot be blank!")
  @JsonProperty("expiration_date")
  var expiration_date:String  = _

  
  
  def this(card_name:String,card_number:String,iCardid : Int,expiration_date : String,iCardUserId:Int ) {
    this()
    this.card_name = card_name
    this.card_number = card_number
	this.iCardid = iCardid
	this.expiration_date=expiration_date
	this.iCardUserId=iCardUserId
}
}