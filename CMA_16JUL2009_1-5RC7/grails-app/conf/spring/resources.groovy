// Place your Spring DSL code here
beans = {
		annotationManager(gov.nih.nci.caintegrator.domain.annotation.service.AnnotationManagerImpl) {
			sessionFactory = ref("sessionFactory")
		}
		idMappingManager(gov.nih.nci.caintegrator.util.idmapping.IdMappingManager) {
			sessionFactory = ref("sessionFactory")
		}
		
}