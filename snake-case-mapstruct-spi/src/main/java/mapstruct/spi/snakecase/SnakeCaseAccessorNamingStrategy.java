package mapstruct.spi.snakecase;

import com.google.common.base.CaseFormat;
import javax.lang.model.element.ExecutableElement;
import org.mapstruct.ap.spi.DefaultAccessorNamingStrategy;
import org.mapstruct.ap.spi.util.IntrospectorUtils;

public class SnakeCaseAccessorNamingStrategy extends DefaultAccessorNamingStrategy {
  @Override
  public String getPropertyName(ExecutableElement getterOrSetterMethod) {
    var methodName = getterOrSetterMethod.getSimpleName().toString();
    methodName =
        CaseFormat.LOWER_UNDERSCORE.to(
            CaseFormat.LOWER_CAMEL,
            CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, methodName));

    if (isFluentSetter(getterOrSetterMethod)) {
      // If this is a fluent setter that starts with set and the 4th character is an
      // uppercase one
      // then we treat it as a Java Bean style method (we get the property starting
      // from the 4th character).
      // Otherwise we treat it as a fluent setter
      // For example, for the following methods:
      // * public Builder setSettlementDate(String settlementDate)
      // * public Builder settlementDate(String settlementDate)
      // We are going to extract the same property name settlementDate
      if (methodName.startsWith("set")
          && methodName.length() > 3
          && Character.isUpperCase(methodName.charAt(3))) {
        return IntrospectorUtils.decapitalize(methodName.substring(3));
      } else {
        return methodName;
      }
    }
    return IntrospectorUtils.decapitalize(
        methodName.substring(methodName.startsWith("is") ? 2 : 3));
  }
}
