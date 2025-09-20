package br.com.empresa.payload;

import java.util.List;

/**
 * Jakarta Specification:
 *  https://jakarta.ee/specifications/data/1.0/apidocs/jakarta.data/jakarta/data/page/page
 *
 * @param totalElements
 * @param totaPages
 * @param content
 */

public record Page(List<?> content, PageRequest pageRequest, Long totalElements, Long totaPages) {
}
