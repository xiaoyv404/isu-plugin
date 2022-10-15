package top.xiaoyv404.isu

import kotlinx.serialization.Serializable
import net.mamoe.mirai.console.data.*
import net.mamoe.yamlkt.*

object PluginConfig : AutoSavePluginConfig("ISeeU") {
    val base by value<BaseConfig>()
}

@Serializable
data class BaseConfig(
    @Comment("管理员ID")
    val adminID: List<Long> = listOf()
)