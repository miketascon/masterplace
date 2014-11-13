/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.masterplace.tienda.controller;

import com.example.masterplace.tienda.entities.Cliente;
import com.example.masterplace.tienda.entities.Pedido;
import com.example.masterplace.tienda.sessions.ClienteFacade;
import com.example.masterplace.tienda.sessions.PedidoFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Andres
 */
@Named(value = "pedidosController")
@SessionScoped
public class PedidosController implements Serializable {

    @EJB
    private PedidoFacade pedidoFacade;
    private Pedido pedidoActual;
    private List<Pedido> listaPedido;
    @EJB
    private ClienteFacade clienteFacade;
    
    public PedidosController() {
    }
      public Pedido getPedidoActual() {
        if (pedidoActual == null) {
            pedidoActual = new Pedido();
        }
        return pedidoActual;
    }
      
       public void setPedidoActual(Pedido pedidoActual) {
        this.pedidoActual = pedidoActual;
    }

    public ClienteFacade getClienteFacade() {
        return clienteFacade;
    }

    private PedidoFacade getPedidoFacade() {
        return pedidoFacade;

    }
      
       public String prepareCreate() {
        pedidoActual = new Pedido();
        return "/faces/pedidos/PedidosCreate";
    }

    public String prepareEdit() {
        return "/faces/admin/PedidosEdit";
    }

    public String prepareView() {
        return "/faces/admin/PedidosView";
    }

    public String prepareList() {
        recargarLista();
        return "/faces/admin/PedidosList";
    }

    private void recargarLista() {
        listaPedido = null;
    }

    public List<Pedido> getListaPedido() {
        if (listaPedido == null) {
            try {
                listaPedido = getPedidoFacade().findAll();
            } catch (Exception e) {
                addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            }
        }
        return listaPedido;
    }
    
     public List<Cliente> getListClienteSelectOne() {
        return getClienteFacade().findAll();
    }
     
        public List<Cliente> getListClienteAutoComplete(String query) {
        try {
            return getClienteFacade().findByNumeroDoc(query);
        } catch (Exception ex) {
            Logger.getLogger(PedidosController.class
                    .getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
      
     public String addCliente() {
        try {
            
            getPedidoFacade().create(pedidoActual);
            addSuccesMessage("Crear Pedido", "Cliente Creado Exitosamente.");
            recargarLista();
            return "/faces/admin/PedidosView";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }

    }

    public String updatePedido() {
        try {
            getPedidoFacade().edit(pedidoActual);
            addSuccesMessage("Actualizar Pedido", "Pedido Actualizado Exitosamente.");
            recargarLista();
            return "/faces/admin/PedidosView";

        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }

    }

    public String deletePedido() {
        try {
            getPedidoFacade().remove(pedidoActual);
            addSuccesMessage("Eliminar Pedido", "Pedido Eliminado Exitosamente.");
            recargarLista();

        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());

        }
        return "/faces/admin/PedidosList";
    }

    private void addErrorMessage(String title, String msq) {
        FacesMessage facesMsg
                = new FacesMessage(FacesMessage.SEVERITY_ERROR, title, msq);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    private void addSuccesMessage(String title, String msq) {
        FacesMessage facesMsg
                = new FacesMessage(FacesMessage.SEVERITY_INFO, title, msq);
        FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
    }

    
    
}
