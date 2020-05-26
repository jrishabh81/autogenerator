package org.utility.autogenerate.hob.models;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name = "propertyMapping", strict = false)
public class PropertyMapping implements Cloneable {
    @Attribute(name = "propertyName")
    private String propertyName;

    @Attribute(name = "datatype")
    private DataTypes datatype;

    @Attribute(name = "isMandatory")
    private boolean mandatory;

    @Attribute(name = "isOneToOneNestedObject", required = false)
    private boolean oneToOneNestedObject;

    @Attribute(name = "propertyInstanceName", required = false)
    private String propertyInstanceName;

    @Attribute(name = "loadNestedObject", required = false)
    private boolean loadNestedObject = true;

    @Attribute(name = "maxLength", required = false)
    private int maxLength = -1;

    @Attribute(name = "isForeignKey", required = false)
    private boolean foreignKey = true;

    @Attribute(name = "prefixRequired", required = false)
    private boolean prefixRequired = false;

    @Attribute(name = "isOrderColumn", required = false)
    private boolean isOrderColumn;

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public DataTypes getDatatype() {
        return datatype;
    }

    public void setDatatype(DataTypes datatype) {
        this.datatype = datatype;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    public boolean isOneToOneNestedObject() {
        return oneToOneNestedObject;
    }

    public void setOneToOneNestedObject(boolean oneToOneNestedObject) {
        this.oneToOneNestedObject = oneToOneNestedObject;
    }

    public String getPropertyInstanceName() {
        return propertyInstanceName;
    }

    public void setPropertyInstanceName(String propertyInstanceName) {
        this.propertyInstanceName = propertyInstanceName;
    }


    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        PropertyMapping that = (PropertyMapping) o;

        if (mandatory != that.mandatory)
            return false;
        if (datatype != that.datatype)
            return false;
        return propertyName.equals(that.propertyName);
    }

    public int hashCode() {
        return propertyName.hashCode();
    }

    public boolean isLoadNestedObject() {
        return loadNestedObject;
    }

    public void setLoadNestedObject(boolean loadNestedObject) {
        this.loadNestedObject = loadNestedObject;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public boolean isForeignKey() {
        return foreignKey;
    }

    public void setForeignKey(boolean foreignKey) {
        this.foreignKey = foreignKey;
    }

    public boolean isPrefixRequired() {
        return prefixRequired;
    }

    public boolean isOrderColumn() {
        return isOrderColumn;
    }
}
