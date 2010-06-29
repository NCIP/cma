package gov.nih.nci.cma.list;

import gov.nih.nci.caintegrator.application.configuration.SpringContext;
import gov.nih.nci.caintegrator.application.lists.ListSubType;
import gov.nih.nci.caintegrator.application.lists.ListType;
import gov.nih.nci.caintegrator.application.lists.ListValidator;
import gov.nih.nci.cma.list.validation.ValidationService;

import java.util.List;

import javax.naming.OperationNotSupportedException;

public class ProjectListValidator extends ListValidator{
    
   
    /**
     * 
     */
    public ProjectListValidator() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @param listType
     * @param unvalidatedList
     * @throws OperationNotSupportedException
     */
    public ProjectListValidator(ListType listType, List<String> unvalidatedList) throws OperationNotSupportedException {
        super(listType, unvalidatedList);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param listType
     * @param listSubType
     * @param unvalidatedList
     * @throws OperationNotSupportedException
     */
    public ProjectListValidator(ListType listType, ListSubType listSubType, List<String> unvalidatedList) throws OperationNotSupportedException {
        super(listType, listSubType, unvalidatedList);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void validate(ListType listType, List<String> unvalidatedList) throws OperationNotSupportedException {
        validate(listType, null, unvalidatedList);
        
    }

    @Override
    public void validate(ListType listType, ListSubType listSubType, List<String> unvalidatedList) throws OperationNotSupportedException {
        ValidationService validationService = (ValidationService) SpringContext.getBean("listValidationService");
        
        if(listType == ListType.PatientDID){ 
            this.validList = unvalidatedList;
            /**
             * TODO VALIDATE
             */
//            invalidList = unvalidatedList;
//            validList = validationService.validatePatients(unvalidatedList);
//            invalidList.removeAll(validList); 
        }
        else{
            this.validList = unvalidatedList;
        }
         
    }

}
