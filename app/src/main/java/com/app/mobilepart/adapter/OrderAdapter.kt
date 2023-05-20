package com.app.mobilepart.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.mobilepart.R
import com.app.mobilepart.databinding.OrderItemBinding.*
import com.app.mobilepart.model.OrderModel

class OrderAdapter: RecyclerView.Adapter<OrderAdapter.OrderHolder>() {

    private val orderus = OrderModel(1337, "OK", "9.05.2023",899.9f)
    private val orderusus = OrderModel(1337, "ZALUPA", "9.05.2023",899.9f)

    private val orderList: ArrayList<OrderModel> = arrayListOf(orderus, orderusus, orderus, orderusus)

    class OrderHolder(item: View): RecyclerView.ViewHolder(item) {
        private val binding = bind(item)

        fun bind(order: OrderModel) = with(binding) {
            orderId.text = "ID:${order.id.toString()}"
            orderCost.text = "Стоимость: ${order.cost.toString()} ₽"
            if (order.status == "OK") {
                orderPhoto.setImageResource(R.drawable.ok)
                orderDate.text = "Одобрено: ${order.date}"
            } else {
                orderPhoto.setImageResource(R.drawable.not_ok)
                orderDate.text = "Отклонено: ${order.date}"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.order_item, parent, false)
        return OrderHolder(view)
    }

    override fun onBindViewHolder(holder: OrderHolder, position: Int) {
        holder.bind(orderList[position])
    }

    override fun getItemCount(): Int {
        return orderList.size
    }


}