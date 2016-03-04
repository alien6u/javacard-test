# javacard-test
a simple javacard demo.

Running the RMIPurse Sample in Eclipse

The RMIPurse sample consists of two projects: a Java Card project with the Java Card applet, and a Java SE project with a Java application that communicates with the applet using RMI.

Start Eclipse. Sample_Platform and Sample_Device must already be created.
This sample uses the rmic tool, which is provided with the development kit.
Create a launch configuration to specify launch parameters for the rmic tool:
From the workbench menu bar, select Run, External Tools and External Tools Configurations...
On the External Tools Configurations dialog, select Program in the left hand list of launch configuration types, and then click New launch configuration in the toolbar.
On the Create, manage, and run configurations dialog, enter the following text for these fields:
Name: rmic

Location: ${env_var:JAVA_HOME}\bin\rmic.exe

Working directory: ${project_loc}

Arguments: ¡ªnowarn ¡ªv1.2 -classpath ".;${project_classpath}" -d .\stubs ${java_type_name}   (in windows create "stubs" use "stubs" not ".\stubs")

Click Apply and Close.
Import the RMIPurse_Applet Java Card project and the RMIPurse_Client Java project into your workspace. You can import both projects in the same Import wizard. If the builds don't start automatically, start them manually.
The RMIPurse_Applet project build creates apdu_scripts and deliverables directories.
In Java Card View, double-click on Sample_Device. In the Properties for Sample_Device dialog, select the CREF tab:
In the Output file with EEPROM data field, type a file name to be used for saving EEPROM between simulator sessions, e.g., RMIPurse.eeprom. The file will be automatically created in the bin directory. Later, after the sample run, you can safely delete it.
Clear the Input file with EEPROM data and the Combined (input and output) file for EEPROM data fields.
Clear Do not open APDU console.
Click OK.
In Java Card View, right-click on Sample_Device and select Start.
The simulator starts and you can see that Sample_Device console is created.
In the Sample_Device console toolbar, click on the Select Script drop-down and execute:
cap-com.sun.jcclassic.samples.rmi and
create-com.sun.jcclassic.samples.rmi.PurseApplet
Stop the Sample_Device.
In the Package Explorer view, expand RMIPurse_Applet\src\com.sun.jcclassic.samples.rmi and select PurseImpl.java. From the Eclipse menu bar, select Run, then External Tools and rmic. (If this is the first rmic execution, select Run, External Tools , External Tools Configurations... and click Run).
Click F5 to refresh the view, and you should see PurseImpl_Stub.class in the RMIPurse_Applet\stubs\com\sun\jcclassic\samples\rmi directory.
In Java Card View, double-click on Sample_Device. In the Properties for Sample_Device dialog, select the CREF tab:
In the Input file with EEPROM data field, click Browse... and select the RMIPurse.eeprom file.
Clear the Output file for EEPROM data field.
Select Do not open APDU console.
Click OK.
In Java Card View, right-click on Sample_Device and select Start.
The simulator starts and you can see the output in the Console view.
In the Package Explorer view, expand RMIPurse_Client project, navigate to PurseClient.java, right-click on it, and select Run As and Java Application.
You see the application output in the console. Now you can compare it with the contents of rmidemo.expected.output file.
