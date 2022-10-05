package top.xiaoyv404.isu

import net.mamoe.mirai.console.data.*

object  PluginData: AbstractPluginData() {
    override val saveName: String = "ISUDataBase"

    @ValueDescription("成员列表")
    val memberList by value<MutableMap<String, Boolean>>()
}