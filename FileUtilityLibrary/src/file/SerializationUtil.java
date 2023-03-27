package file;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Logger;

public class SerializationUtil {
	private static Logger logger = Logger.getLogger(SerializationUtil.class.getName());
	
	public byte[] serialize(Object object) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(baos);
		    oos.writeObject(object);
		    return baos.toByteArray();
		} catch (IOException e) {
			logger.severe("Error serializing object: "+e.getMessage());
			return null;
		}		
	}
	
	public Object deserialize(byte byteArray[]) {
		ByteArrayInputStream bais = new ByteArrayInputStream(byteArray);
		try {
			ObjectInputStream ois = new ObjectInputStream(bais);
			return ois.readObject();
		} catch (IOException e) {
			logger.severe("Error deserializing object: "+e.getMessage());
			return null;
		} catch (ClassNotFoundException e) {
			logger.severe("Error deserializing object: "+e.getMessage());
			return null;
		}
	}
}
