package com.antipattrn.ambassador.controller;

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

import com.antipattrn.ambassador.entity.Ambassador;
import com.antipattrn.ambassador.repository.AmbassadorRepository;

@RunWith(MockitoJUnitRunner.class)
public class AmbassadorControllerTests {

    @Mock
    private AmbassadorRepository ambassadorRepository;

    @InjectMocks
    private AmbassadorController ambassadorController;

    @Test
    public void findAmbassadorsCallsFindAll() {
        Ambassador ambassador = new Ambassador("Bob", "Smith", "63139");
        when(ambassadorRepository.findAll()).thenReturn(Arrays.asList(ambassador));

        List<Ambassador> response = ambassadorController.find();

        assertThat(response, is(notNullValue()));
        assertThat(response.size(), is(1));
        assertThat(response, hasItem(ambassador));
    }

    @Test
    public void createAmbassadorCallsSave() {
        Ambassador ambassador = new Ambassador("Bob", "Smith", "63139");
        when(ambassadorRepository.save(any(Ambassador.class))).thenReturn(ambassador);

        Ambassador response = ambassadorController.create(ambassador);

        assertThat(response, is(notNullValue()));
        assertThat(response, is(ambassador));
    }

    @Test
    public void updateAmbassadorCallsSave() {
        Ambassador ambassador = new Ambassador("Robert", "Smith", "63139");
        when(ambassadorRepository.save(any(Ambassador.class))).thenReturn(ambassador);

        Ambassador response = ambassadorController.update("1", ambassador);

        assertThat(response, is(notNullValue()));
        assertThat(response, is(ambassador));
    }

    @Test
    public void deleteAmbassadorCallsDelete() {
        doNothing().when(ambassadorRepository).delete("1");

        ambassadorController.delete("1");

        verify(ambassadorRepository).delete("1");
    }
}