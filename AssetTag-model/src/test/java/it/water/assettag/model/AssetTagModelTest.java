package it.water.assettag.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AssetTagModelTest {

    @Test
    void requiredConstructorAndCollections() {
        AssetTag tag = new AssetTag("tag", 100L);

        Assertions.assertEquals("tag", tag.getName());
        Assertions.assertEquals(100L, tag.getOwnerUserId());
        Assertions.assertNotNull(tag.getResources());
        Assertions.assertTrue(tag.getResources().isEmpty());
    }
}
