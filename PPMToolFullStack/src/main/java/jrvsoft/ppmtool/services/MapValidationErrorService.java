package jrvsoft.ppmtool.services;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface MapValidationErrorService {
    public ResponseEntity<?> getMapValidationError(BindingResult result);
}
