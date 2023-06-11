package it.uninsubria.emotionalsongs.model.api;

import it.uninsubria.emotionalsongs.controller.Controller;

public class API {
    private String path;
    private String httpMethod;

    public API(String path, String httpMethod) {
        this.path = path;
        this.httpMethod = httpMethod;
    }

    public String getPath() {
        return path;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public boolean equals(API other) {
        return this.path.equals(other.path) && this.httpMethod.equals(other.getHttpMethod());
    }

    public boolean match(String path, String httpMethod) {
        return Controller.isPathMatching(this.path, path) && this.httpMethod.equals(httpMethod);
    }
}
