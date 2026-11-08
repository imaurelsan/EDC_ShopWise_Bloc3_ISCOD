// Test ajouté pour remonter la couverture, à adapter aux vraies classes
package fr.shopwise.api.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ResourceMissingExceptionTest {

    @Test
    void leMessageEstConserve() {
        ResourceMissingException e = new ResourceMissingException("Client introuvable");
        assertEquals("Client introuvable", e.getMessage());
    }
}