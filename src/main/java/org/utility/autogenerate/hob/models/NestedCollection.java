package org.utility.autogenerate.hob.models;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root( name = "nestedCollection" )
public class NestedCollection implements Cloneable
{
    @Attribute( name = "propertyName" )
    private String propertyName;

    @Attribute( name = "foreignObjectMappingName" )
    private String foreignObjectMappingName;

    @Attribute( name = "foreignPropertyName" )
    private String foreignPropertyName;

    @Attribute( name = "OrderingPropertyName", required = false )
    private String orderingPropertyName;

    @Attribute(name = "indexForSearch", required = false)
    private boolean indexForSearch = false;

    @Attribute( name = "noCopy", required = false )
    private boolean noCopy = false;

	@Attribute( name = "refCopy", required = false )
	private boolean refCopy = false;
	
    public String getPropertyName()
    {
        return propertyName;
    }

    public void setPropertyName( String propertyName )
    {
        this.propertyName = propertyName;
    }

    public String getForeignObjectMappingName()
    {
        return foreignObjectMappingName;
    }

    public void setForeignObjectMappingName( String foreignObjectMappingName )
    {
        this.foreignObjectMappingName = foreignObjectMappingName;
    }

    public String getForeignPropertyName()
    {
        return foreignPropertyName;
    }

    public void setForeignPropertyName( String foreignPropertyName )
    {
        this.foreignPropertyName = foreignPropertyName;
    }

    public String getOrderingPropertyName()
    {
        return orderingPropertyName;
    }

    public void setOrderingPropertyName( String orderingPropertyName )
    {
        this.orderingPropertyName = orderingPropertyName;
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }

	public void setNoCopy(boolean noCopy) {
		this.noCopy = noCopy;
	}

	public boolean isNoCopy() {
		return noCopy;
	}

	public void setRefCopy(boolean refCopy) {
		this.refCopy = refCopy;
	}

	public boolean isRefCopy() {
		return refCopy;
	}

    public boolean isIndexForSearch() {
        return indexForSearch;
    }

    public void setIndexForSearch(boolean indexForSearch) {
        this.indexForSearch = indexForSearch;
    }
}
