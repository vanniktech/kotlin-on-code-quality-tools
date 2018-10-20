package com.vanniktech.ktlintcustomrules

import com.github.shyiko.ktlint.core.LintError
import com.github.shyiko.ktlint.test.lint
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class NoInternalImportRuleTest {
  @Test fun noInternalImports() {
    assertThat(NoInternalImportRule().lint("""
        import a.b.c
        import a.internal.foo
        """.trimIndent()
    )).containsExactly(
        LintError(2, 1, "no-internal-import", "Importing 'a.internal.foo' which is an internal import.")
    )
  }
}
