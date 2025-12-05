package com.yearup.dealership.db;

import com.yearup.dealership.models.LeaseContract;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LeaseDao {
    private DataSource dataSource;

    public LeaseDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addLeaseContract(LeaseContract leaseContract) {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("""
                    insert into lease_contracts (vin,lease_Start,lease_End,monthly_Payment) values (?,?,?,?)""")){
            statement.setString(1,leaseContract.getVin());
            statement.setDate(2, Date.valueOf(leaseContract.getLeaseStart()));
            statement.setDate(3, Date.valueOf(leaseContract.getLeaseEnd()));
            statement.setDouble(4, leaseContract.getMonthlyPayment());
            statement.execute();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
