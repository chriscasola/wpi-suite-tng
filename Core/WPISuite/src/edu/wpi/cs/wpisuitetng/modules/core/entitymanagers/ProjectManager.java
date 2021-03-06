/*******************************************************************************
 * Copyright (c) 2012 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: mpdelladonna
 * rchamer
 * twack
 *    
 *******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.core.entitymanagers;

import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import edu.wpi.cs.wpisuitetng.database.Data;
import edu.wpi.cs.wpisuitetng.exceptions.BadRequestException;
import edu.wpi.cs.wpisuitetng.exceptions.ConflictException;
import edu.wpi.cs.wpisuitetng.exceptions.NotFoundException;
import edu.wpi.cs.wpisuitetng.exceptions.WPISuiteException;
import edu.wpi.cs.wpisuitetng.Session;
import edu.wpi.cs.wpisuitetng.modules.EntityManager;
import edu.wpi.cs.wpisuitetng.modules.Model;
import edu.wpi.cs.wpisuitetng.modules.core.models.Project;

public class ProjectManager implements EntityManager<Project>{

	Class<Project> project = Project.class;
	Gson gson;
	Data data;
	
	public ProjectManager(Data data)
	{
		gson = new Gson();
		this.data = data;
	}
	
	@Override
	public Project makeEntity(Session s, String content) throws WPISuiteException {
		
		Project p;
		try{
			p = gson.fromJson(content, project);
		} catch(JsonSyntaxException e){
			throw new BadRequestException();
		}
		
		if(getEntity(s,p.getIdNum())[0] == null)
		{
			save(s,p);
		}
		else
		{
			throw new ConflictException();
		}
		
		return p;
	}

	@Override
	public Project[] getEntity(Session s, String id) 
	{
		Project[] m = new Project[1];
		if(id.equalsIgnoreCase(""))
		{
			return getAll(s);
		}
		else
		{
			return data.retrieve(project, "idNum", id).toArray(m);
		}
	}
	
	/**
	 * returns a project without requiring a session, 
	 * specifically for the scenario where a session needs to be created.
	 * only ever returns one project, "" is not a valid argument;
	 * 
	 * @param id - the id of the user, in this case it's the idNum
	 * @return a list of matching projects
	 * @throws NotFoundException if the project cannot be found
	 */
	public Project[] getEntity(String id) throws NotFoundException
	{
		Project[] m = new Project[1];
		if(id.equalsIgnoreCase(""))
		{
			throw new NotFoundException();
		}
		else
		{
			m = data.retrieve(project, "idNum", id).toArray(m);
			
			if(m[0] == null)
			{
				throw new NotFoundException();
			}
			else
			{
				return m;
			}
		}
	}

	@Override
	public Project[] getAll(Session s) {
		Project[] ret = new Project[1];
		ret = data.retrieveAll(new Project("","")).toArray(ret);
		return ret;
	}

	@Override
	public void save(Session s, Project model) throws WPISuiteException {
		if(data.save(model))
		{
			return ;
		}
		else
		{
			throw new WPISuiteException();
		}
		
	}

	@Override
	public boolean deleteEntity(Session s1, String id)
	{
		
		Model m = data.delete(data.retrieve(project, "idNum", id).get(0));
		
		return (m != null) ? true : false;
		
	}
	
	@Override
	public void deleteAll(Session s) {
		data.deleteAll(new Project("",""));
	}

	@Override
	public int Count() {
		
		return 0;
	}
	
	public Project update(Session s, Project toUpdate, String changeSet) throws WPISuiteException
	{
		// TODO: permissions checking here
		
		// convert updateString into a Map, then load into the User
		try
		{
			HashMap<String, Object> changeMap = new ObjectMapper().readValue(changeSet, HashMap.class);
		
			// check if the changeSet contains each field of User
			if(changeMap.containsKey("name"))
			{
				toUpdate.setName((String)changeMap.get("name"));
			}
			
			if(changeMap.containsKey("idNum"))
			{
				toUpdate.setIdNum((String)changeMap.get("idNum"));
			}
		}
		catch(Exception e)
		{
			throw new WPISuiteException(); // on Mapping failure
		}
		
		// save the changes back
		this.save(s, toUpdate);
		
		// check for changes in each field
		return toUpdate;
	}

	@Override
	public Project update(Session s, String content) throws WPISuiteException {
		// TODO Auto-generated method stub
		return null;
	}


	

}
