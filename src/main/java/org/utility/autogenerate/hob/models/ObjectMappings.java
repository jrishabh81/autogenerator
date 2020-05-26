package org.utility.autogenerate.hob.models;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "schema",strict = false)
public class ObjectMappings implements Cloneable {
    @Attribute(name = "package", required = true)
    private String packageName;

    @ElementList(name = "objectMappings", type = ObjectMapping.class)
    private List<ObjectMapping> objectMappings;

    public String getPackageName() {
        return packageName;
    }

    public ObjectMappings setPackageName(String packageName) {
        this.packageName = packageName;
        return this;
    }

    public List<ObjectMapping> getObjectMappings() {
        return objectMappings;
    }

    public ObjectMappings setObjectMappings(List<ObjectMapping> objectMappings) {
        this.objectMappings = objectMappings;
        return this;
    }
}
