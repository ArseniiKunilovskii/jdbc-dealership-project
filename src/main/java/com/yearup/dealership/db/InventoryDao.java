package com.yearup.dealership.db;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InventoryDao {
    private DataSource dataSource;

    public InventoryDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addVehicleToInventory(String vin, int dealershipId) {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("""
                    insert into inventory (dealership_id,vin) values (?,?)""")){
            statement.setString(1,vin);
            statement.setInt(2, dealershipId);
            statement.execute();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void removeVehicleFromInventory(String vin) {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("""
                    Delete from inventory where vin = ?""")){
            statement.setString(1,vin);
            statement.execute();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
