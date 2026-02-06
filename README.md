# WxGenerate

Generates Flow Services and Document Types (aka docTypes) on Integration Server programmatically.

## Installation

- Download package archive (ZIP file) from [releases](https://github.com/JahnTech/webmethods-is-wxgenerate/releases)
- Copy the package WxGenerate to <<IS_root>>/instances/default/replicate/inbound
- In IS admin UI go to "Packages / Management". Click on "Install Inbound Releases", and select "WxGenerate.zip".
- Click "Install Release"

### Working with source code

Please go to https://github.com/JahnTech/webmethods-is-pkg-installation for details.

## Package description

The package has the following content.

### Document types

Folder: `wx.generate.pub.docType`

 
| Service             | Description |
| ------------------- |---|
| `addFieldToDoctype`	  | Add a given field to a given doctype |
| `createDoctype`		  | Creates a new doctype |
| `createField`		  | Creates a new field |
| `getJavaWrapperTypes` | Display the possible Java values for a field of type object |
| `saveDoctype`         | Save the docType |

### Flow services

Folder: `wx.generate.pub.service.flow`

| Service             | Description |
| ------------------- |---|
| addExitFrom         | Add an exit step to the Flow |
| addIfThanElse       | Add a if-then-else-branch to the Flow |
| addInvoke           | Add an invoke step to the Flow |
| addMap              |	Add a map line from source to destination field to the Flow |
| addMapStep          | Add a map step to the Flow |
| addSwitchCase       | Add a switch-case step to the Flow |
| checkForPackage     | Check if a given package is present in IS and create if not |
| createFlowRoot      | Create the base Flow service |
| getFlowRoot         |	Get the Flow root |
| saveFlow            | Save the given Flow |

## Samples

Folder: `wx.generate.samples`

- After execution you need to refresh the "Package Navigator" view in Designer.
- If the specified package doesn't exist, it will be created. 

| Service             | Description |
| ------------------- |---|
| generateASimpleFlow | Generates a small Flow service that invokes `pub.flow:debugLog`. |
| generateAMoreComplexFlow | Generates a small Flow service that checks the input and invokes different services, depending on the value. |



______________________
These tools are provided as-is and without warranty or support. Users are free to use, fork and modify them, subject to the license agreement.

webMethods® is a registered trademark of International Business Machines Corporation (“IBM”).


