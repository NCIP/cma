package gov.nih.nci.cma.web.graphing;


import java.awt.Color;

import org.jfree.chart.LegendItemCollection;



/**
* caIntegrator License
* 
* Copyright 2001-2005 Science Applications International Corporation ("SAIC"). 
* The software subject to this notice and license includes both human readable source code form and machine readable, 
* binary, object code form ("the caIntegrator Software"). The caIntegrator Software was developed in conjunction with 
* the National Cancer Institute ("NCI") by NCI employees and employees of SAIC. 
* To the extent government employees are authors, any rights in such works shall be subject to Title 17 of the United States
* Code, section 105. 
* This caIntegrator Software License (the "License") is between NCI and You. "You (or "Your") shall mean a person or an 
* entity, and all other entities that control, are controlled by, or are under common control with the entity. "Control" 
* for purposes of this definition means (i) the direct or indirect power to cause the direction or management of such entity,
*  whether by contract or otherwise, or (ii) ownership of fifty percent (50%) or more of the outstanding shares, or (iii) 
* beneficial ownership of such entity. 
* This License is granted provided that You agree to the conditions described below. NCI grants You a non-exclusive, 
* worldwide, perpetual, fully-paid-up, no-charge, irrevocable, transferable and royalty-free right and license in its rights 
* in the caIntegrator Software to (i) use, install, access, operate, execute, copy, modify, translate, market, publicly 
* display, publicly perform, and prepare derivative works of the caIntegrator Software; (ii) distribute and have distributed 
* to and by third parties the caIntegrator Software and any modifications and derivative works thereof; 
* and (iii) sublicense the foregoing rights set out in (i) and (ii) to third parties, including the right to license such 
* rights to further third parties. For sake of clarity, and not by way of limitation, NCI shall have no right of accounting
* or right of payment from You or Your sublicensees for the rights granted under this License. This License is granted at no
* charge to You. 
* 1. Your redistributions of the source code for the Software must retain the above copyright notice, this list of conditions
*    and the disclaimer and limitation of liability of Article 6, below. Your redistributions in object code form must reproduce 
*    the above copyright notice, this list of conditions and the disclaimer of Article 6 in the documentation and/or other materials
*    provided with the distribution, if any. 
* 2. Your end-user documentation included with the redistribution, if any, must include the following acknowledgment: "This 
*    product includes software developed by SAIC and the National Cancer Institute." If You do not include such end-user 
*    documentation, You shall include this acknowledgment in the Software itself, wherever such third-party acknowledgments 
*    normally appear.
* 3. You may not use the names "The National Cancer Institute", "NCI" "Science Applications International Corporation" and 
*    "SAIC" to endorse or promote products derived from this Software. This License does not authorize You to use any 
*    trademarks, service marks, trade names, logos or product names of either NCI or SAIC, except as required to comply with
*    the terms of this License. 
* 4. For sake of clarity, and not by way of limitation, You may incorporate this Software into Your proprietary programs and 
*    into any third party proprietary programs. However, if You incorporate the Software into third party proprietary 
*    programs, You agree that You are solely responsible for obtaining any permission from such third parties required to 
*    incorporate the Software into such third party proprietary programs and for informing Your sublicensees, including 
*    without limitation Your end-users, of their obligation to secure any required permissions from such third parties 
*    before incorporating the Software into such third party proprietary software programs. In the event that You fail 
*    to obtain such permissions, You agree to indemnify NCI for any claims against NCI by such third parties, except to 
*    the extent prohibited by law, resulting from Your failure to obtain such permissions. 
* 5. For sake of clarity, and not by way of limitation, You may add Your own copyright statement to Your modifications and 
*    to the derivative works, and You may provide additional or different license terms and conditions in Your sublicenses 
*    of modifications of the Software, or any derivative works of the Software as a whole, provided Your use, reproduction, 
*    and distribution of the Work otherwise complies with the conditions stated in this License.
* 6. THIS SOFTWARE IS PROVIDED "AS IS," AND ANY EXPRESSED OR IMPLIED WARRANTIES, (INCLUDING, BUT NOT LIMITED TO, 
*    THE IMPLIED WARRANTIES OF MERCHANTABILITY, NON-INFRINGEMENT AND FITNESS FOR A PARTICULAR PURPOSE) ARE DISCLAIMED. 
*    IN NO EVENT SHALL THE NATIONAL CANCER INSTITUTE, SAIC, OR THEIR AFFILIATES BE LIABLE FOR ANY DIRECT, INDIRECT, 
*    INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE 
*    GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF 
*    LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT 
*    OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
* 
*/

public class LegendCreator {

	/**
	 * creates and HTML legend and returns a string of HTML
	 * @param lic - a jFreechart LegendItemCollection
	 * @return
	 */
	public static String buildLegend(LegendItemCollection lic, String legendTitle)	{
		
		//TODO: take annotation link as a string param
		
		String html = new String();
		Color p = null;
		//html = "<fieldset style='display:table;width:600; border:1px solid gray; text-align:left; padding:5px;'>";
		//html += "<legend>Legend: "+ legendTitle+"</legend>";
		
		for(int i=0; i<lic.getItemCount(); i++)	{
			p = (Color) lic.get(i).getFillPaint();
			//html += "<div style='margin:10px; padding:10px;border:1px solid red;'><label style='width:30px; height:10px; background-color: "+ c2hex(p)+"; border:1px solid black;'>&nbsp;&nbsp;&nbsp;</label><b style='color: "+ c2hex(p)+"'>"+lic.get(i).getLabel()+"</b></div>\n";	
			html += "<table style=\"display:inline;\"><tr><td style=\"width:10px; height:10px; background-color: " 
				+ c2hex(p)+"; border:1px solid black;\">&nbsp;&nbsp;&nbsp;</td><td><a style=\"text-decoration:none\" " +
						"href=\"javascript:spawnAnnot('"+lic.get(i).getLabel()+"\');\"><b style=\"color: " 
						+ c2hex(p)+"\">"+lic.get(i).getLabel()+"</b></a></td></tr></table>";

			//html += "<table style=\"display:inline;\"><tr><td style=\"width:10px; height:10px; background-color: "+ c2hex(p)+"; border:1px solid black;\">&nbsp;&nbsp;&nbsp;</td><td><a style=\"text-decoration:none\" href=\"javascript:alert(\'some annotation for:"+lic.get(i).getLabel()+"\');\"><b style=\"color: "+ c2hex(p)+"\">"+lic.get(i).getLabel()+"</b></a></td></tr></table>";
		}
		//html += "</fieldset>";
		//System.out.println(html);
		String js="";
		//js = "<script language=\"javascript\">document.getElementById('legend').innerHTML = \""+ html +" \"; </script>";
		return html;
	}

	/**
	 * creates and HTML legend and returns a string of HTML
	 * @param lic - a jFreechart LegendItemCollection
	 * @return
	 */
	public static String buildSmartLegend(String chipType, LegendItemCollection lic, String legendTitle)	{
		
		//TODO: take annotation link as a string param
		
		String html = new String();
		Color p = null;
		//html = "<fieldset style='display:table;width:600; border:1px solid gray; text-align:left; padding:5px;'>";
		//html += "<legend>Legend: "+ legendTitle+"</legend>";
		
		for(int i=0; i<lic.getItemCount(); i++)	{
			p = (Color) lic.get(i).getFillPaint();
			//html += "<div style='margin:10px; padding:10px;border:1px solid red;'><label style='width:30px; height:10px; background-color: "+ c2hex(p)+"; border:1px solid black;'>&nbsp;&nbsp;&nbsp;</label><b style='color: "+ c2hex(p)+"'>"+lic.get(i).getLabel()+"</b></div>\n";	

			// JB: GF [#21548] Issue for annotation query in GeneView
			// The LPG application that these probeset links redirect the user to, only support the HG-133 Chip.
			// So, when non-HG133 chips were selected for the GeneView search, those links probeset 
			// links are NOT hyperlinked to the LPG site.
			if ( chipType.equals("AFFY_HT_HG-U133A") || chipType.equals("AFFY_HGU133P2") ) {
				html += "<table style=\"display:inline;\"><tr><td style=\"width:10px; height:10px; background-color: " 
					+ c2hex(p)+"; border:1px solid black;\">&nbsp;&nbsp;&nbsp;</td><td><a style=\"text-decoration:none\" " +
							"href=\"javascript:spawnAnnot('"+lic.get(i).getLabel()+"\');\"><b style=\"color: " 
							+ c2hex(p)+"\">"+lic.get(i).getLabel()+"</b></a></td></tr></table>";
			} else {
				html += "<table style=\"display:inline;\"><tr><td style=\"width:10px; height:10px; background-color: " 
					+ c2hex(p)+"; border:1px solid black;\">&nbsp;&nbsp;&nbsp;</td><td><b style=\"color: " 
							+ c2hex(p)+"\">"+lic.get(i).getLabel()+"</b></td></tr></table>";
			}
			//html += "<table style=\"display:inline;\"><tr><td style=\"width:10px; height:10px; background-color: "+ c2hex(p)+"; border:1px solid black;\">&nbsp;&nbsp;&nbsp;</td><td><a style=\"text-decoration:none\" href=\"javascript:alert(\'some annotation for:"+lic.get(i).getLabel()+"\');\"><b style=\"color: "+ c2hex(p)+"\">"+lic.get(i).getLabel()+"</b></a></td></tr></table>";
		}
		//html += "</fieldset>";
		//System.out.println(html);
		String js="";
		//js = "<script language=\"javascript\">document.getElementById('legend').innerHTML = \""+ html +" \"; </script>";
		return html;
	}
	
	/**
	 * converts an awt.Color to a hex color string
	 * in the format: #zzzzzz for use in HTML
	 * @param c
	 * @return
	 */
    public static String c2hex(Color c) {
    	//http://rsb.info.nih.gov/ij/developer/source/index.html
		final char[] hexDigits = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
	    
        int i = c.getRGB();
        char[] buf7 = new char[7];
        buf7[0] = '#';
        for (int pos=6; pos>=1; pos--) {
            buf7[pos] = hexDigits[i&0xf];
            i >>>= 4;
        }
        return new String(buf7);
    }
}
