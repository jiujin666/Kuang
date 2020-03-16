package com.example.kuangjia.interfaces.shop;

import com.example.kuangjia.interfaces.IBasePersenter;
import com.example.kuangjia.interfaces.IBaseView;
import com.example.kuangjia.models.bean.AddressBean;
import com.example.kuangjia.models.bean.AdressSaveBean;
import com.example.kuangjia.models.bean.CartBean;
import com.example.kuangjia.models.bean.CartGoodsCheckBean;
import com.example.kuangjia.models.bean.CartGoodsDeleteBean;
import com.example.kuangjia.models.bean.CartGoodsUpdateBean;
import com.example.kuangjia.models.bean.OrderInfoBean;
import com.example.kuangjia.models.bean.RegionBean;

import java.util.Map;

public interface ShoppingConstact {
    interface View extends IBaseView {
        void getCartIndexReturn(CartBean result);
        //设置购物车商品数据选中状态的返回
        void setCartGoodsCheckedReturn(CartGoodsCheckBean result);
        //更新购物车类表商品数据返回
        void updateCartGoodsReturn(CartGoodsUpdateBean result);
        //删除商品返回
        void deleteCartGoodsReturn(CartGoodsDeleteBean result);
    }

    interface Presenter extends IBasePersenter<View> {
        void getCartIndex();
        //设置购物车商品数据
        void setCartGoodsChecked(String pids,int isChecked);
        //更新购物车列表的商品数据
        void updateCartGoods(String pids,String goodsId,int number,int id);
        //删除商品
        void deleteCartGoods(String pids);

    }

    //订单功能
    interface OrderView extends IBaseView{
        void getCartIndexReturn(CartBean result);
        //设置购物车商品数据选中状态的返回
        void getOrderListReturn(OrderInfoBean result);
    }

    interface OrderPresenter extends IBasePersenter<OrderView>{
        void getCartIndex();
        //设置购物车商品数据
        //地址id+优惠券的ID
        void getOrderList(int addressId,int couponId);
    }

    //地址管理
    interface AdressView extends IBaseView{
        void getAdressListReturn(AddressBean result);
    }

    interface AdressPresenter extends IBasePersenter<AdressView>{
        void getAdressList();
    }

    interface AdressNewView extends IBaseView{
        void saveAdressReturn(AdressSaveBean result);

        //获取省市区数据返回
        void getRegionReturn(RegionBean result);
    }

    interface AdressNewPresenter extends IBasePersenter<AdressNewView>{
        void saveAdress(Map map);

        //获取省市区数据
        void getRegion(int parentId);
    }
}
