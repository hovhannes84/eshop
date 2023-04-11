import manager.CategoryManager;
import manager.ProductManager;
import model.Category;
import model.Product;

import java.util.List;
import java.util.Scanner;

public class EshopMain {
    private static Scanner scanner = new Scanner(System.in);
    private static CategoryManager categoryManager = new CategoryManager();
    private static ProductManager productManager = new ProductManager();

    public static void main(String[] args) {
        boolean isRun = true;
        while (isRun) {
            System.out.println("Please input 0 for exit");
            System.out.println("Please input 1 for add category");
            System.out.println("Please input 2 for edit Category By Id");
            System.out.println("Please input 3 for Delete Category By Id");
            System.out.println("Please input 4 for add product");
            System.out.println("Please input 5 for Edit Product By Id");
            System.out.println("Please input 6 for Delete Product By Id");
            System.out.println("Please input 7 for Print Sum of products");
            System.out.println("Please input 8 for Print Max of price product");
            System.out.println("Please input 9 for Print Min of price product ");
            System.out.println("Please input 10 for Print Avg of price product");
            String command = scanner.nextLine();
            switch (command) {
                case "0":
                    isRun = false;
                    break;
                case "1":
                    addCategory();
                    break;
                case "2":
                    editCategoryById();
                    break;
                case "3":
                    deleteCategoryById();
                    break;
                case "4":
                    addProduct();
                    break;
                case "5":
                    EditProductById();
                    break;
                case "6":
                    DeleteProductById();
                    break;
                case "7":
                    productManager.printSumOfProducts();
                    break;
                case "8":
                    productManager.printMaxOfPriceProduct();
                    break;
                case "9":
                    productManager.printMinOfPriceProduct();
                    break;
                case "10":
                    productManager.printAvgOfPriceProduct();
                    break;
                default:
                    System.out.println("invalid command");
            }
        }

    }


    private static void DeleteProductById() {
        List<Product> all = productManager.getAll();
        for (Product product : all) {
            System.out.println(product);
        }
        System.out.println("please input product Id");
        int id = Integer.parseInt(scanner.nextLine());
        productManager.deliteById(id);
        System.out.println("product removed");
    }

    private static void EditProductById() {
        List<Product> all = productManager.getAll();
        for (Product product : all) {
            System.out.println(product);
        }
        System.out.println("please input product Id");
        int catID = Integer.parseInt(scanner.nextLine());
        System.out.println("please input new product name,description,price,quantity,categoryId");
        String str = scanner.nextLine();
        String[] split = str.split(",");
        Product product = new Product();
        Category category = categoryManager.getById(Integer.parseInt(split[4]));
        if (category != null) {
            product.setId(catID);
            product.setName(split[0]);
            product.setDescription(split[1]);
            product.setPrice(Integer.parseInt(split[2]));
            product.setQuantity(Integer.parseInt(split[3]));
            product.setCategory(category);
            productManager.editById(product);
            System.out.println("product changed by id");
        }

    }

    private static void addProduct() {
        List<Category> all = categoryManager.getAll();
        for (Category category : all) {
            System.out.println(category);
        }
        System.out.println("please input Category Id");
        int id = Integer.parseInt(scanner.nextLine());
        Category category = categoryManager.getById(id);
        if (category != null) {
            System.out.println("please input product name,description,price,quantity");
            String productStr = scanner.nextLine();
            String[] str = productStr.split(",");
            Product product = new Product();
            product.setCategory(category);
            product.setName(str[0]);
            product.setDescription(str[1]);
            product.setPrice(Integer.parseInt(str[2]));
            product.setQuantity(Integer.parseInt(str[3]));
            productManager.save(product);
        }

    }

    private static void deleteCategoryById() {
        List<Category> all = categoryManager.getAll();
        for (Category category : all) {
            System.out.println(category);
        }
        System.out.println("please input Category Id");
        int id = Integer.parseInt(scanner.nextLine());
        categoryManager.deliteById(id);
        System.out.println("category deleted");
    }

    private static void editCategoryById() {
        List<Category> all = categoryManager.getAll();
        for (Category category : all) {
            System.out.println(category);
        }
        System.out.println("please input Category Id");
        int catID = Integer.parseInt(scanner.nextLine());
        System.out.println("please input new Category");
        String name = scanner.nextLine();
        Category category = new Category();
        category.setId(catID);
        category.setName(name);
        categoryManager.editById(category);
        System.out.println("category changed by id");
    }

    private static void addCategory() {
        System.out.println("please input name category");
        String categoryStr = scanner.nextLine();
        Category category = new Category();
        category.setName(categoryStr);
        categoryManager.save(category);

    }
}
