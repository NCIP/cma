/*L
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cma/LICENSE.txt for details.
 */

package gov.nih.nci.cma.util;

import org.apache.commons.lang.StringUtils;
import org.htmlparser.util.Translate;

public class SafeHTMLUtil {

	public static String clean(String s)	{
		String clean = Translate.decode(s).replace("<", "").replace(">", "");
		clean = StringUtils.replace(clean, "script", "");
		clean = StringUtils.replace(clean, "%", "");
		clean = StringUtils.replace(clean, "#", "");
		clean = StringUtils.replace(clean, ";", "");
		clean = StringUtils.replace(clean, "'", "");
		clean = StringUtils.replace(clean, "\"", "");
		clean = StringUtils.replace(clean, "$", "");
		clean = StringUtils.replace(clean, "&", "");
		clean = StringUtils.replace(clean, "(", "");
		clean = StringUtils.replace(clean, ")", "");
		clean = StringUtils.replace(clean, "/", "");
		clean = StringUtils.replace(clean, "\\", "");
		if(clean.length()==0){
			clean = "unamedQuery";
		}
		return clean;
		
	}
}
