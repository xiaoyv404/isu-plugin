package top.xiaoyv404.isu.app

import net.mamoe.mirai.event.*
import top.xiaoyv404.isu.*


fun report() {
    GlobalEventChannel.subscribeUserMessages {
        case("~今日考勤") {
            if (PluginConfig.base.adminID.find { it == sender.id } == null)
                return@case

            if (PluginData.memberList.isEmpty()) {
                subject.sendMessage("唔，没有数据哦")
                return@case
            }

            val msg = StringBuilder()
            msg.append("今日未出勤的猫猫有：")
            var status = true // 是否都出勤
            PluginData.memberList.forEach { (k, v) ->
                if (!v) {
                    status = false
                    msg.append("$k,  ")
                }
            }
            subject.sendMessage(
                if (status)
                    "猫猫今日已全部出勤"
                else
                    msg.toString()
            )
        }
    }
}