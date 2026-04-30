package de.tserv.so.apt.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import de.tserv.so.apt.SpringConfiguration;
import de.tserv.so.apt.db.VersionRepository;
import de.tserv.so.apt.util.ProductDeserializer;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import tools.jackson.databind.annotation.JsonDeserialize;

@Entity
@Table(name = "product")
@JsonDeserialize(using = ProductDeserializer.class)
public class Product {
    private @Id
    @Column(name = "product_id")
    @GeneratedValue Long id;

    @JsonManagedReference
    @OneToMany(mappedBy = "product", cascade=CascadeType.ALL)
    private List<Version> versions = new ArrayList<>();

    private String productExternalId;
    private String productName;
    private String productLink;

    public Product() {}

    public Product(String productExternalId, String product_name, String product_link, List<Long> versions) {
        this.productExternalId = productExternalId;
        this.productName = product_name;
        this.productLink = product_link;
        VersionRepository repo = (VersionRepository) SpringConfiguration.contextProvider().getApplicationContext().getBean("versionRepository");
        this.versions = repo.findAllById(versions);
    }

    public Product(String productExternalId, String productName, String productLink) {
        this.productExternalId = productExternalId;
        this.productName = productName;
        this.productLink = productLink;
        this.versions = new ArrayList<Version>();
    }

    public Long getId() {
        return id;
    }

    public String getProductExternalId() {
        return productExternalId;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductLink() {
        return productLink;
    }

    public List<Version> getVersions() {
        return versions;
    }

    public void setVersions(List<Version> versions) {
        this.versions = versions;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProductExternalId(String product_external_id) {
        this.productExternalId = product_external_id;
    }

    public void setProductName(String product_name) {
        this.productName = product_name;
    }

    public void setProductLink(String product_link) {
        this.productLink = product_link;
    }

    @Override
    public String toString() {
        return String.format("Product[id=%d, product_id='%s', product_name='%s', product_link='%s']", id, productExternalId, productName, productLink);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Product)) return false;
        Product p = (Product) o;
        return p.getId().equals(this.id) && p.getProductExternalId().equals(this.productExternalId) && p.getProductName().equals(this.productName) && p.getProductLink().equals(this.productLink);
    }

    @Override
    public int hashCode() {
        return id.hashCode() + productExternalId.hashCode() + productName.hashCode() + productLink.hashCode();
    }
}
