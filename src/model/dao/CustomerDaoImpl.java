package model.dao;

import model.entity.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerDaoImpl implements CustomerDao{
    @Override
    public List<Customer> queryAllCustomers() {
        String sql = """
                SELECT * FROM "customer"
                """;
        try(
                Connection connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/food_panda_db",
                        "postgres",
                        "Pisey0107"
                );
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                ) {
            List<Customer> customerList = new ArrayList<>();
            while (resultSet.next()){
                customerList.add(
                        new Customer(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getString("email"),
                                resultSet.getString("password"),
                                resultSet.getBoolean("is_deleted"),
                                resultSet.getDate("created_date")
                        )
                );

            }
            return customerList;

        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public int updateAllCustomersById(Integer id) {
        String sql = """
                UPDATE "customer"
                SET name = ?, email = ?
                WHERE id = ?
                """;
        try(
                Connection connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/food_panda_db",
                        "postgres",
                        "Pisey0107"
                );
                PreparedStatement preparedStatement
                        = connection.prepareStatement(sql);
                ){
            Customer customer = searchCustomerById(id);
            if (customer!=null){
                System.out.print("[+] Insert name : ");
                preparedStatement.setString(1,new Scanner(System.in).next());
                System.out.print("[+] Insert email : ");
                preparedStatement.setString(2,new Scanner(System.in).next());
                preparedStatement.setInt(3,id);
                int rowAffected = preparedStatement.executeUpdate();
                String message = rowAffected >0? "Updated successfully." : "Update failed.";
                System.out.println(message);
            }else {
                System.out.println("Customer does not exist.");
            }

        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return 0;
    }

    @Override
    public int deleteAllCustomersById(Integer id) {
        String sql = """
                DELETE FROM "customer"
                WHERE id = ?
                """;
        try(
                Connection connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/food_panda_db",
                        "postgres",
                        "Pisey0107"
                );
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ){
                Customer customer  = searchCustomerById(id);
                if (customer==null){
                    System.out.println("Customer does not exit, CANNOT DELETE!");
                }else {
                    preparedStatement.setInt(1,id);
                    int rowAffected = preparedStatement.executeUpdate();
                    System.out.println("Delete successfully.");
                }
        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return 0;
    }

    @Override
    public int addNewCustomersById(Customer customer) {
        String sql = """
                INSERT INTO "customer" (name, email, password, is_deleted, created_date)
                VALUES (?,?,?,?,?)
                """;
        try(
                Connection connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/food_panda_db",
                        "postgres",
                        "Pisey0107"
                );
                PreparedStatement preparedStatement
                        = connection.prepareStatement(sql);
                ){
                preparedStatement.setString(1, customer.getName());
                preparedStatement.setString(2, customer.getEmail());
                preparedStatement.setString(3, customer.getPassword());
                preparedStatement.setBoolean(4, customer.getIsDeleted());
                preparedStatement.setDate(5,customer.getCreatedDate());
                int rowAffected = preparedStatement.executeUpdate();
                if (rowAffected>0){
                    System.out.println("Customer has been created successfully");
                }

        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());

        }
        return 0;
    }

    @Override
    public Customer searchCustomerById(Integer id) {
        String sql = """
                SELECT * FROM "customer"
                WHERE id = ?
                """;
        try(
                Connection connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/food_panda_db",
                        "postgres",
                        "Pisey0107"
                );
                PreparedStatement preparedStatement
                        = connection.prepareStatement(sql)
                ){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Customer customer = null;
            while (resultSet.next()){
                customer = Customer
                        .builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .email(resultSet.getString("email"))
                        .password(resultSet.getString("password"))
                        .isDeleted(resultSet.getBoolean("is_deleted"))
                        .createdDate(resultSet.getDate("created_date"))
                        .build();
            }
            return customer;

        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }

        return null;
    }
}
