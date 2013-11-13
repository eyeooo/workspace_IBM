/*******************************************************************************
 * Copyright (c) 2012 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

/*
 * Browser example snippet: listen for DOM mousedown events with javascript (improved
 * implementation for Eclipse/SWT 3.5 and newer).
 *
 * For a list of all SWT example snippets see
 * http://www.eclipse.org/swt/snippets/
 */
import org.eclipse.swt.*;
import org.eclipse.swt.browser.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

public class CopyOfSnippet362 {

public static void main(String [] args) {
	final String SCRIPT = "function aa() {alert('hah');location.href='http:\\www.google.com';}";
	Display display = new Display();
	final Shell shell = new Shell(display);
	shell.setLayout(new FillLayout());
	final Browser browser;
	try {
		browser = new Browser(shell, SWT.NONE);
	} catch (SWTError e) {
		System.out.println("Could not instantiate Browser: " + e.getMessage());
		display.dispose();
		return;
	}
//	final BrowserFunction function = new CustomFunction (browser, "mouseDownHappened");
	browser.addProgressListener (new ProgressAdapter () {
		public void completed (ProgressEvent event) {
			System.out.println("work.");
//			browser.addLocationListener (new LocationAdapter () {
//				public void changed (LocationEvent event) {
//					browser.removeLocationListener (this);
////					function.dispose ();
//				}
//			});
			browser.execute("location.href='s?wd=ip&rsv_spt=1&issp=1&rsv_bp=0&ie=utf-8&tn=baiduhome_pg&rsv_sug3=2&rsv_sug=0&rsv_sug4=211&rsv_sug1=1'");
			browser.removeProgressListener (this);
		}
	});
	browser.setUrl("www.baidu.com");
	shell.open();
	while (!shell.isDisposed()) {
		if (!display.readAndDispatch()) display.sleep();
	}
	display.dispose();
}

static class CustomFunction extends BrowserFunction {
	CustomFunction (Browser browser, String name) {
		super (browser, name);
	}
	public Object function (Object[] arguments) {
		System.out.println ("mouseDown: " + ((Number)arguments[0]).intValue() + "," + ((Number)arguments[1]).intValue());
		return null;
	}
}
}