package hello

import org.springframework.data.mongodb.repository.MongoRepository
import java.util.{ArrayList, Date, List}
//remove if not needed

trait UserRepository extends MongoRepository[UserCreation, String] {

 // def findByFirstName(firstName: String): UserCreation
 def findById(Id: Int): UserCreation

 // def findByLastName(lastName: String): List[UserCreation]
}

trait WebCustomerRepository extends MongoRepository[WebaccCreation, String] {

 def findBywebUserid(webUserid: String): ArrayList[WebaccCreation]
 
 def removeBywebLoginId (webLoginId: Int): Long

}

trait userICardRepository extends MongoRepository[ICardCreation, String] {

 def findByiCardUserId(iCardUserId: Int): ArrayList[ICardCreation]
 
 def removeByiCardid (iCardid: Int): Long

}

trait userBankAccRepository extends MongoRepository[BankaccCreation, String] {

 def findBybankUserid(bankUserid: Int): ArrayList[BankaccCreation]
 
 def removeByBankId (bankId: Int): Long

}