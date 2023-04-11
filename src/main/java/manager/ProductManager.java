package manager;

import db.DBConectionProvider;
import model.Category;
import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ProductManager {
    private Connection connection = DBConectionProvider.getInstance().getConnection();
    private CategoryManager categoryManager = new CategoryManager();
    public void save(Product product) {
        String sql = "insert into product (name,description,price,quantity,category_id) values(?,?,?,?,?)";
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1,product.getName());
            ps.setString(2,product.getDescription());
            ps.setInt(3,product.getPrice());
            ps.setInt(4,product.getQuantity());
            ps.setInt(5,product.getCategory().getId());
            ps.executeUpdate();
            System.out.println("Product inserted into DB");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public Product getById(int id){
        try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("Select * from product where id = " + id );
            if (resultSet.next()){
                Product product = new Product();
                product.setId(id);
                product.setName(resultSet.getString("name"));
                product.setDescription(resultSet.getString("description"));
                product.setPrice(resultSet.getInt("price"));
                product.setQuantity(resultSet.getInt("quantity"));
                int categoryId = resultSet.getInt("category_id");
                product.setCategory(categoryManager.getById(categoryId));
                return product;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public List<Product> getAll(){
        List<Product> productList = new ArrayList<>();
        try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("select * from product");
            while (resultSet.next()){
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setDescription(resultSet.getString("description"));
                product.setPrice( resultSet.getInt("price"));
                product.setQuantity(resultSet.getInt("quantity"));
                int categoryId = resultSet.getInt("category_id");
                product.setCategory(categoryManager.getById(categoryId));
                productList.add(product);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;

    }

    public void editById(Product product) {
        String sql = "UPDATE product SET name = '%s',description = '%s',price = %d,quantity = %d,category_id = %d WHERE id = %d";

        try (Statement statement = connection.createStatement()){
            statement.executeUpdate(String.format(sql,product.getName(),product.getDescription(),product.getPrice(),product.getQuantity(),product.getQuantity(),product.getId()));
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deliteById(int id) {
        String sql = "delete from product where id = " + id;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printSumOfProducts() {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT SUM(quantity) AS sum FROM product");
            if (resultSet.next()){
                int sum = resultSet.getInt("sum");
                System.out.println("total products " + sum);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printMaxOfPriceProduct() {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT MAX(price) AS max_value FROM product;");
            if (resultSet.next()){
                int max = resultSet.getInt("max_value");
                System.out.println("total products " + max);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printMinOfPriceProduct() {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT min(price) AS min_value FROM product;");
            if (resultSet.next()){
                int min = resultSet.getInt("min_value");
                System.out.println("total products " + min);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printAvgOfPriceProduct() {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT avg(price) AS avg_value FROM product;");
            if (resultSet.next()){
                int avg = resultSet.getInt("avg_value");
                System.out.println("total products " + avg);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
