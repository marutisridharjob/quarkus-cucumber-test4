package org.tbounsiar.api.bom;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jboss.resteasy.reactive.RestResponse;

@Getter
@RequiredArgsConstructor
public class ResponseBom<T> {
    private final T content;
}
