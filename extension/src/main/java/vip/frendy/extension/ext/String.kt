package vip.frendy.extension.ext

import java.util.regex.Pattern

/**
 * Created by frendy on 2017/8/11.
 */

/**
 * 检测是否有emoji表情
 */
fun String.containEmoji(): Boolean {
    val len = this.length
    for (i in 0..len - 1) {
        val codePoint = this[i]
        if (!isEmojiCharacter(codePoint)) { //如果不能匹配,则该字符是Emoji表情
            return true
        }
    }
    return false
}

/**
 * 判断是否是Emoji
 */
private fun isEmojiCharacter(codePoint: Char): Boolean {
    return codePoint.toInt() == 0x0 || codePoint.toInt() == 0x9 || codePoint.toInt() == 0xA ||
            codePoint.toInt() == 0xD || codePoint.toInt() >= 0x20 && codePoint.toInt() <= 0xD7FF ||
            codePoint.toInt() >= 0xE000 && codePoint.toInt() <= 0xFFFD || codePoint.toInt() >= 0x10000 && codePoint.toInt() <= 0x10FFFF
}

/**
 * Url 提取一级域名
 */
fun String.getTopDomain(): String {
    val RE_TOP = "[\\w-]+\\.(com.cn|net.cn|gov.cn|org\\.nz|org.cn|com|net|org|gov|cc|biz|info|cn|co)\\b()*"
    val pattern = Pattern.compile(RE_TOP , Pattern.CASE_INSENSITIVE);
    var result = this
    try {
        val matcher = pattern.matcher(this)
        matcher.find()
        result = matcher.group()
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return result
}