/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mxv.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author maixuanvinh
 */
@Entity
@Table(name = "menu_item")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MenuItem.findAll", query = "SELECT m FROM MenuItem m"),
    @NamedQuery(name = "MenuItem.findById", query = "SELECT m FROM MenuItem m WHERE m.id = :id"),
    @NamedQuery(name = "MenuItem.findByName", query = "SELECT m FROM MenuItem m WHERE m.name = :name"),
    @NamedQuery(name = "MenuItem.findByDescription", query = "SELECT m FROM MenuItem m WHERE m.description = :description"),
    @NamedQuery(name = "MenuItem.findByIngredients", query = "SELECT m FROM MenuItem m WHERE m.ingredients = :ingredients"),
    @NamedQuery(name = "MenuItem.findByPrice", query = "SELECT m FROM MenuItem m WHERE m.price = :price"),
    @NamedQuery(name = "MenuItem.findByActive", query = "SELECT m FROM MenuItem m WHERE m.active = :active"),
    @NamedQuery(name = "MenuItem.findByImage", query = "SELECT m FROM MenuItem m WHERE m.image = :image")})
public class MenuItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "name")
    private String name;
    @Size(max = 255)
    @Column(name = "description")
    private String description;
    @Size(max = 255)
    @Column(name = "ingredients")
    private String ingredients;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "price")
    private Double price;
    @Column(name = "active")
    private Short active;
    @Size(max = 200)
    @Column(name = "image")
    private String image;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "menuItemId")
    @JsonIgnore
    private Set<InOrder> inOrderSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "menuItemId")
    @JsonIgnore
    private Set<InOffer> inOfferSet;
    @JoinColumn(name = "menu_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Menu menuId;

    public MenuItem() {
    }

    public MenuItem(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Short getActive() {
        return active;
    }

    public void setActive(Short active) {
        this.active = active;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @XmlTransient
    public Set<InOrder> getInOrderSet() {
        return inOrderSet;
    }

    public void setInOrderSet(Set<InOrder> inOrderSet) {
        this.inOrderSet = inOrderSet;
    }

    @XmlTransient
    public Set<InOffer> getInOfferSet() {
        return inOfferSet;
    }

    public void setInOfferSet(Set<InOffer> inOfferSet) {
        this.inOfferSet = inOfferSet;
    }

    public Menu getMenuId() {
        return menuId;
    }

    public void setMenuId(Menu menuId) {
        this.menuId = menuId;
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
        if (!(object instanceof MenuItem)) {
            return false;
        }
        MenuItem other = (MenuItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mxv.pojo.MenuItem[ id=" + id + " ]";
    }
    
}
