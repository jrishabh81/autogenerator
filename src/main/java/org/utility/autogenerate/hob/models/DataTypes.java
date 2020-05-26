package org.utility.autogenerate.hob.models;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;

public enum DataTypes {
    LONG, INT, STRING, DATE_TIME, BOOLEAN, DECIMAL, TEXT, TIME, TIMESPAN, LONG_STRING, UNKNOWN;

    public static DataTypes typeof(Object obj) {
        if (obj == null)
            return DataTypes.UNKNOWN;

        if (obj instanceof java.lang.String)
            return DataTypes.STRING;

        if (obj instanceof org.joda.time.DateTime)
            return DataTypes.DATE_TIME;

        if (obj instanceof Boolean)
            return DataTypes.BOOLEAN;

        if (obj instanceof java.lang.Long)
            return DataTypes.LONG;

        if (obj instanceof Integer)
            return DataTypes.INT;

        if (obj instanceof BigDecimal)
            return DataTypes.DECIMAL;

        return DataTypes.UNKNOWN;
    }

    public static DataTypes toJavaTypeFromTableColumnType(java.lang.String tclType) {
        if (tclType.equals("bool") || tclType.equals("boolean"))
            return DataTypes.BOOLEAN;

        if (tclType.equals("decimal"))
            return DataTypes.DECIMAL;

        if (tclType.equals("long"))
            return DataTypes.LONG;

        if (tclType.equals("datetime"))
            return DataTypes.DATE_TIME;

        if (tclType.equals("string"))
            return DataTypes.STRING;

        if (tclType.equals("int") || tclType.equals("integer"))
            return DataTypes.INT;

        return DataTypes.UNKNOWN;

    }

    // IMPORTANT: jdbc types do not map 1->1 with java types so this is approximation
    // should only be used in certain circumstances as information may be lost if the
    // result type is used to extract column information
    public static DataTypes jdbcTypeToCompatibleJavaType(ResultSet resultSet, int columnIndex) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        switch (metaData.getColumnType(columnIndex)) {
            case Types.NUMERIC:
                return metaData.getPrecision(columnIndex) <= 10 ? DataTypes.INT : DataTypes.LONG;

            case Types.BIGINT:
                return DataTypes.LONG;

            case Types.INTEGER:
                return DataTypes.INT;

            case Types.TIMESTAMP:
            case Types.DATE:
                return DataTypes.DATE_TIME;

            case Types.VARCHAR:
            case Types.NVARCHAR:
                return DataTypes.STRING;

            case Types.CHAR:
                return DataTypes.BOOLEAN;

            default:
                return DataTypes.UNKNOWN;
        }
    }

    public int toJDBCType() {
        switch (this) {
            case LONG:
            case TIMESPAN:
                return Types.BIGINT;

            case INT:
                return Types.INTEGER;

            case STRING:
            case LONG_STRING:
                return Types.VARCHAR;

            case DATE_TIME:
                return Types.TIMESTAMP;

            case BOOLEAN:
                return Types.CHAR;

            case DECIMAL:
                return Types.BIGINT;

            case TEXT:
                return Types.LONGVARCHAR;

            case TIME:
                return Types.TIME;

            default:
                throw new IllegalStateException("Can't convert java type '%1' to jdbc type" + this);

        }
    }

    public int getLength() {
        switch (this) {
            case STRING:
                return 255;

            case LONG_STRING:
            case LONG:
            case INT:
            case DATE_TIME:
            case BOOLEAN:
            case DECIMAL:
            case TEXT:
            case TIME:
            case TIMESPAN:
                return 0;

            default:
                throw new IllegalStateException("Can't convert java type '%1' to jdbc type" + this);

        }
    }

    public java.lang.String toTableColumnType() {
        // cheesy but does the job
        return toString().toLowerCase();
    }

}
