package WxGenerate;

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

public final class doctypes

{
	// ---( internal utility methods )---

	final static doctypes _instance = new doctypes();

	static doctypes _newInstance() { return new doctypes(); }

	static doctypes _cast(Object o) { return (doctypes)o; }

	// ---( server methods )---




	public static final void addFieldToDoctype (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(addFieldToDoctype)>> ---
		// @sigtype java 3.5
		// [i] object:0:required doctype
		// [i] object:0:required field
		// [o] object:0:required doctype
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
			Object	doctypeObj = IDataUtil.get( pipelineCursor, "doctype" );
			Object	fieldObj = IDataUtil.get( pipelineCursor, "field" );
		pipelineCursor.destroy();
		
		//System.out.println("recordObj = "+doctypeObj.toString());
		//System.out.println("fieldObj = "+fieldObj.toString());
		
		NSRecord rec = (NSRecord) doctypeObj;
		NSField f = (NSField) fieldObj;
		
		//System.out.println("trying to add field: "+f+" to record: "+rec);
		rec.addField(f);
		
		// pipeline
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		IDataUtil.put( pipelineCursor_1, "doctype", rec );
		pipelineCursor_1.destroy();
			
		// --- <<IS-END>> ---

                
	}



	public static final void createDoctype (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(createDoctype)>> ---
		// @sigtype java 3.5
		// [i] field:0:required doctypeName
		// [i] field:0:required dimension {"ARRAY","SCALAR","TABLE"}
		// [i] field:0:optional comment
		// [o] object:0:required doctype
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
			String	doctypeName = IDataUtil.getString( pipelineCursor, "doctypeName" );
			String	dimension = IDataUtil.getString( pipelineCursor, "dimension" );
			String	comment = IDataUtil.getString( pipelineCursor, "comment" );
		pipelineCursor.destroy();
		
		//interpret dimension string to constant
		int dim = encodeDimension(dimension);
		
		NSRecord record = new NSRecord(Namespace.current(), doctypeName, dim);
		if (comment != null){
			record.setComment(comment);
		}
		// pipeline
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		IDataUtil.put( pipelineCursor_1, "doctype", record );
		pipelineCursor_1.destroy();
			
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
			String	fieldName = IDataUtil.getString( pipelineCursor, "fieldName" );
			String	fieldType = IDataUtil.getString( pipelineCursor, "fieldType" );
			String	dimension = IDataUtil.getString( pipelineCursor, "dimension" );
			String	isOptional = IDataUtil.getString( pipelineCursor, "isOptional" );
			String	referenceDT = IDataUtil.getString( pipelineCursor, "referenceDT" );
			String comment = IDataUtil.getString( pipelineCursor, "comment" );
			Object parent = IDataUtil.get( pipelineCursor, "parent" );
		pipelineCursor.destroy();
		
		//encode fieldType
		int type = encodeFieldType(fieldType);
		//System.out.println("encoded rec type = "+type+" ("+fieldType+")");
		
		//encode dimension
		int dim = encodeDimension(dimension);
		
		//ensure optional flag is set
		if (isOptional == null){
			isOptional = "true";
		}
		
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
		field.setOptional(Boolean.parseBoolean(isOptional));
		if (comment != null){
			field.setComment(comment);
		}
		
		
		// pipeline
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		IDataUtil.put( pipelineCursor_1, "field", field );
		pipelineCursor_1.destroy();
		
			
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
		// [i] object:0:required doctype
		// [o] field:0:required nsName
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
			String	packageName = IDataUtil.getString( pipelineCursor, "packageName" );
			String	folder = IDataUtil.getString( pipelineCursor, "folder" );
			String	documentName = IDataUtil.getString( pipelineCursor, "documentName" );
			Object	doctype = IDataUtil.get( pipelineCursor, "doctype" );
		pipelineCursor.destroy();
		String nsName = null;
		try{
			NSRecord rec = (NSRecord)doctype;
			NSName ns = NSName.create(folder + ":" + documentName);
			ServerAPI.registerNSRecord(packageName, ns, rec);
			nsName = ns.toString();
		}
		catch(Exception e){
			throw new ServiceException(e);
		}
		
		// pipeline
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		IDataUtil.put( pipelineCursor_1, "nsName", nsName);
		pipelineCursor_1.destroy();
			
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	//encode Field Type from String
	static int encodeFieldType(String fieldType){
		int type;
		if (fieldType.equalsIgnoreCase("OBJECT")){
			type = NSField.FIELD_OBJECT;
		}
		else if (fieldType.equalsIgnoreCase("RECORD")){
			type = NSField.FIELD_RECORD;
		}
		else if (fieldType.equalsIgnoreCase("RECORDREF")){
			type = NSField.FIELD_RECORDREF;
		}
		else{
			type = NSField.FIELD_STRING;
		}
		return type;
	}
	
	//encode dimenstion from string
	static int encodeDimension(String dimension){
		int dim;
		if (dimension.equalsIgnoreCase("ARRAY")){
			dim = NSRecord.DIM_ARRAY;
		}
		else if (dimension.equalsIgnoreCase("TABLE")){
			dim = NSRecord.DIM_TABLE;
		}
		else{
			dim = NSRecord.DIM_SCALAR;
		}
		return dim;
	}
	// --- <<IS-END-SHARED>> ---
}

