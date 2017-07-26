/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucsc.vaias.controller;

import com.ucsc.vaias.connection.factory.DBResourceFactory;
import com.ucsc.vaias.dao.impl.UserDAOImpl;
import com.ucsc.vaias.model.PostAccident;
import com.ucsc.vaias.model.User;
import com.ucsc.vaias.service.PostAccidentService;
import com.ucsc.vaias.service.impl.PostAccidentServiceImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author sajja
 * 
 * 
 */

@WebServlet(name = "PostAccidentController", urlPatterns = {"/PostAccidentController"})
public class PostAccidentController extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        
                
                
        
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
       try (PrintWriter out = response.getWriter()) {
            DBResourceFactory dBResourceFactory = new DBResourceFactory();
            Connection connection = null;

            PostAccident postAccident = new PostAccident();
            //set kaanna database 1n ganna annima row 1;

           
           //postAccident.getUID()

            try {
                connection = dBResourceFactory.getFactoryConnection().getConnection();
                
                PostAccidentService accidentService=new PostAccidentServiceImpl();
                PostAccident searchLastRow = accidentService.SearchLastRow(connection);
                User user = new User();
                user.setUID(searchLastRow.getUID());
                
                //String name=postAccidentUser.getFIRST_NAME();
                //out.print(postAccident.getUID());
                String PID = request.getParameter("PID");
                String AID = request.getParameter("AID");
                String HID = request.getParameter("HID");
                
                //*****************
                
                PostAccident postAccidentHID=new PostAccident();
                PostAccident postAccidentPID=new PostAccident();
                 PostAccidentService postAccidentService=new PostAccidentServiceImpl();
                if (PID != null) {
                    
                    postAccidentPID.setPID(PID);
                     postAccidentPID.setAID(AID);
                     boolean resUpdatePID = postAccidentService.updatePID(connection, postAccidentPID);
                     
                }else if (HID != null) {
                    postAccidentHID.setHID(HID);
                    postAccidentHID.setAID(AID);
                    boolean resUpdateHID = postAccidentService.updateHID(connection, postAccidentHID);
                    
                }
                
                
                
               
               
                
                
                 String HIDDEN = request.getParameter("HIDDEN");
                
                
                if (HIDDEN.equals("police1")) {
                    String redirectURL = "Police_index.jsp";
                    response.sendRedirect(redirectURL);
                    
                } else if (HIDDEN.equals("police2")) {
                    String redirectURL = "Secondary_police.jsp";
                    response.sendRedirect(redirectURL);
                    
                }else if (HIDDEN.equals("hospital1")) {
                    String redirectURL = "Hospital_index.jsp";
                    response.sendRedirect(redirectURL);
                    
                }else if (HIDDEN.equals("hospital2")) {
                    String redirectURL = "Secondary_hospital.jsp";
                    response.sendRedirect(redirectURL);
                    
                }
                
               
                
                
               
            } catch (Exception e) {
                System.out.println(e);
            }

        }

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
