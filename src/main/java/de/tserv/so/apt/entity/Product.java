package de.tserv.so.apt.entity;

import java.util.ArrayList;
import java.util.List;

import de.tserv.so.apt.SpringConfiguration;
import de.tserv.so.apt.db.VersionRepository;
import de.tserv.so.apt.util.ProductDeserializer;
import de.tserv.so.apt.util.ProductSerializer;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import tools.jackson.databind.annotation.JsonDeserialize;
import tools.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "product")
@JsonSerialize(using = ProductSerializer.class)
@JsonDeserialize(using = ProductDeserializer.class)
public class Product {
    private @Id
    @Column(name = "product_id")
    @GeneratedValue Long id;

    @OneToMany(mappedBy = "product", cascade=CascadeType.ALL)
    private List<Version> versions = new ArrayList<>();

    private String productExternalId;
    private String product_name;
    private String product_link;

    public Product() {}

    public Product(String product_external_id, String product_name, String product_link, List<Long> versions) {
        this.productExternalId = product_external_id;
        this.product_name = product_name;
        this.product_link = product_link;
        VersionRepository repo = (VersionRepository) SpringConfiguration.contextProvider().getApplicationContext().getBean("versionRepository");
        this.versions = repo.findAllById(versions);
    }

    public Long getId() {
        return id;
    }

    public String getProductExternalId() {
        return productExternalId;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getProduct_link() {
        return product_link;
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

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setProduct_link(String product_link) {
        this.product_link = product_link;
    }

    @Override
    public String toString() {
        return String.format("Product[id=%d, product_id='%s', product_name='%s', product_link='%s']", id, productExternalId, product_name, product_link);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Product)) return false;
        Product p = (Product) o;
        return p.getId().equals(this.id) && p.getProductExternalId().equals(this.productExternalId) && p.getProduct_name().equals(this.product_name) && p.getProduct_link().equals(this.product_link);
    }

    @Override
    public int hashCode() {
        return id.hashCode() + productExternalId.hashCode() + product_name.hashCode() + product_link.hashCode();
    }
}
