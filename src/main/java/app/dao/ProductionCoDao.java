package app.dao;

import app.model.ShowEntities.ProductionCo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductionCoDao extends Database {

     //Get all the production companies
    public Iterable<ProductionCo> getAllProductionCo() {
        List<ProductionCo> allProductionCo = new ArrayList<ProductionCo>();

        String sql;
        try {
            sql = "select * from production_company";
            setPreparedStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (true) {
                if (rs.next()) {
                    allProductionCo.add(new ProductionCo(rs.getInt("proco_id"), rs.getString("proco_name")));

                } else {
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allProductionCo;
    }

    //Get a production company by production company name
    public ProductionCo getProductionCo(String name) {
        List<ProductionCo> allProductionCo = (List<ProductionCo>) getAllProductionCo();

        for (ProductionCo p : allProductionCo) {
            if (p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }

    //Get the number of production companies
    public int getNumberOfProductionCo() {
        return ((List<ProductionCo>) getAllProductionCo()).size();
    }

    //Add this production company into database
    public void addProductionCo(ProductionCo productionCo) {
        String sql= "insert into production_company(proco_id, proco_name) values(?,?)" ;
        try {
            PreparedStatement preparedStatement = Database.connection.prepareStatement(sql);
            preparedStatement.setInt(1, productionCo.getId());
            preparedStatement.setString(2, productionCo.getName());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
