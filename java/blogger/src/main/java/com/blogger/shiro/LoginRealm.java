package com.blogger.shiro;

import com.blogger.controller.base.CurrentMenu;
import com.blogger.controller.base.CurrentRole;
import com.blogger.controller.base.CurrentUser;
import com.blogger.model.User;
import com.blogger.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author zhuxiaomeng
 * @date 2017/12/4.
 * @email 154040976@qq.com
 */
@Service
public class LoginRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 获取认证
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        String name = (String) principalCollection.getPrimaryPrincipal();
        //根据用户获取角色 根据角色获取所有按钮权限
       CurrentUser cUser= (CurrentUser) ShiroUtil.getSession().getAttribute("currentUser");
       for(CurrentRole cRole : cUser.getCurrentRoleList()){
         info.addRole(cRole.getId());
       }

       for(CurrentMenu cMenu:cUser.getCurrentMenuList()){
         if(!StringUtils.isEmpty(cMenu.getPermission())){
            info.addStringPermission(cMenu.getPermission());
         }
       }
        return info;
    }

    /**
     * 获取授权
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        String name=upToken.getUsername();

        User user = userService.login(upToken.getUsername());
        if (null == user){
            //没找到账户
            throw new UnknownAccountException();
        }

        CurrentUser currentUser = new CurrentUser();
        currentUser.setUsername(user.getUsername());
        Subject subject = ShiroUtil.getSubject();
        Session session = subject.getSession();
        session.setAttribute("currentUser", currentUser);
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                // 用户名
                user.getUsername(),
                // 密码
                user.getPassword(),
                getName()
        );
        return authenticationInfo;
//
//    String name=upToken.getUsername();
//    String username=(String)authenticationToken.getPrincipal();
//    SysUser s=null;
//    try {
//      s = userService.login(username);
//    }catch (Exception e){
//      e.printStackTrace();
//    }
//    if(s==null){
//      throw new UnknownAccountException("账户密码不正确");
//    }else{
//      CurrentUser currentUser=new CurrentUser(s.getId(),s.getUsername(),s.getAge(),s.getEmail(),s.getPhoto(),s.getRealName());
//      Subject subject = ShiroUtil.getSubject();
//      /**角色权限封装进去*/
//      //根据用户获取菜单
//      List<SysMenu> menuList=new ArrayList<>(new HashSet<>(menuService.getUserMenu(s.getId())));
//      JSONArray json=menuService.getMenuJsonByUser(menuList);
//      Session session= subject.getSession();
//      session.setAttribute("menu",json);
//      CurrentMenu currentMenu=null;
//      List<CurrentMenu> currentMenuList=new ArrayList<>();
//      List<SysRole> roleList=new ArrayList<>();
//      for(SysMenu m:menuList){
//       currentMenu=new CurrentMenu(m.getId(),m.getName(),m.getPId(),m.getUrl(),m.getOrderNum(),m.getIcon(),m.getPermission(),m.getMenuType(),m.getNum());
//        currentMenuList.add(currentMenu);
//          roleList.addAll(m.getRoleList());
//      }
//      roleList= new ArrayList<>(new HashSet<>(roleList));
//      List<CurrentRole> currentRoleList=new ArrayList<>();
//      CurrentRole role=null;
//      for(SysRole r:roleList){
//        role=new CurrentRole(r.getId(),r.getRoleName(),r.getRemark());
//        currentRoleList.add(role);
//      }
//      currentUser.setCurrentRoleList(currentRoleList);
//      currentUser.setCurrentMenuList(currentMenuList);
//      session.setAttribute("curentUser",currentUser);
//    }
//    ByteSource byteSource= ByteSource.Util.bytes(username);
//    return new SimpleAuthenticationInfo(username,s.getPassword(), byteSource, getName());
    }
}
