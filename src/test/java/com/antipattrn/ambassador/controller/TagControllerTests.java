package com.antipattrn.ambassador.controller;

import com.antipattrn.ambassador.entity.Tag;
import com.antipattrn.ambassador.repository.TagRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TagControllerTests {
    @Mock
    private TagRepository tagRepository;

    @InjectMocks
    private TagController tagController;

    @Test
    public void findTagsByName() {
        Tag tag = new Tag("1", "Language", "English-England");
        when(tagRepository.findByNameIgnoreCaseContaining("English")).thenReturn(Arrays.asList(tag));

        List<Tag> response = tagController.findByName("English");

        assertThat(response, is(notNullValue()));
        assertThat(response.size(), is(1));
        assertThat(response, hasItem(tag));
    }

    @Test
    public void findTagsByType() {
        Tag tag = new Tag("1", "Language", "Yiddish");
        when(tagRepository.findByTypeIgnoreCaseContaining("language")).thenReturn(Arrays.asList(tag));

        List<Tag> response = tagController.findByType("language");

        assertThat(response, is(notNullValue()));
        assertThat(response.size(), is(1));
        assertThat(response, hasItem(tag));
    }
}
