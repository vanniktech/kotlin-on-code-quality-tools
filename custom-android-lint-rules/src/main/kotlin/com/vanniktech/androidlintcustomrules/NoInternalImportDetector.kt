package com.vanniktech.androidlintcustomrules

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.JavaContext
import com.android.tools.lint.detector.api.Scope.JAVA_FILE
import com.android.tools.lint.detector.api.Scope.TEST_SOURCES
import com.android.tools.lint.detector.api.Severity.WARNING
import org.jetbrains.uast.UImportStatement
import java.util.EnumSet

class NoInternalImportDetector : Detector(), Detector.UastScanner {
  override fun getApplicableUastTypes() = listOf(UImportStatement::class.java)

  override fun createUastHandler(context: JavaContext): UElementHandler = NoInternalImportHandler(context)

  internal class NoInternalImportHandler(private val context: JavaContext) : UElementHandler() {
    override fun visitImportStatement(node: UImportStatement) {
      val importReference = node.importReference
      val import = importReference?.asSourceString()

      if (import?.contains("internal") == true) {
        context.report(ISSUE, node, context.getLocation(importReference), "Importing '$import' which is an internal import.")
      }
    }
  }

  companion object {
    val ISSUE = Issue.create("NoInternalImport",
        "No internal imports",
        "Don't import from an internal package as they are subject to change.",
        Category.CORRECTNESS, 10, WARNING,
        Implementation(NoInternalImportDetector::class.java, EnumSet.of(JAVA_FILE, TEST_SOURCES))
    )
  }
}
