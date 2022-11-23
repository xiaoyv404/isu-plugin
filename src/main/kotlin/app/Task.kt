package top.xiaoyv404.isu.app

import net.mamoe.mirai.event.*
import top.xiaoyv404.isu.*

interface Task {
    val name: String
    val dataSender: Long
    val regex: Regex

    fun listener(){
        GlobalEventChannel.subscribeGroupMessages {
            finding(regex){
                val data = regex.findAll(this.message.contentToString())
                PluginData.appData[name]
            }
        }
    }

    fun reporter(){

    }
}