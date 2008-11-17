package gov.nih.nci.cma.controller

import gov.nih.nci.cma.domain.TcgaClinicalRestricted

class TcgaClinicalRestrictedController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max) params.max = 10
        [ tcgaClinicalRestrictedList: TcgaClinicalRestricted.list( params ) ]
    }

    def show = {
        def tcgaClinicalRestricted = TcgaClinicalRestricted.get( params.id )

        if(!tcgaClinicalRestricted) {
            flash.message = "TcgaClinicalRestricted not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ tcgaClinicalRestricted : tcgaClinicalRestricted ] }
    }

//    def delete = {
//        def tcgaClinicalRestricted = TcgaClinicalRestricted.get( params.id )
//        if(tcgaClinicalRestricted) {
//            tcgaClinicalRestricted.delete()
//            flash.message = "TcgaClinicalRestricted ${params.id} deleted"
//            redirect(action:list)
//        }
//        else {
//            flash.message = "TcgaClinicalRestricted not found with id ${params.id}"
//            redirect(action:list)
//        }
//    }
//
//    def edit = {
//        def tcgaClinicalRestricted = TcgaClinicalRestricted.get( params.id )
//
//        if(!tcgaClinicalRestricted) {
//            flash.message = "TcgaClinicalRestricted not found with id ${params.id}"
//            redirect(action:list)
//        }
//        else {
//            return [ tcgaClinicalRestricted : tcgaClinicalRestricted ]
//        }
//    }
//
//    def update = {
//        def tcgaClinicalRestricted = TcgaClinicalRestricted.get( params.id )
//        if(tcgaClinicalRestricted) {
//            tcgaClinicalRestricted.properties = params
//            if(!tcgaClinicalRestricted.hasErrors() && tcgaClinicalRestricted.save()) {
//                flash.message = "TcgaClinicalRestricted ${params.id} updated"
//                redirect(action:show,id:tcgaClinicalRestricted.id)
//            }
//            else {
//                render(view:'edit',model:[tcgaClinicalRestricted:tcgaClinicalRestricted])
//            }
//        }
//        else {
//            flash.message = "TcgaClinicalRestricted not found with id ${params.id}"
//            redirect(action:edit,id:params.id)
//        }
//    }
//
//    def create = {
//        def tcgaClinicalRestricted = new TcgaClinicalRestricted()
//        tcgaClinicalRestricted.properties = params
//        return ['tcgaClinicalRestricted':tcgaClinicalRestricted]
//    }
//
//    def save = {
//        def tcgaClinicalRestricted = new TcgaClinicalRestricted(params)
//        if(!tcgaClinicalRestricted.hasErrors() && tcgaClinicalRestricted.save()) {
//            flash.message = "TcgaClinicalRestricted ${tcgaClinicalRestricted.id} created"
//            redirect(action:show,id:tcgaClinicalRestricted.id)
//        }
//        else {
//            render(view:'create',model:[tcgaClinicalRestricted:tcgaClinicalRestricted])
//        }
//    }
}
