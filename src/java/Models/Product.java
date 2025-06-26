    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author hgduy
 */
public class Product {
    private int productID;
    private Category category;
    private String name;
    private float price;
    private String description;
    private int sold;
    private float rating;
    private String image;
    private int quantity;

    public Product() {
    }

    public Product(int productID, Category category, String name, float price, String description, int sold, float rating, String image, int quantity) {
        this.productID = productID;
        this.category = category;
        this.name = name;
        this.price = price;
        this.description = description;
        this.sold = sold;
        this.rating = rating;
        this.image = image;
        this.quantity = quantity;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product{" + "productID=" + productID + ", category=" + category + ", name=" + name + ", price=" + price + ", description=" + description + ", sold=" + sold + ", rating=" + rating + ", image=" + image + ", quantity=" + quantity + '}';
    }
    
    
}
