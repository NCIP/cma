/*L
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cma/LICENSE.txt for details.
 */

package gov.nih.nci.cma.util;

import java.util.Comparator;
import gov.nih.nci.cma.domain.CmaRembClin;

public class ClinParmsComparator implements Comparator {

	public int compare(Object o1, Object o2) {
		if (!(o1 instanceof CmaRembClin) || !(o2 instanceof CmaRembClin)) {
		  return 0;
		}
		CmaRembClin d1 = (CmaRembClin) o1;
		CmaRembClin d2 = (CmaRembClin) o2;
		String p1 = d1.getParm();
		String p2 = d2.getParm();
		
		if (p1.equals(p2)) {
		  Integer r1 = d1.getRepNum();
		  Integer r2 = d2.getRepNum();
		  		
		  if ((r1 == null) || (r2 == null)) return 0;
		  
		  if ( r1 < r2) return -1;
		  if (r2 > r1) return 1;
		  if (r2 == r1) return 0;		
		}
		
		return p1.compareTo(p2);
	}

}
