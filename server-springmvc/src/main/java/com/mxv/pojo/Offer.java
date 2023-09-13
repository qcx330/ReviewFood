/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mxv.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author maixuanvinh
 */
@Entity
@Table(name = "offer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Offer.findAll", query = "SELECT o FROM Offer o"),
    @NamedQuery(name = "Offer.findById", query = "SELECT o FROM Offer o WHERE o.id = :id"),
    @NamedQuery(name = "Offer.findByActiveFrom", query = "SELECT o FROM Offer o WHERE o.activeFrom = :activeFrom"),
    @NamedQuery(name = "Offer.findByActiveTo", query = "SELECT o FROM Offer o WHERE o.activeTo = :activeTo"),
    @NamedQuery(name = "Offer.findByOfferPrice", query = "SELECT o FROM Offer o WHERE o.offerPrice = :offerPrice")})
public class Offer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "active_from")
    @Temporal(TemporalType.TIMESTAMP)
    private Date activeFrom;
    @Column(name = "active_to")
    @Temporal(TemporalType.TIMESTAMP)
    private Date activeTo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "offer_price")
    private BigDecimal offerPrice;
    @OneToMany(mappedBy = "offerId")
    @JsonIgnore
    private Set<InOrder> inOrderSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "offerId")
    @JsonIgnore
    private Set<InOffer> inOfferSet;

    public Offer() {
    }

    public Offer(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getActiveFrom() {
        return activeFrom;
    }

    public void setActiveFrom(Date activeFrom) {
        this.activeFrom = activeFrom;
    }

    public Date getActiveTo() {
        return activeTo;
    }

    public void setActiveTo(Date activeTo) {
        this.activeTo = activeTo;
    }

    public BigDecimal getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(BigDecimal offerPrice) {
        this.offerPrice = offerPrice;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Offer)) {
            return false;
        }
        Offer other = (Offer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mxv.pojo.Offer[ id=" + id + " ]";
    }
    
}
