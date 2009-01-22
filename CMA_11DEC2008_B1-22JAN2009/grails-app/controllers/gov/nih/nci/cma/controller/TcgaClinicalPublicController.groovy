package gov.nih.nci.cma.controller

import gov.nih.nci.cma.domain.TcgaClinicalPublic

class TcgaClinicalPublicController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max) params.max = 10
        [ tcgaClinicalPublicList: TcgaClinicalPublic.list( params ) ]
    }

    def show = {
        def tcgaClinicalPublic = TcgaClinicalPublic.get( params.id )

        if(!tcgaClinicalPublic) {
            flash.message = "TcgaClinicalPublic not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ tcgaClinicalPublic : tcgaClinicalPublic ] }
    }

//    def delete = {
//        def tcgaClinicalPublic = TcgaClinicalPublic.get( params.id )
//        if(tcgaClinicalPublic) {
//            tcgaClinicalPublic.delete()
//            flash.message = "TcgaClinicalPublic ${params.id} deleted"
//            redirect(action:list)
//        }
//        else {
//            flash.message = "TcgaClinicalPublic not found with id ${params.id}"
//            redirect(action:list)
//        }
//    }
//
//    def edit = {
//        def tcgaClinicalPublic = TcgaClinicalPublic.get( params.id )
//
//        if(!tcgaClinicalPublic) {
//            flash.message = "TcgaClinicalPublic not found with id ${params.id}"
//            redirect(action:list)
//        }
//        else {
//            return [ tcgaClinicalPublic : tcgaClinicalPublic ]
//        }
//    }
//
//    def update = {
//        def tcgaClinicalPublic = TcgaClinicalPublic.get( params.id )
//        if(tcgaClinicalPublic) {
//            tcgaClinicalPublic.properties = params
//            if(!tcgaClinicalPublic.hasErrors() && tcgaClinicalPublic.save()) {
//                flash.message = "TcgaClinicalPublic ${params.id} updated"
//                redirect(action:show,id:tcgaClinicalPublic.id)
//            }
//            else {
//                render(view:'edit',model:[tcgaClinicalPublic:tcgaClinicalPublic])
//            }
//        }
//        else {
//            flash.message = "TcgaClinicalPublic not found with id ${params.id}"
//            redirect(action:edit,id:params.id)
//        }
//    }
//
//    def create = {
//        def tcgaClinicalPublic = new TcgaClinicalPublic()
//        tcgaClinicalPublic.properties = params
//        return ['tcgaClinicalPublic':tcgaClinicalPublic]
//    }
//
//    def save = {
//        def tcgaClinicalPublic = new TcgaClinicalPublic(params)
//        if(!tcgaClinicalPublic.hasErrors() && tcgaClinicalPublic.save()) {
//            flash.message = "TcgaClinicalPublic ${tcgaClinicalPublic.id} created"
//            redirect(action:show,id:tcgaClinicalPublic.id)
//        }
//        else {
//            render(view:'create',model:[tcgaClinicalPublic:tcgaClinicalPublic])
//        }
//    }
}
