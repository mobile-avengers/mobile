package com.app.mobilepart.tools

import com.app.mobilepart.R
import com.app.mobilepart.model.OrderModel

class ConditionTool {

    public fun getFormatDate(order: OrderModel): String {
        when (order.condition) {
            "new" -> {
                return "Создан: ${order.createDate.substring(0,10)}"
            }
            "canceled" -> {
                return "Отклонен: ${order.createDate.substring(0,10)}"
            }
            "payed" -> {
                return "Оплачен: ${order.createDate.substring(0,10)}"
            }
            "confirmed" -> {
                return "Одобрен: ${order.createDate.substring(0,10)}"
            }
            "boughtInShop" -> {
                return "Выкуплен: ${order.createDate.substring(0,10)}"
            }
            "sent" -> {
                return "Отправлен: ${order.createDate.substring(0,10)}"
            }
            "inDlivery" -> {
                return "В доставке: ${order.createDate.substring(0,10)}"
            }
            "clientReceived" -> {
                return "Получен: ${order.createDate.substring(0,10)}"
            }
            else -> {
                return "Чиним состояния";
            }
        }
    }

    public fun getImage(order: OrderModel): Int {
        when (order.condition) {
            "new" -> {
                return R.drawable.news
            }
            "canceled" -> {
                return R.drawable.canceled
            }
            "payed" -> {
                return R.drawable.payed
            }
            "confirmed" -> {
                return R.drawable.confirmed
            }
            "boughtInShop" -> {
                return R.drawable.bought
            }
            "sent" -> {
                return R.drawable.sent
            }
            "inDlivery" -> {
                return R.drawable.delivery
            }
            "clientReceived" -> {
                return R.drawable.received
            }
            else -> {
                return R.drawable.unknow
            }
        }
    }
}