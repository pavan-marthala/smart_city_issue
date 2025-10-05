package com.smartcity.issue.issue;

import com.smartcity.models.Issue;
import com.smartcity.models.IssueRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface IssueMapper {
    IssueMapper INSTANCE = Mappers.getMapper(IssueMapper.class);

    Issue toModel(IssueEntity issue);

    IssueEntity toEntity(Issue issue);

    IssueEntity toEntity(IssueRequest issueRequest);

    default OffsetDateTime map(Instant instant) {
        return instant == null ? null : instant.atOffset(ZoneOffset.UTC);
    }

    default Instant map(OffsetDateTime offsetDateTime) {
        return offsetDateTime == null ? null : offsetDateTime.toInstant();
    }

    default UUID map(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        return UUID.fromString(value);
    }

    default String map(UUID value) {
        return value == null ? null : value.toString();
    }
}
