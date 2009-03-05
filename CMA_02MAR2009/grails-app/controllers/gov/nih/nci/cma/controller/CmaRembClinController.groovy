package gov.nih.nci.cma.controller

import gov.nih.nci.cma.domain.CmaRembClin

class CmaRembClinController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max) params.max = 10
        [ cmaRembClinList: CmaRembClin.list( params ) ]
    }

    def show = {
        def cmaRembClin = CmaRembClin.get( params.id )

        if(!cmaRembClin) {
            flash.message = "CmaRembClin not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ cmaRembClin : cmaRembClin ] }
    }

//    def delete = {
//        def cmaRembClin = CmaRembClin.get( params.id )
//        if(cmaRembClin) {
//            cmaRembClin.delete()
//            flash.message = "CmaRembClin ${params.id} deleted"
//            redirect(action:list)
//        }
//        else {
//            flash.message = "CmaRembClin not found with id ${params.id}"
//            redirect(action:list)
//        }
//    }
//
//    def edit = {
//        def cmaRembClin = CmaRembClin.get( params.id )
//
//        if(!cmaRembClin) {
//            flash.message = "CmaRembClin not found with id ${params.id}"
//            redirect(action:list)
//        }
//        else {
//            return [ cmaRembClin : cmaRembClin ]
//        }
//    }
//
//    def update = {
//        def cmaRembClin = CmaRembClin.get( params.id )
//        if(cmaRembClin) {
//            cmaRembClin.properties = params
//            if(!cmaRembClin.hasErrors() && cmaRembClin.save()) {
//                flash.message = "CmaRembClin ${params.id} updated"
//                redirect(action:show,id:cmaRembClin.id)
//            }
//            else {
//                render(view:'edit',model:[cmaRembClin:cmaRembClin])
//            }
//        }
//        else {
//            flash.message = "CmaRembClin not found with id ${params.id}"
//            redirect(action:edit,id:params.id)
//        }
//    }
//
//    def create = {
//        def cmaRembClin = new CmaRembClin()
//        cmaRembClin.properties = params
//        return ['cmaRembClin':cmaRembClin]
//    }
//
//    def save = {
//        def cmaRembClin = new CmaRembClin(params)
//        if(!cmaRembClin.hasErrors() && cmaRembClin.save()) {
//            flash.message = "CmaRembClin ${cmaRembClin.id} created"
//            redirect(action:show,id:cmaRembClin.id)
//        }
//        else {
//            render(view:'create',model:[cmaRembClin:cmaRembClin])
//        }
//    }
}
