package hello

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import scala.beans.BeanProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class routeDetails (){


@JsonProperty("customer_name")
var customer_name: String = _
@JsonProperty("routing_number")
var routing_number: String = _
@JsonProperty("change_date")
var change_date: String = _
@JsonProperty("data_view_code")
var data_view_code: String = _
@JsonProperty("message")
var message: String = _
@JsonProperty("record_type_code")
var record_type_code: String = _
@JsonProperty("zip")
var zip: String = _
@JsonProperty("office_code")
var office_code: String = _
@JsonProperty("telephone")
var telephone: String = _
@JsonProperty("rn")
var rn: String = _
@JsonProperty("address")
var address: String = _
@JsonProperty("code")
var code: String = _
@JsonProperty("state")
var state: String = _
@JsonProperty("new_routing_number")
var new_routing_number: String = _
@JsonProperty("institution_status_code")
var institution_status_code: String = _
@JsonProperty("city")
var city: String = _
def this(customer_name:String,routing_number:String) {
this()
this.customer_name = customer_name
this.routing_number = routing_number
}

}