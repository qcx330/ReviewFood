/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mxv.pojo;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author maixuanvinh
 */
@Entity
@Table(name = "comment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comment.findAll", query = "SELECT c FROM Comment c"),
    @NamedQuery(name = "Comment.findById", query = "SELECT c FROM Comment c WHERE c.id = :id"),
    @NamedQuery(name = "Comment.findByCommentText", query = "SELECT c FROM Comment c WHERE c.commentText = :commentText"),
    @NamedQuery(name = "Comment.findByDateCreated", query = "SELECT c FROM Comment c WHERE c.dateCreated = :dateCreated"),
    @NamedQuery(name = "Comment.findByIsComplaint", query = "SELECT c FROM Comment c WHERE c.isComplaint = :isComplaint"),
    @NamedQuery(name = "Comment.findByIsPraise", query = "SELECT c FROM Comment c WHERE c.isPraise = :isPraise"),
    @NamedQuery(name = "Comment.findByImage1", query = "SELECT c FROM Comment c WHERE c.image1 = :image1"),
    @NamedQuery(name = "Comment.findByImage2", query = "SELECT c FROM Comment c WHERE c.image2 = :image2")})
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "comment_text")
    private String commentText;
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Column(name = "is_complaint")
    private Short isComplaint;
    @Column(name = "is_praise")
    private Short isPraise;
    @Size(max = 255)
    @Column(name = "image1")
    private String image1;
    @Size(max = 255)
    @Column(name = "image2")
    private String image2;
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Restaurant restaurantId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userId;

    public Comment() {
    }

    public Comment(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Short getIsComplaint() {
        return isComplaint;
    }

    public void setIsComplaint(Short isComplaint) {
        this.isComplaint = isComplaint;
    }

    public Short getIsPraise() {
        return isPraise;
    }

    public void setIsPraise(Short isPraise) {
        this.isPraise = isPraise;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
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
        if (!(object instanceof Comment)) {
            return false;
        }
        Comment other = (Comment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mxv.pojo.Comment[ id=" + id + " ]";
    }
    
}
