package com.blogger.shiro;

import com.blogger.controller.base.CurrentUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

public class ShiroUtil {

    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    public static Session getSession() {
        return getSubject().getSession();
    }

    public static CurrentUser getCurrentUser() {
        return (CurrentUser) getSession().getAttribute("currentUser");
    }

}
