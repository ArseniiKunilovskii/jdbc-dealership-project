package com.yearup.dealership.db;

import com.yearup.dealership.models.Vehicle;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {
    private DataSource dataSource;

    public VehicleDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addVehicle(Vehicle vehicle) {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("""
                    insert into vehicles (vin,make,model,year, sold,color,vehicleType,odometer,price) values (?,?,?,?,?,?,?,?,?)""")){
            statement.setString(1,vehicle.getVin());
            statement.setString(2,vehicle.getMake());
            statement.setString(3,vehicle.getModel());
            statement.setInt(4,vehicle.getYear());
            statement.setBoolean(5,vehicle.isSold());
            statement.setString(6,vehicle.getColor());
            statement.setString(7,vehicle.getVehicleType());
            statement.setInt(8,vehicle.getOdometer());
            statement.setDouble(9,vehicle.getPrice());

            statement.execute();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void removeVehicle(String VIN) {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("""
                    Delete from vehicles where vin = ?""")){
            statement.setString(1,VIN);
            statement.execute();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<Vehicle> searchByPriceRange(double minPrice, double maxPrice) {
        List<Vehicle> vehicles = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("""
                    Select * from vehicles where price >= ? AND price <= ?""")){
            statement.setDouble(1, minPrice);
            statement.setDouble(2, maxPrice);
            try(ResultSet results = statement.executeQuery()) {
               if (results.next()){
                   do {
                       Vehicle vehicle = new Vehicle(results.getString(1),
                               results.getString(2),
                               results.getString(3),
                               results.getInt(4),
                               results.getBoolean(5),
                               results.getString(6),
                               results.getString(7),
                               results.getInt(8),
                               results.getDouble(9));
                       vehicles.add(vehicle);

                   } while (results.next());
               }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return vehicles;
    }

    public List<Vehicle> searchByMakeModel(String make, String model) {
        List<Vehicle> vehicles = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("""
                    Select * from vehicles where make = ? AND model= ?""")){
            statement.setString(1, make);
            statement.setString(2, model);
            try(ResultSet results = statement.executeQuery()) {
                if (results.next()){
                    do {
                        Vehicle vehicle = new Vehicle(results.getString(1),
                                results.getString(2),
                                results.getString(3),
                                results.getInt(4),
                                results.getBoolean(5),
                                results.getString(6),
                                results.getString(7),
                                results.getInt(8),
                                results.getDouble(9));
                        vehicles.add(vehicle);

                    } while (results.next());
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return vehicles;
    }


    public List<Vehicle> searchByYearRange(int minYear, int maxYear) {
        List<Vehicle> vehicles = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("""
                    Select * from vehicles where year >= ? AND year <= ?""")){
            statement.setInt(1, minYear);
            statement.setInt(2, maxYear);
            try(ResultSet results = statement.executeQuery()) {
                if (results.next()){
                    do {
                        Vehicle vehicle = new Vehicle(results.getString(1),
                                results.getString(2),
                                results.getString(3),
                                results.getInt(4),
                                results.getBoolean(5),
                                results.getString(6),
                                results.getString(7),
                                results.getInt(8),
                                results.getDouble(9));
                        vehicles.add(vehicle);

                    } while (results.next());
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return vehicles;
    }


    public List<Vehicle> searchByColor(String color) {
        List<Vehicle> vehicles = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("""
                    Select * from vehicles where color = ?""")){
            statement.setString(1, color);
            try(ResultSet results = statement.executeQuery()) {
                if (results.next()){
                    do {
                        Vehicle vehicle = new Vehicle(results.getString(1),
                                results.getString(2),
                                results.getString(3),
                                results.getInt(4),
                                results.getBoolean(5),
                                results.getString(6),
                                results.getString(7),
                                results.getInt(8),
                                results.getDouble(9));
                        vehicles.add(vehicle);

                    } while (results.next());
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return vehicles;
    }

    public List<Vehicle> searchByMileageRange(int minMileage, int maxMileage) {
        List<Vehicle> vehicles = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("""
                    Select * from vehicles where odometer >= ? AND odometer <= ?""")){
            statement.setInt(1, minMileage);
            statement.setInt(2, maxMileage);
            try(ResultSet results = statement.executeQuery()) {
                if (results.next()){
                    do {
                        Vehicle vehicle = new Vehicle(results.getString(1),
                                results.getString(2),
                                results.getString(3),
                                results.getInt(4),
                                results.getBoolean(5),
                                results.getString(6),
                                results.getString(7),
                                results.getInt(8),
                                results.getDouble(9));
                        vehicles.add(vehicle);

                    } while (results.next());
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return vehicles;
    }

    public List<Vehicle> searchByType(String type) {
        List<Vehicle> vehicles = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("""
                    Select * from vehicles where vehicleType = ?""")){
            statement.setString(1, type);
            try(ResultSet results = statement.executeQuery()) {
                if (results.next()){
                    do {
                        Vehicle vehicle = new Vehicle(results.getString(1),
                                results.getString(2),
                                results.getString(3),
                                results.getInt(4),
                                results.getBoolean(5),
                                results.getString(6),
                                results.getString(7),
                                results.getInt(8),
                                results.getDouble(9));
                        vehicles.add(vehicle);

                    } while (results.next());
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return vehicles;
    }

    private Vehicle createVehicleFromResultSet(ResultSet resultSet) throws SQLException {
        Vehicle vehicle = new Vehicle();
        vehicle.setVin(resultSet.getString("VIN"));
        vehicle.setMake(resultSet.getString("make"));
        vehicle.setModel(resultSet.getString("model"));
        vehicle.setYear(resultSet.getInt("year"));
        vehicle.setSold(resultSet.getBoolean("SOLD"));
        vehicle.setColor(resultSet.getString("color"));
        vehicle.setVehicleType(resultSet.getString("vehicleType"));
        vehicle.setOdometer(resultSet.getInt("odometer"));
        vehicle.setPrice(resultSet.getDouble("price"));
        return vehicle;
    }
}
