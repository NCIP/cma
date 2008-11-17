package gov.nih.nci.cma.controller

import gov.nih.nci.cma.domain.ListItem

class ListItemController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max) params.max = 10
        [ listItemList: ListItem.list( params ) ]
    }

    def show = {
        def listItem = ListItem.get( params.id )

        if(!listItem) {
            flash.message = "ListItem not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ listItem : listItem ] }
    }

//    def delete = {
//        def listItem = ListItem.get( params.id )
//        if(listItem) {
//            listItem.delete()
//            flash.message = "ListItem ${params.id} deleted"
//            redirect(action:list)
//        }
//        else {
//            flash.message = "ListItem not found with id ${params.id}"
//            redirect(action:list)
//        }
//    }
//
//    def edit = {
//        def listItem = ListItem.get( params.id )
//
//        if(!listItem) {
//            flash.message = "ListItem not found with id ${params.id}"
//            redirect(action:list)
//        }
//        else {
//            return [ listItem : listItem ]
//        }
//    }
//
//    def update = {
//        def listItem = ListItem.get( params.id )
//        if(listItem) {
//            listItem.properties = params
//            if(!listItem.hasErrors() && listItem.save()) {
//                flash.message = "ListItem ${params.id} updated"
//                redirect(action:show,id:listItem.id)
//            }
//            else {
//                render(view:'edit',model:[listItem:listItem])
//            }
//        }
//        else {
//            flash.message = "ListItem not found with id ${params.id}"
//            redirect(action:edit,id:params.id)
//        }
//    }
//
//    def create = {
//        def listItem = new ListItem()
//        listItem.properties = params
//        return ['listItem':listItem]
//    }
//
//    def save = {
//        def listItem = new ListItem(params)
//        if(!listItem.hasErrors() && listItem.save()) {
//            flash.message = "ListItem ${listItem.id} created"
//            redirect(action:show,id:listItem.id)
//        }
//        else {
//            render(view:'create',model:[listItem:listItem])
//        }
//    }
}
