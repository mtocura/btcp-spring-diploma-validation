package br.com.mod10.diplomavalidation.utils.exception;

import org.junit.jupiter.api.Assertions;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.FieldError;

import java.util.*;

@SpringBootTest
public class FieldErrorsTest {

  @Test
  public void shouldReturnAMapOfFieldErrors() {
    FieldError fieldError1 = new FieldError("mockObject", "field1", "Erro field1");
    FieldError fieldError2 = new FieldError("mockObject", "field2", "Erro field2");
    List<FieldError> fieldErrors = new ArrayList<>(Arrays.asList(fieldError1, fieldError2));

    Map<String, String> map = FieldErrors.processFieldErrors(fieldErrors);

    Assertions.assertNotNull(map);
    assertThat(map).contains(entry("field1", "Erro field1"), entry("field2", "Erro field2"));
  }
}
