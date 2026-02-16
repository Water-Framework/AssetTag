package it.water.assettag.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class WaterAssetTagResourceTest {

    @Test
    void constructorAndTagId() {
        AssetTag tag = Mockito.mock(AssetTag.class);
        Mockito.when(tag.getId()).thenReturn(42L);

        WaterAssetTagResource resource = new WaterAssetTagResource("resource", 7L, tag);

        Assertions.assertEquals("resource", resource.getResourceName());
        Assertions.assertEquals(7L, resource.getResourceId());
        Assertions.assertSame(tag, resource.getTag());
        Assertions.assertEquals(42L, resource.getTagId());
    }

    @Test
    void tagIdIsZeroWhenTagIsNull() {
        WaterAssetTagResource resource = new WaterAssetTagResource("resource", 7L, null);
        Assertions.assertEquals(0L, resource.getTagId());
    }
}
