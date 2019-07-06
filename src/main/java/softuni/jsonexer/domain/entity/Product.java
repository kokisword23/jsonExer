package softuni.jsonexer.domain.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    private String name;
    private BigDecimal price;
    private User seller;
    private User buyer;
    private List<Category> categories;

    public Product(){
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "seller_id", referencedColumnName = "id")
    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "buyer_id", referencedColumnName = "id")
    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    @ManyToMany(targetEntity = Category.class)
    @JoinTable(
            name = "categories_products",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id")
    )
    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
