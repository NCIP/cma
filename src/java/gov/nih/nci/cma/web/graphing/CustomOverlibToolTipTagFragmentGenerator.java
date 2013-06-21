/*L
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cma/LICENSE.txt for details.
 */

package gov.nih.nci.cma.web.graphing;


import org.apache.commons.lang.StringUtils;
import org.jfree.chart.imagemap.ToolTipTagFragmentGenerator;

/**
 * @author landyr
 *
 */


public class CustomOverlibToolTipTagFragmentGenerator implements ToolTipTagFragmentGenerator{
	public String extra = ""; //extra, such as href, onclick, etc

	public String generateToolTipFragment(String toolTipText) {

		String ttip = ""; //holds the ttip text
		String _extra = "";	//holds any extra

		if(toolTipText.indexOf("|")!=-1){
			String[] tps = StringUtils.split(toolTipText, "|");
        	_extra = " " + tps[0];
        	ttip = tps[1];
        }
		else	{
			ttip = toolTipText;
		}

		return this.extra + " " + _extra + " onMouseOver=\"return overlib('" + ttip
            + "', CAPTION, 'Additional Info', FGCOLOR, '#FFFFFF', BGCOLOR, '#000000', WIDTH, 150, HEIGHT, 25);\" onMouseOut=\"return nd();\"";

    }

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

}
