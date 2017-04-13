package org.apache.hadoop.hive.ql.parse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.ql.exec.Utilities;
import org.apache.hadoop.hive.ql.parse.DumpMetaData.DUMPTYPE;

public class DumpMetaData {
  // wrapper class for reading and writing metadata about a dump
  // responsible for _dumpmetadata files

  public enum DUMPTYPE {
    BOOTSTRAP("BOOTSTRAP"),
    INCREMENTAL("INCREMENTAL"),
    EVENT_CREATE_TABLE("EVENT_CREATE_TABLE"),
    EVENT_ADD_PARTITION("EVENT_ADD_PARTITION"),
    EVENT_DROP_TABLE("EVENT_DROP_TABLE"),
    EVENT_DROP_PARTITION("EVENT_DROP_PARTITION"),
    EVENT_ALTER_TABLE("EVENT_ALTER_TABLE"),
    EVENT_RENAME_TABLE("EVENT_RENAME_TABLE"),
    EVENT_ALTER_PARTITION("EVENT_ALTER_PARTITION"),
    EVENT_RENAME_PARTITION("EVENT_RENAME_PARTITION"),
    EVENT_INSERT("EVENT_INSERT"),
    EVENT_UNKNOWN("EVENT_UNKNOWN");
  
    String type = null;
    DUMPTYPE(String type) {
      this.type = type;
    }
  
    @Override
    public String toString(){
      return type;
    }
  
  }

  private DumpMetaData.DUMPTYPE dumpType;
  private Long eventFrom = null;
  Long eventTo = null;
  private String payload = null;
  private boolean initialized = false;

  private final Path dumpRoot;
  private final Path dumpFile;
  private final HiveConf hiveConf;
  private Path cmRoot;

  public DumpMetaData(Path dumpRoot, HiveConf hiveConf) {
    this.dumpRoot = dumpRoot;
    this.hiveConf = hiveConf;
    dumpFile = new Path(dumpRoot, ReplicationSemanticAnalyzer.DUMPMETADATA);
  }

  public DumpMetaData(Path dumpRoot, DumpMetaData.DUMPTYPE lvl, Long eventFrom, Long eventTo, Path cmRoot,
      HiveConf hiveConf){
    this(dumpRoot,hiveConf);
    setDump(lvl, eventFrom, eventTo, cmRoot);
  }

  public void setDump(DumpMetaData.DUMPTYPE lvl, Long eventFrom, Long eventTo, Path cmRoot){
    this.dumpType = lvl;
    this.eventFrom = eventFrom;
    this.eventTo = eventTo;
    this.initialized = true;
    this.cmRoot = cmRoot;
  }

  public void loadDumpFromFile() throws SemanticException {
    try {
      // read from dumpfile and instantiate self
      FileSystem fs = dumpFile.getFileSystem(hiveConf);
      BufferedReader br = new BufferedReader(new InputStreamReader(fs.open(dumpFile)));
      String line = null;
      if ( (line = br.readLine()) != null){
        String[] lineContents = line.split("\t", 5);
        setDump(DumpMetaData.DUMPTYPE.valueOf(lineContents[0]), Long.valueOf(lineContents[1]), Long.valueOf(lineContents[2]),
            new Path(lineContents[3]));
        setPayload(lineContents[4].equals(Utilities.nullStringOutput) ? null : lineContents[4]);
        ReplChangeManager.setCmRoot(cmRoot);
      } else {
        throw new IOException("Unable to read valid values from dumpFile:"+dumpFile.toUri().toString());
      }
    } catch (IOException ioe){
      throw new SemanticException(ioe);
    }
  }

  public DumpMetaData.DUMPTYPE getDumpType() throws SemanticException {
    initializeIfNot();
    return this.dumpType;
  }

  public String getPayload() throws SemanticException {
    initializeIfNot();
    return this.payload;
  }

  public void setPayload(String payload) {
    this.payload = payload;
  }

  public Long getEventFrom() throws SemanticException {
    initializeIfNot();
    return eventFrom;
  }

  public Long getEventTo() throws SemanticException {
    initializeIfNot();
    return eventTo;
  }

  public Path getCmRoot() {
    return cmRoot;
  }

  public void setCmRoot(Path cmRoot) {
    this.cmRoot = cmRoot;
  }

  public Path getDumpFilePath() {
    return dumpFile;
  }

  public boolean isIncrementalDump() throws SemanticException {
    initializeIfNot();
    return (this.dumpType == DumpMetaData.DUMPTYPE.INCREMENTAL);
  }

  private void initializeIfNot() throws SemanticException {
    if (!initialized){
      loadDumpFromFile();
    }
  }

  public void write() throws SemanticException {
    ReplicationSemanticAnalyzer.writeOutput(
        Arrays.asList(
            dumpType.toString(),
            eventFrom.toString(),
            eventTo.toString(),
            cmRoot.toString(),
            payload),
        dumpFile,
        hiveConf
    );
  }

}