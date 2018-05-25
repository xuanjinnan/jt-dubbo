package com.jt.cart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.cart.pojo.Cart;
import com.jt.cart.service.CartService;
import com.jt.common.vo.SysResult;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	CartService cartService;
	
	//查询用户购物车的商品
	//http://cart.jt.com/cart/query/"+userId
	@RequestMapping("/query/{userId}")
	@ResponseBody
	public SysResult queryCartByUserId(@PathVariable Long userId){
		try {
			
			List<Cart> cartList=cartService.queryCartByUserId(userId);
			if(!StringUtils.isEmpty(cartList)){
				
				return SysResult.oK(cartList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201, "查询购物车失败");
	}
	
	// //http://cart.jt.com/cart/upadate/num/001/002/2
	@RequestMapping("/upadate/num/{userId}/{itemId}/{num}")
	@ResponseBody
	public SysResult updateCartNum(@PathVariable Long userId,@PathVariable Long itemId,@PathVariable Integer num){
		try {
			cartService.updateCartNum(userId,itemId,num);
			return SysResult.oK();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201, "更新购物车失败");
	}
	
	//增加购物车商品
	//"http://cart.jt.com/cart/save";;
	@RequestMapping("/save")
	@ResponseBody
	public SysResult save(Cart cart){
		try {
			System.out.println("CartController.save()");
			cartService.save(cart);
			return SysResult.oK();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201,"该商品已经存在购物车中");
	}
	
	//删除购物车商品
	//http://cart.jt.com/cart/delete/"+userId+"/"+itemId
	@RequestMapping("/delete/{userId}/{itemId}")
	public SysResult doDelete(@PathVariable Long userId,@PathVariable Long itemId){
		try {
			cartService.doDelete(userId,itemId);
			return SysResult.oK();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201,"");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
