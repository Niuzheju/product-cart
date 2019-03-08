# product-cart
cookie实现的购物车
分别有添加和列表两个功能
添加:
从页面接收数据,把数据存储在Map中(Map<productId, data>)序列化为json字符串并进行url encoder添加到cookie中
如果该商品在已存在,就累加数量,并把商品的最新信息保存起来
列表:
从cookie中取出Map, 遍历Map中所有的值

