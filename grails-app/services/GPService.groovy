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

import gov.nih.nci.caintegrator.application.configuration.SpringContext;
import gov.nih.nci.caintegrator.application.analysis.gp.GenePatternPublicUserPool;
import gov.nih.nci.caintegrator.application.cache.PresentationCacheManager;
import gov.nih.nci.caintegrator.application.cache.PresentationTierCache;
import gov.nih.nci.caintegrator.application.lists.ListItem;
import gov.nih.nci.caintegrator.application.lists.ListType;
import gov.nih.nci.caintegrator.application.lists.UserList;
import gov.nih.nci.caintegrator.application.lists.UserListBeanHelper;
import gov.nih.nci.caintegrator.application.util.ApplicationConstants;
import gov.nih.nci.caintegrator.domain.annotation.gene.bean.GeneBiomarker;
import gov.nih.nci.caintegrator.domain.annotation.gene.bean.GeneExprReporter;
import gov.nih.nci.caintegrator.domain.annotation.service.AnnotationManager;
import gov.nih.nci.caintegrator.security.EncryptionUtil;
import gov.nih.nci.caintegrator.security.PublicUserPool;
import gov.nih.nci.caintegrator.security.UserCredentials;
import gov.nih.nci.caintegrator.studyQueryService.dto.annotation.AnnotationCriteria;
import gov.nih.nci.caintegrator.util.PlatformMapping;
import gov.nih.nci.caintegrator.util.idmapping.IdMapper;
import gov.nih.nci.caintegrator.util.idmapping.IdMappingCriteria;
import gov.nih.nci.cma.util.ProjectConstants;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.genepattern.client.GPServer;
import org.genepattern.webservice.Parameter;



class GPService {
	static scope = "request"
	boolean transactional = false
	
    private static Logger logger = Logger.getLogger(GPService.class);
    private IdMapper idMappingManager;
    private AnnotationManager annotationManager;
    private String gpPoolString = ":GP30:RBT";
    
    def generateGPSubmission = { request ->
    
    	// Harvest parameters:
		// Create a list for samples, if only 1 list is selected, grails makes it a string, not string[]
		String[] patientGroups = request.getParameterValues("selectedGroups");			// Required
		// If patientGroups is null, use 'ALL_PATIENTS'
    	if (patientGroups == null || patientGroups.length == 0) {
        	patientGroups = new String[1];
        	patientGroups[0] = "ALL_PATIENTS";
    	}		
		String geneReporterName = request.getParameter("geneReporterName");		// Optional
		if (geneReporterName == null || geneReporterName.equals("")) {
			geneReporterName = "none";
		}
		String gpModule = request.getParameter("analysisModuleName");			// Required
		//String binaryFileName = request.getParameter("platformName");			// Required
		String binaryFileName = request.getParameter("arrayPlatform");			// Required
		String chromosome = request.getParameter("chromosomeName");				// Conditional on analysisModuleName (= 'Copy Number') 
		String analysisResultName = request.getParameter("analysisResultName");	// Required
		if (analysisResultName == null || analysisResultName.equals("")) {
			analysisResultName = "unnamed_task";
		}
		// Get corresponding annotation file name for binaryFileName (platform.fileName)
		def platform = gov.nih.nci.cma.domain.Platform.findByFileName(binaryFileName)
		String annotationFileName = platform.annotationFileName
		// Get corresponding platform name for binaryFileName (platform.fileName)
		String arrayPlatformName = platform.platformName
		
//		 Remove printlns later:
		/*
		println("******")
		println("patientGroups[0] = " + patientGroups[0])
    	println("gpModule = " + gpModule)
    	println("geneReporterName = " + geneReporterName)
    	println("binaryFileName = " + binaryFileName)
    	println("chromosome = " + chromosome)
    	println("analysisResultName = " + analysisResultName)
    	println("annotationFileName = " + annotationFileName)
    	println("arrayPlatformName = " + arrayPlatformName)
    	*/
			
		idMappingManager = SpringContext.getBean("idMappingManager");
		annotationManager = SpringContext.getBean("annotationManager");
    	
        List<List<String>> allStringList = new ArrayList<List<String>>();
        List<String> fileNameList = new ArrayList<String>();
        List<String> idStringList = new ArrayList<String>();
        List<String> reportIdStringList = new ArrayList<String>();

		// First process sample groups information, which are the same for gene expression
		// and copy number.
    	UserListBeanHelper helper = new UserListBeanHelper(request.getSession().getId());
    	IdMappingCriteria criteria = new IdMappingCriteria();
    	
		for (String patientGroup : patientGroups) {
			Set<String> idSet = null;

	    	List<ListItem> listItems = helper.getUserList(patientGroup).getListItems();
	    	
			//println("### patientIdset ###")
	    	
	    	Set<String> patientIdset = new HashSet<String>();
			for (Iterator i = listItems.iterator(); i.hasNext(); ) {
				ListItem item = (ListItem)i.next();
				patientIdset.add(item.getName());
				
				//print(" " + item.getName())
			}
			
			//println(" END")
			
			criteria.setPatientIds(patientIdset);
			criteria.setFileName(binaryFileName);
			
	    	idSet = idMappingManager.getRbinaryIdsForPatientDIDs(criteria);
	    	
	    	//IdSet to hold patient Id in a format recognized by the R module.
	    	///	idSet.add("TCGA.06.119.01A.08D.00214.01");
	    	//For copy number analysis, the patient group file requires special format
	    	if (gpModule.equalsIgnoreCase("Copy Number")) {
	    		idStringList.add(getIdsAsDelimitedString(patientGroup, idSet, "\t", 1));
	    	} else {
	    		idStringList.add(getIdsAsDelimitedString(patientGroup, idSet, "\t", 0));
	    	}
		}

		allStringList.add(idStringList);
		fileNameList.add(request.getSession().getId() + "_labIdsFile");

/*
		List<PlatformMapping> platformMappings = 
			(List)request.getSession().getAttribute(ProjectConstants.ARRAY_PLATFORM_MAPPING);
		String annotationFileName = null;
		String binaryFileName = platformName;
		String arrayPlatformName = null;
		for (PlatformMapping pmapping : platformMappings) {
			if (pmapping.getFileName().equals(binaryFileName)) {
				arrayPlatformName = pmapping.getPlatformName();
				annotationFileName = pmapping.getAnnotationFileName();
				break;
			}
		}
*/
		
		// For reporter id file
		Set<String> reporterGroup = new HashSet<String>();
		if (geneReporterName != null && !geneReporterName.equalsIgnoreCase("none")) {
// Currently only using Gene lists, so this block isnt needed
// Also, no need for the substring stuff since we're not appending listtype

/*
			int i = geneReporterName.lastIndexOf(ListType.Reporter.toString());
			//it's a reporter filter
			if (i > 0) {
				geneReporterName = geneReporterName.substring(0, i - 3);
		    	List<ListItem> listItems = helper.getUserList(geneReporterName).getListItems();
		    	
		    	//Set<String> reporterIdSet = new HashSet<String>();
				for (Iterator it = listItems.iterator(); it.hasNext(); ) {
					ListItem item = (ListItem)it.next();
					reporterGroup.add(item.getName());
				}
				
				//reporterGroups = new String[reporterIdSet.size()];
				//reporterGroups = reporterIdSet.toArray(reporterGroups);
			}
			//It's a gene
			else {
				i = geneReporterName.lastIndexOf(ListType.Gene.toString());
				geneReporterName = geneReporterName.substring(0, i - 3);
*/

		    	List<ListItem> listItems = helper.getUserList(geneReporterName).getListItems();
		    	
		    	List<String> geneList = new ArrayList<String>();
				for (Iterator it = listItems.iterator(); it.hasNext(); ) {
					ListItem item = (ListItem)it.next();
					geneList.add(item.getName().toUpperCase());
				}
				AnnotationCriteria ac = new AnnotationCriteria();
				ac.setGeneSymbols(geneList);
				Map<GeneBiomarker, Collection<GeneExprReporter>> tempMap = null;

				//This is for gene expression, not copy number
				ac.setArrayPlatformName(arrayPlatformName);
				try {
					tempMap = annotationManager.getReportersForGenes(ac);
					logger.info("Have found " + tempMap.size() + " reporters.");
				} catch (Exception e) {
					logger.error("getReportersForGenes returns error: " + e.getMessage());
				}
			
				Set<GeneBiomarker> markerSet = tempMap.keySet();
				for (GeneBiomarker marker : markerSet) {
					Collection<GeneExprReporter> c = tempMap.get(marker);
					for (GeneExprReporter reporter : c) {
						reporterGroup.add(reporter.getName());
					}
				}
			//}
		}
		
		if (reporterGroup.isEmpty()) {
			reportIdStringList.add("reporter=NONE");
			allStringList.add(reportIdStringList);
			fileNameList.add(request.getSession().getId() + "_reporterIdsFile");
			logger.info("Have no gene or report list...");
		}
		else {
			reportIdStringList.add(getIdsAsDelimitedString("reporter", reporterGroup, "\t", 0));
			allStringList.add(reportIdStringList);
			fileNameList.add(request.getSession().getId() + "_reporterIdsFile");
		}

		
		//For class file
		//makeClassFile(classStringList, sampleGroups);
		//allStringList.add(classStringList);
		//fileNameList.add("ispyClassFile");
		
		//Now let's write them to files
		List<String> filePathList = new ArrayList<String>();
		writeGPFile(filePathList, allStringList, fileNameList);
		
		//Now get the R-binary file name:
		//SNP6.broad.Chromosome10.Rda
		//to-do here for r-binary name used in copy number analysis
		String r_fileName = binaryFileName;
		if (gpModule.equalsIgnoreCase("Copy Number")) {
			r_fileName = r_fileName + ".Chromosome" + chromosome + ".Rda";
			logger.info("Chromosome file for copy number analysis: " + r_fileName);
		}

		//*** RUN TASK ON THE GP SERVER
		HttpSession session = request.getSession();
		String tid = "209";

		String gpserverURL = System.getProperty("gov.nih.nci.caintegrator.gp.server") !=null ? 
				(String)System.getProperty("gov.nih.nci.caintegrator.gp.server") : "localhost:8080"; //default to localhost
				
		//println("gpserverURL = ")
				
		try {
		//*	
			PresentationTierCache presentationTierCache = PresentationCacheManager.getInstance();

			UserCredentials info = (UserCredentials) presentationTierCache.getNonPersistableObjectFromSessionCache(session.getId(), ApplicationConstants.userInfoBean);

			String cmaUser = null;
			
			if (info != null)
				cmaUser = info.getUserName();
			
			String publicUser = System.getProperty("gov.nih.nci.caintegrator.gp.publicuser.name");
			String password = System.getProperty("gov.nih.nci.caintegrator.gp.publicuser.password");
			
			//Check to see the user is already created otherwise create one.
			
			//temporary code
			if (cmaUser == null)
				cmaUser = publicUser;
			
			//println("cmaUser = " + cmaUser)
			//println("password = " + password)
			
			GPServer gpServer = null;
			if (cmaUser.equals(publicUser)) {
				String gpUser = (String)session.getAttribute(GenePatternPublicUserPool.PUBLIC_USER_NAME);
				if (gpUser == null) {
					PublicUserPool pool = GenePatternPublicUserPool.getInstance();
					gpUser = pool.borrowPublicUser();
					session.setAttribute(GenePatternPublicUserPool.PUBLIC_USER_NAME, gpUser);
					session.setAttribute(GenePatternPublicUserPool.PUBLIC_USER_POOL, pool);
					
					//if (pool != null)
					//	println("PublicUserPool not null")
					//else
					//	println("gpUser = " + gpUser)
				}
				cmaUser = gpUser;
				//println("cmaUser now = " + cmaUser)
			}
			String encryptKey = System.getProperty("gov.nih.nci.caintegrator.gp.desencrypter.key");
			//println("encryptKey = " + encryptKey)
			String urlString = EncryptionUtil.encrypt(cmaUser + gpPoolString, encryptKey);
			//println("urlString = " + urlString)
			urlString = URLEncoder.encode(urlString, "UTF-8");
			//println("urlString after encoding = " + urlString)
			String ticketString = gpserverURL + "gp?ticket=" + urlString;
			//println("Gene Pattern ticketString = " + ticketString)
			
			logger.info(ticketString);
			URL url;
            try {
            	url = new java.net.URL(ticketString);
            	URLConnection conn = url.openConnection();
            	final int size = conn.getContentLength();
            	logger.info(Integer.toString(size));

            } catch (Exception e) {
            	logger.error(e.getMessage());
            }
            
			gpServer = new GPServer(gpserverURL, cmaUser, password);
			
			//if (gpServer != null)
			//	println("gpServer not null")
			//else
			//	println("gpServer is null")
			
			//*/
			//GPServer gpServer = new GPServer(gpserverURL, gpuname, password);
			
			int offset = 4;
			String moduleName =  System.getProperty("gov.nih.nci.caintegrator.gp.modulename.geneexpression");
			
			//println("moduleName = " + moduleName)
			
			Parameter[] par = null;
			if (gpModule.equalsIgnoreCase("Copy Number")) {
				offset = 3;
				moduleName = System.getProperty("gov.nih.nci.caintegrator.gp.modulename.copynumber");
				par = new Parameter[filePathList.size() + offset + 1];
			}
			else
				par = new Parameter[filePathList.size() + offset + 2];
			
			// Store filenames
			int currpos= 1;
			for (int i = 0; i < filePathList.size(); i++) {
				par[i] = new Parameter("input.filename" + currpos++, filePathList.get(i));
			}
			par[--currpos] = new Parameter("project.name", "cma");

			//r_fileName = "'/usr/local/genepattern/resources/DataMatrix_ISPY_306cDNA_17May07.Rda'";
			par[++currpos] = new Parameter("array.filename", r_fileName);
			if (gpModule.equals("Gene Expression"))
				par[++currpos] = new Parameter("annotation.filename", annotationFileName);
			
			par[++currpos] = new Parameter("analysis.name", analysisResultName);

			//always just 2
			if (gpModule.equalsIgnoreCase("Gene Expression")) {
				par[++currpos] = new Parameter("output.cls.file",analysisResultName+".cls");
				par[++currpos] = new Parameter("output.gct.file",analysisResultName+".gct");
			}
			else 
				par[++currpos] = new Parameter("output.cn.file",analysisResultName+".cn");
			//JobResult preprocess = gpServer.runAnalysis(gpModule, par);
			
			//println("Before gpServer.runAnalysisNoWait")
			//println("par = " + par)
			
			int nowait = gpServer.runAnalysisNoWait(moduleName, par);
			
			//println("After gpServer.runAnalysisNoWait")

			tid = String.valueOf(nowait);
			//LSID = urn:lsid:8080.root.localhost:genepatternmodules:20:2.1.7
			request.setAttribute("jobId", tid);
			request.setAttribute("gpStatus", "running");
			session.setAttribute("genePatternServer", gpServer);
			request.setAttribute("genePatternURL", ticketString);
			request.getSession().setAttribute("gptid", tid);
			//session.setAttribute("genePatternPreprocess", preprocess);
			
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			logger.error(sw.toString());
			logger.error(gpModule + " failed...." + e.getMessage());
			throw new Exception(e.getMessage());
		}
		//return mapping.findForward("viewJob");
    }
    
    //private Set<String> getReporterGroupId(String filterName){
    	
    	//return null;
    //}
    
    private String getIdsAsDelimitedString(String listName, Set<String> idSet, 
    		String token, int extraColumn) {
		StringBuffer sb = null;
		if (extraColumn == 1) {
			sb = new StringBuffer(replaceSpace(listName) + "=Chromosome" + token
					+ "PhysicalPosition" + token);
		} else {
			sb = new StringBuffer(replaceSpace(listName) + "=");
		}
		int size = idSet.size();
		int count = 0;
		for (String id : idSet) {
			sb.append(id);
			if (++count < size) {
				sb.append(token);
			}
		}
		if (sb.length() == 0) {
			return "";
		}
		
		return sb.toString();
    }
    
    private void writeGPFile(List<String> filePathList, 
    		List<List<String>> allIdStringList,
    		List<String> fileNameList)throws IOException {
    	int count = 0; 
    	String fileName = null;
    	String fileExtension = ".txt";
		for (List<String> list : allIdStringList) {
			if (!list.isEmpty()){
				fileName = fileNameList.get(count);
				if (fileName.equals("ispyClassFile"))
					fileExtension = ".cls";
				else
					fileExtension = ".txt";
				//File idFile =File.createTempFile(fileName, fileExtension, new File("C:\\temp\\cma"));
				File idFile =File.createTempFile(fileName, fileExtension);
				FileWriter idFw = new FileWriter(idFile);
				for (String ids : list) {
					idFw.write(ids);
					idFw.write("\n");
				}
				idFw.close();
				filePathList.add(idFile.getAbsolutePath());
			} else {
				filePathList.add("");
			}
			count++;
		}
    }

    private String replaceSpace(String text) {
    	return text.replaceAll(" ", "_");
    }
    
    /**
     * @return the idMappingManager
     */
    public IdMapper getIdMappingManager() {
        return idMappingManager;
    }
    
    /**
     * @param idMappingManager the idMappingManager to set
     */
    public void setIdMappingManager(IdMapper idMappingManager) {
        this.idMappingManager = idMappingManager;
    }
    
	public AnnotationManager getAnnotationManager() {
		return annotationManager;
	}
	
	public void setAnnotationManager(AnnotationManager annotationManager) {
		this.annotationManager = annotationManager;
	}
	
	
}

