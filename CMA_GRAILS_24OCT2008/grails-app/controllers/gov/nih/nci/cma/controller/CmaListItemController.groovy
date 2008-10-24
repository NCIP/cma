package gov.nih.nci.cma.controller

import gov.nih.nci.cma.domain.CmaListItem

class CmaListItemController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max) params.max = 10
        [ cmaListItemList: CmaListItem.list( params ) ]
    }

    def show = {
        def cmaListItem = CmaListItem.get( params.id )

        if(!cmaListItem) {
            flash.message = "CmaListItem not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ cmaListItem : cmaListItem ] }
    }

    def delete = {
        def cmaListItem = CmaListItem.get( params.id )
        if(cmaListItem) {
            cmaListItem.delete()
            flash.message = "CmaListItem ${params.id} deleted"
            redirect(action:list)
        }
        else {
            flash.message = "CmaListItem not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def cmaListItem = CmaListItem.get( params.id )

        if(!cmaListItem) {
            flash.message = "CmaListItem not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ cmaListItem : cmaListItem ]
        }
    }

    def update = {
        def cmaListItem = CmaListItem.get( params.id )
        if(cmaListItem) {
            cmaListItem.properties = params
            if(!cmaListItem.hasErrors() && cmaListItem.save()) {
                flash.message = "CmaListItem ${params.id} updated"
                redirect(action:show,id:cmaListItem.id)
            }
            else {
                render(view:'edit',model:[cmaListItem:cmaListItem])
            }
        }
        else {
            flash.message = "CmaListItem not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def cmaListItem = new CmaListItem()
        cmaListItem.properties = params
        return ['cmaListItem':cmaListItem]
    }

    def save = {
        def cmaListItem = new CmaListItem(params)
        if(!cmaListItem.hasErrors() && cmaListItem.save()) {
            flash.message = "CmaListItem ${cmaListItem.id} created"
            redirect(action:show,id:cmaListItem.id)
        }
        else {
            render(view:'create',model:[cmaListItem:cmaListItem])
        }
    }
}
