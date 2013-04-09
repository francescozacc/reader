package com.sismics.reader.core.model.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.common.base.Objects;

/**
 * User entity.
 * 
 * @author jtremeaux
 */
@Entity
@Table(name = "T_USER")
public class User {
    /**
     * User ID.
     */
    @Id
    @Column(name = "USE_ID_C", length = 36)
    private String id;
    
    /**
     * Locale ID.
     */
    @Column(name = "USE_IDLOCALE_C", nullable = false, length = 10)
    private String localeId;
    
    /**
     * User's username.
     */
    @Column(name = "USE_USERNAME_C", nullable = false, length = 50)
    private String username;
    
    /**
     * User's password.
     */
    @Column(name = "USE_PASSWORD_C", nullable = false, length = 100)
    private String password;

    /**
     * Email address.
     */
    @Column(name = "USE_EMAIL_C", nullable = false, length = 100)
    private String email;
    
    /**
     * Display only article titles (web application).
     */
    @Column(name = "USE_DISPLAYTITLEWEB_B", nullable = false)
    private boolean displayTitleWeb;

    /**
     * Display only article titles (mobile application).
     */
    @Column(name = "USE_DISPLAYTITLEMOBILE_B", nullable = false)
    private boolean displayTitleMobile;

    /**
     * Display only unread articles (web application).
     */
    @Column(name = "USE_DISPLAYUNREADWEB_B", nullable = false)
    private boolean displayUnreadWeb;

    /**
     * Display only unread articles (mobile application).
     */
    @Column(name = "USE_DISPLAYUNREADMOBILE_B", nullable = false)
    private boolean displayUnreadMobile;

    /**
     * Creation date.
     */
    @Column(name = "USE_CREATEDATE_D", nullable = false)
    private Date createDate;
    
    /**
     * Last login date.
     */
    @Column(name = "USE_LASTLOGINDATE_D")
    private Date lastLoginDate;
    
    /**
     * Deletion date.
     */
    @Column(name = "USE_DELETEDATE_D")
    private Date deleteDate;
    
    /**
     * Getter of id.
     *
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Setter of id.
     *
     * @param id id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter of localeId.
     *
     * @return localeId
     */
    public String getLocaleId() {
        return localeId;
    }

    /**
     * Setter of localeId.
     *
     * @param localeId localeId
     */
    public void setLocaleId(String localeId) {
        this.localeId = localeId;
    }

    /**
     * Getter of username.
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter of username.
     *
     * @param username username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter of password.
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter of password.
     *
     * @param password password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter of email.
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter of email.
     *
     * @param email email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter of displayTitleWeb.
     *
     * @return displayTitleWeb
     */
    public boolean isDisplayTitleWeb() {
        return displayTitleWeb;
    }

    /**
     * Setter of displayTitleWeb.
     *
     * @param displayTitleWeb displayTitleWeb
     */
    public void setDisplayTitleWeb(boolean displayTitleWeb) {
        this.displayTitleWeb = displayTitleWeb;
    }

    /**
     * Getter of displayTitleMobile.
     *
     * @return displayTitleMobile
     */
    public boolean isDisplayTitleMobile() {
        return displayTitleMobile;
    }

    /**
     * Setter of displayTitleMobile.
     *
     * @param displayTitleMobile displayTitleMobile
     */
    public void setDisplayTitleMobile(boolean displayTitleMobile) {
        this.displayTitleMobile = displayTitleMobile;
    }

    /**
     * Getter of displayUnreadWeb.
     *
     * @return displayUnreadWeb
     */
    public boolean isDisplayUnreadWeb() {
        return displayUnreadWeb;
    }

    /**
     * Setter of displayUnreadWeb.
     *
     * @param displayUnreadWeb displayUnreadWeb
     */
    public void setDisplayUnreadWeb(boolean displayUnreadWeb) {
        this.displayUnreadWeb = displayUnreadWeb;
    }

    /**
     * Getter of displayUnreadMobile.
     *
     * @return displayUnreadMobile
     */
    public boolean isDisplayUnreadMobile() {
        return displayUnreadMobile;
    }

    /**
     * Setter of displayUnreadMobile.
     *
     * @param displayUnreadMobile displayUnreadMobile
     */
    public void setDisplayUnreadMobile(boolean displayUnreadMobile) {
        this.displayUnreadMobile = displayUnreadMobile;
    }

    /**
     * Getter of createDate.
     *
     * @return createDate
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * Setter of createDate.
     *
     * @param createDate createDate
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * Getter of lastLoginDate.
     *
     * @return lastLoginDate
     */
    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    /**
     * Setter of lastLoginDate.
     *
     * @param lastLoginDate lastLoginDate
     */
    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    /**
     * Getter of deleteDate.
     *
     * @return deleteDate
     */
    public Date getDeleteDate() {
        return deleteDate;
    }

    /**
     * Setter of deleteDate.
     *
     * @param deleteDate deleteDate
     */
    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("username", username)
                .toString();
    }
}