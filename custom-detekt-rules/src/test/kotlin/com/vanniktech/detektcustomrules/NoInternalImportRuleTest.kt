package com.vanniktech.detektcustomrules

import io.gitlab.arturbosch.detekt.test.lint
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class NoInternalImportRuleTest {
  @Test fun noInternalImports() {
    val findings = NoInternalImportRule().lint("""
        import a.b.c
        import a.internal.foo
        """.trimIndent())

    assertThat(findings).hasSize(1)
    assertThat(findings[0].message).isEqualTo("Importing 'a.internal.foo' which is an internal import.")
  }
}
