package club.musician.mysecurity.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    protected Log logger = LogFactory.getLog(this.getClass());

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        handler(request, response, authentication);
        clearAuthenticationAttributes(request);
    }


    protected void handler(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String targetUrl = determinTargetUrl(authentication);
        if (response.isCommitted()) {
            logger.debug("response has already been commited .Unable to reredict to " + targetUrl);
        }
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    protected String determinTargetUrl(final Authentication authentication) {
        //需要手动设置不同权限的角色跳转的路径是哪个
        Map<String, String> roleUrlMap = new HashMap<>();
        roleUrlMap.put("ROLE_admin", "/hello");
        roleUrlMap.put("ROLE_admin", "/helloAdmin");
        roleUrlMap.put("ROLE_normal", "/helloNormal");

        final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (final GrantedAuthority grantedAuthority : authorities) {
            String authenriyName = grantedAuthority.getAuthority();
            //查询跳转路径里面有没有角色的信息，如果匹配上直接跳转到第一个配对的路径
            if (roleUrlMap.containsKey(authenriyName)) {
                return roleUrlMap.get(authenriyName);
            }
        }

        throw new IllegalStateException();
    }


    protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}