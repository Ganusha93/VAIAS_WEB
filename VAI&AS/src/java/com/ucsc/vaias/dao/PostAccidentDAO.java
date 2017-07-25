/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucsc.vaias.dao;

import com.ucsc.vaias.model.PoliceStation;
import com.ucsc.vaias.model.PostAccident;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author sajja
 */
public interface PostAccidentDAO {

    public PostAccident SearchLastRow(Connection connection) throws ClassNotFoundException, SQLException;

    public boolean UpdatePID(Connection connection, PostAccident postAccident) throws ClassNotFoundException, SQLException;

    public boolean UpdateHID(Connection connection, PostAccident postAccident) throws ClassNotFoundException, SQLException;

    public ArrayList<PostAccident> getAllDetail(Connection connection) throws ClassNotFoundException, SQLException;

    public ArrayList<PostAccident> getAllDetailByPooliceDevision(Connection connection, String divition) throws ClassNotFoundException, SQLException;

    public ArrayList<PoliceStation> getCountByPooliceDevision(Connection connection) throws ClassNotFoundException, SQLException;

}
