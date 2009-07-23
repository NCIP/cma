package gov.nih.nci.cma.list.validation;

import java.util.List;

public interface ValidationService {
    public List<String> validatePatients(List<String> unvalidatedList);
    
    public List<String> validateGenes(List<String> unvalidatedList);
    
    public List<String> validateSamples(List<String> unvalidatedList);
}
