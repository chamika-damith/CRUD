package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.ItemDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<ItemDto> getAllItems() throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();

        String sql="SELECT * FROM item";

        PreparedStatement preparedStatement=connection.prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery();

        List<ItemDto> dtoList=new ArrayList<>();

        while (resultSet.next()){
            dtoList.add(new ItemDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            ));
        }
        return dtoList;
    }

    public ItemDto SearchItem(String textCode) throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();

        String sql="SELECT * FROM item WHERE code=?";

        PreparedStatement preparedStatement=connection.prepareStatement(sql);

        preparedStatement.setString(1,textCode);

        ResultSet resultSet = preparedStatement.executeQuery();

        ItemDto dto = null;

        if (resultSet.next()) {
            String code = resultSet.getString(1);
            String description = resultSet.getString(2);
            String price = resultSet.getString(3);
            String qty = resultSet.getString(4);

            dto=new ItemDto(code, description, price,qty);

        }
        
        return dto;
    }
}
