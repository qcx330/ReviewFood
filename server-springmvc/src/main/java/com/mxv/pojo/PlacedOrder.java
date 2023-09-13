/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mxv.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author maixuanvinh
 */
@Entity
@Table(name = "placed_order")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PlacedOrder.findAll", query = "SELECT p FROM PlacedOrder p"),
    @NamedQuery(name = "PlacedOrder.findById", query = "SELECT p FROM PlacedOrder p WHERE p.id = :id"),
    @NamedQuery(name = "PlacedOrder.findByOrderTime", query = "SELECT p FROM PlacedOrder p WHERE p.orderTime = :orderTime"),
    @NamedQuery(name = "PlacedOrder.findByEstimatedDeliveryTime", query = "SELECT p FROM PlacedOrder p WHERE p.estimatedDeliveryTime = :estimatedDeliveryTime"),
    @NamedQuery(name = "PlacedOrder.findByFoodReady", query = "SELECT p FROM PlacedOrder p WHERE p.foodReady = :foodReady"),
    @NamedQuery(name = "PlacedOrder.findByActualDeliveryTime", query = "SELECT p FROM PlacedOrder p WHERE p.actualDeliveryTime = :actualDeliveryTime"),
    @NamedQuery(name = "PlacedOrder.findByDeliveryAddress", query = "SELECT p FROM PlacedOrder p WHERE p.deliveryAddress = :deliveryAddress"),
    @NamedQuery(name = "PlacedOrder.findByPrice", query = "SELECT p FROM PlacedOrder p WHERE p.price = :price"),
    @NamedQuery(name = "PlacedOrder.findByDiscount", query = "SELECT p FROM PlacedOrder p WHERE p.discount = :discount"),
    @NamedQuery(name = "PlacedOrder.findByFinalPrice", query = "SELECT p FROM PlacedOrder p WHERE p.finalPrice = :finalPrice")})
public class PlacedOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "order_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderTime;
    @Column(name = "estimated_delivery_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date estimatedDeliveryTime;
    @Column(name = "food_ready")
    @Temporal(TemporalType.TIMESTAMP)
    private Date foodReady;
    @Column(name = "actual_delivery_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actualDeliveryTime;
    @Size(max = 255)
    @Column(name = "delivery_address")
    private String deliveryAddress;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "price")
    private Double price;
    @Column(name = "discount")
    private Double discount;
    @Column(name = "final_price")
    private Double finalPrice;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "placedOrderId")
    @JsonIgnore
    private Set<InOrder> inOrderSet;
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Restaurant restaurantId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userId;

    public PlacedOrder() {
    }

    public PlacedOrder(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getEstimatedDeliveryTime() {
        return estimatedDeliveryTime;
    }

    public void setEstimatedDeliveryTime(Date estimatedDeliveryTime) {
        this.estimatedDeliveryTime = estimatedDeliveryTime;
    }

    public Date getFoodReady() {
        return foodReady;
    }

    public void setFoodReady(Date foodReady) {
        this.foodReady = foodReady;
    }

    public Date getActualDeliveryTime() {
        return actualDeliveryTime;
    }

    public void setActualDeliveryTime(Date actualDeliveryTime) {
        this.actualDeliveryTime = actualDeliveryTime;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Double finalPrice) {
        this.finalPrice = finalPrice;
    }

    @XmlTransient
    public Set<InOrder> getInOrderSet() {
        return inOrderSet;
    }

    public void setInOrderSet(Set<InOrder> inOrderSet) {
        this.inOrderSet = inOrderSet;
    }

    public Restaurant getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Restaurant restaurantId) {
        this.restaurantId = restaurantId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
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
        if (!(object instanceof PlacedOrder)) {
            return false;
        }
        PlacedOrder other = (PlacedOrder) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mxv.pojo.PlacedOrder[ id=" + id + " ]";
    }
    
}
