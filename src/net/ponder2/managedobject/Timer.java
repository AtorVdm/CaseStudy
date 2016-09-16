package net.ponder2.managedobject; /**
 * Copyright � 2007 Kevin Twidle, Imperial College, London, England.
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General
 * Public License along with this library; if not, write to the
 * Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor,
 * Boston, MA 02110-1301 USA
 *
 * Contact: Kevin Twidle <kpt@doc.ic.ac.uk>
 *
 * Created on Jun 6, 2007
 *
 * $Log:$
 */

import net.ponder2.ManagedObject;
import net.ponder2.apt.Ponder2op;
import net.ponder2.objects.P2Object;

/**
 * Implements a timer that produces a repetitive event when started.
 * 
 * @author Kevin Twidle
 * @version $Id:$
 */
public class Timer implements ManagedObject {

  private P2Object myP2Object;
  private SleepThread thread;

  /**
   * Creates a new Timer managed object which will send anEvent when necessary
   * 
   * @param anEvent
   */
  @Ponder2op("event:")
  public Timer(P2Object myP2Object, P2Object anEvent) {
    // myP2Object is magic and is a hidden argument recognised by the compiler
    // If it is present this Managed Object's identifier is given to the
    // constructor in addition to any normal PonderTalk arguments
    // It is needed here because we need it to send an event.
    // We will save it for later.
    this.myP2Object = myP2Object;
    
    //***** Save the event in a field called event
  }

  
  /**
   * Starts the time with an interval of secs seconds
   */
  //****** Start method here for PonderTalk start: command, remember ':' because of the argument
  //****** @Ponder2op(...)
  //****** public void startTimer(int secs) {
  
  //***** The body will contain the following line
  //   thread = start(event, secs);

  
  /**
   * Cancels the timer
   */
  // @Ponder2op ...
  //***** Cancel method here, interrupt the thread
  //***** Use the following line in the body
  //  thread.cancel();
 
  
  
  // ********************** The rest of this file is simply the thread management for the timer ***************
  
  //
  // Useful methods
  //
  /**
   * Starts a timer and sends an event every secs seconds
   * 
   * @param event
   *            the Ponder2 event to send
   * @param secs
   *            the time in seconds to repeat the event
   * @return the active thread
   */
  private SleepThread start(P2Object event, int secs) {
    SleepThread thread = new SleepThread(event, secs);
    thread.start();
    return thread;
  }

  /**
   * Helper class to create a looping, event sending process
   * <p>
   * Start it with
   * 
   * <pre>
   * new SleepThread(event, seconds);
   * </pre>
   * 
   * End it with
   * 
   * <pre>
   * sleepThread.cancel();
   * </pre>
   * 
   */
  class SleepThread extends Thread {

    private P2Object tickEvent;
    private int millisecs;
    boolean running;

    /**
     * Creates an instance of SleepThread
     * 
     * @param event
     *            the event to send
     * @param secs
     *            the number of seconds between events
     */
    public SleepThread(P2Object event, int secs) {
      super();
      tickEvent = event;
      millisecs = secs * 1000;
    }

    /**
     * Cancels the currently running event sending loop
     */
    public void cancel() {
      running = false;
      interrupt();
    }

    /* (non-Javadoc)
     * @see java.lang.Thread#run()
     */
    @Override
    public void run() {
      running = true;
      try {
        while (running) {
          // Sleep for the required time
          Thread.sleep(millisecs);
          tickEvent.operation(myP2Object, "create");
        }
      }
      catch (Exception e) {
        System.out.println("Caught exception " + e);
        running = false;
      }
      System.out.println("Timer thread exiting");
    }

  }

}
