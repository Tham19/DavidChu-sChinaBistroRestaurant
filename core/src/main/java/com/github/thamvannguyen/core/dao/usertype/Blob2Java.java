package com.github.thamvannguyen.core.dao.usertype;


import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.internal.util.compare.EqualsHelper;
import org.hibernate.usertype.UserType;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;


/**
 * Created by huynhduychuong on Oct 3, 2016.
 */
public abstract class Blob2Java<T> implements UserType {

    private Class<T> className;

    public Blob2Java(Class<T> className) {
        this.className = className;
    }

    @Override
    public Object assemble(Serializable cached, Object arg1) throws HibernateException {
        return deepCopy(cached);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.hibernate.usertype.UserType#deepCopy(java.lang.Object)
     */
    @Override
    public abstract Object deepCopy(Object arg0) throws HibernateException;


    @Override
    public Serializable disassemble(Object value) throws HibernateException {
        return (Serializable) deepCopy(value);
    }

    @Override
    public boolean equals(Object arg0, Object arg1) throws HibernateException {
        return EqualsHelper.areEqual(arg0, arg1);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.hibernate.usertype.UserType#hashCode(java.lang.Object)
     */
    @Override
    public int hashCode(Object owner) throws HibernateException {
        return owner.hashCode();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.hibernate.usertype.UserType#isMutable()
     */
    @Override
    public boolean isMutable() {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor arg2, Object arg3) throws HibernateException, SQLException {
        InputStream binaryStream = rs.getBinaryStream(names[0]);

        if (binaryStream == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            int binary;
            while ((binary = binaryStream.read()) != -1) {
                byteArrayOutputStream.write(binary);
            }
        } catch (IOException e) {
            throw new HibernateException(e);
        }
        byte[] binary = byteArrayOutputStream.toByteArray();
        if (binary == null) {
            return null;
        }
        return ObjectConvertor.convertToObject(binary);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor arg3) throws HibernateException, SQLException {
        T attributes = null;
        if (value != null) {
            attributes = (T) value;
            try {
                st.setBytes(index, ObjectConvertor.convertToBytes(attributes));
            } catch (SQLException e) {
                throw new HibernateException(e);
            }
        }
        if (attributes == null) {
            try {
                st.setNull(index, Types.BINARY);
            } catch (SQLException e) {
                throw new HibernateException(e);
            }
        }

    }

    @Override
    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return original;
    }

    @Override
    public Class returnedClass() {
        return className;
    }

    @Override
    public int[] sqlTypes() {
        return new int[]{Types.BLOB};
    }

}
