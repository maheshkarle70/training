package com.training.core.models;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static junitx.framework.Assert.assertEquals;
import static junitx.framework.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HelloIndiaModelTest {

    @Mock
    private Resource currentResource;

    @Mock
    private ResourceResolver resourceResolver;

    @Mock
    private Page currentPage;

    @Mock
    private PageManager pageManager;

    @InjectMocks
    private HelloIndiaModel helloIndiaModel;

    //initialize
    @Before
    public void setUp() {

        when(resourceResolver.adaptTo(PageManager.class)).thenReturn(pageManager);
        when(pageManager.getContainingPage(currentResource)).thenReturn(currentPage);
        when(currentPage.getPath()).thenReturn("/content/geometrixx");

        helloIndiaModel.init();

    }

    @Test
    public void shouldNotMatch(){
        assertNotEquals("Hiiiii",helloIndiaModel.getMessage());
    }

    @Test
    public void shouldMatch(){
        assertEquals("Hello Geometrixx",helloIndiaModel.getMessage());
    }

    @Test
    public void shouldNotReturnPagePathWhenPageManagerIsNull() {
        when(resourceResolver.adaptTo(PageManager.class)).thenReturn(null);
        helloIndiaModel.init();
        assertEquals("Hello World",helloIndiaModel.getMessage());
    }

    @Test
    public void shouldNotReturnPagePathWhenPageIsNull() {
        when(pageManager.getContainingPage(currentResource)).thenReturn(null);
        helloIndiaModel.init();
        assertEquals("Hello World",helloIndiaModel.getMessage());

    }

    @Test
    public void shouldReturnEmptyPagePathWhenPagePathIsNull() {
        when(currentPage.getPath()).thenReturn(null);
        helloIndiaModel.init();
        assertEquals("Hello World",helloIndiaModel.getMessage());

    }
}
