package com.smartbookstore.server.services;

public interface OptionalGenericService<T, REQ> {
    T createCustom(REQ request);
}
