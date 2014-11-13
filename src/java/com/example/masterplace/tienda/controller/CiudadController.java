/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.masterplace.tienda.controller;

import com.example.masterplace.tienda.entities.Ciudad;
import com.example.masterplace.tienda.sessions.CiudadFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Andres
 */
@Named(value = "ciudadController")
@SessionScoped
public class CiudadController implements Serializable {

    @EJB
    private CiudadFacade ciudadFacade;
    
    public CiudadController() {
    }

    public CiudadFacade getCiudadFacade() {
        return ciudadFacade;
    }
    
     public Ciudad getCiudad(com.example.masterplace.tienda.entities.CiudadPK id) {
        return getCiudadFacade().find(id);
    }

    @FacesConverter(forClass = Ciudad.class, value = "ciudadConverter")
    public static class CiudadControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CiudadController controller = (CiudadController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "ciudadController");
            return controller.getCiudad(getKey(value));
        }

        com.example.masterplace.tienda.entities.CiudadPK getKey(String value) {
            com.example.masterplace.tienda.entities.CiudadPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new com.example.masterplace.tienda.entities.CiudadPK();
            key.setIdCiudad(Integer.parseInt(values[0]));
            key.setIdDepartamento(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(com.example.masterplace.tienda.entities.CiudadPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdCiudad());
            sb.append(SEPARATOR);
            sb.append(value.getIdDepartamento());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Ciudad) {
                Ciudad o = (Ciudad) object;
                return getStringKey(o.getCiudadPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Ciudad.class.getName());
            }
        }

    }

}
