package com.vanniktech.androidlintcustomrules

import com.android.tools.lint.checks.infrastructure.TestFiles.java
import com.android.tools.lint.checks.infrastructure.TestFiles.kt
import com.android.tools.lint.checks.infrastructure.TestLintTask.lint
import org.junit.Test

class NoInternalImportRuleTest {
  @Test fun noInternalImports() {
    lint()
        .files(kt("""
            import a.b.c
            import a.internal.foo
            """).indented())
        .issues(NoInternalImportDetector.ISSUE)
        .run()
        .expect("""
            |src/test.kt:2: Warning: Importing 'a.internal.foo' which is an internal import. [NoInternalImport]
            |import a.internal.foo
            |       ~~~~~~~~~~~~~~
            |0 errors, 1 warnings""".trimMargin())
  }

  @Test fun noInternalImportsJava() {
    lint()
        .files(java("""
            import a.b.c;
            import a.internal.foo;
            """).indented())
        .issues(NoInternalImportDetector.ISSUE)
        .run()
        .expect("""
            |src/package-info.java:2: Warning: Importing 'a.internal.foo' which is an internal import. [NoInternalImport]
            |import a.internal.foo;
            |       ~~~~~~~~~~~~~~
            |0 errors, 1 warnings""".trimMargin())
  }
}
