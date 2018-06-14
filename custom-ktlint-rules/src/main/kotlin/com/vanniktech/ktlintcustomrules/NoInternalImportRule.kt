package com.vanniktech.ktlintcustomrules

import com.github.shyiko.ktlint.core.Rule
import org.jetbrains.kotlin.com.intellij.lang.ASTNode
import org.jetbrains.kotlin.psi.KtImportDirective
import org.jetbrains.kotlin.psi.stubs.elements.KtStubElementTypes

class NoInternalImportRule : Rule("no-internal-import") {
  override fun visit(node: ASTNode, autoCorrect: Boolean,
    emit: (offset: Int, errorMessage: String, canBeAutoCorrected: Boolean) -> Unit) {
    if (node.elementType == KtStubElementTypes.IMPORT_DIRECTIVE) {
      val importDirective = node.psi as KtImportDirective
      val path = importDirective.importPath?.pathStr
      if (path != null && path.contains("internal")) {
        emit(node.startOffset, "Importing from an internal package", false)
      }
    }
  }
}
