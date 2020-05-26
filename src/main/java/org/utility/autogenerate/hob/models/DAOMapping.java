package org.utility.autogenerate.hob.models;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * Created by Sriraman S. on 05-05-2016.
 */

@Root( name = "daoMapping", strict = false )
public class DAOMapping {
    @Attribute(name = "propertyName", required = true)
    private String propertyName;

    public DAOMapping(){

    }

    public DAOMapping(String propertyName) {
        this.propertyName = propertyName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DAOMapping)) return false;

        DAOMapping that = (DAOMapping) o;

        return propertyName.equals(that.propertyName);

    }

    @Override
    public int hashCode() {
        return propertyName.hashCode();
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }
}
