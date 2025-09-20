package br.com.empresa.payload;

import org.jboss.resteasy.reactive.RestQuery;

public record PageRequest(@RestQuery Integer page, @RestQuery Integer size) {
}
