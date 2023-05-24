package com.app.mobilepart.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.app.mobilepart.AdminMenu
import com.app.mobilepart.OrderLots
import com.app.mobilepart.R
import com.app.mobilepart.databinding.OrderItemBinding.*
import com.app.mobilepart.model.OrderModel
import com.app.mobilepart.tools.ConditionTool

class AdminOrderAdapter(): RecyclerView.Adapter<AdminOrderAdapter.OrderHolder>() {

    private val orderList: ArrayList<OrderModel> = arrayListOf()


    class OrderHolder(item: View): RecyclerView.ViewHolder(item) {
        private val binding = bind(item)
        private val tool: ConditionTool = ConditionTool()

        fun bind(order: OrderModel) = with(binding) {
            orderId.text = "ID:${order.id.toString()}"
            orderCost.text = "Стоимость: ${order.cost.toString()} ₽"
            orderPhoto.setImageResource(tool.getImage(order))
            orderDate.text = tool.getFormatDate(order)

            itemView.setOnClickListener {
                Toast.makeText(it.context, "Нажали на заказ: ${order.id}", Toast.LENGTH_SHORT).show()

                val orderLotsIntent = Intent(it.context, AdminMenu::class.java)
                orderLotsIntent.putExtra("order_id", order.id)
                startActivity(it.context,orderLotsIntent, null)
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

    fun refresh(list: List<OrderModel>) {
        orderList.clear()
        orderList.addAll(list)
        notifyDataSetChanged()
    }

}