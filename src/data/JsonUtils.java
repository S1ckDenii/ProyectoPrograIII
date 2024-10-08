package data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonUtils<T> {

	private final String filePath;
	private static final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

	public JsonUtils(String route) {
		this.filePath = route;
	}

	public void editElement(T t, int index) throws IOException {
		List<T> temp = getElements((Class<T>) t.getClass());
		temp.set(index, t);
		mapper.writeValue(new File(filePath), temp);

	}

	public void deleteElement(int index, T t) throws IOException {
		List<T> temp = getElements((Class<T>) t.getClass());
		temp.remove(index);
		mapper.writeValue(new File(filePath), temp);
	}

	public void saveElement(T t) throws IOException {
		List<T> temp = getElements((Class<T>) t.getClass());
		temp.add(t);
		mapper.writeValue(new File(filePath), temp);
	}

	public List<T> getElements(Class<T> temp) throws IOException {
		File file = new File(filePath);
		if (!file.exists()) {
			return new ArrayList<>();
		}
		return mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(List.class, temp));
	}

}
