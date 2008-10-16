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
		return p1.compareTo(p2);
	}

}
