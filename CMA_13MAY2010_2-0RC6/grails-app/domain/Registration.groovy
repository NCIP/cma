class Registration {

	String firstName
	String lastName
	String contexts
	String email
	String phone
	String institution
	String department
	String verificationCode
		
    static transients = ["firstName","lastName","contexts","email","phone","institution","department","verificationCode"]

	static constraints = {
		firstName(maxLength:20,blank:false)
		lastName(maxLength:20,blank:false)
		contexts(nullable:false,blank:false)
		email(email:true,blank:false)
		phone(matches:"[0-9]{3}-[0-9]{3}-[0-9]{4}",blank:false)
		institution(maxLength:50,blank:false)
		department(maxLength:50,blank:true)
	    verificationCode(blank:false)
	}
	
	String toString() {"${this.firstName} ${this.lastName}; ${this.contexts}; ${this.email}; ${this.phone}; ${this.institution}; ${this.department}; "}
	
}