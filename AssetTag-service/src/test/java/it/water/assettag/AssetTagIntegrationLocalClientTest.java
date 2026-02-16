package it.water.assettag;

import it.water.assettag.service.integration.AssetTagIntegrationLocalClient;
import it.water.core.api.asset.AssetTagManager;
import it.water.core.api.model.AssetTagResource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class AssetTagIntegrationLocalClientTest {

    @Test
    void nullManagerBranches() {
        AssetTagIntegrationLocalClient client = new AssetTagIntegrationLocalClient();

        Assertions.assertNull(client.findAssetTagResource("res", 1L, 2L));
        Assertions.assertArrayEquals(new long[0], client.findAssetTags("res", 1L));

        Assertions.assertDoesNotThrow(() -> client.addAssetTag("res", 1L, 2L));
        Assertions.assertDoesNotThrow(() -> client.addAssetTags("res", 1L, new long[]{2L, 3L}));
        Assertions.assertDoesNotThrow(() -> client.removeAssetTag("res", 1L, 2L));
        Assertions.assertDoesNotThrow(() -> client.removeAssetTags("res", 1L, new long[]{2L, 3L}));
    }

    @Test
    void delegateCallsWhenManagerPresent() {
        AssetTagIntegrationLocalClient client = new AssetTagIntegrationLocalClient();
        AssetTagManager manager = Mockito.mock(AssetTagManager.class);
        AssetTagResource expected = Mockito.mock(AssetTagResource.class);
        long[] expectedIds = new long[]{10L, 11L};

        Mockito.when(manager.findAssetTagResource("res", 1L, 2L)).thenReturn(expected);
        Mockito.when(manager.findAssetTags("res", 1L)).thenReturn(expectedIds);

        client.setAssetTagManager(manager);

        Assertions.assertSame(expected, client.findAssetTagResource("res", 1L, 2L));
        Assertions.assertArrayEquals(expectedIds, client.findAssetTags("res", 1L));

        client.addAssetTag("res", 1L, 2L);
        client.addAssetTags("res", 1L, new long[]{2L, 3L});
        client.removeAssetTag("res", 1L, 2L);
        client.removeAssetTags("res", 1L, new long[]{2L, 3L});

        Mockito.verify(manager).findAssetTagResource("res", 1L, 2L);
        Mockito.verify(manager).findAssetTags("res", 1L);
        Mockito.verify(manager).addAssetTag("res", 1L, 2L);
        Mockito.verify(manager).addAssetTags(Mockito.eq("res"), Mockito.eq(1L), Mockito.any(long[].class));
        Mockito.verify(manager).removeAssetTag("res", 1L, 2L);
        Mockito.verify(manager).removeAssetTags(Mockito.eq("res"), Mockito.eq(1L), Mockito.any(long[].class));
    }
}
