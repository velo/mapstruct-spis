package sample;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface MappingExample {

  Target map(Source source);

  Source map(Target target);
}
