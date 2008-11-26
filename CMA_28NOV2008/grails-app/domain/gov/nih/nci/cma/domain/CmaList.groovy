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

	  String toString() {"${this.name}"}

	  
}