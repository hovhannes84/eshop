package manager;

import db.DBConectionProvider;
import model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryManager {
    private Connection connection = DBConectionProvider.getInstance().getConnection();
    public Category getById(int id){
        try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("Select * from category where id = " + id );
            if (resultSet.next()){
                String name = resultSet.getString("name");
                Category category = new Category();
                category.setId(id);
                category.setName(name);
                return category;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Category> getAll(){
        List<Category> categoryList = new ArrayList<>();
        try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("select * from category");
            while (resultSet.next()){
                Category category = new Category();
                category.setId(resultSet.getInt("id"));
                category.setName(resultSet.getString("name"));
                categoryList.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryList;
    }
    public void save(Category category) {
        String sql = "insert into category (name) values(?)";
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1,category.getName());
            ps.executeUpdate();
            System.out.println("Category inserted into DB");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void editById(Category category){
        String sql = "UPDATE category SET name = '%s' WHERE id = %d";
        try (Statement statement = connection.createStatement()){
            statement.executeUpdate(String.format(sql,category.getName(),category.getId()));
            }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public void deliteById(int id) {
        String sql = "delete from category where id = " + id;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
