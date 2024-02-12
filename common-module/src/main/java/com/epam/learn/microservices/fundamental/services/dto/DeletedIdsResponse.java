package com.epam.learn.microservices.fundamental.services.dto;

import java.util.List;

public record DeletedIdsResponse(List<Integer> ids) {
}