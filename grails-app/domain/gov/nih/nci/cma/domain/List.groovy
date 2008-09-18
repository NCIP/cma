package gov.nih.nci.cma.domain


class List {
	 /*
	 LIST_ID        NUMBER,
	  NAME           VARCHAR2(50 BYTE),
	  TYPE           VARCHAR2(50 BYTE),
	  SUBTYPE        VARCHAR2(50 BYTE),
	  AUTHOR         VARCHAR2(50 BYTE),
	  INSTITUTION    VARCHAR2(50 BYTE),
	  CREATION_DATE  DATE,
	  CATEGORY       VARCHAR2(50 BYTE),
	  DESCRIPTION    VARCHAR2(500 BYTE),
	  ORIGIN         VARCHAR2(50 BYTE)              NOT NULL
	  */
	  
	  //Integer listId
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
	  
	  static hasMany = [listItems:ListItem]

	  String toString() {"${this.name}"}

	  
}