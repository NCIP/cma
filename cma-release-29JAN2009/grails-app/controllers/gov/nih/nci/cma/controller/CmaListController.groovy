package gov.nih.nci.cma.controller

import gov.nih.nci.cma.domain.CmaList

class CmaListController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max) params.max = 10
        [ cmaListList: CmaList.list( params ) ]
    }

    def show = {
        def cmaList = CmaList.get( params.id )

        if(!cmaList) {
            flash.message = "CmaList not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ cmaList : cmaList ] }
    }

//    def delete = {
//        def cmaList = CmaList.get( params.id )
//        if(cmaList) {
//            cmaList.delete()
//            flash.message = "CmaList ${params.id} deleted"
//            redirect(action:list)
//        }
//        else {
//            flash.message = "CmaList not found with id ${params.id}"
//            redirect(action:list)
//        }
//    }
//
//    def edit = {
//        def cmaList = CmaList.get( params.id )
//
//        if(!cmaList) {
//            flash.message = "CmaList not found with id ${params.id}"
//            redirect(action:list)
//        }
//        else {
//            return [ cmaList : cmaList ]
//        }
//    }
//
//    def update = {
//        def cmaList = CmaList.get( params.id )
//        if(cmaList) {
//            cmaList.properties = params
//            if(!cmaList.hasErrors() && cmaList.save()) {
//                flash.message = "CmaList ${params.id} updated"
//                redirect(action:show,id:cmaList.id)
//            }
//            else {
//                render(view:'edit',model:[cmaList:cmaList])
//            }
//        }
//        else {
//            flash.message = "CmaList not found with id ${params.id}"
//            redirect(action:edit,id:params.id)
//        }
//    }
//
//    def create = {
//        def cmaList = new CmaList()
//        cmaList.properties = params
//        return ['cmaList':cmaList]
//    }
//
//    def save = {
//        def cmaList = new CmaList(params)
//        if(!cmaList.hasErrors() && cmaList.save()) {
//            flash.message = "CmaList ${cmaList.id} created"
//            redirect(action:show,id:cmaList.id)
//        }
//        else {
//            render(view:'create',model:[cmaList:cmaList])
//        }
//    }
}
