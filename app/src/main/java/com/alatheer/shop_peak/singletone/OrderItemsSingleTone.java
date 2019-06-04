package com.alatheer.shop_peak.singletone;

import com.alatheer.shop_peak.Model.OrderItemList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by M.Hamada on 02/06/2019.
 */
/*
public class OrderItemsSingleTone {
    private static OrderItemsSingleTone instance = null;
    private List<OrderItemList> orderItemList = new ArrayList<>();

    private OrderItemsSingleTone(){}

    public static synchronized OrderItemsSingleTone newInstance()
    {
        if (instance == null)
        {
            instance = new OrderItemsSingleTone();
        }
        return instance;
    }

    public void AddProduct(OrderItemList orderItem)
    {
        int pos = getItemPosition(orderItem);
        if (pos !=-1)
        {
            OrderItemList item = orderItemList.get(pos);

            orderItemList.set(pos,item);

        }else
        {

            orderItemList.add(orderItem);

        }
    }

    public void UpdateProduct(OrderItemList orderItem)
    {
        int pos = getItemPosition(orderItem);

//        AlternativeProductItem alternativeProductItem = orderItem.getAlternativeProductItem();
//        alternativeProductItem.setProduct_quantity(orderItem.getProduct_quantity());
//        orderItem.setAlternativeProductItem(alternativeProductItem);
        orderItemList.set(pos,orderItem);
    }

    public void RemoveProduct (OrderItemList orderItem)
    {
        int pos = getItemPosition(orderItem);
        orderItemList.remove(pos);
        if (orderItemList.size()==0)
        {
            ClearCart();
        }
    }

    private int getItemPosition(productitemModel orderItem)
    {
        int pos = -1;

        for (int i = 0 ; i< orderItemList.size() ; i++)
        {

            OrderItemList item = orderItemList.get(i);

            if (item.getId()==orderItem.getId())
            {

                pos = i;
                break;
            }
        }

        return pos;
    }


    public List<OrderItemList> getOrderItemList() {
        return orderItemList;
    }

    public int getItemsCount ()
    {
        return orderItemList.size();
    }

    public void ClearCart()
    {
        orderItemList.clear();
    }
}
*/