/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mxv.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author maixuanvinh
 */
@Entity
@Table(name = "in_order")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InOrder.findAll", query = "SELECT i FROM InOrder i"),
    @NamedQuery(name = "InOrder.findById", query = "SELECT i FROM InOrder i WHERE i.id = :id"),
    @NamedQuery(name = "InOrder.findByQuantity", query = "SELECT i FROM InOrder i WHERE i.quantity = :quantity"),
    @NamedQuery(name = "InOrder.findByItemPrice", query = "SELECT i FROM InOrder i WHERE i.itemPrice = :itemPrice"),
    @NamedQuery(name = "InOrder.findByPrice", query = "SELECT i FROM InOrder i WHERE i.price = :price")})
public class InOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "quantity")
    private Integer quantity;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "item_price")
    private BigDecimal itemPrice;
    @Column(name = "price")
    private BigDecimal price;
    @JoinColumn(name = "menu_item_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private MenuItem menuItemId;
    @JoinColumn(name = "offer_id", referencedColumnName = "id")
    @ManyToOne
    private Offer offerId;
    @JoinColumn(name = "placed_order_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private PlacedOrder placedOrderId;

    public InOrder() {
    }

    public InOrder(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

    public PlacedOrder getPlacedOrderId() {
        return placedOrderId;
    }

    public void setPlacedOrderId(PlacedOrder placedOrderId) {
        this.placedOrderId = placedOrderId;
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
        if (!(object instanceof InOrder)) {
            return false;
        }
        InOrder other = (InOrder) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mxv.pojo.InOrder[ id=" + id + " ]";
    }
    
}
