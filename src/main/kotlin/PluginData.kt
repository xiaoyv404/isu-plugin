package top.xiaoyv404.isu

import net.mamoe.mirai.console.data.*

object  PluginData: AbstractPluginData() {
    override val saveName: String = "ISUDataBase"

    @ValueDescription("ๆๅๅ่กจ")
    val memberList by value<MutableMap<String, Boolean>>()
}