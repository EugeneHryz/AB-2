package com.example.hw1.repository.model;

public enum OperationType {

    ADD_USER_IMAGE(OperationDomain.PERSONAL_INFO),
    DOWNLOAD_IMAGE(OperationDomain.PERSONAL_INFO),

    GET_ALL_USERS(OperationDomain.USERS),
    CREATE_NEW_USER(OperationDomain.USERS),
    GET_USER_IMAGES_METADATA(OperationDomain.USERS);

    private final OperationDomain domain;

    OperationType(OperationDomain domain) {
        this.domain = domain;
    }

    public OperationDomain getDomain() {
        return domain;
    }
}
