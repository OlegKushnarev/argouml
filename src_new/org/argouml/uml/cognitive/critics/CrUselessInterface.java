// $Id$
// Copyright (c) 1996-99 The Regents of the University of California. All
// Rights Reserved. Permission to use, copy, modify, and distribute this
// software and its documentation without fee, and without a written
// agreement is hereby granted, provided that the above copyright notice
// and this paragraph appear in all copies.  This software program and
// documentation are copyrighted by The Regents of the University of
// California. The software program and documentation are supplied "AS
// IS", without any accompanying services from The Regents. The Regents
// does not warrant that the operation of the program will be
// uninterrupted or error-free. The end-user understands that the program
// was developed for research purposes and is advised not to rely
// exclusively on the program for any reason.  IN NO EVENT SHALL THE
// UNIVERSITY OF CALIFORNIA BE LIABLE TO ANY PARTY FOR DIRECT, INDIRECT,
// SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES, INCLUDING LOST PROFITS,
// ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF
// THE UNIVERSITY OF CALIFORNIA HAS BEEN ADVISED OF THE POSSIBILITY OF
// SUCH DAMAGE. THE UNIVERSITY OF CALIFORNIA SPECIFICALLY DISCLAIMS ANY
// WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
// MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE. THE SOFTWARE
// PROVIDED HEREUNDER IS ON AN "AS IS" BASIS, AND THE UNIVERSITY OF
// CALIFORNIA HAS NO OBLIGATIONS TO PROVIDE MAINTENANCE, SUPPORT,
// UPDATES, ENHANCEMENTS, OR MODIFICATIONS.

// File: CrUselessInterface.java
// Classes: CrUselessInterface
// Original Author: jrobbins@ics.uci.edu

package org.argouml.uml.cognitive.critics;

import java.util.Iterator;
import org.argouml.cognitive.Designer;
import org.argouml.cognitive.Goal;
import org.argouml.cognitive.critics.Critic;
import org.argouml.model.ModelFacade;

// Use Model through ModelFacade

/** A critic to detect when a class can never have instances (of
 *  itself of any subclasses). */

public class CrUselessInterface extends CrUML {

    /**
     * The constructor.
     * 
     */
    public CrUselessInterface() {
	setHeadline("Define Class to Implement <ocl>self</ocl>");
	addSupportedDecision(CrUML.decINHERITANCE);
	addSupportedGoal(Goal.UNSPEC);
	setKnowledgeTypes(Critic.KT_COMPLETENESS);
	addTrigger("realization");
    }

    /**
     * @see org.argouml.uml.cognitive.critics.CrUML#predicate2(
     * java.lang.Object, org.argouml.cognitive.Designer)
     */
    public boolean predicate2(Object dm, Designer dsgr) {
	if (!ModelFacade.isAInterface(dm))
	    return NO_PROBLEM;
	
	if (!ModelFacade.isPrimaryObject(dm))
	    return NO_PROBLEM;


	Iterator iter = ModelFacade.getSupplierDependencies(dm).iterator();

	while (iter.hasNext())
	    if (ModelFacade.isRealize(iter.next()))
		return NO_PROBLEM;

	return PROBLEM_FOUND;
    }

} /* end class CrUselessInterface */
