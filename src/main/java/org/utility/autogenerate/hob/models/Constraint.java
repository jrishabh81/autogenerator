package org.utility.autogenerate.hob.models;

import org.utility.autogenerate.hob.helpers.StringHelper;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

@Root(name = "constraint")
public class Constraint implements Cloneable {
    @Attribute(name = "constraintSuffix")
    private String constraintSuffix;

    @Attribute(name = "isUnique")
    private boolean unique;

    @Attribute(name = "isClustered")
    private boolean clustered;

    @Attribute(name = "isBusinessConstraint")
    private boolean businessConstraint;

    // TODO - remove this - just use the display string property of the object mapping
    @Attribute(name = "isDisplayName")
    private boolean displayName;

    @Attribute(name = "propertyList")
    private String propertyList;

    public Constraint() {
    }

    public Constraint(String constraintSuffix, boolean unique, boolean clustered, boolean businessConstraint, boolean displayName) {
        this.constraintSuffix = constraintSuffix;
        this.unique = unique;
        this.clustered = clustered;
        this.businessConstraint = businessConstraint;
        this.displayName = displayName;
    }

    public void addProperty(String propertyName) {
        if (StringHelper.isEmpty(propertyList))
            propertyList = propertyName;
        else
            propertyList += "," + propertyName;
    }

    public boolean equals(Object object) {
        if (object == null)
            return false;

        if (!(object instanceof Constraint))
            return false;

        Constraint fromConstraint = (Constraint) object;

        if (!constraintSuffix.equalsIgnoreCase(fromConstraint.constraintSuffix))
            return false;

        if (unique != fromConstraint.unique)
            return false;

        if (clustered != fromConstraint.clustered)
            return false;

        if (!propertyList.equals(fromConstraint.propertyList))
            return false;

        // NOTE: We do not test IsBusinessContraint as db schema cannot report this property

        return true;
    }

    public String getConstraintSuffix() {
        return constraintSuffix;
    }

    public void setConstraintSuffix(String constraintSuffix) {
        this.constraintSuffix = constraintSuffix;
    }

    public boolean isUnique() {
        return unique;
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
    }

    public boolean isClustered() {
        return clustered;
    }

    public void setClustered(boolean clustered) {
        this.clustered = clustered;
    }

    public boolean isBusinessConstraint() {
        return businessConstraint;
    }

    public void setBusinessConstraint(boolean businessConstraint) {
        this.businessConstraint = businessConstraint;
    }

    public boolean isDisplayName() {
        return displayName;
    }

    public void setDisplayName(boolean displayName) {
        this.displayName = displayName;
    }

    public String getPropertyList() {
        return propertyList;
    }

    public void setPropertyList(String propertyList) {
        this.propertyList = propertyList;
    }

    public List<PropertyMapping> getPropertyMappings(ObjectMapping objectMapping) {
        List<PropertyMapping> propertyMappings = new ArrayList<PropertyMapping>();
        String[] propertyNames = StringHelper.split(getPropertyList(), ",");

        for (String propertyName : propertyNames) {
            PropertyMapping propertyMapping = objectMapping.findPropertyMapping(propertyName);

            if (propertyMapping == null)
                throw new RuntimeException(String.format("The constraint %1 on object mapping %2 is invalid - the property '%3' is not in the object mapping", getConstraintSuffix(), objectMapping.getObjectName(), propertyName));

            propertyMappings.add(propertyMapping);
        }

        return propertyMappings;
    }


    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
