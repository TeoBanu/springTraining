package base.repository;


import base.ConnectionSingleton;
import base.model.Product;

import java.sql.*;

import static java.sql.Connection.*;

public class ProductRepository {

    public int getCount() {
        Connection connection = null;
        ResultSet resultSet = null;
        Statement statement = null;

        try {
            connection = ConnectionSingleton.getInstance().getConnection();
            statement = connection.createStatement();

            resultSet = statement.executeQuery("SELECT count(*) FROM Product");
            resultSet.next();
            return resultSet.getInt(1);
        } catch (final SQLException ex) {
            throw new IllegalArgumentException(ex.getMessage());
        } finally {
            ConnectionSingleton.getInstance().closeResources(connection, resultSet, statement);
        }
    }

    public Product getProduct(final int id) {
        Connection connection = null;
        ResultSet resultSet = null;
        Statement statement = null;

        try {
            connection = ConnectionSingleton.getInstance().getConnection();
            statement = connection.createStatement();

            resultSet = statement.executeQuery("SELECT * FROM Product WHERE ID = " + id);
            if (resultSet.next()) {
                Product result = new Product();
                result.setId(resultSet.getInt("id"));
                result.setName(resultSet.getString("name"));
                result.setSectionId(resultSet.getInt("sectionId"));
                result.setStock(resultSet.getInt("stock"));
                return result;
            } else {
                throw new IllegalArgumentException("There is no product with the ID " + id);
            }
        } catch (final SQLException ex) {
            throw new IllegalArgumentException(ex.getMessage());
        } finally {
            ConnectionSingleton.getInstance().closeResources(connection, resultSet, statement);
        }
    }



    public void updateProduct(int oldSectionId, int newSectionId) {
        Connection connection = null;
        ResultSet resultSet = null;
        Statement statement = null;

        try {
            connection = ConnectionSingleton.getInstance().getConnection();
            statement = connection.createStatement();

            int updatedRecords = statement.executeUpdate("update Product set sectionId=" + newSectionId + " WHERE sectionId = " + oldSectionId);
            System.out.println("There were " + updatedRecords + " products updated.");
        } catch (final SQLException ex) {
            throw new IllegalArgumentException(ex.getMessage());
        } finally {
            ConnectionSingleton.getInstance().closeResources(connection, resultSet, statement);
        }
    }

    public void createProduct(Product product) {
        Connection connection = null;
        PreparedStatement statement = null;
        String createProductString = "insert into product (name,sectionId,stock) values (?,?,?)";

        try {
            connection = ConnectionSingleton.getInstance().getConnection();
            statement = connection.prepareStatement(createProductString);
            statement.setString(1, product.getName());
            statement.setInt(2, product.getSectionId());
            statement.setInt(3, product.getStock());

            int createdRecords = statement.executeUpdate();
            System.out.println("There were " + createdRecords + " products created.");
        } catch (final SQLException ex) {
            throw new IllegalArgumentException(ex.getMessage());
        } finally {
            ConnectionSingleton.getInstance().closeResources(connection, null, statement);
        }
    }

    public void deleteProduct(int id) {
        Connection connection = null;
        Statement statement = null;
        String deleteProductString = "delete from product where id = " + id;

        try {
            connection = ConnectionSingleton.getInstance().getConnection();
            statement = connection.createStatement();
            int deleted = statement.executeUpdate(deleteProductString);
            System.out.println("Deleted " + deleted + " products.");
        } catch (final SQLException ex) {
            throw new IllegalArgumentException(ex.getMessage());
        } finally {
            ConnectionSingleton.getInstance().closeResources(connection, null, statement);
        }
    }
}
