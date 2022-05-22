package com.jagmeet.portfoliobuilder.service;

import com.jagmeet.portfoliobuilder.exceptions.NotFoundException;
import com.jagmeet.portfoliobuilder.entities.Project;
import com.jagmeet.portfoliobuilder.repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    private ProjectService underTest;

    @BeforeEach
    void setUp(){
        underTest = new ProjectService(projectRepository);
    }


    @Test
    void shouldGetAllProjects() {
        // Given
        List<Project> projects = List.of(
                Project.builder()
                        .description("abcdefgh")
                        .githubLink("http://githublink.com")
                        .tags(Arrays.asList("React","Spring"))
                        .title("Github Viewer")
                        .build(),
                Project.builder()
                        .description("abcdefgh")
                        .githubLink("http://githublink.com")
                        .tags(Arrays.asList("React","Spring"))
                        .title("Github Viewer")
                        .build()
        );
        when(projectRepository.findAll()).thenReturn(projects);
        // When
        List<Project> actual = underTest.getAllProjects();
        // Then
        System.out.println(actual);
        assertEquals(projects.size(),actual.size());
    }

    @Test
    void shouldGetEmptyListProjects() {
        // Given
        when(projectRepository.findAll()).thenReturn(List.of());
        // When
        List<Project> actual = underTest.getAllProjects();
        // Then
        System.out.println(actual);
        assertEquals(0,actual.size());
    }

    @Test
    void shouldGetProjectById() {

        // Given
        String id = "1234";
        Project dummy = Project.builder()
                .id(id)
                .title("Github Viewer")
                .description("ssfsdfsfdsdfsdfs")
                .build();
        when(projectRepository.findById(id)).thenReturn(Optional.ofNullable(dummy));

        // When
        Project actual = underTest.getProjectById(id);

        // Then
        assert dummy != null;
        assertEquals(dummy.getId(),actual.getId());
        assertEquals(dummy.getTitle(),actual.getTitle());

    }

    @Test
    void shouldThrowNotFoundExceptionWhenGetProjectById() {

        // Given
        when(projectRepository.findById("1234")).thenReturn(Optional.empty());

        // Then
        assertThrows(NotFoundException.class,() -> underTest.getProjectById("1234"));

    }

    @Test
    void shouldUpdateProjectById() {

        // Given
        String id = "1234";
        Project dummy = Project.builder()
                .id(id)
                .title("Github Viewer")
                .description("ssfsdfsfdsdfsdfs")
                .build();
        Project updatedDummy = Project.builder()
                .id(id)
                .title("Github Viewer updated")
                .description("lorem ipsum")
                .build();
        when(projectRepository.findById(id)).thenReturn(Optional.ofNullable(dummy));
        when(projectRepository.save(updatedDummy)).thenReturn(updatedDummy);
        // When
        Project actual = underTest.updateProjectById(updatedDummy,id);

        // Then
        assertEquals("Github Viewer updated",actual.getTitle());

    }

    @Test
    void shouldCreateProject() {
        // Given
        Project dummy = Project.builder()
                .id("1234")
                .description("abcdefgh")
                .githubLink("http://githublink.com")
                .tags(Arrays.asList("React","Spring"))
                .title("Github Viewer")
                .build();
        when(projectRepository.save(dummy)).thenReturn(dummy);

        // When
        underTest.createProject(dummy);
        // Then
        verify(projectRepository).save(dummy);
    }

}