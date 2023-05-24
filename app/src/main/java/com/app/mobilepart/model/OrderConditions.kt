package com.app.mobilepart.model

enum class OrderConditions(val status: String) {
    NEW("NEW"),
    CANCELED("CANCELED"),
    PAYED("PAYED"),
    CONFIRMED("CONFIRMED"),
    BOUGHT_IN_SHOP("BOUGHT_IN_SHOP"), // выкуплен
    SENT("SENT"), // отправлен
    IN_DELIVERY("IN_DELIVERY"),
    CLIENT_RECEIVED("CLIENT_RECEIVED"),
}