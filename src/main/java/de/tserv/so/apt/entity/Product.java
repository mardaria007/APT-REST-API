package de.tserv.so.apt.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
public class Product {
    private @Id
    @Column(name = "product_id")
    @GeneratedValue Long id;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private List<Version> versions = new ArrayList<>();

    private String product_external_id;
    private String product_name;
    private String product_link;

    public Product() {}

    public Product(String product_external_id, String product_name, String product_link, List<Version> versions) {
        this.product_external_id = product_external_id;
        this.product_name = product_name;
        this.product_link = product_link;
        this.versions = versions;
    }

    public Long getId() {
        return id;
    }

    public String getProduct_external_id() {
        return product_external_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getProduct_link() {
        return product_link;
    }

    @JsonManagedReference(value="product-version")
    public List<Version> getVersions() {
        return versions;
    }

    public void setVersions(List<Version> versions) {
        this.versions = versions;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProduct_external_id(String product_external_id) {
        this.product_external_id = product_external_id;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setProduct_link(String product_link) {
        this.product_link = product_link;
    }

    @Override
    public String toString() {
        return String.format("Product[id=%d, product_id='%s', product_name='%s', product_link='%s']", id, product_external_id, product_name, product_link);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Product)) return false;
        Product p = (Product) o;
        return p.getId().equals(this.id) && p.getProduct_external_id().equals(this.product_external_id) && p.getProduct_name().equals(this.product_name) && p.getProduct_link().equals(this.product_link);
    }

    @Override
    public int hashCode() {
        return id.hashCode() + product_external_id.hashCode() + product_name.hashCode() + product_link.hashCode();
    }
}
