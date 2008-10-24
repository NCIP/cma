package gov.nih.nci.cma.controller

import gov.nih.nci.cma.domain.AccessCode

class AccessCodeController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max) params.max = 10
        [ accessCodeList: AccessCode.list( params ) ]
    }

    def show = {
        def accessCode = AccessCode.get( params.id )

        if(!accessCode) {
            flash.message = "AccessCode not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ accessCode : accessCode ] }
    }

    def delete = {
        def accessCode = AccessCode.get( params.id )
        if(accessCode) {
            accessCode.delete()
            flash.message = "AccessCode ${params.id} deleted"
            redirect(action:list)
        }
        else {
            flash.message = "AccessCode not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def accessCode = AccessCode.get( params.id )

        if(!accessCode) {
            flash.message = "AccessCode not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ accessCode : accessCode ]
        }
    }

    def update = {
        def accessCode = AccessCode.get( params.id )
        if(accessCode) {
            accessCode.properties = params
            if(!accessCode.hasErrors() && accessCode.save()) {
                flash.message = "AccessCode ${params.id} updated"
                redirect(action:show,id:accessCode.id)
            }
            else {
                render(view:'edit',model:[accessCode:accessCode])
            }
        }
        else {
            flash.message = "AccessCode not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def accessCode = new AccessCode()
        accessCode.properties = params
        return ['accessCode':accessCode]
    }

    def save = {
        def accessCode = new AccessCode(params)
        if(!accessCode.hasErrors() && accessCode.save()) {
            flash.message = "AccessCode ${accessCode.id} created"
            redirect(action:show,id:accessCode.id)
        }
        else {
            render(view:'create',model:[accessCode:accessCode])
        }
    }
}
