package com.nmeo.dto;

import java.util.ArrayList;
import java.util.List;

public class ResponseMessage<T> {
    public List<T> result;

    public ResponseMessage() {
        this.result = new ArrayList<>();
    }

    public ResponseMessage(List<T> result) {
        this.result = result;
    }

    public void addElement(T element) {
        this.result.add(element);
    }

    public void addElements(List<T> elements) {
        this.result.addAll(elements);
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }
}
