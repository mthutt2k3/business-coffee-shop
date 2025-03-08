package com.business.coffeshop.repository;

import com.business.coffeshop.entity.Account;
import com.business.coffeshop.entity.Cart;
import com.business.coffeshop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByAccount(Account account);
    Cart findByAccountAndProduct(Account account, Product product);
}
