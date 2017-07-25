/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucsc.vaias.dao.impl;

import com.ucsc.vaias.dao.PostAccidentDAO;
import com.ucsc.vaias.model.PoliceStation;
import com.ucsc.vaias.model.PostAccident;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author sajja
 */
public class PostAccidentDAOImpl implements PostAccidentDAO{

    @Override
    public PostAccident SearchLastRow(Connection connection) throws ClassNotFoundException,SQLException{
        PreparedStatement stm = connection.prepareStatement("SELECT UID,LAT,LON,PID,HID,AID FROM post_accident order by AID DESC limit 1");
        ResultSet rst = stm.executeQuery();
        PostAccident postAccident=new PostAccident();
        if (rst.next()) {  
            
           
            //System.out.println(rst.getString("UID"));
            //System.out.println(rst.getFloat("LAT"));
            //System.out.println(rst.getFloat("LON"));
            postAccident.setAID(rst.getString("AID"));
            postAccident.setUID(rst.getString("UID"));
            postAccident.setLAT(rst.getFloat("LAT"));
            postAccident.setLON(rst.getFloat("LON"));
            postAccident.setPID(rst.getString("PID"));
            postAccident.setHID(rst.getString("HID"));
        }
        return postAccident;
    }

    @Override
    public boolean UpdatePID(Connection connection, PostAccident postAccident) throws ClassNotFoundException, SQLException {
        PreparedStatement stm = connection.prepareStatement("UPDATE post_accident SET PID=? WHERE AID=?");
        stm.setObject(1, postAccident.getPID());
        stm.setObject(2, postAccident.getAID());
        
        int rst = stm.executeUpdate();
        if (rst>0) {
            return true;
        }
          return false;
        }

    @Override
    public boolean UpdateHID(Connection connection, PostAccident postAccident) throws ClassNotFoundException, SQLException {
        PreparedStatement stm = connection.prepareStatement("UPDATE post_accident SET HID=? WHERE AID=?");
        stm.setObject(1, postAccident.getHID());
        stm.setObject(2, postAccident.getAID());
        
        int rst = stm.executeUpdate();
        if (rst>0) {
            return true;
        }
          return false;
    }
    
    @Override
    public ArrayList<PostAccident> getAllDetail(Connection connection) throws ClassNotFoundException, SQLException {

        String sql = "SELECT * FROM post_accident ORDER BY AID DESC;";
        Statement stm = connection.createStatement();
        ResultSet res = stm.executeQuery(sql);

        ArrayList<PostAccident> accidents = new ArrayList<PostAccident>();

        while (res.next()) {
            PostAccident accident = new PostAccident();
            accident.setAID(res.getString("AID"));
            accident.setUID(res.getString("UID"));
            accident.setLAT(res.getFloat("LAT"));
            accident.setLON(res.getFloat("LON"));
            accident.setDATE(res.getDate("DATE"));
            accident.setTIME(res.getTime("TIME"));
            accident.setPID(res.getString("PID"));
            accident.setHID(res.getString("HID"));
            accidents.add(accident);
        }
        return accidents;
    }
    
    @Override
    public ArrayList<PostAccident> getAllDetailByPooliceDevision(Connection connection,String divition) throws ClassNotFoundException, SQLException {

        String sql = "SELECT AID,UID,LAT,LON,DATE,TIME,DIVITION,HID,PID FROM post_accident a,police_station p where a.PID=p.PID and DIVITION='"+divition+"'";
        Statement stm = connection.createStatement();
        ResultSet res = stm.executeQuery(sql);

        ArrayList<PostAccident> accidents = new ArrayList<PostAccident>();

        while (res.next()) {
            PostAccident accident = new PostAccident();
            accident.setAID(res.getString("AID"));
            accident.setUID(res.getString("UID"));
            accident.setLAT(res.getFloat("LAT"));
            accident.setLON(res.getFloat("LON"));
            accident.setDATE(res.getDate("DATE"));
            accident.setTIME(res.getTime("TIME"));
            accident.setPID(res.getString("PID"));
            accident.setHID(res.getString("HID"));
            accidents.add(accident);
        }
        return accidents;
    }
    
    @Override
    public ArrayList<PoliceStation> getCountByPooliceDevision(Connection connection) throws ClassNotFoundException, SQLException {

        String sql = "SELECT DIVITION count(AID) as cot FROM post_accident a,police_station p where a.PID=p.PID GROUP BY DIVITION";
        Statement stm = connection.createStatement();
        ResultSet res = stm.executeQuery(sql);

        ArrayList<PoliceStation> accidents = new ArrayList<PoliceStation>();

        while (res.next()) {
            PoliceStation ps = new PoliceStation();
            ps.setDIVITION(res.getString("DIVITION"));
            ps.setLAT(res.getInt("cot"));
           
            accidents.add(ps);
        }
        return accidents;
    }
        
    }
    

