// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.build.properties

import zigzen.build.plugins.ZigIdePluginLayout
import zigzen.build.plugins.ZigLanguagePluginLayout
import zigzen.build.plugins.ZonLanguagePluginLayout
import kotlinx.collections.immutable.persistentMapOf
import kotlinx.collections.immutable.plus
import org.jetbrains.intellij.build.ApplicationInfoProperties
import org.jetbrains.intellij.build.CommunityRepositoryModules
import org.jetbrains.intellij.build.JetBrainsProductProperties
import org.jetbrains.intellij.build.LinuxDistributionCustomizer
import org.jetbrains.intellij.build.MacDistributionCustomizer
import org.jetbrains.intellij.build.WindowsDistributionCustomizer
import java.nio.file.Path

class ZigZenBuildProperties(private val home: Path) : JetBrainsProductProperties() {
  init {
    platformPrefix = "Zig"
    applicationInfoModule = "zigzen.resources"
    scrambleMainJar = false
    useSplash = true
    buildCrossPlatformDistribution = true

    productLayout.productImplementationModules = listOf(
      "zigzen.resources",
      "intellij.platform.starter",
    )
    productLayout.bundledPluginModules.addAll(listOf(
      "zigzen.ide",
      "zigzen.zig",
      "zigzen.zon",
      "intellij.vcs.git",
      "intellij.vcs.github.community",
      "intellij.yaml",
    ))
    productLayout.pluginLayouts = CommunityRepositoryModules.COMMUNITY_REPOSITORY_PLUGINS.addAll(listOf(
      CommunityRepositoryModules.githubPlugin("intellij.vcs.github.community"),
      ZigIdePluginLayout.zigIdePlugin(),
      ZigLanguagePluginLayout.zigLanguagePlugin(),
      ZonLanguagePluginLayout.zonLanguagePlugin(),
    ))

    additionalVmOptions += "-Dide.show.tips.on.startup.default.value=false"
  }

  override val baseFileName: String
    get() = "zigzen"

  override fun getBaseArtifactName(appInfo: ApplicationInfoProperties, buildNumber: String): String =
    "ZZ-$buildNumber"

  override fun createWindowsCustomizer(projectHome: String): WindowsDistributionCustomizer
    = ZigZenWindowsDistributionCustomizer()

  override fun createLinuxCustomizer(projectHome: String): LinuxDistributionCustomizer = ZigZenLinuxDistributionCustomizer()

  override fun createMacCustomizer(projectHome: String): MacDistributionCustomizer? = null

  override fun getSystemSelector(appInfo: ApplicationInfoProperties, buildNumber: String): String {
    return "ZZ${appInfo.majorVersion}.${appInfo.minorVersionMainPart}"
  }

  inner class ZigZenLinuxDistributionCustomizer : LinuxDistributionCustomizer() {
    init {
      iconPngPath = "${home}/zig/zig-build/conf/linux/images/zigzen.png"
      iconPngPathForEAP = "${home}/zig/zig-build/conf/linux/images/zigzen-eap.png"
    }
  }

  inner class ZigZenWindowsDistributionCustomizer : WindowsDistributionCustomizer() {
    init {
      fileAssociations = listOf("zig", "zon")
      icoPath = "${home}/zig/zig-build/conf/win/images/zigzen.ico"
      icoPathForEAP = "${home}/zig/zig-build/conf/win/images/zigzen-eap.ico"
      installerImagesPath = "${home}/zig/zig-build/conf/win/images"
    }
  }
}
