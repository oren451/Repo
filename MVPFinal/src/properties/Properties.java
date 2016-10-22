package properties;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The class Properties object for the properties XML file
 * 
 * @author orenk
 */
@SuppressWarnings("serial")
@XmlRootElement
public class Properties implements Serializable {
	private int mThreadPoolNumber;
	private String mGenerateAlgorithm;
	private String mSolveAlgorithm;
	private String mUserInterface;
	private String mGUIUpDownHints;

	public Properties() {
		mThreadPoolNumber = 0;
	}

	/**
	 * Getters
	 */

	public String getGenerateAlgorithm() {
		return mGenerateAlgorithm;
	}

	public String getGUIUpDownHints() {
		return mGUIUpDownHints;
	}

	public String getSolveAlgorithm() {
		return mSolveAlgorithm;
	}

	public int getThreadPoolNumber() {
		return mThreadPoolNumber;
	}

	public String getUserInterface() {
		return mUserInterface;
	}

	/**
	 * Setters
	 */

	@XmlElement
	public void setGenerateAlgorithm(final String generateAlgorithm) {
		mGenerateAlgorithm = generateAlgorithm;
	}

	@XmlElement
	public void setGUIUpDownHints(final String gUIUpDownHints) {
		mGUIUpDownHints = gUIUpDownHints;
	}

	@XmlElement
	public void setSolveAlgorithm(final String solveAlgorithm) {
		mSolveAlgorithm = solveAlgorithm;
	}

	@XmlElement
	public void setThreadPoolNumber(final int threadPoolNumber) {
		mThreadPoolNumber = threadPoolNumber;
	}

	@XmlElement
	public void setUserInterface(final String userInterface) {
		mUserInterface = userInterface;
	}
}