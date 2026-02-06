package wx.generate.pub;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.app.b2b.server.ServerAPI;
import com.wm.app.b2b.server.ns.Namespace;
import com.wm.lang.ns.NSException;
import com.wm.lang.ns.NSField;
import com.wm.lang.ns.NSName;
import com.wm.lang.ns.NSRecord;
import com.wm.lang.ns.NSRecordUtil;
import com.wm.util.JavaWrapperType;
// --- <<IS-END-IMPORTS>> ---

public final class docType

{
	// ---( internal utility methods )---

	final static docType _instance = new docType();

	static docType _newInstance() { return new docType(); }

	static docType _cast(Object o) { return (docType)o; }

	// ---( server methods )---




	public static final void addFieldToDoctype (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(addFieldToDoctype)>> ---
		// @sigtype java 3.5
		// [i] object:0:required docType
		// [i] object:0:required field
		// [o] object:0:required docType
		IDataCursor pipelineCursor = pipeline.getCursor();
		try {
			Object	docTypeObj = IDataUtil.get( pipelineCursor, "docType" );
			Object	fieldObj = IDataUtil.get( pipelineCursor, "field" );
		
			NSRecord rec = (NSRecord) docTypeObj;
			NSField f = (NSField) fieldObj;
			rec.addField(f);
			IDataUtil.put( pipelineCursor, "docType", rec );
		} finally {
			pipelineCursor.destroy();
		}
			
		// --- <<IS-END>> ---

                
	}



	public static final void createDoctype (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(createDoctype)>> ---
		// @sigtype java 3.5
		// [i] field:0:required docTypeName
		// [i] field:0:required dimension {"ARRAY","SCALAR","TABLE"}
		// [i] field:0:optional comment
		// [o] object:0:required docType
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		try {
			String	docTypeName = IDataUtil.getString( pipelineCursor, "docTypeName" );
			String	dimension = IDataUtil.getString( pipelineCursor, "dimension" );
			String	comment = IDataUtil.getString( pipelineCursor, "comment" );
		
			//interpret dimension string to constant
			int dim = encodeDimension(dimension);
		
			NSRecord record = new NSRecord(Namespace.current(), docTypeName, dim);
			if (comment != null){
				record.setComment(comment);
			}
			// pipeline
			IDataUtil.put( pipelineCursor, "docType", record );
		} finally {
			pipelineCursor.destroy();
		}
			
		// --- <<IS-END>> ---

                
	}



	public static final void createField (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(createField)>> ---
		// @sigtype java 3.5
		// [i] field:0:required fieldName
		// [i] field:0:required fieldType {"OBJECT","RECORD","RECORDREF","STRING"}
		// [i] field:0:required dimension {"ARRAY","SCALAR","TABLE"}
		// [i] field:0:optional isOptional {"true","false"}
		// [i] field:0:optional referenceDT {"java.lang.Boolean","java.lang.Byte","java.lang.Character","java.lang.Double","java.lang.Float","java.lang.Integer","java.lang.Short","java.lang.Long","java.util.Date","byte [ ]","java.math.BigDecimal","java.math.BigInteger"}
		// [i] field:0:optional comment
		// [i] object:0:optional parent
		// [o] object:0:required field
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		try {
			String	fieldName = IDataUtil.getString( pipelineCursor, "fieldName" );
			String	fieldType = IDataUtil.getString( pipelineCursor, "fieldType" );
			String	dimension = IDataUtil.getString( pipelineCursor, "dimension" );
			Boolean	isOptional = IDataUtil.getBoolean( pipelineCursor, "isOptional", true );
			String	referenceDT = IDataUtil.getString( pipelineCursor, "referenceDT" );
			String comment = IDataUtil.getString( pipelineCursor, "comment" );
			Object parent = IDataUtil.get( pipelineCursor, "parent" );
		
			int type = encodeFieldType(fieldType);
			int dim = encodeDimension(dimension);
		
			NSField field = NSRecordUtil.createField(fieldName,type,dim,Namespace.current(),referenceDT);
			if (parent != null){
				NSRecordUtil.mergeField(field, (NSRecord) parent);
			}
			
			// Set Java Wrapper Type
			if (type ==  NSField.FIELD_OBJECT) {
				
				for (int i = 0; i < JavaWrapperType.getNames().length; i++) {
					if (JavaWrapperType.getNames()[i].equalsIgnoreCase(referenceDT)) {
						field.setJavaWrapperType(i);
						break;
					}
				}
			}
			
			field.setOptional(isOptional);
			
			if (comment != null){
				field.setComment(comment);
			}
			
			IDataUtil.put( pipelineCursor, "field", field );
		} finally {
			pipelineCursor.destroy();
		}
		
		
		
			
		// --- <<IS-END>> ---

                
	}



	public static final void getJavaWrapperTypes (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getJavaWrapperTypes)>> ---
		// @sigtype java 3.5
		// [o] field:1:required javaWrapperTypes
		String[]	javaWrapperTypes = JavaWrapperType.getNames();
		
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		
		javaWrapperTypes[0] = "javaWrapperTypes";
		IDataUtil.put( pipelineCursor, "javaWrapperTypes", javaWrapperTypes );
		pipelineCursor.destroy();
			
		// --- <<IS-END>> ---

                
	}



	public static final void saveDoctype (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(saveDoctype)>> ---
		// @sigtype java 3.5
		// [i] field:0:required packageName
		// [i] field:0:required folder
		// [i] field:0:required documentName
		// [i] object:0:required docType
		// [o] field:0:required nsName
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		try {
			String	packageName = IDataUtil.getString( pipelineCursor, "packageName" );
			String	folder = IDataUtil.getString( pipelineCursor, "folder" );
			String	documentName = IDataUtil.getString( pipelineCursor, "documentName" );
			Object	docType = IDataUtil.get( pipelineCursor, "docType" );
			String nsName = null;
			try{
				NSRecord rec = (NSRecord)docType;
				NSName ns = NSName.create(folder + ":" + documentName);
				ServerAPI.registerNSRecord(packageName, ns, rec);
				nsName = ns.toString();
			}
			catch(Exception e){
				throw new ServiceException(e);
			}
		
			// pipeline
			IDataUtil.put( pipelineCursor, "nsName", nsName);
		} finally {
			pipelineCursor.destroy();
		}
			
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	
	
	//encode Field Type from String
	static int encodeFieldType(String fieldType){
		if (fieldType == null) {
			throw new IllegalArgumentException("Argument 'fieldType' must not be null");
		}
		
		switch (fieldType.toUpperCase()) {
		case "OBJECT":
			return NSField.FIELD_OBJECT;
		case "RECORD":
			return NSField.FIELD_RECORD;
		case "RECORDREF":
			return NSField.FIELD_RECORDREF;
		default:
			return NSField.FIELD_STRING;
		}
		
	}
	
	// Encode dimension from string
	static int encodeDimension(String dimension){
		if (dimension == null) {
			throw new IllegalArgumentException("Argument 'dimension' must not be null");
		}
		switch (dimension.toUpperCase()) {
		case "ARRAY":
			return NSRecord.DIM_ARRAY;
		case "TABLE":
			return NSRecord.DIM_TABLE;
		default:
			return NSRecord.DIM_SCALAR;
		}
		
	}
	// --- <<IS-END-SHARED>> ---
}

