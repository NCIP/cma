package gov.nih.nci.cma.domain

class ListItem {
	
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
	 static belongsTo = List
	 
	 String listName
	 Integer listId
	 String itemName
	 Integer rank
	 String itemDescription
	 Integer itemId
	 Integer version
}