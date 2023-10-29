package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.CustomerDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

public class CustomerModel {
    public boolean saveCustomer(CustomerDto dto) throws SQLException {
        Connection connection= DbConnection.getInstance().getConnection();

        String sql="INSERT INTO customer VALUES (?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, dto.getCustomer_id());
        preparedStatement.setString(2, dto.getName());
        preparedStatement.setString(3, dto.getAddress());
        preparedStatement.setString(4, dto.getTel());

        boolean isSaved=preparedStatement.executeUpdate() > 0;

        return isSaved;
    }

    public List<CustomerDto> getAllCustomer() throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();

        String sql="SELECT * FROM customer";

        PreparedStatement preparedStatement=connection.prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery();

        ArrayList<CustomerDto> dtoList=new ArrayList();

        while (resultSet.next()){
            dtoList.add(
                    new CustomerDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4)
                    )
            );
        }
        return dtoList;
    }

    public boolean deleteCustomer(String  id) throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();

        String sql="DELETE FROM customer WHERE customer_id = ? ";

        PreparedStatement preparedStatement=connection.prepareStatement(sql);

        preparedStatement.setString(1,id);

        return preparedStatement.executeUpdate() > 0;
    }

    public boolean updateCustomer(CustomerDto dto) throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();

        String sql="UPDATE customer SET name=?, address=?, tel=? WHERE customer_id=?";

        PreparedStatement preparedStatement=connection.prepareStatement(sql);

        preparedStatement.setString(1, dto.getName());
        preparedStatement.setString(2, dto.getAddress());
        preparedStatement.setString(3,dto.getTel());
        preparedStatement.setString(4,dto.getCustomer_id());

        return preparedStatement.executeUpdate() > 0;
    }
}
