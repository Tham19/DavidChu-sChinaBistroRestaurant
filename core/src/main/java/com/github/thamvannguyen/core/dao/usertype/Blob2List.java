package com.github.thamvannguyen.core.dao.usertype;

import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huynhduychuong on Oct 3, 2016.
 */
@SuppressWarnings("unchecked")
public class Blob2List extends Blob2Java<List> {

    public Blob2List() {
        super(List.class);
    }


    @Override
    public Object deepCopy(Object value) throws HibernateException {
        List originalValue = (List) value;
        List<Object> deepCopiedValue = new ArrayList<>();
        deepCopiedValue.addAll(originalValue);
        return deepCopiedValue;
    }

}
