package top.xiaoyv404.isu

import net.mamoe.mirai.console.data.*
import top.xiaoyv404.isu.data.*

object  PluginData: AbstractPluginData() {
    override val saveName: String = "ISUDataBase"

    @ValueDescription("成员列表")
    val memberList by value<MutableMap<String, Boolean>>()

    @ValueDescription("APP数据")
    val appData by value<MutableMap<String, AppData>>()
}