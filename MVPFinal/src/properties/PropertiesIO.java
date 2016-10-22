package properties;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * 
 * Read and write from properties XML file
 * 
 * @author orenk
 */
public class PropertiesIO {
	private static final String PROPERTIES_PATH = "res/properties.xml";

	private static Properties properties = null;

	public static Properties getProperties() {
		return properties;
	}

	public static Properties readPropertiesFile() {
		File file = null;
		JAXBContext jaxbContext = null;
		Unmarshaller unmarshaller = null;

		try {
			file = new File(PROPERTIES_PATH);
			jaxbContext = JAXBContext.newInstance(Properties.class);
			unmarshaller = jaxbContext.createUnmarshaller();
			properties = (Properties) unmarshaller.unmarshal(file);
		} catch (final JAXBException e) {
			e.printStackTrace();
		}
		return properties;
	}

	public static void writeToPropertiesFile(final String ui, final String upDownHints) {
		properties = new Properties();
		properties.setThreadPoolNumber(10);
		properties.setGenerateAlgorithm("Simple");
		properties.setSolveAlgorithm("BFS");
		properties.setUserInterface(ui);
		properties.setGUIUpDownHints(upDownHints);

		File file = null;
		JAXBContext jaxbContext = null;
		Marshaller marshaller = null;

		try {
			file = new File(PROPERTIES_PATH);
			jaxbContext = JAXBContext.newInstance(properties.getClass());
			marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(properties, file);

		} catch (final JAXBException e) {
			e.printStackTrace();
		}
	}
}