package com.epam.learn.microservices.fundamental.common.dto;

import java.util.List;

public record DeletedIdsResponse(List<Integer> ids) {
}