class CmaStudyParticipant {

	//this obj will be used w/ grails, but mapped to the caintegrator table
	//and renamed to avoid confusion/naming conflicts
	static mapping = {
		table 'STUDY_PARTICIPANT'
		version false
		 columns {
	         id column:'PARTICIPANT_ID'
	      }

	}
	
	Integer participantId
	Integer daysOnStudy
	String participantDid
	String survivalStatus

}
