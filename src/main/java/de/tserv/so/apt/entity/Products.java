package de.tserv.so.apt.entity;

import java.util.List;

public class Products {
    private List<Product> products; 

    private boolean isAdmin; 

    public Products(List<Product> p, boolean a) {
        products = p; 
        isAdmin = a; 
    }

    public List<Product> getProducts() {
        return products;
    }

    public boolean getIsAdmin() {
        return isAdmin; 
    }

    public void setProducts(List<Product> p) {
        products = p;
    }

    public void setIsAdmin(boolean a) {
        isAdmin = a; 
    }

    @Override
    public String toString() {
        return "Products [products=" + products + ", isAdmin=" + isAdmin + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((products == null) ? 0 : products.hashCode());
        result = prime * result + (isAdmin ? 1231 : 1237);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Products other = (Products) obj;
        if (products == null) {
            if (other.products != null)
                return false;
        } else if (!products.equals(other.products))
            return false;
        if (isAdmin != other.isAdmin)
            return false;
        return true;
    }

    
}
