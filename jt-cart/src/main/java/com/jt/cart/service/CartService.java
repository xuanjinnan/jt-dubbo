package com.jt.cart.service;

import java.util.List;

import com.jt.cart.pojo.Cart;

public interface CartService {

	List<Cart> queryCartByUserId(Long userId);

	void updateCartNum(Long userId, Long itemId, Integer num);

	void save(Cart cart);

	void doDelete(Long userId, Long itemId);


}
