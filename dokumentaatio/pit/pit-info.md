Pitistä hieman:

* impl-alakansioissa sijaitsevat luokat ovat funktionaalisesti identtisiä, 
	joten yhden tälläisen luokan testaus kattaa myös muiden impl-luokkien toiminnallisuuden.
* Joissain luokissa satunnaisuus estää testauksen. En saanut selvää, miten Random-luokkaa
	käyttävät metodit tulisi testata. Näitä luokkia esim. Spawner ja Location.

04.03: 

* `Please copy and paste the information and the complete stacktrace below when reporting an issue
VM : Java HotSpot(TM) 64-Bit Server VM
Vendor : Oracle Corporation
Version : 25.60-b23
Uptime : 10552
Input -> 
 1 : -Dclassworlds.conf=C:\Program Files (x86)\tmcbeans\java\maven\bin\m2.conf
 2 : -Dmaven.home=C:\Program Files (x86)\tmcbeans\java\maven
BootClassPathSupported : true

	at java.util.concurrent.FutureTask.report(FutureTask.java:122)
	at java.util.concurrent.FutureTask.get(FutureTask.java:192)
	at org.pitest.util.CommunicationThread.waitToFinish(CommunicationThread.java:60)
	at org.pitest.coverage.execute.CoverageProcess.waitToDie(CoverageProcess.java:35)
	at org.pitest.coverage.execute.DefaultCoverageGenerator.gatherCoverageData(DefaultCoverageGenerator.java:133)
	at org.pitest.coverage.execute.DefaultCoverageGenerator.calculateCoverage(DefaultCoverageGenerator.java:87)
	at org.pitest.coverage.execute.DefaultCoverageGenerator.calculateCoverage(DefaultCoverageGenerator.java:49)
	at org.pitest.mutationtest.tooling.MutationCoverage.runReport(MutationCoverage.java:110)
	at org.pitest.mutationtest.tooling.EntryPoint.execute(EntryPoint.java:103)
	at org.pitest.mutationtest.tooling.EntryPoint.execute(EntryPoint.java:45)
	at org.pitest.maven.RunPitStrategy.execute(RunPitStrategy.java:35)
	at org.pitest.maven.AbstractPitMojo.analyse(AbstractPitMojo.java:381)
	at org.pitest.maven.AbstractPitMojo.execute(AbstractPitMojo.java:336)
	at org.apache.maven.plugin.DefaultBuildPluginManager.executeMojo(DefaultBuildPluginManager.java:101)
	at org.apache.maven.lifecycle.internal.MojoExecutor.execute(MojoExecutor.java:209)
	at org.apache.maven.lifecycle.internal.MojoExecutor.execute(MojoExecutor.java:153)
	at org.apache.maven.lifecycle.internal.MojoExecutor.execute(MojoExecutor.java:145)
	at org.apache.maven.lifecycle.internal.LifecycleModuleBuilder.buildProject(LifecycleModuleBuilder.java:84)
	at org.apache.maven.lifecycle.internal.LifecycleModuleBuilder.buildProject(LifecycleModuleBuilder.java:59)
	at org.apache.maven.lifecycle.internal.LifecycleStarter.singleThreadedBuild(LifecycleStarter.java:183)
	at org.apache.maven.lifecycle.internal.LifecycleStarter.execute(LifecycleStarter.java:161)
	at org.apache.maven.DefaultMaven.doExecute(DefaultMaven.java:320)
	at org.apache.maven.DefaultMaven.execute(DefaultMaven.java:156)
	at org.apache.maven.cli.MavenCli.execute(MavenCli.java:537)
	at org.apache.maven.cli.MavenCli.doMain(MavenCli.java:196)
	at org.apache.maven.cli.MavenCli.main(MavenCli.java:141)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:497)
	at org.codehaus.plexus.classworlds.launcher.Launcher.launchEnhanced(Launcher.java:290)
	at org.codehaus.plexus.classworlds.launcher.Launcher.launch(Launcher.java:230)
	at org.codehaus.plexus.classworlds.launcher.Launcher.mainWithExitCode(Launcher.java:409)
	at org.codehaus.plexus.classworlds.launcher.Launcher.main(Launcher.java:352)
Caused by: org.pitest.util.PitError: Connection reset

Please copy and paste the information and the complete stacktrace below when reporting an issue
VM : Java HotSpot(TM) 64-Bit Server VM
Vendor : Oracle Corporation
Version : 25.60-b23
Uptime : 10552
Input -> 
 1 : -Dclassworlds.conf=C:\Program Files (x86)\tmcbeans\java\maven\bin\m2.conf
 2 : -Dmaven.home=C:\Program Files (x86)\tmcbeans\java\maven
BootClassPathSupported : true

	at org.pitest.util.Unchecked.translateCheckedException(Unchecked.java:20)
	at org.pitest.util.SafeDataInputStream.readString(SafeDataInputStream.java:44)
	at org.pitest.util.SafeDataInputStream.read(SafeDataInputStream.java:50)
	at org.pitest.coverage.execute.Receive.handleTestEnd(Receive.java:69)
	at org.pitest.coverage.execute.Receive.apply(Receive.java:45)
	at org.pitest.util.SocketReadingCallable.receiveResults(SocketReadingCallable.java:64)
	at org.pitest.util.SocketReadingCallable.call(SocketReadingCallable.java:37)
	at org.pitest.util.SocketReadingCallable.call(SocketReadingCallable.java:12)
	at java.util.concurrent.FutureTask.run(FutureTask.java:266)
	at java.lang.Thread.run(Thread.java:745)
Caused by: java.net.SocketException: Connection reset
	at java.net.SocketInputStream.read(SocketInputStream.java:209)
	at java.net.SocketInputStream.read(SocketInputStream.java:141)
	at java.io.BufferedInputStream.fill(BufferedInputStream.java:246)
	at java.io.BufferedInputStream.read1(BufferedInputStream.java:286)
	at java.io.BufferedInputStream.read(BufferedInputStream.java:345)
	at java.io.DataInputStream.readFully(DataInputStream.java:195)
	at java.io.DataInputStream.readFully(DataInputStream.java:169)
	at org.pitest.util.SafeDataInputStream.readString(SafeDataInputStream.java:41)
	... 8 more
19:10:14 PIT >> SEVERE : Coverage generator Minion exited abnormally due to UNKNOWN_ERROR
------------------------------------------------------------------------
BUILD FAILURE
------------------------------------------------------------------------
Total time: 9.151s
Finished at: Fri Mar 04 19:10:14 EET 2016
Final Memory: 12M/309M
------------------------------------------------------------------------
Failed to execute goal org.pitest:pitest-maven:1.1.9:mutationCoverage (default-cli) on project Jarmo-s-Syndrome: Execution default-cli of goal org.pitest:pitest-maven:1.1.9:mutationCoverage failed: Coverage generation minion exited abnormally!

Please copy and paste the information and the complete stacktrace below when reporting an issue
VM : Java HotSpot(TM) 64-Bit Server VM
Vendor : Oracle Corporation
Version : 25.60-b23
Uptime : 10564
Input ->
1 : -Dclassworlds.conf=C:\Program Files (x86)\tmcbeans\java\maven\bin\m2.conf
2 : -Dmaven.home=C:\Program Files (x86)\tmcbeans\java\maven
BootClassPathSupported : true


Please copy and paste the information and the complete stacktrace below when reporting an issue
VM : Java HotSpot(TM) 64-Bit Server VM
Vendor : Oracle Corporation
Version : 25.60-b23
Uptime : 10564
Input ->
1 : -Dclassworlds.conf=C:\Program Files (x86)\tmcbeans\java\maven\bin\m2.conf
2 : -Dmaven.home=C:\Program Files (x86)\tmcbeans\java\maven
BootClassPathSupported : true
-> [Help 1]

To see the full stack trace of the errors, re-run Maven with the -e switch.
Re-run Maven using the -X switch to enable full debug logging.

For more information about the errors and possible solutions, please read the following articles:
[Help 1] http://cwiki.apache.org/confluence/display/MAVEN/PluginExecutionException`
