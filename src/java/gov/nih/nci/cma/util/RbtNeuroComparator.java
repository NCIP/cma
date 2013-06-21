/*L
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cma/LICENSE.txt for details.
 */

package gov.nih.nci.cma.util;

import java.util.Comparator;
import gov.nih.nci.cma.domain.rembrandt.NeurologicalEvaluation;

public class RbtNeuroComparator implements Comparator {

	public int compare(Object o1, Object o2) {
		NeurologicalEvaluation n1 = (NeurologicalEvaluation) o1;
		NeurologicalEvaluation n2 = (NeurologicalEvaluation) o2;
		
		java.math.BigDecimal f1 = n1.getFollowupMonth();
		java.math.BigDecimal f2 = n2.getFollowupMonth();
		
		if ((n1.getFollowupMonth() != null) &&
			(n2.getFollowupMonth() != null)) {		
			return f1.compareTo(f2);
		}
		return 0;
	}

}
