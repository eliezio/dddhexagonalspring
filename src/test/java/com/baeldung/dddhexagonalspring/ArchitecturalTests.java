package com.baeldung.dddhexagonalspring;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.Architectures.onionArchitecture;

@AnalyzeClasses(
        packages = "com.baeldung.dddhexagonalspring",
        importOptions = { ImportOption.DoNotIncludeTests.class }
)
public class ArchitecturalTests {

    @ArchTest
    static final ArchRule shouldFollowOnionArchitecture = onionArchitecture()
            .domainModels("..domain..")
            .applicationServices("..application..")
            .adapter("config", "..infrastracture.configuration..")
            .adapter("cassandra", "..infrastracture.repository.cassandra..")
            .adapter("mongo", "..infrastracture.repository.mongo..")
            .withOptionalLayers(true);

}
