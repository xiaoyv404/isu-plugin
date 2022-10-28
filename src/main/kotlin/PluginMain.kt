package top.xiaoyv404.isu

import net.mamoe.mirai.console.plugin.jvm.*
import net.mamoe.mirai.utils.*
import top.xiaoyv404.isu.app.*
import java.util.*


object PluginMain : KotlinPlugin(
    JvmPluginDescription(
        id = "top.xiaoyv404.isu",
        name = "监工插件",
        version = "0.1.2"
    )
) {
    override fun onEnable() {
        PluginData.reload()
        PluginConfig.reload()
        report()
        listener()
        timer.schedule(task, Date(), 60000)
        logger.info("管理员列表: ${PluginConfig.base.adminID}")
        logger.info { "监工插件已加载" }
    }

    override fun onDisable() {
        timer.cancel()
        PluginData.save()
    }
}
