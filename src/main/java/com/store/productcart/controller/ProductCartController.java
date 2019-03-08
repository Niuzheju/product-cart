package com.store.productcart.controller;

import com.store.productcart.model.CartItem;
import com.store.productcart.util.JsonUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductCartController {

    //购物车在Cookie中的name
    private String productCart = "productCart";

    //url encoder使用的字符集
    private String enc = "utf-8";

    /**
     * 添加一个商品到购物车
     */
    @RequestMapping("addCart")
    public String addCart(CartItem cartItem, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Integer productId = cartItem.getProductId();
        if (cartItem == null || cartItem.getCount() <= 0 || cartItem.getPrice() < 0 || productId == null) {
            return "参数错误";
        }
        Cookie[] cookies = request.getCookies();
        Cookie cookie = null;
        if (cookies != null && cookies.length >= 0) {
            for (Cookie cookie1 : cookies) {
                if (productCart.equals(cookie1.getName())) {
                    cookie = cookie1;
                    break;
                }
            }
        }
        int i = 10 / 0;
        if (cookie == null) {
            cookie = new Cookie(productCart, URLEncoder.encode(JsonUtil.objectToString(new HashMap<Integer, CartItem>()), enc));
            cookie.setPath("/product");
        }
        String value = cookie.getValue();
        Map<Integer, CartItem> hashMap = JsonUtil.stringToMap(URLDecoder.decode(value, enc), Integer.class, CartItem.class);
        CartItem item = hashMap.get(productId);
        if (item != null) {
            cartItem.setCount(cartItem.getCount() + item.getCount());
        }
        hashMap.put(productId, cartItem);
        cookie.setValue(URLEncoder.encode(JsonUtil.objectToString(hashMap), enc));
        response.addCookie(cookie);
        return "200";
    }

    /**
     * 购物车商品列表
     */
    @RequestMapping("/cartList")
    public String cartList(HttpServletRequest request) throws UnsupportedEncodingException {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            Cookie cookie = null;
            for (Cookie cookie1 : cookies) {
                if (productCart.equals(cookie1.getName())) {
                    cookie = cookie1;
                }
            }
            if (cookie != null) {
                String value = cookie.getValue();
                Map<Integer, CartItem> itemMap = JsonUtil.stringToMap(URLDecoder.decode(value, enc), Integer.class, CartItem.class);
                if (itemMap != null && !itemMap.isEmpty()) {
                    System.out.println("购物车列表如下:");
                    for (CartItem cartItem : itemMap.values()) {
                        System.out.println(cartItem);
                    }
                    return "200";
                }
            }
        }
        System.out.println("无数据");
        return "200";
    }

}
