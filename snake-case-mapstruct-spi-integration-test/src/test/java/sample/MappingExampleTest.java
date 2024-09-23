package sample;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class MappingExampleTest {

  MappingExample underTest = new MappingExampleImpl();

  @Test
  void givenSource_whenMapping_thenMapSnakeCase() {
    var source = new Source(55, "str");

    var result = underTest.map(source);

    assertThat(result.getInt_value()).isEqualTo(55);
    assertThat(result.getStringValue()).isEqualTo("str");
  }

  @Test
  void givenTarget_whenMapping_thenMapCamelCase() {
    var target = new Target(42, "fooBar");

    var result = underTest.map(target);

    assertThat(result.getIntValue()).isEqualTo(42);
    assertThat(result.getString_value()).isEqualTo("fooBar");
  }
}
