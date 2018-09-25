package vladyslav.lubenets.data;

import android.content.Context;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import vladyslav.lubenets.data.executor.BackgroundExecutor;
import vladyslav.lubenets.data.repository.datasource.ImageDataStore;
import vladyslav.lubenets.data.repository.datasource.LocalImageDataStore;

/**
 * Created by Lubenets Vladyslav on 9/25/18.
 */
public class ImageDataStoreTest {


    private ImageDataStore localImageDataStore;

    @Mock
    private Context context;

    @Mock
    private BackgroundExecutor backgroundExecutor;

    @Before
    public void setUp() {
        localImageDataStore = new LocalImageDataStore(context, backgroundExecutor);
    }

    @Test
    public void testFileName() {
        long currentTimeMillis = System.currentTimeMillis();
        String expectedImageName = currentTimeMillis + ".jpg";
        String actualImageName = localImageDataStore.provideImageName(currentTimeMillis);
        Assert.assertEquals(expectedImageName, actualImageName);
    }
}