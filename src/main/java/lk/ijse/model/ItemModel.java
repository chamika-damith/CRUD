package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.ItemDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ItemModel {

    public boolean saveItem(ItemDto dto) throws SQLException {
        Connection connection= DbConnection.getInstance().getConnection();

        String sql="INSERT INTO item VALUES(?,?,?,?)";

        PreparedStatement preparedStatement=connection.prepareStatement(sql);

        preparedStatement.setString(1,dto.getCode());
        preparedStatement.setString(2,dto.getDescription());
        preparedStatement.setString(3,dto.getUnitPrice());
        preparedStatement.setString(4,dto.getQty());

        boolean isSaved=preparedStatement.executeUpdate() >0;

        return isSaved;

    }
}
