package gov.nih.nci.cma.domain

  import gov.nih.nci.cma.domain.CmaList

class CmaListItem {
	
	/*  LIST_ITEM table definition
	 *  LIST_ID           NUMBER,
	 	ITEM_NAME         VARCHAR2(50 BYTE),
	 	RANK              NUMBER,
	 	ITEM_DESCRIPTION  VARCHAR2(256 BYTE),
	 	LIST_NAME         VARCHAR2(50 BYTE),
	 	ITEM_ID           NUMBER,
	 	ID                NUMBER(19),
	 	VERSION           NUMBER(19)
	 * 
	 */
	 static belongsTo = CmaList
	 
	 //Integer listId
	 gov.nih.nci.cma.domain.CmaList cmaList	 
	 String listName
	 String itemName
	 Integer rank
	 String itemDescription
	 Integer itemId
	 Integer version
	  
	 static constraints = {
	  	listName(blank:false)
		itemName(blank:false)
		rank(nullable:true)
		itemDescription(nullable:true)
		itemId(nullable:true)
	 }
}