/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.masterplace.tienda.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Andres
 */
@Entity
@Table(name = "productos_bodega")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductosBodega.findAll", query = "SELECT p FROM ProductosBodega p"),
    @NamedQuery(name = "ProductosBodega.findByIdProductosBodega", query = "SELECT p FROM ProductosBodega p WHERE p.idProductosBodega = :idProductosBodega"),
    @NamedQuery(name = "ProductosBodega.findByNombre", query = "SELECT p FROM ProductosBodega p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "ProductosBodega.findByReferencia", query = "SELECT p FROM ProductosBodega p WHERE p.referencia = :referencia"),
    @NamedQuery(name = "ProductosBodega.findByTalla", query = "SELECT p FROM ProductosBodega p WHERE p.talla = :talla"),
    @NamedQuery(name = "ProductosBodega.findByPrecio", query = "SELECT p FROM ProductosBodega p WHERE p.precio = :precio")})
public class ProductosBodega implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_productos_bodega")
    private Integer idProductosBodega;
    @Size(max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 45)
    @Column(name = "referencia")
    private String referencia;
    @Size(max = 45)
    @Column(name = "Talla")
    private String talla;
    @Size(max = 45)
    @Column(name = "precio")
    private String precio;

    public ProductosBodega() {
    }

    public ProductosBodega(Integer idProductosBodega) {
        this.idProductosBodega = idProductosBodega;
    }

    public Integer getIdProductosBodega() {
        return idProductosBodega;
    }

    public void setIdProductosBodega(Integer idProductosBodega) {
        this.idProductosBodega = idProductosBodega;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProductosBodega != null ? idProductosBodega.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductosBodega)) {
            return false;
        }
        ProductosBodega other = (ProductosBodega) object;
        if ((this.idProductosBodega == null && other.idProductosBodega != null) || (this.idProductosBodega != null && !this.idProductosBodega.equals(other.idProductosBodega))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.masterplace.tienda.entities.ProductosBodega[ idProductosBodega=" + idProductosBodega + " ]";
    }
    
}
