/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author KBT
 */
@Entity
@Table(name = "product")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p"),
    @NamedQuery(name = "Product.findBySale", query = "SELECT p FROM Product p WHERE p.sale = :sale"),
    @NamedQuery(name = "Product.findById", query = "SELECT p FROM Product p WHERE p.id = :id"),
    @NamedQuery(name = "Product.findByName", query = "SELECT p FROM Product p WHERE p.name = :name"),
    @NamedQuery(name = "Product.findByAmount", query = "SELECT p FROM Product p WHERE p.amount = :amount"),
    @NamedQuery(name = "Product.findByPrice", query = "SELECT p FROM Product p WHERE p.price = :price"),
    @NamedQuery(name = "Product.findByDescription", query = "SELECT p FROM Product p WHERE p.description = :description"),
    @NamedQuery(name = "Product.findByImage", query = "SELECT p FROM Product p WHERE p.image = :image")})
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "sale")
    private Double sale;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "amount")
    private int amount;
    @Basic(optional = false)
    @Column(name = "price")
    private double price;
    @Column(name = "description")
    private String description;
    @Column(name = "image")
    private String image;
    @JoinColumn(name = "id_brand", referencedColumnName = "id")
    @ManyToOne
    private Brand idBrand;
    @OneToMany(mappedBy = "productId")
    private Collection<Orrderdetail> orrderdetailCollection;
    @OneToMany(mappedBy = "productId")
    private Collection<Cart> cartCollection;

    public Product() {
    }

    public Product(Integer id) {
        this.id = id;
    }

    public Product(Integer id, String name, int amount, double price) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.price = price;
    }

    public Double getSale() {
        return sale;
    }

    public void setSale(Double sale) {
        this.sale = sale;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Brand getIdBrand() {
        return idBrand;
    }

    public void setIdBrand(Brand idBrand) {
        this.idBrand = idBrand;
    }
    
    public String getPriceCurrencyFormat() {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format((price));
    }
    
    public String getSalePriceFormat() {
        sale = 1.0 - sale;
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format((price*sale));
    }

    @XmlTransient
    public Collection<Orrderdetail> getOrrderdetailCollection() {
        return orrderdetailCollection;
    }

    public void setOrrderdetailCollection(Collection<Orrderdetail> orrderdetailCollection) {
        this.orrderdetailCollection = orrderdetailCollection;
    }

    @XmlTransient
    public Collection<Cart> getCartCollection() {
        return cartCollection;
    }

    public void setCartCollection(Collection<Cart> cartCollection) {
        this.cartCollection = cartCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Product[ id=" + id + " ]";
    }
    
}
