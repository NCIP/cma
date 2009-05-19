package gov.nih.nci.cma.controller

import gov.nih.nci.cma.domain.ProbesetGeneAsso

class ProbesetGeneAssoController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max) params.max = 10
        [ probesetGeneAssoList: ProbesetGeneAsso.list( params ) ]
    }

    def show = {
        def probesetGeneAsso = ProbesetGeneAsso.get( params.id )

        if(!probesetGeneAsso) {
            flash.message = "ProbesetGeneAsso not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ probesetGeneAsso : probesetGeneAsso ] }
    }

//    def delete = {
//        def probesetGeneAsso = ProbesetGeneAsso.get( params.id )
//        if(probesetGeneAsso) {
//            probesetGeneAsso.delete()
//            flash.message = "ProbesetGeneAsso ${params.id} deleted"
//            redirect(action:list)
//        }
//        else {
//            flash.message = "ProbesetGeneAsso not found with id ${params.id}"
//            redirect(action:list)
//        }
//    }
//
//    def edit = {
//        def probesetGeneAsso = ProbesetGeneAsso.get( params.id )
//
//        if(!probesetGeneAsso) {
//            flash.message = "ProbesetGeneAsso not found with id ${params.id}"
//            redirect(action:list)
//        }
//        else {
//            return [ probesetGeneAsso : probesetGeneAsso ]
//        }
//    }
//
//    def update = {
//        def probesetGeneAsso = ProbesetGeneAsso.get( params.id )
//        if(probesetGeneAsso) {
//            probesetGeneAsso.properties = params
//            if(!probesetGeneAsso.hasErrors() && probesetGeneAsso.save()) {
//                flash.message = "ProbesetGeneAsso ${params.id} updated"
//                redirect(action:show,id:probesetGeneAsso.id)
//            }
//            else {
//                render(view:'edit',model:[probesetGeneAsso:probesetGeneAsso])
//            }
//        }
//        else {
//            flash.message = "ProbesetGeneAsso not found with id ${params.id}"
//            redirect(action:edit,id:params.id)
//        }
//    }
//
//    def create = {
//        def probesetGeneAsso = new ProbesetGeneAsso()
//        probesetGeneAsso.properties = params
//        return ['probesetGeneAsso':probesetGeneAsso]
//    }
//
//    def save = {
//        def probesetGeneAsso = new ProbesetGeneAsso(params)
//        if(!probesetGeneAsso.hasErrors() && probesetGeneAsso.save()) {
//            flash.message = "ProbesetGeneAsso ${probesetGeneAsso.id} created"
//            redirect(action:show,id:probesetGeneAsso.id)
//        }
//        else {
//            render(view:'create',model:[probesetGeneAsso:probesetGeneAsso])
//        }
//    }
}
