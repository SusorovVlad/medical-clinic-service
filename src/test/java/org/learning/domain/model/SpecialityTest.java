package org.learning.domain.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Test;

import java.math.BigDecimal;

public class SpecialityTest {

    @Test
    public void equalsContract() {

        EqualsVerifier.forClass(Speciality.class)
                      .suppress(Warning.ALL_FIELDS_SHOULD_BE_USED)
                      .verify();
    }
}
