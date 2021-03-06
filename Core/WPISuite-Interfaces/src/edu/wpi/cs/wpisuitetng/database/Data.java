/*******************************************************************************
 * Copyright (c) 2012 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    ??
 *******************************************************************************/

package edu.wpi.cs.wpisuitetng.database;

import java.util.List;

import edu.wpi.cs.wpisuitetng.modules.Model;

public interface Data 
{
	/**
	 * Saves aTNG passed into the database
	 * @param aTNG
	 * @return true if save was successful, false otherwise
	 */
	public <T> boolean save(T aTNG);
	
	/**
	 * Retrieves the object of the class anObjectQueried with the value
	 * theGivenValue in the field aFieldName
	 * @param anObjectQueried - class of object to be retrieved
	 * @param aFieldName - field that object will be retrieved by
	 * @param theGivenValue - value of aFieldName
	 * @return
	 */
	public List<Model> retrieve(@SuppressWarnings("rawtypes") final Class anObjectQueried, String aFieldName, final Object theGivenValue);
	
	/**
	 * Deletes aTNG from the database
	 * @param aTNG
	 * @return the object that was deleted
	 */
	public <T> T delete(T aTNG);
	
	/**
	 * Updates an object in the database.  
	 * @param anObjectToBeModified - Class of object to be updated
	 * @param fieldName - Field of object that object is being identified by
	 * @param uniqueID - Value of fieldName
	 * @param changeField - Field that will be changed through the update
	 * @param changeValue - Value that changeField will be changed to
	 */
	public void update(final Class anObjectToBeModified, String fieldName, Object uniqueID, String changeField, Object changeValue);
	
	/**
	 * Retrieves all objects of the the same class as aSample in the database
	 * @param aSample - Object whose class will be used 
	 * @return List of all of the objects of the class of aSample
	 */
	public <T> List<T> retrieveAll(T aSample);
	public <T> List<T> deleteAll(T aSample);
}
