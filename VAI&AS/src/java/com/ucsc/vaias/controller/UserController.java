/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucsc.vaias.controller;

import com.ucsc.vaias.connection.factory.DBResourceFactory;
import com.ucsc.vaias.model.Hospital;
import com.ucsc.vaias.model.User;
import com.ucsc.vaias.service.HospitalService;
import com.ucsc.vaias.service.UserService;
import com.ucsc.vaias.service.impl.HospitalServiceImpl;
import com.ucsc.vaias.service.impl.UserServiceImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author sajja
 */
public class UserController extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String type = request.getParameter("type");
        String name = request.getParameter("query");
        DBResourceFactory bResourceFactory = new DBResourceFactory();
        Connection connection = null;
        PrintWriter out = response.getWriter();
        try {
            try {
                connection = bResourceFactory.getFactoryConnection().getConnection();
                UserService userService = new UserServiceImpl();
                User user = new User();

                System.out.println("nammmmmmmmmmmmmmmmmmmmmmm    " + name);
                user.setFIRST_NAME(name);
                if (!name.isEmpty()) {
                    ArrayList<User> res_Select = userService.searchUsers(user, connection);
                    ArrayList<String> names = new ArrayList<String>();
                    JSONObject json = new JSONObject();
                    int i = 0;
                    for (User user1 : res_Select) {

                        System.out.println("searchhhhhhhhhh   " + user1.getFIRST_NAME());
                        out.println("<li>" + user1.getFIRST_NAME() + "</li>");

                    }
//                for(int j=0;j<names.size();j++){
//                    System.out.println(names.get(j));
//                }
//                if (!names.isEmpty()) {
//                    
                    // out.println("<div>"+names+"</div>");
//                }
                }
            } catch (Exception e) {

            }
            if (type.equals("reg")) {
                String UID = request.getParameter("UID");
                String NIC = request.getParameter("NIC");
                String FIRST_NAME = request.getParameter("FIRST_NAME");
                String LAST_NAME = request.getParameter("LAST_NAME");
                String GENDER = request.getParameter("GENDER");
                String TP_HOME = request.getParameter("TP_HOME");
                String TP_MOBILE = request.getParameter("TP_MOBILE");
                String ADDRESS = request.getParameter("ADDRESS");
                String LICENSE_NO = request.getParameter("LICENSE_NO");
                String BLOOD_GROUP = request.getParameter("BLOOD_GROUP");
                String EMAIL = request.getParameter("EMAIL");
                String date = request.getParameter("BIRTH_DAY");
                Date BIRTH_DAY = Date.valueOf(date);

                String OTHER = request.getParameter("OTHER");

                User user = new User(UID, NIC, FIRST_NAME, LAST_NAME, GENDER, TP_HOME, TP_MOBILE, ADDRESS, LICENSE_NO, BLOOD_GROUP, EMAIL, BIRTH_DAY, OTHER);

                UserServiceImpl userServiceImpl = new UserServiceImpl();
                try {
                    connection = bResourceFactory.getFactoryConnection().getConnection();
                    boolean addUser = userServiceImpl.addUser(user, connection);
                    if (addUser) {
                        response.sendRedirect(request.getHeader("referer"));
                        out.println("<script>alert('added');</script>");
                    } else {
                        response.sendRedirect(request.getHeader("referer"));
                    }
                } catch (ClassNotFoundException | SQLDataException ex) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (type.equals("sel")) {
                try {

                    connection = bResourceFactory.getFactoryConnection().getConnection();
                    UserService userService = new UserServiceImpl();
                    ArrayList<User> res_Select = userService.selectAllUsers(connection);

                    for (User user1 : res_Select) {
                        System.out.println("dgsgfdrbffffffffff " + user1.getUID());
                        System.out.println("dgsgfdrbffffffffff " + user1.getNIC());
                        System.out.println("dgsgfdrbffffffffff " + user1.getFIRST_NAME());
                        System.out.println("dgsgfdrbffffffffff " + user1.getLAST_NAME());
                        System.out.println("dgsgfdrbffffffffff " + user1.getTP_MOBILE());

                    }

                    if (!res_Select.isEmpty()) {
                        request.setAttribute("list", res_Select);
                        getServletContext().getRequestDispatcher("/Admin_users.jsp").forward(request, response);

                        out.flush();
                        out.close();
                        return;
                    } else {
                        response.sendRedirect(request.getHeader("referer"));
                    }

                    if (response.isCommitted()) {

                        System.out.println("yeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeees");

                    }
                    if (!response.isCommitted()) {

                        System.out.println("noooooooooooooooooooooooooooooooooooooooooooooooooooooo");

                    }
//                        if (response.isCommitted()) {
//
//                            System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwfffffffffffffffffffff");
//                        }
                    // }

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(HospitalController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(HospitalController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            if (type.equals("selbyID")) {
                try {

                    connection = bResourceFactory.getFactoryConnection().getConnection();
                    UserService userService = new UserServiceImpl();
                    User user = new User();
                    String UID = request.getParameter("UID");
                    user.setUID(UID);

                    User res_Select = userService.searchUserByUID(user, connection);
                    System.out.println(res_Select.getADDRESS());
                    System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" + res_Select.getOTHER());
                    request.setAttribute("sellist", res_Select);
                    getServletContext().getRequestDispatcher("/Admin_users_update.jsp").forward(request, response);

                    out.flush();
                    out.close();
                    return;

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(HospitalController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(HospitalController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            if (type.equals("selbyName")) {
                try {

                    connection = bResourceFactory.getFactoryConnection().getConnection();
                    UserService userService = new UserServiceImpl();
                    User user = new User();
                    String first_Name = request.getParameter("first_Name");
                    user.setFIRST_NAME(first_Name);

                    User res_Select = userService.searchUserByName(user, connection);
                    System.out.println(res_Select.getADDRESS());
                    System.out.println("seaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaarrrrrrch" + res_Select.getTP_MOBILE());
                    request.setAttribute("userDetails", res_Select);
                    getServletContext().getRequestDispatcher("/Admin_users_search.jsp").forward(request, response);

                    out.flush();
                    out.close();
                    return;

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(HospitalController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(HospitalController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            if (type.equals("update")) {

                String UID = request.getParameter("upUID");
                System.out.println(UID);
                String NIC = request.getParameter("NIC");
                String FIRST_NAME = request.getParameter("FIRST_NAME");
                String LAST_NAME = request.getParameter("LAST_NAME");
                String GENDER = request.getParameter("GENDER");
                String TP_HOME = request.getParameter("TP_HOME");
                String TP_MOBILE = request.getParameter("TP_MOBILE");
                String ADDRESS = request.getParameter("ADDRESS");
                String LICENSE_NO = request.getParameter("LICENSE_NO");
                String BLOOD_GROUP = request.getParameter("BLOOD_GROUP");
                String EMAIL = request.getParameter("EMAIL");
                String date = request.getParameter("BIRTH_DAY");
                Date BIRTH_DAY = Date.valueOf(date);

                String OTHER = request.getParameter("OTHER");
                //String OTHER ="ees efwefew";
                User user = new User(UID, NIC, FIRST_NAME, LAST_NAME, GENDER, TP_HOME, TP_MOBILE, ADDRESS, LICENSE_NO, BLOOD_GROUP, EMAIL, BIRTH_DAY, OTHER);

                UserService userServiceImpl = new UserServiceImpl();
                try {
                    connection = bResourceFactory.getFactoryConnection().getConnection();
                    boolean updateUser = userServiceImpl.upDateUserByUID(user, connection);
                    if (updateUser) {
                        response.sendRedirect("Admin_users_update.jsp");
                        out.println("<script>alert('added');</script>");
                    } else {
                        response.sendRedirect(request.getHeader("referer"));
                    }
                } catch (ClassNotFoundException | SQLDataException ex) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (type.equals("delete")) {
                try {

                    connection = bResourceFactory.getFactoryConnection().getConnection();
                    UserService userService = new UserServiceImpl();
                    User user = new User();
                    String UID = request.getParameter("upUID");
                    user.setUID(UID);

                    boolean deleteUser = userService.removeUserByUID(user, connection);
                    if (deleteUser) {
                        response.sendRedirect("Admin_users_update.jsp");
                        out.println("<script>alert('added');</script>");
                    } else {
                        response.sendRedirect(request.getHeader("referer"));
                    }

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(HospitalController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(HospitalController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        } catch (Exception e) {

            System.out.println("yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy" + e);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
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
