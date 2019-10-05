package com.proyecto.app.commons;

import java.io.Serializable;
import java.util.List;

public interface Generic<T, ID extends Serializable> {

	T save(T entity);
	
	void deleted (ID id);
	
	T get(ID id);
	
	List<T>getAll();
}
