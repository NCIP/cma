package gov.nih.nci.cma.controller

import gov.nih.nci.cma.domain.Platform

class PlatformController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max) params.max = 10
        [ platformList: Platform.list( params ) ]
    }

    def show = {
        def platform = Platform.get( params.id )

        if(!platform) {
            flash.message = "Platform not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ platform : platform ] }
    }

//    def delete = {
//        def platform = Platform.get( params.id )
//        if(platform) {
//            platform.delete()
//            flash.message = "Platform ${params.id} deleted"
//            redirect(action:list)
//        }
//        else {
//            flash.message = "Platform not found with id ${params.id}"
//            redirect(action:list)
//        }
//    }
//
//    def edit = {
//        def platform = Platform.get( params.id )
//
//        if(!platform) {
//            flash.message = "Platform not found with id ${params.id}"
//            redirect(action:list)
//        }
//        else {
//            return [ platform : platform ]
//        }
//    }
//
//    def update = {
//        def platform = Platform.get( params.id )
//        if(platform) {
//            platform.properties = params
//            if(!platform.hasErrors() && platform.save()) {
//                flash.message = "Platform ${params.id} updated"
//                redirect(action:show,id:platform.id)
//            }
//            else {
//                render(view:'edit',model:[platform:platform])
//            }
//        }
//        else {
//            flash.message = "Platform not found with id ${params.id}"
//            redirect(action:edit,id:params.id)
//        }
//    }
//
//    def create = {
//        def platform = new Platform()
//        platform.properties = params
//        return ['platform':platform]
//    }
//
//    def save = {
//        def platform = new Platform(params)
//        if(!platform.hasErrors() && platform.save()) {
//            flash.message = "Platform ${platform.id} created"
//            redirect(action:show,id:platform.id)
//        }
//        else {
//            render(view:'create',model:[platform:platform])
//        }
//    }
}
