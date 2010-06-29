package gov.nih.nci.cma.list.validation;

import gov.nih.nci.caintegrator.dto.query.QueryDTO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ValidationServiceImpl implements ValidationService{
    private SessionFactory sessionFactory;
    
    
    

    

    /**
     * @return Returns the sessionFactory.
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * @param sessionFactory The sessionFactory to set.
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<String> validatePatients(List<String> unvalidatedList) {
        /**TODO
         * ADD PROPER VALIDATION!!
         */
        //        Session theSession = sessionFactory.getCurrentSession();        
//        String theHQL = "";
//        Query theQuery = null;
//        Collection objs = null;
//        theHQL = "select sp.studySubjectIdentifier from StudyParticipant as sp " +
//                "where sp.studySubjectIdentifier IN (:sp_studySubjectIdentifiers) ";
//        theQuery = theSession.createQuery(theHQL);
//        theQuery.setParameterList("sp_studySubjectIdentifiers",unvalidatedList);
//        objs = theQuery.list();
//        ArrayList<String> validList = new ArrayList<String>(objs);
//        for(String s:validList){
//            System.out.println(s);
//        }        
//        return validList;
        return unvalidatedList;
    }

    public List<String> validateGenes(List<String> unvalidatedList) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<String> validateSamples(List<String> unvalidatedList) {
        // TODO Auto-generated method stub
        return null;
    }

}
