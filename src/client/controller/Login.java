/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.controller;

import client.model.Client;
import client.model.ModelFacade;
import client.model.Tower;
import client.model.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Juan
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ControllerFacade controller = new ControllerFacade();
        Integer errorCount = 0;

        if (session.getAttribute("errorCount") != null) {
            errorCount = Integer.parseInt(session.getAttribute("errorCount").toString());
            if (errorCount >= 5) {
                controller.blockAccount(session.getAttribute("errorAccount").toString());
                session.setAttribute("error_message", "Too many login fails.");
                response.setHeader("Location", "error.jsp");
            }
        }

        if ((request.getParameter("user") != null) && (request.getParameter("pass") != null)) {

            String email = request.getParameter("user");
            String pass = request.getParameter("pass");
            String token = null;
            ModelFacade mf = new ModelFacade();

            try {
                //token = controller.login(email, pass);
                token = mf.login(email, pass);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {

            }

            if (token != null && !token.equals("")) {
                User user = controller.selectUserByEmail(token, email);
                session.setAttribute("email", email);
                session.setAttribute("token", token);
                session.setAttribute("name", user.getFname() + " " + user.getLname());
                session.setAttribute("userTypeId", user.getUserTypeId());
                session.setAttribute("menu", controller.getMenu(user.getUserTypeId()));
                if (user.getUserTypeId() == 1) {
                    Client client = controller.selectClientByEmail(token, email);
                    if(client!=null){
                    session.setAttribute("client", client);
                    session.setAttribute("client_id", client.getId());
                    session.setAttribute("activeServicesClient", controller.selectServices(email, token));
                    }
                }
                if (user.getUserTypeId() == 2) {
                    Tower tower = controller.selectTowerByEmail(token, email);
                    if(tower!=null){
                    session.setAttribute("tower", tower);
                    session.setAttribute("tower_id", tower.getId());
                    }
                }
                //response.setStatus(response.SC_MOVED_TEMPORARILY);
                response.sendRedirect("index.jsp");
            } else {
                errorCount++;
                session.setAttribute("errorCount", errorCount);
                session.setAttribute("errorAccount", email);
                //response.setStatus(response.SC_MOVED_TEMPORARILY);
                response.sendRedirect("login.jsp");
            }
        } else {
            //response.setStatus(response.SC_MOVED_TEMPORARILY);
            session.setAttribute("errorMessage", "General Error");
            response.sendRedirect("errorMessage.jsp");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
