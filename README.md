# product-cart
cookie实现的购物车<br>
分别有添加和列表两个功能<br>
添加:<br>
从页面接收数据,把数据存储在Map中(Map<productId, data>)序列化为json字符串并进行url encoder添加到cookie中<br>
如果该商品在已存在,就累加数量,并把商品的最新信息保存起来<br>
列表:<br>
从cookie中取出Map, 遍历Map中所有的值<br>

