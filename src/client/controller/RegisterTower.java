/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.controller;

import client.model.ModelFacade;
import client.model.Tower;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.Utility;

/**
 *
 * @author Juan
 */
public class RegisterTower extends HttpServlet {

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
        ModelFacade model = new ModelFacade();
        Tower obj = new Tower();

        //Set Initial Location with Attribute Location
        Double latitude = 0.;
        Double longitude = 0.;
        if (request.getAttribute("location") != null) {
            String location = request.getAttribute("location").toString();
            latitude = Double.parseDouble(location.split(",")[0]);
            longitude = Double.parseDouble(location.split(",")[1]);
        }

        //Set object values with form values
        obj.setFname(request.getParameter("fname"));
        obj.setLname(request.getParameter("lname"));
        obj.setEmail(request.getParameter("email"));
        obj.setPassword(request.getParameter("password"));
        obj.setStreetAddress(request.getParameter("street_address"));
        obj.setCity(request.getParameter("city"));
        obj.setState(request.getParameter("state"));
        obj.setZipcode(request.getParameter("zipcode"));
        obj.setDob(Utility.StringToDate(request.getParameter("dob")));
        obj.setPermitNumber(request.getParameter("permit_number"));
        if (request.getParameter("price_mile") != null) {
            obj.setPriceMile(Double.parseDouble(request.getParameter("price_mile")));
        }
        obj.setLatitude(latitude);
        obj.setLongitude(longitude);
        if (model.EmailNotExist(obj.getEmail())) {
            if (model.createTower(obj)) {
                String token = model.login(obj.getEmail(), obj.getPassword());
                session.setAttribute("token", token);
                session.setAttribute("userTypeId", 2);
                session.setAttribute("email", obj.getEmail());
                session.setAttribute("name", obj.getFname() + " " + obj.getLname());
                response.sendRedirect("index.jsp");
            } else {
                session.setAttribute("errorMessage", "There was an error. Action couldn't be performed.");
                response.sendRedirect("errorMessage.jsp");
            }
        } else {
            session.setAttribute("registerClientMessage", "The email address is already registered.");
            response.sendRedirect("registerClient.jsp");
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
