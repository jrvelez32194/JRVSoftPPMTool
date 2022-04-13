package jrvsoft.ppmtool.services;

import jrvsoft.ppmtool.repositories.ProjectRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectServinceImpl {

    private final ProjectRepo projectRepo;

}
