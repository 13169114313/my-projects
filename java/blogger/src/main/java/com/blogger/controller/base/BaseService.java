package com.blogger.controller.base;

import java.io.Serializable;
import java.util.List;

/**
 * @author chen
 */
public interface BaseService<T,E extends Serializable> {

    public String show(T t, int page, int limit);
}
