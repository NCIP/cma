package gov.nih.nci.cma.util;

import java.util.Comparator;
import gov.nih.nci.cma.domain.CmaRembClin2;

public class ClinParmsComparator implements Comparator {

	public int compare(Object o1, Object o2) {
		if (!(o1 instanceof CmaRembClin2) || !(o2 instanceof CmaRembClin2)) {
		  return 0;
		}
		CmaRembClin2 d1 = (CmaRembClin2) o1;
		CmaRembClin2 d2 = (CmaRembClin2) o2;
		String p1 = d1.getParm();
		String p2 = d2.getParm();
		return p1.compareTo(p2);
	}

}
