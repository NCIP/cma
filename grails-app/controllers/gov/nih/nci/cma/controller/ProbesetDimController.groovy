package gov.nih.nci.cma.controller

import gov.nih.nci.cma.domain.ProbesetDim

class ProbesetDimController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max) params.max = 10
        [ probesetDimList: ProbesetDim.list( params ) ]
    }

    def show = {
        def probesetDim = ProbesetDim.get( params.id )

        if(!probesetDim) {
            flash.message = "ProbesetDim not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ probesetDim : probesetDim ] }
    }

    def delete = {
        def probesetDim = ProbesetDim.get( params.id )
        if(probesetDim) {
            probesetDim.delete()
            flash.message = "ProbesetDim ${params.id} deleted"
            redirect(action:list)
        }
        else {
            flash.message = "ProbesetDim not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def probesetDim = ProbesetDim.get( params.id )

        if(!probesetDim) {
            flash.message = "ProbesetDim not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ probesetDim : probesetDim ]
        }
    }

    def update = {
        def probesetDim = ProbesetDim.get( params.id )
        if(probesetDim) {
            probesetDim.properties = params
            if(!probesetDim.hasErrors() && probesetDim.save()) {
                flash.message = "ProbesetDim ${params.id} updated"
                redirect(action:show,id:probesetDim.id)
            }
            else {
                render(view:'edit',model:[probesetDim:probesetDim])
            }
        }
        else {
            flash.message = "ProbesetDim not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def probesetDim = new ProbesetDim()
        probesetDim.properties = params
        return ['probesetDim':probesetDim]
    }

    def save = {
        def probesetDim = new ProbesetDim(params)
        if(!probesetDim.hasErrors() && probesetDim.save()) {
            flash.message = "ProbesetDim ${probesetDim.id} created"
            redirect(action:show,id:probesetDim.id)
        }
        else {
            render(view:'create',model:[probesetDim:probesetDim])
        }
    }
}
