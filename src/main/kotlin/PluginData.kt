package top.xiaoyv404.isu

import net.mamoe.mirai.console.data.*
import net.mamoe.mirai.contact.*

object  PluginData: AbstractPluginData() {
    override val saveName: String = "ISUDataBase"

    @ValueDescription("成员列表")
    val memberList by value<MutableMap<NormalMember, Boolean>>()
}