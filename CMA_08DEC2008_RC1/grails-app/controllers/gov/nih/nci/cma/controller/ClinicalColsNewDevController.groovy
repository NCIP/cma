package gov.nih.nci.cma.controller

import gov.nih.nci.cma.domain.ClinicalColsNewDev

class ClinicalColsNewDevController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max) params.max = 10
        [ clinicalColsNewDevList: ClinicalColsNewDev.list( params ) ]
    }

    def show = {
        def clinicalColsNewDev = ClinicalColsNewDev.get( params.id )

        if(!clinicalColsNewDev) {
            flash.message = "ClinicalColsNewDev not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ clinicalColsNewDev : clinicalColsNewDev ] }
    }

//    def delete = {
//        def clinicalColsNewDev = ClinicalColsNewDev.get( params.id )
//        if(clinicalColsNewDev) {
//            clinicalColsNewDev.delete()
//            flash.message = "ClinicalColsNewDev ${params.id} deleted"
//            redirect(action:list)
//        }
//        else {
//            flash.message = "ClinicalColsNewDev not found with id ${params.id}"
//            redirect(action:list)
//        }
//    }
//
//    def edit = {
//        def clinicalColsNewDev = ClinicalColsNewDev.get( params.id )
//
//        if(!clinicalColsNewDev) {
//            flash.message = "ClinicalColsNewDev not found with id ${params.id}"
//            redirect(action:list)
//        }
//        else {
//            return [ clinicalColsNewDev : clinicalColsNewDev ]
//        }
//    }
//
//    def update = {
//        def clinicalColsNewDev = ClinicalColsNewDev.get( params.id )
//        if(clinicalColsNewDev) {
//            clinicalColsNewDev.properties = params
//            if(!clinicalColsNewDev.hasErrors() && clinicalColsNewDev.save()) {
//                flash.message = "ClinicalColsNewDev ${params.id} updated"
//                redirect(action:show,id:clinicalColsNewDev.id)
//            }
//            else {
//                render(view:'edit',model:[clinicalColsNewDev:clinicalColsNewDev])
//            }
//        }
//        else {
//            flash.message = "ClinicalColsNewDev not found with id ${params.id}"
//            redirect(action:edit,id:params.id)
//        }
//    }
//
//    def create = {
//        def clinicalColsNewDev = new ClinicalColsNewDev()
//        clinicalColsNewDev.properties = params
//        return ['clinicalColsNewDev':clinicalColsNewDev]
//    }
//
//    def save = {
//        def clinicalColsNewDev = new ClinicalColsNewDev(params)
//        if(!clinicalColsNewDev.hasErrors() && clinicalColsNewDev.save()) {
//            flash.message = "ClinicalColsNewDev ${clinicalColsNewDev.id} created"
//            redirect(action:show,id:clinicalColsNewDev.id)
//        }
//        else {
//            render(view:'create',model:[clinicalColsNewDev:clinicalColsNewDev])
//        }
//    }
}
