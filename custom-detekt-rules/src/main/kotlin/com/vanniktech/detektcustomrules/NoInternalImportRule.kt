package com.vanniktech.detektcustomrules

import io.gitlab.arturbosch.detekt.api.CodeSmell
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity
import org.jetbrains.kotlin.psi.KtImportDirective

class NoInternalImportRule(config: Config = Config.empty) : Rule(config) {
  override val issue = Issue(javaClass.simpleName, Severity.Style,
      "Don't import packages from an internal package as they are subject to change.",
      Debt.TWENTY_MINS)

  override fun visitImportDirective(importDirective: KtImportDirective) {
    val import = importDirective.importPath?.pathStr

    if (import?.contains("internal") == true) {
      report(CodeSmell(issue, Entity.from(importDirective),
          "Importing '$import' which is an internal import."))
    }
  }
}
