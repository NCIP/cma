package gov.nih.nci.cma.controller

import gov.nih.nci.cma.domain.List

class ListController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max) params.max = 10
        [ listList: List.list( params ) ]
    }

    def show = {
        def list = List.get( params.id )

        if(!list) {
            flash.message = "List not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ list : list ] }
    }

    def delete = {
        def list = List.get( params.id )
        if(list) {
            list.delete()
            flash.message = "List ${params.id} deleted"
            redirect(action:list)
        }
        else {
            flash.message = "List not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def list = List.get( params.id )

        if(!list) {
            flash.message = "List not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ list : list ]
        }
    }

    def update = {
        def list = List.get( params.id )
        if(list) {
            list.properties = params
            if(!list.hasErrors() && list.save()) {
                flash.message = "List ${params.id} updated"
                redirect(action:show,id:list.id)
            }
            else {
                render(view:'edit',model:[list:list])
            }
        }
        else {
            flash.message = "List not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def list = new List()
        list.properties = params
        return ['list':list]
    }

    def save = {
        def list = new List(params)
        if(!list.hasErrors() && list.save()) {
            flash.message = "List ${list.id} created"
            redirect(action:show,id:list.id)
        }
        else {
            render(view:'create',model:[list:list])
        }
    }
}
