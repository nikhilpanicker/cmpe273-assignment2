package hello



import org.springframework.web.bind.annotation.PathVariable
import com.mongodb.DBObject
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.validation.ObjectError
import com.mongodb.MongoClient
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.beans.factory.annotation.Autowired
import com.mongodb.DBCursor
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid
import com.mongodb.MongoClientURI
import java.net.HttpURLConnection
import java.net.URL
//import org.simpleframework.http.session.Controller
import java.util.ArrayList
import java.util.Date
import org.springframework.context.annotation.Bean
import com.fasterxml.jackson.databind.ObjectMapper
import com.mongodb.BasicDBObject
import org.springframework.web.bind.annotation.RequestMethod
import scala.collection.mutable.ListBuffer
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.{ExceptionHandler, PathVariable, RequestBody, RequestMapping, RequestMethod, ResponseBody, ResponseStatus, RestController}
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.http.HttpStatus
import com.mashape.unirest.http.HttpResponse
import com.mashape.unirest.http.Unirest
import com.mashape.unirest.http.JsonNode
import org.springframework.stereotype.Controller
import org.springframework.web.filter.ShallowEtagHeaderFilter
import java.io.BufferedReader
import java.io.InputStreamReader






@Controller
@RestController
@RequestMapping(Array("/")) //delete this
class HelloWorldController {

  var mapobj: Map[Int, UserCreation] = Map()
  var Icardmapobj: Map[Int, ICardCreation] = Map()
  var BankAccmapobj: Map[Int, BankaccCreation] = Map()
  var WebAccmapobj: Map[Int, WebaccCreation] = Map()
  var addviewobj2 = new ListBuffer[BankaccCreation]()
  var icardCreationList = new ListBuffer[ICardCreation]()
  var webCreationList = new ListBuffer[WebaccCreation]()
  var bankAccCreationList = new ListBuffer[BankaccCreation]()
  
  @Autowired
  val webRepository : WebCustomerRepository = null
  @Autowired
  val repository: UserRepository = null
  @Autowired
  val iCardrepository: userICardRepository = null
  @Autowired
  val bankAccrepository: userBankAccRepository = null


  val uri = "mongodb://wallet:wallet@ds047930.mongolab.com:47930/wallet"
  val mongoClientURI = new MongoClientURI(uri)
  val mongoClient = new MongoClient(mongoClientURI)
  val db = mongoClient.getDB("wallet")

  //MongoOperations mongoOps = new MongoTemplate(mongoClient, "wallet");
  @Autowired
  val mongoOps = new MongoTemplate(mongoClient, "wallet")
  val coll = db.getCollection("test")
  val userCollection = db.getCollection("userCreation")
  val iCardCollection = db.getCollection("iCardCreation")
  val webCollection = db.getCollection("webaccCreation")
  val bankCollection = db.getCollection("bankaccCreation")
 // coll.insert(new BasicDBObject("testCol","hello"));


  val dbCursor = coll.find()
  while (dbCursor.hasNext) {
    val o = dbCursor.next()
    println(o)
  }


   @ResponseStatus(HttpStatus.CREATED)
  @RequestMapping(value = Array("/api/v1/users"), method = Array(RequestMethod.POST)) //def createuser (@RequestParam(value="email", required=true)  email : String , @RequestParam(value="password", required=true)  password : String) = {
  def createuser(@Valid @RequestBody user: UserCreation) : UserCreation = {

   // println("Email from req is " + user.email + "pass");
  var start = 10000
  var end   = 200000
  var rnd = new scala.util.Random
	
  
  user.id  = start + rnd.nextInt( (end - start) + 1 )
	user.creationdt = new Date().toString()
	user.updateddt = new Date().toString()
	  //repository.save(new UserCreation(user.email,user.password,user.creationdt,user.id,user.updateddt));
	  //repository.save(user);
     userCollection.insert(new BasicDBObject("_id",user.id).append("email",user.email).append("password",user.password).append("created_at",user.creationdt).append("updated_at",user.updateddt));
    println("inser success");
    return user
  }

  @RequestMapping(value = Array("/api/v1/users/{user_id}"), method = Array(RequestMethod.GET))
  def viewuser(@PathVariable("user_id") id: Int): UserCreation = {

    // var viewuser: UserCreation = null;
	  var returnUser: UserCreation = new UserCreation()
    //	returnUser=userCollection.findById(id);
    var o:DBObject=   userCollection.findOne(new BasicDBObject("_id",id))
    returnUser.email = o.get("email").toString
    returnUser.id = o.get("_id").asInstanceOf[Int]
    returnUser.password=o.get("password").toString
    returnUser.creationdt=o.get("created_at").toString
    returnUser.updateddt=o.get("updated_at").toString

  return returnUser

  }

   @ResponseStatus(HttpStatus.CREATED)
  @RequestMapping(value = Array("/api/v1/users/{user_id}"), method = Array(RequestMethod.PUT))
  def updateuser(@Valid @RequestBody user: UserCreation,@PathVariable("user_id") id: Int) : UserCreation = {

     var updateUser: UserCreation = new UserCreation()
     var o: DBObject = userCollection.findOne(new BasicDBObject("_id",id))
     o.put("updated_at", new Date().toString)
     o.put("email", user.email)
     o.put("password", user.password)
     userCollection.save(o)
     updateUser.email = o.get("email").toString
     updateUser.id = o.get("_id").asInstanceOf[Int]
     updateUser.password=o.get("password").toString
     updateUser.creationdt=o.get("created_at").toString
     updateUser.updateddt=o.get("updated_at").toString

     return updateUser

  }
  
  
  @ResponseStatus(HttpStatus.CREATED)
  @RequestMapping(value = Array("/api/v1/users/{iCardUserId}/idcards"), method = Array(RequestMethod.POST))
  def idcardcreation(@Valid @RequestBody useridcard: ICardCreation,@PathVariable("iCardUserId") id: Int) : ICardCreation = {
  
  var start = 801000
  var end   = 888000
  var Newrnd = new scala.util.Random
  useridcard.iCardid = start + Newrnd.nextInt( (end - start) + 1 )

  useridcard.iCardUserId=id

  iCardCollection.insert(new BasicDBObject("_id",useridcard.iCardid).append("iCardUserId",useridcard.iCardUserId).append("card_name",useridcard.card_name).append("card_number",useridcard.card_number).append("expiration_date",useridcard.expiration_date));
  println("Icard insert success");

	return useridcard
  }

  @RequestMapping(value = Array("/api/v1/users/{iCardUserId}/idcards"), method = Array(RequestMethod.GET))
  def idcardview(@PathVariable("iCardUserId") id: Int) :ArrayList[ICardCreation]  = {

    var currentCardList = new ArrayList[ICardCreation]
    var returnUser : ICardCreation = null
    var cursor : DBCursor = iCardCollection.find(new BasicDBObject("iCardUserId",id));
    while (cursor.hasNext()) {
      //System.out.println(cursor.next());
      returnUser = new ICardCreation()
      var  obj:DBObject = cursor.next();
      returnUser.card_name=obj.get("card_name").toString
      returnUser.card_number=obj.get("card_number").toString
      returnUser.iCardid=Integer.parseInt(obj.get("_id").toString)
      returnUser.iCardUserId=Integer.parseInt(obj.get("iCardUserId").toString)
      returnUser.expiration_date=obj.get("expiration_date").toString
      System.out.println(returnUser);
      currentCardList.add(returnUser);
    }



	return currentCardList

  }
   @ResponseStatus(HttpStatus.NO_CONTENT)
  @RequestMapping(value = Array("/api/v1/users/{iCardUserId}/idcards/{iCardid}"), method = Array(RequestMethod.DELETE))
  def idcarddelete(@Valid @RequestBody useridcard: ICardCreation,@PathVariable("iCardid") cardId: Int) = {

     var o:DBObject = iCardCollection.findOne(new BasicDBObject("_id",cardId))
     iCardCollection.remove(o)
  }
  
  @ResponseStatus(HttpStatus.CREATED)
  @RequestMapping(value = Array("/api/v1/users/{webUserid}/weblogins"), method = Array(RequestMethod.POST))
  def Webacccreate(@Valid @RequestBody webacc: WebaccCreation,@PathVariable("webUserid") id: String) : WebaccCreation  = {
  
   var start = 900000
   var end   = 1000000
   var rnd = new scala.util.Random
   
   webacc.webLoginId =start + rnd.nextInt( (end - start) + 1 )
   webacc.webUserid=id
   //webRepository.save(webacc);
    webCollection.insert(new BasicDBObject("_id",webacc.webLoginId).append("webUserid",webacc.webUserid).append("password",webacc.password).append("login",webacc.login).append("url",webacc.url));
    println("insert success");

   return webacc
  }

  @RequestMapping(value = Array("/api/v1/users/{webUserid}/weblogins"), method = Array(RequestMethod.GET))
 // def viewwebacc(@PathVariable("user_id") id: String) : ArrayList[WebaccCreation] = {
    def viewwebacc(@PathVariable("webUserid") id: String) : ArrayList[WebaccCreation] = {

    var returnWebUser: WebaccCreation = null
    var returnWebUserList = new ArrayList[WebaccCreation]

    //	returnWebUser=webRepository.findBywebUserid(id)
    var cursor: DBCursor = webCollection.find(new BasicDBObject("webUserid", id))
    while (cursor.hasNext()) {
    returnWebUser  = new WebaccCreation()
    var obj: DBObject = cursor.next();
    returnWebUser.webLoginId = obj.get("_id").asInstanceOf[Int]
    returnWebUser.webUserid = obj.get("webUserid").toString
    returnWebUser.password = obj.get("password").toString
    returnWebUser.login = obj.get("login").toString
    returnWebUser.url = obj.get("url").toString
    returnWebUserList.add(returnWebUser)
  }

	return returnWebUserList
  }
    
	@ResponseStatus(HttpStatus.NO_CONTENT)
  	@RequestMapping(value = Array("/api/v1/users/{user_id}/weblogins/{webLoginId}"), method = Array(RequestMethod.DELETE))
  def deletewebacc(@Valid @RequestBody webacc: WebaccCreation,@PathVariable("webLoginId") loginId: Int) = {

	//webRepository.removeBywebLoginId(loginId)
    var delObj : DBObject = webCollection.findOne(new BasicDBObject("_id",loginId))
    webCollection.remove(delObj)
  }
  
  @ResponseStatus(HttpStatus.CREATED)
  @RequestMapping(value = Array("/api/v1/users/{user_id}/bankaccounts"), method = Array(RequestMethod.POST))
  def bankacccreate(@Valid @RequestBody bankacc: BankaccCreation,@PathVariable("user_id") id: Int) : BankaccCreation = {
  
    var start = 10550000
    var end   = 20550000
    var rnd = new scala.util.Random
	  bankacc.bankId = start + rnd.nextInt( (end - start) + 1 )
    bankacc.bankUserid=id

    println("Bank insert success");


  //  var url: URL = null
   // var conn: HttpURLConnection = null
    var line: String = null
    var result: String = null
    var urlToRead : String= "http://www.routingnumbers.info/api/data.json?rn="+bankacc.routing_number
    System.out.println("Url being accessed"+ urlToRead)
    //url = new URL(urlToRead)

  // var hpURLConn:HttpURLConnection = url.openConnection().asInstanceOf[HttpURLConnection]
  //  hpURLConn.setRequestMethod("GET")
    var response : HttpResponse[JsonNode] =Unirest.get("http://www.routingnumbers.info/api/data.json")
    .field("rn", bankacc.routing_number ).asJson();
   
    var body : JsonNode =response.getBody();

    var mapper:ObjectMapper = new ObjectMapper();
    
    
    	//	var url1:String = "http://www.routingnumbers.info/api/data.json?rn="+bankAccount.routing_number 
		val url:URL = new URL(urlToRead);
		var conn:HttpURLConnection = url.openConnection().asInstanceOf[HttpURLConnection]
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");
		if (conn.getResponseCode() != 200) {
		//throw new RuntimeException("Failed : HTTP error code : "+ conn.getResponseCode());
		bankacc.account_name = "Default Account Name"
		}else{
		var br:BufferedReader = new BufferedReader(new InputStreamReader(
		(conn.getInputStream())));
		var output:String=""
		System.out.println("Output from Server .... \n");
		/*while ((output = br.readLine()) != null) {
		System.out.println(output);
		}*/
		output = br.readLine();
		System.out.println(output);
		conn.disconnect();
		var mapper:ObjectMapper = new ObjectMapper();
		var routingDetails:routeDetails = mapper.readValue(output, classOf[routeDetails])
		if(routingDetails.message .equals("OK")){
		bankacc.account_name = routingDetails.customer_name
		}else{
		bankacc.account_name = "Default Bank Name"
		}
		println("customer name "+bankacc.account_name);
		}


    bankCollection.insert(new BasicDBObject("_id",bankacc.bankId).append("account_name",bankacc.account_name).append("account_number",bankacc.account_number).append("bankUserid",bankacc.bankUserid).append("routing_number",bankacc.routing_number));

	return bankacc
  }

  @RequestMapping(value = Array("/api/v1/users/{user_id}/bankaccounts"), method = Array(RequestMethod.GET))
  def viewbankacc(@PathVariable("user_id") id: Int) : ArrayList[BankaccCreation] = {

    var returnBankUser: BankaccCreation =null
    var returnBankUserList = new ArrayList[BankaccCreation]

      //	returnWebUser=webRepository.findBywebUserid(id)
      var cursor: DBCursor = bankCollection.find(new BasicDBObject("bankUserid", id))
      //System.out.println("Inside cursor"+id)
      while (cursor.hasNext()) {
      returnBankUser = new BankaccCreation()
      var obj: DBObject = cursor.next();
     // System.out.println("Inside while lool"+obj.get("_id").asInstanceOf[Int]+"userid "+obj.get("bankUserid").asInstanceOf[Int])
      returnBankUser.bankId= obj.get("_id").asInstanceOf[Int]
      returnBankUser.bankUserid = obj.get("bankUserid").asInstanceOf[Int]
      returnBankUser.account_name = obj.get("account_name").toString
      returnBankUser.account_number = obj.get("account_number").toString
      returnBankUser.routing_number = obj.get("routing_number").toString
	  //returnBankUser.customer_name = obj.get("customer_name").toString
	  
      returnBankUserList.add(returnBankUser)
    }
    return returnBankUserList

  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @RequestMapping(value = Array("/api/v1/users/{user_id}/bankaccounts/{ba_id}"), method = Array(RequestMethod.DELETE))
  def deletebankacc(@Valid @RequestBody bankacc: BankaccCreation,@PathVariable("ba_id") bank_id: Int) = {

	  //bankAccrepository.removeByBankId(bank_id)
    var delObj : DBObject = bankCollection.findOne(new BasicDBObject("_id",bank_id))
    bankCollection.remove(delObj)
  }
  
  
/*    @Bean
    def etagFilter(): Filter = {
    val shallowEtagHeaderFilter = new ShallowEtagHeaderFilter()
    return shallowEtagHeaderFilter
	}
  
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    def handleException(ex:MethodArgumentNotValidException):ErrorMessage ={
        var fldErrorsList:List[FieldError] =ex.getBindingResult().getFieldError()
          //ex.getBindingResult().getFieldErrors()S
        
        var objErrorsList:List[ObjectError] = ex.getBindingResult().getGlobalErrors()
        
        
        var errorList:List[String] = new ArrayList[String]
        var errorString:String = ""
        
         var tempitr = fldErrorsList.iterator()
        while(tempitr.hasNext()){
          var fldError:FieldError = tempitr.next()
          errorString = fldError.getField() + ", "+fldError.getDefaultMessage()
          errorList.add(errorString)
        }
        
        var tempitr2 = objErrorsList.iterator()
        while(tempitr2.hasNext()){
          var objError:ObjectError = tempitr2.next()
          errorString = objError.getObjectName() + ", "+objError.getDefaultMessage()
          errorList.add(errorString)
        }
        return new ErrorMessage(errorList);
    }*/
 
   }
