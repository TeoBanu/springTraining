package base.repository;

import base.ConnectionSingleton;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by banut on 06/10/2016.
 */
public class StoreRepository {
    public ResultSet getStockForAllProducts(int id) {
        Connection connection = null;
        ResultSet resultSet = null;
        Statement statement = null;

        try {
            connection = ConnectionSingleton.getInstance().getConnection();
            statement = connection.createStatement();

            String query = "select store.name as 'Store name', section.name as 'Section name', product.name as 'Product name', " +
                    "product.stock as 'Stock' from Store store, Section section, Product product where store.id=" + id +
                    " and section.storeId=store.id and product.sectionId=section.id";
            resultSet = statement.executeQuery(query);
            resultSet.next();
            return resultSet;
        } catch (final SQLException ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
    }
}
