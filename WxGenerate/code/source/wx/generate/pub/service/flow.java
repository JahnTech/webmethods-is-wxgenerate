package wx.generate.pub.service;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.lang.ns.rsd.RESTDefinition;
import com.wm.lang.ns.rsd.RESTResource;
import com.wm.lang.flow.FlowBranch;
import com.wm.lang.flow.FlowElement;
import com.wm.lang.flow.FlowExit;
import com.wm.lang.flow.FlowInvoke;
import com.wm.lang.flow.FlowMap;
import com.wm.lang.flow.FlowMapCopy;
import com.wm.lang.flow.FlowMapSet;
import com.wm.lang.flow.FlowPathInfo;
import com.wm.lang.flow.FlowRoot;
import com.wm.lang.ns.NSField;
import com.wm.lang.ns.NSName;
import com.wm.lang.ns.NSNode;
import com.wm.lang.ns.NSRecord;
import com.wm.lang.ns.NSRecordUtil;
import com.wm.lang.ns.NSSignature;
import com.wm.util.JavaWrapperType;
import com.wm.app.b2b.server.BaseService;
import com.wm.app.b2b.server.FlowSvcImpl;
import com.wm.app.b2b.server.ServerAPI;
import com.wm.app.b2b.server.ServiceSetupException;
import com.wm.app.b2b.server.ns.Namespace;
import java.util.ArrayList;
import java.util.Map;
import java.util.StringTokenizer;
import com.wm.lang.flow.FlowSequence;
import com.wm.lang.flow.MapSet;
import com.wm.lang.ns.rsd.RESTApplicationInfo;
// --- <<IS-END-IMPORTS>> ---

public final class flow

{
	// ---( internal utility methods )---

	final static flow _instance = new flow();

	static flow _newInstance() { return new flow(); }

	static flow _cast(Object o) { return (flow)o; }

	// ---( server methods )---




	public static final void addExitFrom (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(addExitFrom)>> ---
		// @sigtype java 3.5
		// [i] object:0:required parentFlowElem
		// [i] field:0:required exitLevel {"Flow","Parent","Loop"}
		// [i] field:0:required signal {"FAILURE","SUCCESS"}
		// [o] object:0:required parentFlowElem
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		try {
			FlowElement	parentFlowElem = (FlowElement) IDataUtil.get( pipelineCursor, "parentFlowElem" );
			String	exitLevel = IDataUtil.getString( pipelineCursor, "exitLevel" );
			String	signal = IDataUtil.getString( pipelineCursor, "signal" );
		
			IData feIData = IDataFactory.create();
			String exitFrom = exitLevel.equals("Flow") ? "$flow" : exitLevel.equals("Parent") ? "$parent" : "$loop";
			IDataUtil.put(feIData.getCursor(),FlowExit.KEY_EXIT_FROM, exitFrom);
			IDataUtil.put(feIData.getCursor(),FlowExit.KEY_EXIT_SIGNAL, signal);
			FlowExit fe = new FlowExit(feIData);
			parentFlowElem.addNode(fe);
		
			// pipeline
			IDataUtil.put( pipelineCursor, "parentFlowElem", parentFlowElem );
		} finally {
			pipelineCursor.destroy();
		}
			
		// --- <<IS-END>> ---

                
	}



	public static final void addIfThanElse (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(addIfThanElse)>> ---
		// @sigtype java 3.5
		// [i] object:0:required parentFlowElem
		// [i] field:0:required ifCond
		// [i] field:0:required elseCond
		// [o] object:0:required parentFlowElem
		// [o] object:0:required ifFlowElem
		// [o] object:0:required elseFlowElem
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		try {
			FlowElement	parentFlowElem = (FlowElement) IDataUtil.get( pipelineCursor, "parentFlowElem" );
			String	ifCond = IDataUtil.getString( pipelineCursor, "ifCond" );
			String	elseCond = IDataUtil.getString( pipelineCursor, "elseCond" );
		
			IData branchIdata = IDataFactory.create();
			IDataUtil.put(branchIdata.getCursor(), "evaluate-labels", "true");
			FlowBranch fb = new FlowBranch(branchIdata);
			branchIdata.getCursor().destroy();
		
			FlowSequence fs1 = new FlowSequence(null);
			fs1.setName(ifCond);
			FlowSequence fs2 = new FlowSequence(null);
			fs2.setName(elseCond);
		
		
			fb.addNode(fs1);
			fb.addNode(fs2);
		
			parentFlowElem.addNode(fb);
		
			// pipeline
			IDataUtil.put( pipelineCursor, "parentFlowElem", parentFlowElem );
			IDataUtil.put( pipelineCursor, "ifFlowElem", fs1 );
			IDataUtil.put( pipelineCursor, "elseFlowElem", fs2 );
		} finally {
			pipelineCursor.destroy();
		}
			
		// --- <<IS-END>> ---

                
	}



	public static final void addInvoke (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(addInvoke)>> ---
		// @sigtype java 3.5
		// [i] object:0:required parentFlowElem
		// [i] field:0:required serviceName
		// [o] object:0:required parentFlowElem
		// [o] object:0:required serviceFlowElem
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		try {
			FlowElement	parentFlowElem = (FlowElement) IDataUtil.get( pipelineCursor, "parentFlowElem" );
			String	serviceName = IDataUtil.getString( pipelineCursor, "serviceName" );
		
		
			IData invokeData = IDataFactory.create();
			IDataUtil.put(invokeData.getCursor(),FlowInvoke.KEY_INVOKE_SERVICE, serviceName);
			invokeData.getCursor().destroy();
		
			FlowInvoke fi = new FlowInvoke(invokeData);
			parentFlowElem.addNode(fi);
		
			// pipeline
			IDataUtil.put( pipelineCursor, "parentFlowElem", parentFlowElem );
			IDataUtil.put( pipelineCursor, "serviceFlowElem", fi );
		} finally {
			pipelineCursor.destroy();
		}
			
		// --- <<IS-END>> ---

                
	}



	public static final void addMap (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(addMap)>> ---
		// @sigtype java 3.5
		// [i] object:0:required parentFlowElem
		// [i] field:0:optional srcFieldName
		// [i] field:0:required destFieldName
		// [i] field:0:required destFieldValue
		// [i] field:0:required mapSide {"inputMap","outputMap"}
		// [o] object:0:required parentFlowElem
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		try {
			FlowElement	parentFlowElem = (FlowElement) IDataUtil.get( pipelineCursor, "parentFlowElem" );
			String	srcFieldName = IDataUtil.getString( pipelineCursor, "srcFieldName" );
			String	destFieldName = IDataUtil.getString( pipelineCursor, "destFieldName" );
			String	destFieldValue = IDataUtil.getString( pipelineCursor, "destFieldValue" );
			String	mapSide = IDataUtil.getString( pipelineCursor, "mapSide" );
		
			FlowMapCopy fmc = null;
			FlowMapSet fms = null;
			if (srcFieldName == null && destFieldName != null && destFieldValue != null){
				fms = new FlowMapSet(null);
				fms.setOverwrite(true);
				fms.setVariables(false);
				fms.setEncoding(FlowMapSet.ENCODING_XML_VALUES);
				fms.setName("Setter");
				fms.setField( destFieldName.charAt(0) == '/' ? destFieldName : "/"+destFieldName+";1;0" );
				fms.setInput(destFieldValue);
			} else {
				fmc = new FlowMapCopy(null);
				fmc.setMapFrom( srcFieldName.charAt(0) == '/' ? srcFieldName : "/"+srcFieldName+";1;0");
				if (fmc.getMapFrom().startsWith("/null")) throw new ServiceException("WxGenerate: map src "+srcFieldName+" not found");
				fmc.setMapTo( destFieldName.charAt(0) == '/' ? destFieldName : "/"+destFieldName+";1;0");
				if (fmc.getMapTo().startsWith("/null")) throw new ServiceException("WxGenerate: map dest "+destFieldName+" not found");
			}
			if (mapSide.equals("inputMap")) {
				if (parentFlowElem.getInputMap() == null){
					parentFlowElem.setInputMap(new FlowMap(null));
				}
				parentFlowElem.getInputMap().addNode( fmc != null ? fmc : fms );
			} else {
				if (parentFlowElem.getOutputMap() == null){
					parentFlowElem.setOutputMap(new FlowMap(null));
				}
				parentFlowElem.getOutputMap().addNode( fmc != null ? fmc : fms );
			}
		
			IDataUtil.put( pipelineCursor, "parentFlowElem", parentFlowElem );
		} finally {
			pipelineCursor.destroy();
		}
			
		// --- <<IS-END>> ---

                
	}



	public static final void addMapStep (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(addMapStep)>> ---
		// @sigtype java 3.5
		// [i] object:0:required parentFlowElem
		// [i] field:0:required srcFieldName
		// [i] field:0:required destFieldName
		// [o] object:0:required parentFlowElem
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		try {
			FlowElement	parentFlowElem = (FlowElement) IDataUtil.get( pipelineCursor, "parentFlowElem" );
			String	srcFieldName = IDataUtil.getString( pipelineCursor, "srcFieldName" );
			String	destFieldName = IDataUtil.getString( pipelineCursor, "destFieldName" );
		
			FlowMapCopy fmc = new FlowMapCopy(null);
			fmc.setMapFrom( srcFieldName.charAt(0) == '/' ? srcFieldName : "/"+srcFieldName+";1;0");
			if (fmc.getMapFrom().startsWith("/null")) throw new ServiceException("WxGenerate: map src "+srcFieldName+" not found");
			fmc.setMapTo( destFieldName.charAt(0) == '/' ? destFieldName : "/"+destFieldName+";1;0");
			if (fmc.getMapTo().startsWith("/null")) throw new ServiceException("WxGenerate: map dest "+destFieldName+" not found");
		
			FlowMap fm = new FlowMap(null);
			fm.addNode(fmc);
			parentFlowElem.addNode(fm);
		
			IDataUtil.put( pipelineCursor, "parentFlowElem", parentFlowElem );
		} finally {
			pipelineCursor.destroy();
		}
			
		// --- <<IS-END>> ---

                
	}



	public static final void addSwitchCase (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(addSwitchCase)>> ---
		// @sigtype java 3.5
		// [i] object:0:required parentFlowElem
		// [i] field:1:required switchList
		// [o] object:0:required parentFlowElem
		// [o] object:1:required caseFlowElemList
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		try {
			FlowElement	parentFlowElem = (FlowElement) IDataUtil.get( pipelineCursor, "parentFlowElem" );
			String[]	switchList = IDataUtil.getStringArray( pipelineCursor, "switchList" );
		
			FlowSequence[] caseFlowElemList = new FlowSequence[switchList.length];
		
			IData branchIdata = IDataFactory.create();
			IDataUtil.put(branchIdata.getCursor(), "evaluate-labels", "true");
			FlowBranch fb = new FlowBranch(branchIdata);
			branchIdata.getCursor().destroy();
		
			int i = 0;
			for (String switchString : switchList){
				caseFlowElemList[i] = new FlowSequence(null);
				caseFlowElemList[i].setName(switchString);
				fb.addNode(caseFlowElemList[i]);
				i++;
			}
		
			parentFlowElem.addNode(fb);
		
			// pipeline
			IDataUtil.put( pipelineCursor, "parentFlowElem", parentFlowElem );
			IDataUtil.put( pipelineCursor, "caseFlowElemList", caseFlowElemList );
		} finally {
			pipelineCursor.destroy();
		}
			
		// --- <<IS-END>> ---

                
	}



	public static final void createFlowRoot (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(createFlowRoot)>> ---
		// @sigtype java 3.5
		// [o] object:0:required flowRoot
		FlowRoot root = new FlowRoot(IDataFactory.create(
				new Object[][]{ 
					{ FlowElement.KEY_ELEMENT_TYPE, FlowElement.TYPE_ROOT }
				}));
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		//Object flowRoot = new Object();
		IDataUtil.put( pipelineCursor, "flowRoot", root.getFlowRoot() );
		pipelineCursor.destroy();
		
			
		// --- <<IS-END>> ---

                
	}



	public static final void getFlowRoot (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getFlowRoot)>> ---
		// @sigtype java 3.5
		// [i] field:0:required flowName
		// [o] object:0:required flowRoot
		// [o] object:0:required sig
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		try {
			String	flowName = IDataUtil.getString( pipelineCursor, "flowName" );
		
			FlowSvcImpl rootFlowSvc = (FlowSvcImpl) Namespace.getService(NSName.create(flowName));
			FlowRoot fr = rootFlowSvc.getFlowRoot();
		
			// pipeline
			IDataUtil.put( pipelineCursor, "flowRoot", fr );
			IDataUtil.put( pipelineCursor, "sig", rootFlowSvc.getSignature() );
		} finally {
			pipelineCursor.destroy();
		}
			
			
		// --- <<IS-END>> ---

                
	}



	public static final void saveFlow (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(saveFlow)>> ---
		// @sigtype java 3.5
		// [i] field:0:required pkgName
		// [i] field:0:required serviceName
		// [i] object:0:required flowRoot
		// [i] recref:1:required inputList wx.generate.impl.doc:InputOutputSignature
		// [i] recref:0:required output wx.generate.impl.doc:InputOutputSignature
		//   pipeline
				IDataCursor pipelineCursor = pipeline.getCursor();
				String	pkgName = IDataUtil.getString( pipelineCursor, "pkgName" );
				String	serviceName = IDataUtil.getString( pipelineCursor, "serviceName" );
				FlowRoot flowRoot = (FlowRoot) IDataUtil.get(pipelineCursor, "flowRoot");
				NSField[] inp = null;
				NSField outp = null;
				boolean pkgExists = false;
				
				//System.out.println("running registerService with the following parms:\n\n");
				for (String str : ServerAPI.getEnabledPackages()) {
					if (str.equals(pkgName)){
						pkgExists = true;
						break;
					}
				}
				
				if (!pkgExists){
					//System.out.println("create missing package here!!!");
					NSName name = NSName.create("wx.generate.pub.pkg:createPackage");
					IData tmp = IDataFactory.create();
					IDataUtil.put(tmp.getCursor(), "package", pkgName);
					try {
						Service.doInvoke(name, tmp);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						throw new ServiceException(e);
					}
					tmp.getCursor().destroy();
				}
				
				
				// NSFieldInputList
				IData[]	NSFieldInputList = IDataUtil.getIDataArray( pipelineCursor, "inputList" );
				if ( NSFieldInputList != null)
				{
					inp = new NSField[NSFieldInputList.length];
					for ( int i = 0; i < NSFieldInputList.length; i++ )
					{
						IDataCursor NSFieldInputListCursor = NSFieldInputList[i].getCursor();
							String	FieldName = IDataUtil.getString( NSFieldInputListCursor, "fieldName" );
							String	FieldType = IDataUtil.getString( NSFieldInputListCursor, "fieldType" );
							String	Dimension = IDataUtil.getString( NSFieldInputListCursor, "dimension" );
							String	ReferenceNS = IDataUtil.getString( NSFieldInputListCursor, "referenceDT" );
						NSFieldInputListCursor.destroy();
						//System.out.println("input("+i+"): "+FieldName+" --- "+FieldType+" --- "+Dimension+" --- "+ReferenceNS);
						inp[i] = NSRecordUtil.createField(FieldName,encodeFieldType(FieldType),encodeDimension(Dimension),Namespace.current(),ReferenceNS);
						if (FieldType.equals("OBJECT")) {
							inp[i].setJavaWrapperType(encodeJavaWrapper(ReferenceNS));
						}
					}
				}
				
				// NSFieldOutput
				IData	NSFieldOutput = IDataUtil.getIData( pipelineCursor, "output" );
				if ( NSFieldOutput != null)
				{
					IDataCursor NSFieldOutputCursor = NSFieldOutput.getCursor();
						String	FieldName_1 = IDataUtil.getString( NSFieldOutputCursor, "fieldName" );
						String	FieldType_1 = IDataUtil.getString( NSFieldOutputCursor, "fieldType" );
						String	Dimension_1 = IDataUtil.getString( NSFieldOutputCursor, "dimension" );
						String	ReferenceNS_1 = IDataUtil.getString( NSFieldOutputCursor, "referenceDT" );
					NSFieldOutputCursor.destroy();
					System.out.println("output: "+FieldName_1+" --- "+FieldType_1+" --- "+Dimension_1+" --- "+ReferenceNS_1);
					outp = NSRecordUtil.createField(FieldName_1,encodeFieldType(FieldType_1),encodeDimension(Dimension_1),Namespace.current(),ReferenceNS_1);
					if (FieldType_1.equals("OBJECT")) {
						outp.setJavaWrapperType(encodeJavaWrapper(ReferenceNS_1));
					}
				}
				pipelineCursor.destroy();
				
				FlowRoot root = flowRoot;
				
				
				NSSignature sig = null;
				NSRecord sigInRecord = new NSRecord(Namespace.current(), "inRecord", NSRecord.DIM_SCALAR);
				NSRecord sigOutRecord = new NSRecord(Namespace.current(), "outRecord", NSRecord.DIM_SCALAR);
				
				if (inp != null) {
					for (NSField f : inp) {
							sigInRecord.addField(f);
					}
				}
				
				if (outp != null) {
					sigOutRecord.addField( outp );
				}
				
				
				sig = new NSSignature(sigInRecord, sigOutRecord);
				
				
				NSName srvName = null;
				try {
					//System.out.println("Package: "+pkgName+"\n");
					//System.out.println("Servicename: "+serviceName+"\n");
					//System.out.println("Signature: "+sig.toString()+"\n");
					srvName = NSName.create(serviceName);
					ServerAPI.registerFlowService(pkgName, srvName, root, sig);
				} catch (ServiceSetupException e) {
					// TODO Auto-generated catch block
					throw new ServiceException(e);
				}
		
				
			
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
		
		//encode dimension from string
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
		
		static int encodeJavaWrapper(String type) {
			//for (String str : JavaWrapperType.getNames()){
			//	System.out.println("java type: "+str);
			//}
			//System.out.println("JavaWrapperType.getNames()"+JavaWrapperType.getNames());
			for (int i = 0; i < JavaWrapperType.getNames().length; i++)  {
				if (JavaWrapperType.getNames()[i].equalsIgnoreCase(type)) {
					return i;
				}
			}
			return -1;
		}
		
		
	// --- <<IS-END-SHARED>> ---
}

