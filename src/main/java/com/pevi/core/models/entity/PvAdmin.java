/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pevi.core.models.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author johnson3yo
 */
@Entity
@Table(name = "pv_admin")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PvAdmin.findAll", query = "SELECT p FROM PvAdmin p")
    , @NamedQuery(name = "PvAdmin.findById", query = "SELECT p FROM PvAdmin p WHERE p.id = :id")
    , @NamedQuery(name = "PvAdmin.findByLoginId", query = "SELECT p FROM PvAdmin p WHERE p.loginId = :loginId")
    , @NamedQuery(name = "PvAdmin.findByAlias", query = "SELECT p FROM PvAdmin p WHERE p.alias = :alias")
    , @NamedQuery(name = "PvAdmin.findByPassword", query = "SELECT p FROM PvAdmin p WHERE p.password = :password")
    , @NamedQuery(name = "PvAdmin.findByLastLogin", query = "SELECT p FROM PvAdmin p WHERE p.lastLogin = :lastLogin")})
public class PvAdmin implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 32)
    @Column(name = "login_id")
    private String loginId;
    @Size(max = 32)
    @Column(name = "alias")
    private String alias;
    @Size(max = 64)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Column(name = "last_login")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;

    public PvAdmin() {
    }

    public PvAdmin(Integer id) {
        this.id = id;
    }

    public PvAdmin(Integer id, Date lastLogin) {
        this.id = id;
        this.lastLogin = lastLogin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
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
        if (!(object instanceof PvAdmin)) {
            return false;
        }
        PvAdmin other = (PvAdmin) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pevi.core.models.entity.PvAdmin[ id=" + id + " ]";
    }
    
}
