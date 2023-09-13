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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author maixuanvinh
 */
@Entity
@Table(name = "restaurant")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Restaurant.findAll", query = "SELECT r FROM Restaurant r"),
    @NamedQuery(name = "Restaurant.findById", query = "SELECT r FROM Restaurant r WHERE r.id = :id"),
    @NamedQuery(name = "Restaurant.findByName", query = "SELECT r FROM Restaurant r WHERE r.name = :name"),
    @NamedQuery(name = "Restaurant.findByAddress", query = "SELECT r FROM Restaurant r WHERE r.address = :address"),
    @NamedQuery(name = "Restaurant.findByActive", query = "SELECT r FROM Restaurant r WHERE r.active = :active"),
    @NamedQuery(name = "Restaurant.findByImage", query = "SELECT r FROM Restaurant r WHERE r.image = :image")})
public class Restaurant implements Serializable {

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
    @Column(name = "address")
    private String address;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private boolean active;
    @Size(max = 200)
    @Column(name = "image")
    private String image;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurantId")
    @JsonIgnore
    private Set<Follower> followerSet;
    @JoinColumn(name = "category_res_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CategoryRes categoryResId;
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private City cityId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurantId")
    @JsonIgnore
    private Set<Comment> commentSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurantId")
    @JsonIgnore
    private Set<Menu> menuSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurantId")
    @JsonIgnore
    private Set<PlacedOrder> placedOrderSet;

    public Restaurant() {
    }

    public Restaurant(Integer id) {
        this.id = id;
    }

    public Restaurant(Integer id, boolean active) {
        this.id = id;
        this.active = active;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @XmlTransient
    public Set<Follower> getFollowerSet() {
        return followerSet;
    }

    public void setFollowerSet(Set<Follower> followerSet) {
        this.followerSet = followerSet;
    }

    public CategoryRes getCategoryResId() {
        return categoryResId;
    }

    public void setCategoryResId(CategoryRes categoryResId) {
        this.categoryResId = categoryResId;
    }

    public City getCityId() {
        return cityId;
    }

    public void setCityId(City cityId) {
        this.cityId = cityId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @XmlTransient
    public Set<Comment> getCommentSet() {
        return commentSet;
    }

    public void setCommentSet(Set<Comment> commentSet) {
        this.commentSet = commentSet;
    }

    @XmlTransient
    public Set<Menu> getMenuSet() {
        return menuSet;
    }

    public void setMenuSet(Set<Menu> menuSet) {
        this.menuSet = menuSet;
    }

    @XmlTransient
    public Set<PlacedOrder> getPlacedOrderSet() {
        return placedOrderSet;
    }

    public void setPlacedOrderSet(Set<PlacedOrder> placedOrderSet) {
        this.placedOrderSet = placedOrderSet;
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
        if (!(object instanceof Restaurant)) {
            return false;
        }
        Restaurant other = (Restaurant) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mxv.pojo.Restaurant[ id=" + id + " ]";
    }
    
}
