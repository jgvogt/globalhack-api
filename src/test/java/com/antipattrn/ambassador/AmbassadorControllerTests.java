package com.antipattrn.ambassador;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AmbassadorControllerTests {

    @Mock
    private AmbassadorRepository ambassadorRepository;

    @InjectMocks
    private AmbassadorController ambassadorController;

    @Test
    public void findCoursesCallsFindAll() {
        Ambassador ambassador = new Ambassador("1", "Course 1", "Cource 1 Description");
        when(ambassadorRepository.findAll()).thenReturn(Arrays.asList(ambassador));

        List<Ambassador> response = ambassadorController.find();

        assertThat(response, is(notNullValue()));
        assertThat(response.size(), is(1));
        assertThat(response, hasItem(ambassador));
    }

    @Test
    public void createCourseCallsSave() {
        Ambassador ambassador = new Ambassador("Course 1", "Cource 1 Description");
        when(ambassadorRepository.save(any(Ambassador.class))).thenReturn(ambassador);

        Ambassador response = ambassadorController.create(ambassador);

        assertThat(response, is(notNullValue()));
        assertThat(response, is(ambassador));
    }

    @Test
    public void updateCourseCallsSave() {
        Ambassador ambassador = new Ambassador("Course 1", "Cource 1 Description");
        when(ambassadorRepository.save(any(Ambassador.class))).thenReturn(ambassador);

        Ambassador response = ambassadorController.update("1", ambassador);

        assertThat(response, is(notNullValue()));
        assertThat(response, is(ambassador));
    }

    @Test
    public void deleteCourseCallsDelete() {
        doNothing().when(ambassadorRepository).delete("1");

        ambassadorController.delete("1");

        verify(ambassadorRepository).delete("1");
    }
}