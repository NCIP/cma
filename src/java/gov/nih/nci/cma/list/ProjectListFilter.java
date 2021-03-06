/*L
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cma/LICENSE.txt for details.
 */

package gov.nih.nci.cma.list;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import gov.nih.nci.caintegrator.application.lists.ListSubType;
import gov.nih.nci.caintegrator.application.lists.ListType;

/**
 * ProjectListFilter "filters" the kind of lists expected in this 
 * project by type and pertaining subtypes.
 * @author rossok
 *
 */
public class ProjectListFilter{
	
	public static ListType[] values()	{

		ListType[] lsa = {ListType.PatientDID,ListType.Gene, ListType.Reporter}; //no duplicates here
		
		return lsa;
	}
    
    public static List<ListSubType> getSubTypesForType(ListType lt) {
        //control the mapping between which subtypes are associated with a primary type
        //for example: when adding a new "Reporter List", the app needs to know
        //which subtypes "Reporter" has so we can add a "IMAGE CLONE REPORTER" list
        ArrayList<ListSubType> lsta = new ArrayList();
        if(lt == ListType.Reporter){
            //list the reporter subtypes here and return them
            //lsta.add(ListSubType.PROBE_SET);
            lsta.add(ListSubType.IMAGE_CLONE);
            lsta.add(ListSubType.DBSNP);
            //lsta.add(ListSubType.SNP_PROBESET);
        }
        else if(lt == ListType.Gene){
            //ListSubType[] lsta = {ListSubType.GENBANK_ACCESSION_NUMBER, ListSubType.GENESYMBOL, ListSubType.LOCUS_LINK};
            //lsta.add(ListSubType.GENBANK_ACCESSION_NUMBER);
            lsta.add(ListSubType.GENESYMBOL);
           // lsta.add(ListSubType.LOCUS_LINK);
        }
        //   Default, Custom, IMAGE_CLONE, PROBE_SET, SNPProbeSet, DBSNP, GENBANK_ACCESSION_NUMBER, GENESYMBOL, LOCUS_LINK;
        return lsta;
    }
}
