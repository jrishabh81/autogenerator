package org.utility.autogenerate.hob.models;


import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root( name = "nestedObject")
public class NestedObject implements Cloneable
{

    @Attribute( name = "propertyName")
    public String propertyName;

    @Attribute( name = "objectName")
    public String objectName;

    @Attribute( name = "foreignKey")
    public String foreignKey;

    @Attribute( name = "serverPackage")
    public String serverPackage;

    @Attribute(name = "indexForSearch", required = false)
    private boolean indexForSearch = false;

    @Attribute(name = "indexForSearchSkip", required = false)
    private boolean indexForSearchSkip = false;

    @Attribute(name = "isPropertyInstGroup", required = false)
    private boolean isPropertyInstGroup = false;

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getForeignKey() {
        return foreignKey;
    }

    public void setForeignKey(String foreignKey) {
        this.foreignKey = foreignKey;
    }

    public String getServerPackage() {
        return serverPackage;
    }

    public void setServerPackage(String serverPackage) {
        this.serverPackage = serverPackage;
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }

    public boolean isIndexForSearch() {
        return indexForSearch;
    }

    public void setIndexForSearch(boolean indexForSearch) {
        this.indexForSearch = indexForSearch;
    }

    public boolean isIndexForSearchSkip() {
        return indexForSearchSkip;
    }

    public void setIndexForSearchSkip(boolean indexForSearchSkip) {
        this.indexForSearchSkip = indexForSearchSkip;
    }

    public boolean isPropertyInstGroup() {
        return isPropertyInstGroup;
    }

    public void setPropertyInstGroup(boolean propertyInstGroup) {
        isPropertyInstGroup = propertyInstGroup;
    }
}
