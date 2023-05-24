package com.app.mobilepart

import com.app.mobilepart.tools.CreateHelper
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Test

class CreateHelperTest {
    private var createHelper = CreateHelper()

    @Test
    fun commonLink() {
        val link = "https://dw4.co/t/A/1F1MGHWP"
        assertTrue(createHelper.checkLink(link))
    }

    @Test
    fun spammedLink() {
        val link = "【得物】得物er-R5M5W3C0发现一件好物， 2BDH œLvjVlOKœ  https://dw4.co/t/A/1F1MGHWP Apple iPhone 14 大陆国行 双卡双待 5G 手机 星光色,20万+人想要\n点击链接直接打开"
        assertTrue(createHelper.checkLink(link))
    }

    @Test
    fun shortenLink() {
        val link = "dw4.co/t/A/1F1JbtVp"
        assertTrue(createHelper.checkLink(link))
    }

    @Test
    fun hasNoLinks() {
        val link = "Нет ссылок на сайт dw4.co"
        assertFalse(createHelper.checkLink(link))
    }

    @Test
    fun manyLinks() {
        val link =
            "https://dw4.co/t/A/1F1JbtVp и https://dw4.co/t/A/2R2XYZAB две ссылки на сайт dw4.co"
        assertFalse(createHelper.checkLink(link))
    }
}