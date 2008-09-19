package gov.nih.nci.cma.controller

import gov.nih.nci.cma.domain.IdMapping

class IdMappingController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max) params.max = 10
        [ idMappingList: IdMapping.list( params ) ]
    }

    def show = {
        def idMapping = IdMapping.get( params.id )

        if(!idMapping) {
            flash.message = "IdMapping not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ idMapping : idMapping ] }
    }

    def delete = {
        def idMapping = IdMapping.get( params.id )
        if(idMapping) {
            idMapping.delete()
            flash.message = "IdMapping ${params.id} deleted"
            redirect(action:list)
        }
        else {
            flash.message = "IdMapping not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def idMapping = IdMapping.get( params.id )

        if(!idMapping) {
            flash.message = "IdMapping not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ idMapping : idMapping ]
        }
    }

    def update = {
        def idMapping = IdMapping.get( params.id )
        if(idMapping) {
            idMapping.properties = params
            if(!idMapping.hasErrors() && idMapping.save()) {
                flash.message = "IdMapping ${params.id} updated"
                redirect(action:show,id:idMapping.id)
            }
            else {
                render(view:'edit',model:[idMapping:idMapping])
            }
        }
        else {
            flash.message = "IdMapping not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def idMapping = new IdMapping()
        idMapping.properties = params
        return ['idMapping':idMapping]
    }

    def save = {
        def idMapping = new IdMapping(params)
        if(!idMapping.hasErrors() && idMapping.save()) {
            flash.message = "IdMapping ${idMapping.id} created"
            redirect(action:show,id:idMapping.id)
        }
        else {
            render(view:'create',model:[idMapping:idMapping])
        }
    }
}
