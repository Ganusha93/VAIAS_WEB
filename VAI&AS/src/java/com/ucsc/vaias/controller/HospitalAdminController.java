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
 * @author Ishan
 */
public class HospitalAdminController extends HttpServlet {

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
        String type = request.getParameter("type");
        DBResourceFactory dBResourceFactory = new DBResourceFactory();
        Connection connection = null;
        response.setContentType("text/html;charset=UTF-8");
        
        String name = request.getParameter("query");
         
        PrintWriter out = response.getWriter();
        //out.flush();
        try {
            try{
                connection = dBResourceFactory.getFactoryConnection().getConnection();
                HospitalService hospitalservice = new HospitalServiceImpl();
                Hospital hospital = new Hospital();
                
                System.out.println("nammmmmmmmmmmmmmmmmmmmmmm    "+ name);
                hospital.setHOSPITAL_NAME(name);
                if(!name.isEmpty()){
                ArrayList<Hospital> res_Select = hospitalservice.searchHospital(hospital, connection);
                ArrayList<String> names=new ArrayList<String>();
                
                for (Hospital hos1 : res_Select) {
                        
                       System.out.println("searchhhhhhhhhh   " + hos1.getTP());
                       out.println("<li>"+hos1.getHOSPITAL_NAME()+"</li>");
                       
                        
                    }

                }
                }catch(Exception e){
                    
                }

            //TODO output your page here. You may use following sample code. 
            try {
                connection = dBResourceFactory.getFactoryConnection().getConnection();
                if (type.equals("reg")) {
                    String HID = request.getParameter("HID");
                    String HOSPITAL_NAME = request.getParameter("HOSPITAL_NAME");
                    String PROVINCE = request.getParameter("PROVINCE");
                    String DISTRICT = request.getParameter("DISTRICT");
                    String CITY = request.getParameter("CITY");
                    float LAT = Float.valueOf(request.getParameter("LAT"));
                    float LON = Float.valueOf(request.getParameter("LON"));
                    String TP = request.getParameter("TP");

                    System.out.println(type);

                    Hospital hospital = new Hospital(HID, HOSPITAL_NAME, PROVINCE, DISTRICT, CITY, LAT, LON, TP);

                    HospitalService hospitalService = new HospitalServiceImpl();
                    boolean res_Add = hospitalService.addHospital(hospital, connection);

                    if (res_Add) {
                        response.sendRedirect("Admin_hospital_register.jsp");
                    } else {

                        response.sendRedirect("Admin_hospital_register.jsp");
                    }
                }

            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(HospitalController.class.getName()).log(Level.SEVERE, null, ex);
            }

            if(type.equals("sel")){
                try {
                    
                    //request.getRequestDispatcher("/Admin_hospital.jsp").forward(request, response);
                    connection = dBResourceFactory.getFactoryConnection().getConnection();
                    HospitalService hospitalService = new HospitalServiceImpl();
                    ArrayList<Hospital> res_Select = hospitalService.selectAllHospital(connection);

                    for (Hospital hospital1 : res_Select) {
                        System.out.println("dgsgfdrbffffffffff " + hospital1.getHID());
                        System.out.println("dgsgfdrbffffffffff " + hospital1.getHOSPITAL_NAME());
                        System.out.println("dgsgfdrbffffffffff " + hospital1.getDISTRICT());
                        System.out.println("dgsgfdrbffffffffff " + hospital1.getCITY());
                        System.out.println("dgsgfdrbffffffffff " + hospital1.getTP());

                    }
                    //if (hospital != null) {
//                    JSONObject jSONObject = new JSONObject(hospitalService.selectAllHospital(connection));
//                    jSONObject.put("list", res_Select);
//                    //System.out.println(jSONObject);
//                    response.setContentType("json");
//                    out.print(jSONObject.toString());
                   if (!res_Select.isEmpty()) {
                   request.setAttribute("list",res_Select);
                   getServletContext().getRequestDispatcher("/Admin_hospital.jsp").forward(request,response);
                    
                    
                    out.flush();
                    out.close();
                    return;
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
            if (type.equals("selbyName")) {
                try {

                    connection = dBResourceFactory.getFactoryConnection().getConnection();
                    HospitalService hospitalService = new HospitalServiceImpl();
                    Hospital hospital=new Hospital();
                    String hospitalName = request.getParameter("hospitalName");
                    hospital.setHOSPITAL_NAME(hospitalName);

                    Hospital res_Select = hospitalService.searchHospitalByName(hospital, connection);
                    System.out.println(res_Select.getTP());
                    System.out.println("seaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaarrrrrrch"+res_Select.getPROVINCE());
                    request.setAttribute("hospitalNames", res_Select);
                    getServletContext().getRequestDispatcher("/Admin_hospital_search.jsp").forward(request, response);
                    
                    out.flush();
                    out.close();
                    return;

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(HospitalController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(HospitalController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            
            if (type.equals("selbyID")) {
                try {

                    connection = dBResourceFactory.getFactoryConnection().getConnection();
                    HospitalService hospitalService = new HospitalServiceImpl();
                    Hospital hospital = new Hospital();
                    String HID = request.getParameter("HID");
                    System.out.println("Updatttttttttttttttt"+HID);
                    hospital.setHID(HID);

                    Hospital res_Select =hospitalService.searchHospitalByHID(hospital, connection);
                    //System.out.println(res_Select.getHOSPITAL_NAME());
                    System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"+res_Select.getTP());
                    request.setAttribute("sellist", res_Select);
                    getServletContext().getRequestDispatcher("/Admin_hospital_update.jsp").forward(request, response);
                    System.out.println("epaaaaaaaaaaaaaawenawaaaaaaa    "+res_Select.getHOSPITAL_NAME());
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
              try{  
                connection = dBResourceFactory.getFactoryConnection().getConnection();
                    String HID = request.getParameter("HID");
                    String HOSPITAL_NAME = request.getParameter("HOSPITAL_NAME");
                    String PROVINCE = request.getParameter("PROVINCE");
                    String DISTRICT = request.getParameter("DISTRICT");
                    String CITY = request.getParameter("CITY");
                    float LAT = Float.valueOf(request.getParameter("LAT"));
                    float LON = Float.valueOf(request.getParameter("LON"));
                    String TP = request.getParameter("TP");
                    System.out.println("qqqqqqqqqqqqqqqq       "+HOSPITAL_NAME);

                
                //String OTHER ="ees efwefew";
                Hospital hospital = new Hospital(HID, HOSPITAL_NAME, PROVINCE, DISTRICT, CITY, LAT, LON, TP);

                

                HospitalService hospitalService = new HospitalServiceImpl();
                    boolean res_Add = hospitalService.updateHospitalByHID(hospital, connection);

                    if (res_Add) {
                        response.sendRedirect("Admin_hospital_update.jsp");
                    } else {

                         response.sendRedirect(request.getHeader("referer"));
                    }
                } catch (ClassNotFoundException | SQLDataException ex) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (type.equals("delete")) {
                try {

                    connection = dBResourceFactory.getFactoryConnection().getConnection();
                    HospitalService hospitalService = new HospitalServiceImpl();
                     Hospital hospital = new Hospital();
                    String HID = request.getParameter("HID");
                    hospital.setHID(HID);

                    boolean deleteHospital =hospitalService.removeHospitalByHID(hospital, connection);
                    if (deleteHospital) {
                        response.sendRedirect("Admin_hospital_update.jsp");
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
