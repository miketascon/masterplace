/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.masterplace.tienda.sessions;

import com.example.masterplace.tienda.entities.Ciudad;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Andres
 */
@Stateless
public class CiudadFacade extends AbstractFacade<Ciudad> {
    @PersistenceContext(unitName = "MasterPlacePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CiudadFacade() {
        super(Ciudad.class);
    }
  public List<Ciudad> findByNombre(String nombre) {
        Query q = getEntityManager().createNamedQuery("Ciudad.findByNombreCiudad");
        q.setParameter("nombreCiudad", nombre + "%");
        q.setMaxResults(10);
        return q.getResultList();
    }
    
}
