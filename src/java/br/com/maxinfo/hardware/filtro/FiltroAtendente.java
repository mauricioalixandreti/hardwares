/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.maxinfo.hardware.filtro;
import br.com.maxinfo.hardware.jsf.UsuarioController;
import java.io.IOException;
import java.io.Serializable;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author maxpc
 */
public class FiltroAtendente implements Filter, Serializable {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        UsuarioController session = (UsuarioController) req.getSession().getAttribute("usuarioController");
        String url = req.getRequestURI();

        if (session == null || session.getUsuarioLogado() == null) {
            resp.sendRedirect(req.getServletContext().getContextPath() + "/index.xhtml");
        } else if (session.getUsuarioLogado().getTipoUsuario().equalsIgnoreCase("ADM")||session.getUsuarioLogado().getTipoUsuario().equalsIgnoreCase("ATENDENTE") ) {            
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
//        throw new UnsupportedOperationException("Not supported yet.");
    }
}
