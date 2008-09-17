class ApplicationUser {
	String userId
	String password

	static constraints = {
		userId(length:6..20,unique:true)
		password(length:6..20)
	}
}
