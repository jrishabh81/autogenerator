package org.utility.autogenerate.hob.models;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

@Root(name = "objectMapping", strict = false)
public class ObjectMapping implements Cloneable {
    @Attribute(name = "objectName")
    private String objectName;

    @Attribute(name = "objectPrefix")
    private String objectPrefix;

    @ElementList(name = "propertyMappings", type = PropertyMapping.class)
    private List<PropertyMapping> propertyMappings = new ArrayList<>();

    @ElementList(name = "nestedCollections", type = NestedCollection.class, required = false)
    private List<NestedCollection> nestedCollections = new ArrayList<>();

    @ElementList(name = "nestedObjects", type = NestedObject.class, required = false)
    private List<NestedObject> nestedObjects = new ArrayList<>();

    @ElementList(name = "constraints", type = Constraint.class,required = false)
    private List<Constraint> constraints = new ArrayList<>();

    @ElementList(name = "daoMappings", type = DAOMapping.class, required = false)
    private List<DAOMapping> daoMappings = new ArrayList<>();

    @Attribute(name = "primaryKeyDataType", required = false)
    private String primaryKeyDataType;

    @Attribute(name = "customTableInterceptor", required = false)
    private String customTableInterceptor;


    public PropertyMapping findPropertyMapping(String propertyName) {
        for (PropertyMapping propertyMapping : propertyMappings) {
            if (propertyMapping.getPropertyName().equals(propertyName)) {
                return propertyMapping;
            }
        }

        return null;
    }

    public Constraint findConstraint(String constraintSuffix) {
        for (Constraint constraint : constraints) {
            if (constraint.getConstraintSuffix().equals(constraintSuffix))
                return constraint;
        }

        return null;
    }

    public NestedCollection findNestedCollection(String propertyName) {
        for (NestedCollection nestedCollection : nestedCollections) {
            if (nestedCollection.getPropertyName().equals(propertyName)) {
                return nestedCollection;
            }
        }

        return null;
    }


    public String getObjectName() {
        return objectName;
    }


    public List<PropertyMapping> getPropertyMappings() {
        return propertyMappings;
    }

    public List<NestedCollection> getNestedCollections() {
        return nestedCollections;
    }

    public List<Constraint> getConstraints() {
        return constraints;
    }

    public void setConstraints(List<Constraint> constraints) {
        this.constraints = constraints;
    }


    public List<NestedObject> getNestedObjects() {
        return nestedObjects;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        ObjectMapping clonedObjectMapping = (ObjectMapping) super.clone();

        // have to clone all of the property mappings
        clonedObjectMapping.propertyMappings = new ArrayList<PropertyMapping>();
        for (PropertyMapping propertyMapping : getPropertyMappings()) {
            clonedObjectMapping.getPropertyMappings().add((PropertyMapping) propertyMapping.clone());
        }

        // have to clone all of the nested collections
        clonedObjectMapping.nestedCollections = new ArrayList<NestedCollection>();
        for (NestedCollection nestedCollection : getNestedCollections()) {
            clonedObjectMapping.getNestedCollections().add((NestedCollection) nestedCollection.clone());
        }

        clonedObjectMapping.nestedObjects = new ArrayList<NestedObject>();
        for (NestedObject nestedObject : getNestedObjects()) {
            clonedObjectMapping.getNestedObjects().add((NestedObject) nestedObject.clone());
        }

        // have to clone all of the constraints
        clonedObjectMapping.constraints = new ArrayList<Constraint>();
        for (Constraint constraint : getConstraints()) {
            clonedObjectMapping.getConstraints().add((Constraint) constraint.clone());
        }

        clonedObjectMapping.daoMappings = new ArrayList<DAOMapping>();
        for (DAOMapping daoMapping : getDaoMappings()) {
            clonedObjectMapping.getDaoMappings().add(daoMapping);
        }

        return clonedObjectMapping;
    }

    public List<DAOMapping> getDaoMappings() {
        return daoMappings;
    }

    public void setDaoMappings(List<DAOMapping> daoMappings) {
        this.daoMappings = daoMappings;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ObjectMapping that = (ObjectMapping) o;

        if (!constraints.equals(that.constraints)) return false;
        if (!nestedCollections.equals(that.nestedCollections)) return false;
        if (!propertyMappings.equals(that.propertyMappings)) return false;
        if (!objectName.equals(that.objectName)) return false;
        if (!objectPrefix.equals(that.objectPrefix)) return false;
        if (!nestedObjects.equals(that.nestedObjects)) return false;
        return daoMappings.equals(that.daoMappings);
    }

    public int hashCode() {
        int result;
        result = objectName.hashCode();
        result = 31 * result + objectPrefix.hashCode();
        return result;
    }

    public String getPrimaryKeyDataType() {
        return primaryKeyDataType;
    }

    public void setPrimaryKeyDataType(String primaryKeyDataType) {
        this.primaryKeyDataType = primaryKeyDataType;
    }

    public String getCustomTableInterceptor() {
        return customTableInterceptor;
    }

    public void setCustomTableInterceptor(String customTableInterceptor) {
        this.customTableInterceptor = customTableInterceptor;
    }
}
