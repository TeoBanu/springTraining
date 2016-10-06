package base.repository;

import base.ConnectionSingleton;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by banut on 06/10/2016.
 */
public class StoreRepository {
    public List<String> getStockForAllProducts(int id) {
        Connection connection = null;
        ResultSet resultSet = null;
        Statement statement = null;
        List<String> result = new ArrayList<>();

        try {
            connection = ConnectionSingleton.getInstance().getConnection();
            statement = connection.createStatement();

            String query = "select store.name as 'Store name', section.name as 'Section name', product.name as 'Product name', " +
                    "product.stock as 'Stock' from Store store, Section section, Product product where store.id=" + id +
                    " and section.storeId=store.id and product.sectionId=section.id";
            resultSet = statement.executeQuery(query);

            while (resultSet.next() == true) {
                result.add("Store name: " + resultSet.getString(1) + " Section name: "
                        + resultSet.getString(2) + " Product name: "
                        + resultSet.getString(3) + " Stock: " + resultSet.getString(4));
            }
            return result;
        } catch (final SQLException ex) {
            throw new IllegalArgumentException(ex.getMessage());
        } finally {
            ConnectionSingleton.getInstance().closeResources(connection, resultSet, statement);
        }
    }
}
