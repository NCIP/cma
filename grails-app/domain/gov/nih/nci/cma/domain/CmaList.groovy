package gov.nih.nci.cma.domain

class CmaList {
	 
	  String  name
	  String  type
	  String subtype
	  String category
	  String author
	  String institution
	  Date   creationDate
	  String description
	  String origin	  
	 
	  Set listItems = new HashSet()
	  
	  static hasMany = [listItems:CmaListItem]
	  
	  static constraints = {
	  	name(blank:false)
		type(blank:false)
		subtype(nullable:true)
		category(nullable:true)
		author(blank:false)
		institution(nullable:true)
		category(nullable:true)
		creationDate(blank:false)
		description(blank:false)
		origin(blank:false)
	  }

	  String toString() {"${this.name}"}

	  
}