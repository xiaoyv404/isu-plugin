package top.xiaoyv404.isu.app

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import net.mamoe.mirai.event.*
import net.mamoe.mirai.event.events.*
import top.xiaoyv404.isu.*
import top.xiaoyv404.isu.PluginMain.reload
import top.xiaoyv404.isu.PluginMain.save
import java.text.*
import java.util.*

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

            PluginData.memberList.clear()

            regex.findAll(dataMsg.contentToString()).forEach {
                val name = it.groups[1]!!.value
                val status = it.groups[2] == null
                PluginMain.logger.info("设置$name 状态为 $status")
                PluginData.memberList[name] = status
            }
            PluginData.save()
        }
        case("isu reload"){
            PluginConfig.reload()
            subject.sendMessage("Reload successful")
        }
    }
}


val task = object : TimerTask() {
    override fun run() {
        PluginMain.launch {
            val sdf = SimpleDateFormat("HH:mm")
            val time = sdf.format(Date())
            if (time == PluginConfig.base.flashTime) {
                PluginMain.logger.info("清除所有考勤信息")
                PluginData.memberList.forEach { (k, _) ->
                    PluginData.memberList[k] = false
                }
                PluginData.save()
            }
        }
    }
}

val timer = Timer()
