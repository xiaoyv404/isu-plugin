package top.xiaoyv404.isu.app

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import net.mamoe.mirai.contact.*
import net.mamoe.mirai.event.*
import net.mamoe.mirai.event.events.*
import top.xiaoyv404.isu.*
import java.text.*
import java.util.*
import javax.naming.Name

fun listener() {
    GlobalEventChannel.subscribeGroupMessages {
        case("~公司完整员工表") {
            val regex = Regex("(.+)，出勤率.+，今日(未)?出勤 参考效益：")
            val dataMsg = withTimeoutOrNull(20000) {
                GlobalEventChannel.asFlow()
                    .filterIsInstance<GroupMessageEvent>()
                    .map { it.message }.first()
            }
            if (dataMsg == null) {
                group.sendMessage("获取数据超时")
                return@case
            }
            val groupList = mutableMapOf<String, NormalMember>()
            group.members.forEach {
                groupList[it.nameCard] = it
            }
            regex.findAll(dataMsg.contentToString()).forEach {
                val name = it.groups[1]!!.value
                val member = groupList[name]
                val status = it.groups[2] == null
                if (member == null) {
                    PluginMain.logger.warning("$name 不在此群")
                    return@forEach
                } else
                    PluginMain.logger.info("设置$name(${member.id}) 状态为 $status")
                PluginData.memberList[member] = status
            }
        }
    }
}


val task = object : TimerTask() {
    override fun run() {
        PluginMain.launch {
            val sdf = SimpleDateFormat("HH:mm")
            val time = sdf.format(Date())
            if (time == "00:00") {
                PluginMain.logger.info("清除所有考勤信息")
                PluginData.memberList.forEach { (k, _) ->
                    PluginData.memberList[k] = false
                }
            }
        }
    }
}

val timer = Timer()
