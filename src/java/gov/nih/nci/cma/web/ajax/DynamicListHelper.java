/*L
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cma/LICENSE.txt for details.
 */

package gov.nih.nci.cma.web.ajax;

import gov.nih.nci.caintegrator.application.lists.ListOrigin;
import gov.nih.nci.caintegrator.application.lists.ListSubType;
import gov.nih.nci.caintegrator.application.lists.ListType;
import gov.nih.nci.caintegrator.application.lists.UserList;
import gov.nih.nci.caintegrator.application.lists.UserListBeanHelper;
import gov.nih.nci.caintegrator.application.lists.ajax.CommonListFunctions;

import gov.nih.nci.cma.list.ProjectListFilter;
import gov.nih.nci.cma.list.ProjectListValidator;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import javax.naming.OperationNotSupportedException;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DynamicListHelper {
/**
 * Basically a wrapper for app-commons/application/lists/ajax/CommonListFunctions
 * except this specifically sets the ListValidator for this context and passes it off
 * to the CommonListFunctions
 *
 */
	
	public DynamicListHelper() {}
	
    	
	public static String createGenericList(String listType, List<String> list, String name) throws OperationNotSupportedException	{
        try {
            String[] tps = CommonListFunctions.parseListType(listType);
            //tps[0] = ListType
            //tps[1] = ListSubType (if not null)
            ListType lt = ListType.valueOf(tps[0]);
            if(tps.length > 1 && tps[1] != null){
                //create a list out of [1]
                ListSubType lst = ListSubType.valueOf(tps[1]);
                ProjectListValidator lv = new ProjectListValidator(ListType.valueOf(tps[0]), ListSubType.valueOf(tps[1]), list);
                return CommonListFunctions.createGenericList(lt, lst, list, name, lv);
            }
            else if(tps.length >0 && tps[0] != null)    {
                //no subtype, only a primary type - typically a PatientDID then
                ProjectListValidator lv = new ProjectListValidator(ListType.valueOf(tps[0]), list);
                return CommonListFunctions.createGenericList(lt, list, name, lv);
            }
            else    {
                //no type or subtype, not good, force to clinical in catch                
                throw new Exception();
            }
        }
        catch(Exception e)  {
            //try as a patient list as default, will fail validation if its not accepted
            return CommonListFunctions.createGenericList(ListType.PatientDID, list, name, new ProjectListValidator(ListType.PatientDID, list));
        }
    }
	
	public static String getAllLists()	{
		//create a list of allowable types
		//Added <String> then magically worked, also added json simple jar
		ArrayList<String> listTypesList = new ArrayList<String>();
		for(ListType l  : ProjectListFilter.values())	{
			listTypesList.add(l.toString());
		}
		//call CommonListFunctions.getAllLists(listTypesList);
		return CommonListFunctions.getAllLists(listTypesList);
		
		/************* BS copied from app-commons*******************************/
	/*	
		UserListBeanHelper helper = new UserListBeanHelper();
        JSONArray listContainerArray = new JSONArray();
        for(String listType : listTypesList)	{
	        Collection<String> myLists = new ArrayList<String>();
	        
	        JSONObject listContainer = new JSONObject();
	       
	        JSONArray myJSONLists = new JSONArray();
	        
	        listContainer.put("listType", listType);
	        //which do we want to display differently in the UI
	        
	        
	        myLists = helper.getGenericListNames(ListType.valueOf(listType));
	        
	        for(String listName : myLists) {
	            UserList ul = helper.getUserList(listName);
	            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm aaa", Locale.US);
	            if(ul!=null)  {
                    String listSubType ="";
                    if(ul.getListSubType()!=null){
                        listSubType = "Subtype: " + ul.getListSubType().toString() + " - ";
                    }
                    
                    JSONObject jsonListName = new JSONObject();
                    String listNotes = ul.getNotes();
                    if(ul.getNotes()!=null){                        
                        if(listNotes.length()>100){
                            listNotes = listNotes.substring(0,99);
                        }                        
                    }
                    
                    String style = "";
                    if(ul.getListOrigin()!=null) {
                        jsonListName.put("origin", ul.getListOrigin().toString());                        
                        if(ul.getListOrigin().equals(ListOrigin.Default)){
                            style = "color:#000000";
                            jsonListName.put("highlightType", style);
                        }
                        if(ul.getListOrigin().equals(ListOrigin.Custom)){
                            style = "color:#A90101";
                            jsonListName.put("highlightType", style);
                        }
                        
                    }
                    else{
                        jsonListName.put("highlightType", style);
                    }
                       
                    
                    
                    
                    
                    jsonListName.put("author", ul.getAuthor());
                   
                    jsonListName.put("notes", listNotes);
	                
	                jsonListName.put("listSubType", listSubType);
	                jsonListName.put("listName", ul.getName());
                    if(ul.getDateCreated()!=null){
                        jsonListName.put("listDate", dateFormat.format(ul.getDateCreated()).toString());
                    }
	                jsonListName.put("itemCount", String.valueOf(ul.getItemCount()));
	                jsonListName.put("invalidItems", String.valueOf(ul.getInvalidList().size()));
	                
	                String commas = StringUtils.join(ul.getList().toArray(), ", ");
	                jsonListName.put("listItems", commas);
	                myJSONLists.add(jsonListName);
	            }
	            
	        }
	        listContainer.put("listItems",myJSONLists);
	        listContainerArray.add(listContainer);
        }
        return listContainerArray.toString();*/
		
		/****************************************************/
	}
	
	public static String exportListasTxt(String name, HttpSession session){
		return CommonListFunctions.exportListasTxt(name, session);
	}

		
	public static String uniteLists(String[] sLists, String groupName, String groupType, String action)	{	
		return CommonListFunctions.uniteLists(sLists, groupName, groupType, action);
	}
	public String saveSamples(String commaSepList, String name)	{
		String success = "fail";
		String[] listArr = commaSepList.split(",");
		List<String> list = Arrays.asList(listArr);
		try	{
            ProjectListValidator lv = new ProjectListValidator(ListType.PatientDID, list);

			success = CommonListFunctions.createGenericList(ListType.PatientDID, list, name, lv);
		}
		catch(Exception e) {
			//most likely cant access the session
			System.out.println("err: "+ e.getMessage());
		}
		return success;
	}
	
	//same as above, but callable outside DWR context
	public static String saveSamplesWithSession(String commaSepList, String name, HttpSession session)	{
		String success = "fail";
		String[] listArr = commaSepList.split(",");
		List<String> list = Arrays.asList(listArr);
		try	{
            ProjectListValidator lv = new ProjectListValidator(ListType.PatientDID, list);
			success = CommonListFunctions.createGenericListWithSession(ListType.PatientDID, null, list, name, lv, session);
		}
		catch(Exception e) {
			//most likely cant access the session
			System.out.println("err: "+ e.getMessage());
		}
		return success;
	}
	
	
	public String processRepoterAnnotation(String key)	{
		String url = System.getProperty("gov.nih.nci.cma.annotations.links.reporter");
		String aurl = url != null ? url +key : "";
		return aurl;
	}
}
