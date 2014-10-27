package hello

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.NotBlank
import org.springframework.data.annotation.Id;

class WebaccCreation {
  @Id
  @JsonProperty("webLoginId")
  var webLoginId : Int = _
 @NotBlank(message = "URL cannot be blank!")
  @JsonProperty("url")
  var url :String = _
  @NotBlank(message = "Login ID cannot be blank!")
  @JsonProperty("login")
  var login :String  = _
  @NotBlank(message = "Password cannot be blank!")
  @JsonProperty("password")
  var password :String  = _
  @JsonProperty("webUserid")
  var webUserid:String = _

  
  
  def this(url:String,login:String,password:String,webUserid:String,webLoginId : Int) {
    this()
    this.webLoginId = webLoginId
    this.url = url
    this.login = login
    this.password = password
	this.webUserid=webUserid
}
}