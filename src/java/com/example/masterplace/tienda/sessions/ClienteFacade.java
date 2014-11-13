/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.masterplace.tienda.sessions;

import com.example.masterplace.tienda.entities.Cliente;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Andres
 */
@Stateless
public class ClienteFacade extends AbstractFacade<Cliente> {

    @PersistenceContext(unitName = "MasterPlacePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClienteFacade() {
        super(Cliente.class);
    }
    public List<Cliente> findByNumeroDoc(String numeroDocumento) {
        Query q = getEntityManager().createNamedQuery("Cliente.findByNumeroDocumento");
        q.setParameter("numeroDocumento", numeroDocumento + "%");
        q.setMaxResults(10);
        return q.getResultList();
    }
    

    public Cliente finByNumeroDocumento(String numeroDocumento) {
        Query q = getEntityManager().createNamedQuery("Cliente.findByNumeroDocumento");
        q.setParameter("numeroDocumento", numeroDocumento);
        try{
        return (Cliente) q.getSingleResult();
        }catch (NonUniqueResultException ex) {
            throw ex;
        }catch (NoResultException ex){
            return null;
        }
    }
    
}
