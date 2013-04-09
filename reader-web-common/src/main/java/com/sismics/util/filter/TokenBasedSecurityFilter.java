package com.sismics.util.filter;

import java.io.IOException;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.joda.time.DateTimeZone;

import com.sismics.reader.core.constant.Constants;
import com.sismics.reader.core.dao.jpa.AuthenticationTokenDao;
import com.sismics.reader.core.dao.jpa.UserBaseFunctionDao;
import com.sismics.reader.core.dao.jpa.UserDao;
import com.sismics.reader.core.model.jpa.AuthenticationToken;
import com.sismics.reader.core.model.jpa.User;
import com.sismics.security.AnonymousPrincipal;
import com.sismics.security.UserPrincipal;
import com.sismics.util.LocaleUtil;

/**
 * This filter is used to authenticate the user having an active session via an authentication token stored in database.
 * The filter extracts the authentication token stored in a cookie.
 * If the ocokie exists and the token is valid, the filter injects a UserPrincipal into a request attribute.
 * If not, the user is anonymous, and the filter injects a AnonymousPrincipal into the request attribute.
 *
 * @author jtremeaux
 */
public class TokenBasedSecurityFilter implements Filter {
    /**
     * Logger.
     */
    private static final Logger log = LoggerFactory.getLogger(TokenBasedSecurityFilter.class);

    /**
     * Name of the cookie used to store the authentication token.
     */
    public static final String COOKIE_NAME = "auth_token";

    /**
     * Name of the attribute containing the principal.
     */
    public static final String PRINCIPAL_ATTRIBUTE = "principal";
    
    /**
     * Lifetime of the authentication token in seconds.
     */
    public static final int TOKEN_LIFETIME = 3600 * 24 * 365 * 20;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // NOP
    }

    @Override
    public void destroy() {
        // NOP
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        // Get the value of the client authentication token
        HttpServletRequest request = (HttpServletRequest) req;
        String authToken = null;
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (COOKIE_NAME.equals(cookie.getName())) {
                    authToken = cookie.getValue();
                }
            }
        }
        
        // Get the corresponding server token
        AuthenticationTokenDao authenticationTokenDao = new AuthenticationTokenDao();
        AuthenticationToken authenticationToken = null;
        if (authToken != null) {
            authenticationToken = authenticationTokenDao.get(authToken);
        }
        
        if (authenticationToken == null) {
            injectAnonymousUser(request);
        } else {
            // Check if the token is still valid
            if (new Date().getTime() >= authenticationToken.getCreationDate().getTime() + ((long) TOKEN_LIFETIME) * 1000L) {
                try {
                    authenticationTokenDao.delete(authToken);
                } catch (Exception e) {
                    injectAnonymousUser(request);
                    log.error("Error deleting authentication token: " + authToken, e);
                }
            } else {
                // Check if the user is still valid
                UserDao userDao = new UserDao();
                User user = userDao.getById(authenticationToken.getUserId());
                if (user != null && user.getDeleteDate() == null) {
                    injectAuthenticatedUser(request, user);
                } else {
                    injectAnonymousUser(request);
                }
            }
        }
        
        filterChain.doFilter(request, response);
    }
    
    /**
     * Inject an authenticated user into the request attributes.
     * 
     * @param request HTTP request
     * @param user User to inject
     */
    private void injectAuthenticatedUser(HttpServletRequest request, User user) {
        UserPrincipal userPrincipal = new UserPrincipal(user.getId(), user.getUsername());

        // Add locale
        Locale locale = LocaleUtil.getLocale(user.getLocaleId());
        userPrincipal.setLocale(locale);
        
        // Add base functions
        UserBaseFunctionDao userBaseFuction = new UserBaseFunctionDao();
        Set<String> baseFunctionSet = userBaseFuction.findByUserId(user.getId());
        userPrincipal.setBaseFunctionSet(baseFunctionSet);
        
        request.setAttribute(PRINCIPAL_ATTRIBUTE, userPrincipal);
    }

    /**
     * Inject an anonymous user into the request attributes.
     * 
     * @param request HTTP request
     */
    private void injectAnonymousUser(HttpServletRequest request) {
        AnonymousPrincipal anonymousPrincipal = new AnonymousPrincipal();
        anonymousPrincipal.setLocale(request.getLocale());
        anonymousPrincipal.setDateTimeZone(DateTimeZone.forID(Constants.DEFAULT_TIMEZONE_ID));

        request.setAttribute(PRINCIPAL_ATTRIBUTE, anonymousPrincipal);
    }
}