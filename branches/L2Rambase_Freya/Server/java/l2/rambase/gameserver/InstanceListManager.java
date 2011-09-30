/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver;


public interface InstanceListManager {

	/**
 	* Loads instances with their data from persistant format. This method
 	* has no side effect as calling methods of another instance manager.
 	*/
	void loadInstances();

	/**
	 * For each loaded instance, updates references to related instances.
	 */
	void updateReferences();

	/**
	 * Activates instances so their setup is performed.
	 */
	void activateInstances();
}