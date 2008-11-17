package gov.nih.nci.cma.controller

import gov.nih.nci.cma.domain.tcga.ClinicalNew

class ClinicalNewController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max) params.max = 10
        [ clinicalNewList: ClinicalNew.list( params ) ]
    }

    def show = {
        def clinicalNew = ClinicalNew.get( params.id )

        if(!clinicalNew) {
            flash.message = "ClinicalNew not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ clinicalNew : clinicalNew ] }
    }

//    def delete = {
//        def clinicalNew = ClinicalNew.get( params.id )
//        if(clinicalNew) {
//            clinicalNew.delete()
//            flash.message = "ClinicalNew ${params.id} deleted"
//            redirect(action:list)
//        }
//        else {
//            flash.message = "ClinicalNew not found with id ${params.id}"
//            redirect(action:list)
//        }
//    }
//
//    def edit = {
//        def clinicalNew = ClinicalNew.get( params.id )
//
//        if(!clinicalNew) {
//            flash.message = "ClinicalNew not found with id ${params.id}"
//            redirect(action:list)
//        }
//        else {
//            return [ clinicalNew : clinicalNew ]
//        }
//    }
//
//    def update = {
//        def clinicalNew = ClinicalNew.get( params.id )
//        if(clinicalNew) {
//            clinicalNew.properties = params
//            if(!clinicalNew.hasErrors() && clinicalNew.save()) {
//                flash.message = "ClinicalNew ${params.id} updated"
//                redirect(action:show,id:clinicalNew.id)
//            }
//            else {
//                render(view:'edit',model:[clinicalNew:clinicalNew])
//            }
//        }
//        else {
//            flash.message = "ClinicalNew not found with id ${params.id}"
//            redirect(action:edit,id:params.id)
//        }
//    }
//
//    def create = {
//        def clinicalNew = new ClinicalNew()
//        clinicalNew.properties = params
//        return ['clinicalNew':clinicalNew]
//    }
//
//    def save = {
//        def clinicalNew = new ClinicalNew(params)
//        if(!clinicalNew.hasErrors() && clinicalNew.save()) {
//            flash.message = "ClinicalNew ${clinicalNew.id} created"
//            redirect(action:show,id:clinicalNew.id)
//        }
//        else {
//            render(view:'create',model:[clinicalNew:clinicalNew])
//        }
//    }
}
