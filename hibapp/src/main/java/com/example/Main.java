package com.example;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Product> expensiveProducts = session.createQuery("FROM Product WHERE price > 500", Product.class).list();
        System.out.println("Products with price greater than 500:");
        expensiveProducts.forEach(product -> System.out.println(product.getPname()));

        List<Product> highQtyProducts = session.createQuery("FROM Product WHERE qty > 10", Product.class).list();
        System.out.println("Products with quantity greater than 10:");
        highQtyProducts.forEach(product -> System.out.println(product.getPname()));

        Long productCount = (Long) session.createQuery("SELECT COUNT(*) FROM Product").uniqueResult();
        System.out.println("Number of products available: " + productCount);

        Product highestPriceProduct = session.createQuery("FROM Product ORDER BY price DESC", Product.class).setMaxResults(1).uniqueResult();
        Product lowestPriceProduct = session.createQuery("FROM Product ORDER BY price ASC", Product.class).setMaxResults(1).uniqueResult();
        System.out.println("Highest price product: " + highestPriceProduct.getPname() + " - " + highestPriceProduct.getPrice());
        System.out.println("Lowest price product: " + lowestPriceProduct.getPname() + " - " + lowestPriceProduct.getPrice());

        Double totalProductSum = (Double) session.createQuery("SELECT SUM(price * qty) FROM Product").uniqueResult();
        System.out.println("Total sum of all available products: " + totalProductSum);

        session.close();
        HibernateUtil.getSessionFactory().close();
    }
}