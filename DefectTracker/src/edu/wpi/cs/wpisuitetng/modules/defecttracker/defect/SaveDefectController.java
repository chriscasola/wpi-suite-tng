package edu.wpi.cs.wpisuitetng.modules.defecttracker.defect;

import java.net.MalformedURLException;
import java.util.Observer;

import edu.wpi.cs.wpisuitetng.modules.defecttracker.defect.DefectPanel.Mode;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.Request.RequestMethod;

/**
 * Controller to handle the saving of a defect
 *
 */
public class SaveDefectController {

	/** The view object containing the request fields */
	protected DefectView view;

	/**
	 * Construct a new handler for the given view
	 * @param view the view containing the request fields
	 */
	public SaveDefectController(DefectView view) {
		this.view = view;
	}

	/**
	 * Save the view's Defect model to the server (asynchronous).
	 */
	public void save() {
		final DefectPanel panel = (DefectPanel) view.getDefectPanel();
		final Observer requestObserver = (panel.getEditMode() == Mode.CREATE) ? new CreateDefectRequestObserver(view) : new UpdateDefectRequestObserver(view);
		Request request;
		try {
			panel.setInputEnabled(false); //TODO change to view
			request = Network.getInstance().makeRequest("defecttracker/defect", (panel.getEditMode() == Mode.CREATE) ? RequestMethod.PUT : RequestMethod.POST);
			request.setRequestBody(panel.getEditedModel().toJSON());
			request.addObserver(requestObserver);
			request.send();
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}
		finally {
			panel.setInputEnabled(true);
		}
	}

}