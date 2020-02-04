package com.yzd.jutils.springBoot.conf;


/**
 * 拦截未登录的用户信息
 * Created by Administrator on 2016/10/11.
 */
/*
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        //CurrentUser currentUser = LoginSessionUtil.getCurrentUserByCookie(request, response);
        //LoginSessionUtil.setLogin(currentUser);
        //boolean isLogin = LoginSessionUtil.isLogin();
        if (!isLogin) {
            String redirectUrl = request.getRequestURI();
            //response.sendRedirect(request.getContextPath() + "/account/doLogin?redirect=" + redirectUrl);
            response.sendRedirect(request.getContextPath() + "/login.html");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        //操作完成后清空当前线程[ThreadLocal]中的用户信息
        LoginSessionUtil.removeCurrentUserContextHolder();
    }
}
*/
