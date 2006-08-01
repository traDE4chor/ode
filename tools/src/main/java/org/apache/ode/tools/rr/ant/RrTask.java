/*
 * File:      $RCSfile$
 * Copyright: (C) 1999-2005 FiveSight Technologies Inc.
 *
 */
package org.apache.ode.tools.rr.ant;

import org.apache.ode.utils.fs.TempFileManager;
import org.apache.ode.utils.rr.ResourceRepositoryBuilder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;


/**
 * ANT Task for constructing PXE resource repositories.
 * <p>
 * Usage:
 * <pre>
 *    &lt;rr failOnError="yes/no" rrdir="destination.rr"&gt;
 *        &lt;rrfileset ... /&gt;*
 *        &lt;resource ... /&gt;*
 *        &lt;alias ... /&gt;*
 *    &lt;/rr&gt;
 * </pre>
 *
 * Each resource repository may contain sub-elements corresponding to: collections
 * of resource repositories (<code>rrfileset</codde>s), individual resources
 * (<code>resource</code>s), or resource aliases (<code>alias</code>es).
 * @see AliasElement
 * @see ResourceElement
 * @see ResourceFileSet
 * </p>
 */
public class RrTask extends Task {
  
  private List<RrOperation> _operations = new ArrayList<RrOperation>();
  private boolean _failOnError = true;
  private File _rr;

  /**
   * Returns the fail-on-error flag.
   * @return true or false
   */
  public boolean getFailOnError() {
    return _failOnError;
  }

  /**
   * Set the fail-on-error flag.
   * @param b value of the fail-on-error flag
   */
  public void setFailOnError(boolean b) {
    _failOnError = b;
  }

  /**
   * Set the destination <em>directory</em> (resource repositories are
   * directories, not files).
   * @param f
   */
  public void setRrDir(File f) {
    _rr = f;
  }
  
  public void addRrFileSet(ResourceFileSet fs) {
    _operations.add(fs);
  }

  public void addConfiguredResource(ResourceElement ue) {
    _operations.add(ue);
  }

  public void addConfiguredAlias(AliasElement uae) {
    _operations.add(uae);
  }
  
  public void execute() throws BuildException {
    ResourceRepositoryBuilder wcr;
    _rr.mkdirs();

    try {
      wcr = new ResourceRepositoryBuilder(_rr);
    } catch (IOException ioex) {
      TempFileManager.cleanup();
      log("I/O Error",Project.MSG_ERR);
      if (_failOnError) {
        throw new BuildException(ioex);
      }
      log("Aborting RR modification operation.",Project.MSG_INFO);
      return;
    }

    Iterator<RrOperation> it = _operations.iterator();
    while (it.hasNext()) {
      RrOperation o = it.next();
      try {
        o.execute(this, wcr);
      } catch (Exception e) {
        log("Error processing " + o, Project.MSG_ERR);
        if (_failOnError) {
          throw new BuildException(e);
        }
        break;
      }
    }

    TempFileManager.cleanup();
  }

}
