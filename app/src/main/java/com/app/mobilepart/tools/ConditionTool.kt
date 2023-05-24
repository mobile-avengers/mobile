package com.app.mobilepart.tools

import com.app.mobilepart.R
import com.app.mobilepart.enums.OrderConditions
import com.app.mobilepart.model.OrderModel

class ConditionTool {

    public fun getFormatDate(order: OrderModel): String {
        when (order.condition) {
            OrderConditions.NEW.status -> {
                return "Создан: ${order.createDate.substring(0,10)}"
            }
            OrderConditions.CANCELED.status -> {
                return "Отклонен: ${order.createDate.substring(0,10)}"
            }
            OrderConditions.PAYED.status -> {
                return "Оплачен: ${order.createDate.substring(0,10)}"
            }
            OrderConditions.CONFIRMED.status -> {
                return "Одобрен: ${order.createDate.substring(0,10)}"
            }
            OrderConditions.BOUGHT_IN_SHOP.status -> {
                return "Выкуплен: ${order.createDate.substring(0,10)}"
            }
            OrderConditions.SENT.status -> {
                return "Отправлен: ${order.createDate.substring(0,10)}"
            }
            OrderConditions.IN_DELIVERY.status -> {
                return "В доставке: ${order.createDate.substring(0,10)}"
            }
            OrderConditions.CLIENT_RECEIVED.status -> {
                return "Получен: ${order.createDate.substring(0,10)}"
            }
            else -> {
                return "Чиним состояния";
            }
        }
    }

    public fun getImage(order: OrderModel): Int {
        when (order.condition) {
            OrderConditions.NEW.status -> {
                return R.drawable.news
            }
            OrderConditions.CANCELED.status -> {
                return R.drawable.canceled
            }
            OrderConditions.PAYED.status -> {
                return R.drawable.payed
            }
            OrderConditions.CONFIRMED.status -> {
                return R.drawable.confirmed
            }
            OrderConditions.BOUGHT_IN_SHOP.status -> {
                return R.drawable.bought
            }
            OrderConditions.SENT.status -> {
                return R.drawable.sent
            }
            OrderConditions.IN_DELIVERY.status -> {
                return R.drawable.delivery
            }
            OrderConditions.CLIENT_RECEIVED.status -> {
                return R.drawable.received
            }
            else -> {
                return R.drawable.unknow
            }
        }
    }
}