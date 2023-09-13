/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mxv.pojo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author maixuanvinh
 */
@Entity
@Table(name = "in_offer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InOffer.findAll", query = "SELECT i FROM InOffer i"),
    @NamedQuery(name = "InOffer.findById", query = "SELECT i FROM InOffer i WHERE i.id = :id"),
    @NamedQuery(name = "InOffer.findByMenuItemMenuId", query = "SELECT i FROM InOffer i WHERE i.menuItemMenuId = :menuItemMenuId")})
public class InOffer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "menu_item_menu_id")
    private int menuItemMenuId;
    @JoinColumn(name = "menu_item_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private MenuItem menuItemId;
    @JoinColumn(name = "offer_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Offer offerId;

    public InOffer() {
    }

    public InOffer(Integer id) {
        this.id = id;
    }

    public InOffer(Integer id, int menuItemMenuId) {
        this.id = id;
        this.menuItemMenuId = menuItemMenuId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMenuItemMenuId() {
        return menuItemMenuId;
    }

    public void setMenuItemMenuId(int menuItemMenuId) {
        this.menuItemMenuId = menuItemMenuId;
    }

    public MenuItem getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(MenuItem menuItemId) {
        this.menuItemId = menuItemId;
    }

    public Offer getOfferId() {
        return offerId;
    }

    public void setOfferId(Offer offerId) {
        this.offerId = offerId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InOffer)) {
            return false;
        }
        InOffer other = (InOffer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mxv.pojo.InOffer[ id=" + id + " ]";
    }
    
}
