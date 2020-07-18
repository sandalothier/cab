package com.cab.tva;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.cab.tva");

        noClasses()
            .that()
            .resideInAnyPackage("com.cab.tva.service..")
            .or()
            .resideInAnyPackage("com.cab.tva.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.cab.tva.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
