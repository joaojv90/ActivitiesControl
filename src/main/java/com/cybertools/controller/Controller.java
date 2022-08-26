package com.cybertools.controller;

//@author jpjar
import com.cybertools.model.ActivitiesModel;
import com.cybertools.activitiescontrol.CBDD;
import java.sql.*;
import java.util.*;

public class Controller implements DAO<ActivitiesModel> {

    static Connection conn = CBDD.getConnection();

    @Override
    public List<ActivitiesModel> read() {
        List<ActivitiesModel> listActivities = new ArrayList<>();
        try {
            String query = "SELECT * FROM activities";
            ResultSet rs = conn.createStatement().executeQuery(query);
            while (rs.next()) {
                ActivitiesModel am = new ActivitiesModel();
                am.setId(rs.getInt(1));
                am.setStartDate(rs.getDate(2));
                am.setEndDate(rs.getDate(3));
                am.setActivity(rs.getString(4));
                am.setObservation(rs.getString(5));
                listActivities.add(am);
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return listActivities;
    }

    @Override
    public boolean create(ActivitiesModel t) {
        try {
            String query = "INSERT INTO activities (startDate,endDate,activity,observation)values(?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setDate(1, t.getStartDate());
            ps.setDate(2, t.getEndDate());
            ps.setString(3, t.getActivity());
            ps.setString(4, t.getObservation());
            
            return ps.executeUpdate() != 0;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return false;
    }

    @Override
    public boolean update(ActivitiesModel t) {
        try {
            String query = "UPDATE activities SET startDate=?,endDate=?, activity=?, observation=? WHERE idAct=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setDate(1, t.getStartDate());
            ps.setDate(2, t.getEndDate());
            ps.setString(3, t.getActivity());
            ps.setString(4, t.getObservation());
            ps.setInt(5, t.getId());
            return ps.executeUpdate() != 0;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return false;
    }

    @Override
    public boolean delete(ActivitiesModel t) {
        try {
            String query = "DELETE FROM activities WHERE idAct=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, t.getId());
            return ps.executeUpdate() != 0;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return false;
    }

}
