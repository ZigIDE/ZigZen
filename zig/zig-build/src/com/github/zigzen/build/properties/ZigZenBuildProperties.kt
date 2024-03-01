// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.build.properties

import com.github.zigzen.build.plugins.ZigIdePluginLayout
import com.github.zigzen.build.plugins.ZigLanguagePluginLayout
import com.github.zigzen.build.plugins.ZonLanguagePluginLayout
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
    additionalVmOptions += "-Dide.show.tips.on.startup.default.value=false"
    applicationInfoModule = "com.github.zigzen.resources"
    brandingResourcePaths = listOf(home.resolve("resources"))
    buildSourcesArchive = true
    buildCrossPlatformDistribution = true
    customJvmMemoryOptions = persistentMapOf("-Xms" to "256m", "-Xmx" to "1500m")
    embeddedJetBrainsClientMainModule = null
    platformPrefix = "Zig"
    productLayout.bundledPluginModules.addAll(listOf(
      "com.github.zigzen.ide",
      "com.github.zigzen.zig",
      "com.github.zigzen.zon",
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
    productLayout.mainModules = listOf("com.github.zigzen.main")
    productLayout.productImplementationModules = listOf(
      "com.github.zigzen.resources",
      "intellij.platform.main",
    )
    reassignAltClickToMultipleCarets = true
    useSplash = true
    scrambleMainJar = false
  }

  override val baseFileName: String
    get() = "zigzen"

  override fun getBaseArtifactName(appInfo: ApplicationInfoProperties, buildNumber: String): String =
    "ZZ-$buildNumber"

  override fun createWindowsCustomizer(projectHome: String): WindowsDistributionCustomizer
    = ZigZenWindowsDistributionCustomer()

  override fun createLinuxCustomizer(projectHome: String): LinuxDistributionCustomizer? = null

  override fun createMacCustomizer(projectHome: String): MacDistributionCustomizer? = null

  override fun getSystemSelector(appInfo: ApplicationInfoProperties, buildNumber: String): String {
    return "ZZ${appInfo.majorVersion}.${appInfo.minorVersionMainPart}"
  }

  inner class ZigZenWindowsDistributionCustomer : WindowsDistributionCustomizer() {
    init {
      fileAssociations = listOf("zig", "zon")
      icoPath = "${home}/zig/zig-build/conf/win/images/zigzen.ico"
      icoPathForEAP = "${home}/zig/zig-build/conf/win/images/zigzen-eap.ico"
      installerImagesPath = "${home}/zig/zig-build/conf/win/images"
    }
  }
}
