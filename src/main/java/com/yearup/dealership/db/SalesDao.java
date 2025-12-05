package com.yearup.dealership.db;

import com.yearup.dealership.models.SalesContract;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SalesDao {
    private DataSource dataSource;

    public SalesDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addSalesContract(SalesContract salesContract) {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("""
                    insert into sales_contracts (vin,sales_date,price) values (?,?,?)""")){
            statement.setString(1,salesContract.getVin());
            statement.setDate(2, Date.valueOf(salesContract.getSaleDate()));
            statement.setDouble(3, salesContract.getPrice());
            statement.execute();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
