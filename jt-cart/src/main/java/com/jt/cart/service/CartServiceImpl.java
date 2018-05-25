package com.jt.cart.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.cart.mapper.CartMapper;
import com.jt.cart.pojo.Cart;
import com.jt.common.service.BaseService;
import com.jt.common.service.HttpClientService;
@Service
public class CartServiceImpl extends BaseService<Cart>implements CartService {

	@Autowired
	CartMapper cartMapper;

	@Autowired
	HttpClientService httpClientService;
	@Override
	public List<Cart> queryCartByUserId(Long userId) {
		Cart cart = new Cart();
		cart.setUserId(userId);
		
		return cartMapper.select(cart);
	}

	@Override
	public void updateCartNum(Long userId, Long itemId, Integer num) {
		Cart cart = new Cart();
		cart.setItemId(itemId);
		cart.setUserId(userId);
		cart.setNum(num);
		cart.setUpdated(new Date());
		
		cartMapper.updateByPrimaryKeySelective(cart);
	}

	//新增购物车业务
	/**
	 * 1,根据用户的id和itemid查询数据库信息
	 * 2.如果查询的结果为null，新增
	 * 3.如果查询的结果不为空，更新
	 */
	@Override
	public void save(Cart cart) {
	
		Cart cartDB= new Cart();
		cartDB.setItemId(cart.getItemId());
		cartDB.setUserId(cart.getUserId());
		Cart cartTemp = super.queryByWhere(cartDB);
		System.out.println(cartTemp);
		if(cartTemp==null){
			cart.setCreated(new Date());
			cart.setUpdated(cart.getCreated());
			cartMapper.insert(cart);
		}else{
			int num=cartTemp.getNum()+cart.getNum();
			cartTemp.setNum(num);
			cartTemp.setUpdated(new Date());
			cartMapper.updateByPrimaryKeySelective(cartTemp);
		}
		
	}

	@Override
	public void doDelete(Long userId, Long itemId) {
		Cart cart = new Cart();
		cart.setUserId(userId);
		cart.setItemId(itemId);
		cartMapper.delete(cart);
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
